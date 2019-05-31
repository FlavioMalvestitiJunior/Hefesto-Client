/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hefesto.hefestocliente.core;

import com.Hefesto.core.entidades.HefNotas;
import com.Hefesto.core.entidades.HefUsuarioInfo;
import com.Hefesto.core.paineldecontrole.ControlaNotasDaVersaoRemote;
import com.Hefesto.core.relatorios.ControlaRelatoriosRemote;
import com.Hefesto.core.relatorios.objetos.FilaRelatorioObj;
import com.Hefesto.core.user.ControlaLoginUsuarioRemote;
import com.Hefesto.core.utils.EJBConstants;
import com.hefesto.hefestocomponentes.HFDoInBackGround.DoInBackGround;
import com.hefesto.hefestocomponentes.HFLogger.LoggerUtil;
import com.hefesto.hefestocomponentes.HFUtils.ZipUtil;
import java.awt.TrayIcon;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Flavio
 */
public class ConnectionVerifier {

    public static final Integer MAX_TIME_UPDATE_FILA = 10000;
    private Boolean conectado = false;
    private Boolean verificando = false;
    private ControlaLoginUsuarioRemote loginRemote;
    private ControlaRelatoriosRemote relRemote;
    private ControlaNotasDaVersaoRemote notasRemote;
    private TrayIcon trayIcon;

    public ConnectionVerifier(TrayIcon icon) {
        this.trayIcon = icon;
    }

    public void Verifier() {
        verificando = true;
        DoInBackGround work = new DoInBackGround() {
            private int contador = 1;

            @Override
            public Object doInBackground() {
                while (true) {
                    try {
                        if (Icone.getInstance().getUsuario() != null) {
                            if (contador == 100) {
                                verificaNota();
                                contador = 1;
                            }

                        }
                        atualizaLista();
                        contador++;
                        Thread.sleep(MAX_TIME_UPDATE_FILA);
                    } catch (InterruptedException ex) {
                        LoggerUtil.severe(ex);
                    }
                }
            }
        };
        work.execute();
    }

    private void verificaNota() {
        if (conectado) {
            try {
                notasRemote = Icone.getInstance().lookup(EJBConstants.PAINEL_DE_CONTROLE_NOTAS);
                HefNotas nota = ZipUtil.Descompacta(notasRemote.buscaUltimaNota());
                if (!Messages.getMessage("versao.sistema").equals(nota.getVersao())) {
                    Icone.getInstance().getPrincipal().novidades(nota);
                }
            } catch (Exception ex) {

            }
        }

    }

    private void atualizaLista() {
        Icone icone = Icone.getInstance();
        try {
            if (!conectado) {
                if (icone.getUsuario() != null) {
                    loginRemote = icone.lookup(EJBConstants.CONTROLA_USUARIO);
                    //Autentica o usuário novamente
                    HefUsuarioInfo usu = loginRemote.validaLogin(icone.getUsuario().getIdusuario().getLogin(), icone.getUsuario().getSenha());
                    if (usu == null) {
                        Icone.getInstance().Logoff();
                    }
                    LoggerUtil.info("Usuario " + icone.getUsuario().getNome() + " logado com sucesso!");
                }
                conectado = true;
                Icone.getInstance().setConectado(conectado);
                trayIcon.getPopupMenu().getItem(0).setEnabled(true);
                trayIcon.displayMessage(Messages.getMessage("verifica.conectado"),
                        Messages.getMessage("verifica.conectado.msg"), TrayIcon.MessageType.INFO);
            }
            if (conectado) {
                if (icone.getUsuario() != null) {
                    relRemote = icone.lookup(EJBConstants.RELATORIO_CONTROLA);
                    List<FilaRelatorioObj> relatorios = ZipUtil.Descompacta(relRemote.filaRelatorios(icone.getUsuario().getIdusuario().getIdusuario()));
                    montaTabelaRelatoriosUsuario(relatorios);
                }
            }
        } catch (Exception ex) {
            notConnected();
            LoggerUtil.severe(ex);
        }
    }

    private void notConnected() {
        if (conectado) {
            conectado = false;
            Icone.getInstance().setConectado(conectado);
            trayIcon.getPopupMenu().getItem(0).setEnabled(false);
            trayIcon.displayMessage(Messages.getMessage("verifica.erro"),
                    Messages.getMessage("verifica.erro.msg"), TrayIcon.MessageType.ERROR);

        }
    }

    public Boolean getVerificando() {
        return verificando;
    }

    public void setVerificando(Boolean verificando) {
        this.verificando = verificando;
    }

    private void montaTabelaRelatoriosUsuario(List<FilaRelatorioObj> relatorios) throws Exception {
        List<List<?>> tabela = new ArrayList<>();
        List<Object> linha;
        for (FilaRelatorioObj rel : relatorios) {
            linha = new ArrayList<>();
            linha.add(rel.getPID());
            linha.add(rel.getTitulo());
            linha.add(rel.getStatus());
            tabela.add(linha);
        }
        Icone.getInstance().setPrincipalTabRel(tabela);
    }

}
