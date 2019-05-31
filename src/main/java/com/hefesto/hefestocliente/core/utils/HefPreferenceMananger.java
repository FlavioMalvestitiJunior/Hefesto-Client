/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hefesto.hefestocliente.core.utils;

import com.Hefesto.core.entidades.HefUsuarioInfo;
import com.Hefesto.core.user.ControlaLoginUsuarioRemote;
import static com.Hefesto.core.utils.EJBConstants.CONTROLA_USUARIO;
import com.hefesto.hefestocliente.Core.HefProperties;
import com.hefesto.hefestocliente.Core.utils.PrefListener;
import com.hefesto.hefestocliente.core.AbstractBean;
import com.hefesto.hefestocliente.core.BeanEvent;
import com.hefesto.hefestocliente.core.Messages;
import com.hefesto.hefestocliente.core.telaprincipal.TelaPrincipal;
import com.hefesto.hefestocomponentes.HFDoInBackGround.DoInBackGround;
import com.hefesto.hefestocomponentes.HFLabel.HFLabelEnum;
import com.hefesto.hefestocomponentes.HFLogger.LoggerUtil;
import com.hefesto.hefestocomponentes.HFModal.HFModalEnum;
import com.hefesto.hefestocomponentes.HFModal.HFModalPainel;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Classe que gerencia as preferencias do usu�rio.</p>
 * As preferencias ficam na maquina do usuario e a cada save elas s�o reenviadas
 * ao servidor remoto para atualizar.
 *
 * @author Rafael Felix
 * @version 1.0
 */
public class HefPreferenceMananger extends AbstractBean<ControlaLoginUsuarioRemote> implements PrefListener {

    private HefUsuarioInfo usuario = icone.getUsuario();
    private String tempDir = System.getProperty("java.io.tmpdir");
    private HefProperties properties;

    /**
     * Construtor vazio
     *
     * @since 1.0
     */
    public HefPreferenceMananger() {
        this(new Properties());
    }

    /**
     * Construtor que aceita um objeto properties
     *
     * @param properties arquivo a ser analisado
     * @since 1.0
     */
    public HefPreferenceMananger(Properties properties) {
        this(new HefProperties(properties));

    }

    /**
     * Construtor completo
     *
     * @param properties objeto pre definido para trabalhar alto nivel com as
     * informa��es
     * @since 1.0
     */
    public HefPreferenceMananger(HefProperties properties) {
        this.properties = properties == null ? new HefProperties() : properties;
    }

    /**
     * Salva o arquivo de preferencias no sistema de arquivos.
     *
     * @since 1.0
     */
    private void salvarArquivo() {
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(new FileOutputStream(getFile()));
            dos.write((byte[]) usuario.getPreferencias());
            dos.flush();
            properties.loadFromFile(getFile());
        } catch (IOException ex) {
            LoggerUtil.severe(ex);
        } finally {
            try {
                if (dos != null) {
                    dos.close();
                }
            } catch (IOException ex) {
                LoggerUtil.severe(ex);
            }
            dos = null;
        }
    }

    /**
     * Le o arquivo do sistema de arquivos, utilizado para salvar novamente o
     * arquivo
     *
     * @return array de bytes correpondendo ao arquivo
     * @throws java.io.IOException
     * @since 1.0
     */
    private byte[] leArquivo() throws IOException {
        File file = getFile();
        byte[] b = new byte[(int) file.length()];
        FileInputStream fis = new FileInputStream(file);
        fis.read(b);
        return b;
    }

    /**
     * Salva o arquivo no disco e no servidor remoto.</p>
     * Utiliza SwingWorker para n�o causar demora para o cliente. Ocorre um
     * delay maior, mas n�o ocorre paraliza��o da tela
     *
     * @since 1.0
     * @see #save()
     * @see #salvarPreferencias()
     */
    @Override
    public void saveFile() {
        DoInBackGround work = new DoInBackGround() {
            @Override
            public Object doInBackground() {
                try {
                    save();
                    usuario.setPreferencias(leArquivo());
                    salvarPreferencias();
                    LoggerUtil.info("Preferencias salvas.");
                    icone.getPrincipal().setMessage("Preferencias salvas.", HFLabelEnum.SUCESS_MESSAGE);
                } catch (IOException ex) {
                    LoggerUtil.severe(ex);
                }
                return null;
            }
        };
        work.execute();
    }

    /**
     * Salvar interno, somente salva o arquivo de propriedaes
     *
     * @since 1.0
     * @see #saveFile()
     */
    private void save() throws IOException {
        DataOutputStream dos;
        dos = new DataOutputStream(new FileOutputStream(getFile()));
        properties.toProperties().store(dos, "Hefestos Properties File");
        dos.flush();
        dos.close();
        dos = null;
    }

    /**
     * Arquivo de preferencias
     *
     * @return File
     * @since 1.0
     */
    private File getFile() {
        return new File(getFileName());
    }

    /**
     * Gera o nome do arqivo de preferencias, pegando o cdusu e adicionado
     * _process.properties. Cada usu�rio tem seu arquivo de preferencias
     *
     * @return String nome do arquivo
     * @since 1.0
     */
    private String getFileName() {
        StringBuilder sb = new StringBuilder(tempDir);
        sb.append(System.getProperty("file.separator"));
        sb.append(usuario.getIdusuario().getLogin()).append("_hefesto.properties");
        return sb.toString();
    }

    /**
     * Metodo que faz a chamada ao EJB.
     *
     * @since 1.0
     */
    private void salvarPreferencias() {
        try {
            ejb.salvaPreferencia(usuario);
        } catch (Exception ex) {
            registraException(ex);
        }
    }

    /**
     * Retorna arquivo com as propriedades
     *
     * @return Arquivo de Propriedades manipulavel como um objeto
     * @since 1.0
     */
    @Override
    public HefProperties getProperties() {
        return properties;
    }

    @Override
    public void lookup(BeanEvent e) {
    }

    @Override
    public void salva(TelaPrincipal tela) {
        try {
            ejb = icone.lookup(CONTROLA_USUARIO);
            if (usuario.getPreferencias() == null) {
                //Salva o arquivo antes.
                try {
                    save();
                    usuario.setPreferencias(leArquivo());
                } catch (IOException ex) {
                    LoggerUtil.severe(ex);
                }
                //Salva no remoto o arquivo
                salvarPreferencias();
            } else {
                //Converte a preferencia recebida em arquivo
                salvarArquivo();
            }
        } catch (Exception ex) {
            new HFModalPainel(tela, "Erro ao Salvar Preferencias", Messages.getMessage("erro.salvar.preferencias"),
                    HFModalEnum.CANCEL_MODAL);
        } finally {
            //properties = null;
        }
    }

}
