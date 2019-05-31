/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hefesto.hefestocliente.core;

import com.Hefesto.core.entidades.HefLiberacaoTela;
import com.Hefesto.core.entidades.HefTelas;
import com.Hefesto.core.entidades.HefUsuarioInfo;
import com.Hefesto.core.user.ControlaLoginUsuarioRemote;
import com.Hefesto.core.utils.EJBConstants;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.hefesto.hefestocliente.core.annotations.Intercept;
import com.hefesto.hefestocliente.core.interceptors.InterceptorModule;
import com.hefesto.hefestocliente.core.telaprincipal.TelaPrincipal;
import com.hefesto.hefestocomponentes.HFDoInBackGround.BackGroundPool;
import com.hefesto.hefestocomponentes.HFDoInBackGround.DoInBackGround;
import com.hefesto.hefestocomponentes.HFExceptionPainel.HFExceptionPainel;
import com.hefesto.hefestocomponentes.HFImagenLoader.HFImageLoader;
import com.hefesto.hefestocomponentes.HFLabel.HFLabelEnum;
import com.hefesto.hefestocomponentes.HFLogger.LoggerUtil;
import com.hefesto.hefestocomponentes.HFLogon.HFLoginPainel;
import com.hefesto.hefestocomponentes.HFModal.HFModalEnum;
import com.hefesto.hefestocomponentes.HFModal.HFModalPainel;
import com.hefesto.hefestocomponentes.HFUtils.ZipUtil;
import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import java.awt.AWTException;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameListener;
import org.japura.modal.Modal;

/**
 *
 * @author flavio
 */
public class Icone {

    private static Icone icone;
    private Injector injetor;
    private TelaPrincipal telaPrincipal;
    private HefUsuarioInfo usuario;
    private ImageIcon principalIcon;
    private ImageIcon framesIcon;
    private Map<String, HefLiberacaoTela> telasUsuario;
    private TrayIcon trayIcon;
    private ConnectionVerifier verifier;
    private boolean conectado = true;
    private List<Component> botoesSuperior;
    InitialContext inc;

    private Icone() {

        try {
            try {
                this.principalIcon = new HFImageLoader(this.getClass()).getImagen("/com/hefesto/hefestocliente/icones/iconprincipal.png");
                this.framesIcon = new HFImageLoader(this.getClass()).getResizedImage("/com/hefesto/hefestocliente/icones/frameicon.png", 20, 20, Image.SCALE_DEFAULT);
                botoesSuperior = new ArrayList<>();
            } catch (Exception ex) {
                ex.printStackTrace();
                this.principalIcon = new ImageIcon(getClass().getResource(null));
            }
            injetor = Guice.createInjector(new InterceptorModule());

            Properties props = new Properties();
            props.setProperty("org.omg.CORBA.ORBInitialHost", Messages.getMessage("host"));
            props.setProperty("org.omg.CORBA.ORBInitialPort", Messages.getMessage("porta"));
            inc = new InitialContext(props);

            if (SystemTray.isSupported()) {
                SystemTray tray = SystemTray.getSystemTray();
                PopupMenu pop = new PopupMenu();

                MenuItem opc = new MenuItem(Messages.getMessage("tray.menu.abrir"));
                opc.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (telaPrincipal.isVisible()) {
                            telaPrincipal.setExtendedState(Frame.MAXIMIZED_BOTH);
                        } else {
                            Start();
                        }
                    }
                });
                pop.add(opc);

                pop.addSeparator();

                MenuItem opc2 = new MenuItem(Messages.getMessage("tray.menu.sair"));
                opc2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });
                pop.add(opc2);

                trayIcon = new TrayIcon(principalIcon.getImage(), Messages.getMessage("nome.sistema"), pop);
                trayIcon.setImageAutoSize(true);
                trayIcon.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getClickCount() == 2) {
                            if (telaPrincipal.isVisible()) {
                                telaPrincipal.setExtendedState(Frame.MAXIMIZED_BOTH);
                            } else {
                                Start();
                            }
                        }
                    }
                });
                try {
                    tray.add(trayIcon);
                    this.verifier = new ConnectionVerifier(trayIcon);
                } catch (AWTException ex) {

                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, Messages.getMessage("erro.connect.server"));
        }
    }

    /**
     * Executa os procedimentos de Logoff, liberando os recursos para o sistema.
     */
    public void Logoff() {
        try {
            telaPrincipal.dispose();
            usuario = null;
            Start();
        } catch (Exception ex) {
            printStackTrace();
            JOptionPane.showMessageDialog(null, Messages.getMessage("logoff.problema", ex.getMessage()));
        }
    }

    /**
     * Retorna ou cria uma Instancia de Icone para executar as ações.
     *
     * @return
     */
    public static Icone getInstance() {
        if (icone == null) {
            icone = new Icone();
        }
        return icone;
    }

    /**
     * retorna o initialContext para conexão com o Servidor.
     *
     * @return
     */
    public InitialContext getInitialContext() {
        return inc;
    }

    /**
     * Método que altera o cursor do Mouse. Altera o cursor de todos os
     * compoenentes contidos no frame também
     *
     * @param cursor O elemento final do Cursor
     * @see Cursor#type
     * @since 1.3
     * @see Frame#setaCursorComponentes(java.awt.Cursor)
     */
    public void mudaCursor(int cursor) {
        if (telaPrincipal != null) {
            Cursor cur = Cursor.getPredefinedCursor(cursor);
            telaPrincipal.setCursor(cur);
            HefInternalFrame frame;
            for (JInternalFrame oneFrame : telaPrincipal.getDesktop().getAllFrames()) {
                frame = (HefInternalFrame) oneFrame;
                if (frame != null) {
                    frame.setCursor(cur);
                    frame.setaCursorComponentes(cur);
                }
            }
        }
    }

    public <K> K getBeanInstance(Class classe) {
        return (K) injetor.getInstance(classe);
    }

    public void podeSalvar(Boolean podeSalvar) {
        telaPrincipal.podeSalvar((podeSalvar && conectado));
    }

    public void podeExecutar(Boolean podeExecutar) {
        telaPrincipal.podeExecutar((podeExecutar && conectado));
    }

    public void podeExcluir(Boolean podeExcluir) {
        telaPrincipal.podeExcluir((podeExcluir && conectado));
    }

    public void podeCarregar(Boolean podeCarregar) {

    }

    public void podeNovo(Boolean podeNovo) {
        telaPrincipal.podeNovo((podeNovo && conectado));
    }

    public void podeAbrir(Boolean podeAbrir) {
        telaPrincipal.podeAbrir((podeAbrir && conectado));
    }

    /**
     * Adiciona os Novos botões de ação da tela a barra principal.
     *
     * @param comp
     */
    public void addToolBarMainFunctions(List<Component> comp) {
        botoesSuperior = comp;

        for (Component c : comp) {
            telaPrincipal.addBotaoSuperior(c);
        }
    }

    /**
     * remove os Novos botões de ação da tela da barra principal.
     */
    public void removeToolBarMainFunctions() {
        for (Component c : botoesSuperior) {
            telaPrincipal.removeBotaoSuperior(c);
        }
        botoesSuperior = new ArrayList<>();
    }

    public void permissoes(Boolean write, Boolean read, Boolean execute, Boolean delete, Boolean novo, Boolean abrir) {
        podeSalvar((write && conectado));
        podeExcluir((delete && conectado));
        podeCarregar((read && conectado));
        podeExecutar((execute && conectado));
        podeAbrir((abrir && conectado));
        podeNovo((novo && conectado));
    }

    /**
     * Executa os Processos de Logon
     *
     * @param login
     */
    @Intercept
    public void Logon(HFLoginPainel login) {
        DoInBackGround back = new DoInBackGround() {
            @Override
            public Object doInBackground() {
                try {
                    mudaCursor(Cursor.WAIT_CURSOR);
                    login.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    ControlaLoginUsuarioRemote listnerUsuario = (ControlaLoginUsuarioRemote) inc.lookup(EJBConstants.CONTROLA_USUARIO);
                    System.out.println(login.getSenha());
                    usuario = listnerUsuario.validaLogin(login.getUsuario(), login.getSenha());
                    if (usuario == null) {
                        login.msgErro("Usuário ou senha invalidos!");
                        return false;
                    } else {
                        CarregaPerfilTelas();
                        telaPrincipal.CarregaProperties();
                        telaPrincipal.removeModal(login);
                        telaPrincipal.setUsuarioLogado(usuario.getIdusuario().getLogin() + " - " + usuario.getNome());
                        if (!verifier.getVerificando()) {
                            verifier.Verifier();
                        }
                        return true;
                    }
                } catch (Exception ex) {
                    login.msgErro("Não Foi possível Conectar ao Servidor");
                    ex.printStackTrace();
                    return false;
                } finally {
                    mudaCursor(Cursor.DEFAULT_CURSOR);
                    login.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
            }
        };
        back.execute();

    }

    /**
     * Inicia o Sistema.
     */
    @Intercept
    public void Start() {
        try {
            if (telaPrincipal != null) {
                telaPrincipal.dispose();
            }
            usuario = null;
            telaPrincipal = new TelaPrincipal();
            telaPrincipal.setTitle(Messages.getMessage("nome.sistema")
                    + " " + Messages.getMessage("versao.sistema"));
            telaPrincipal.setIconImage(principalIcon.getImage());
            telaPrincipal.setVisible(true);
            telaPrincipal.Logon();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, Messages.getMessage("logoff.problema", ex.getMessage()));
        }

    }

    /**
     * encerra a execução do Sistema.
     */
    public void sair() {
        System.exit(0);
    }

    public HefInternalFrame criaFrame(HefTelas tela, InternalFrameListener listener) {
        if (tela != null) {
            JInternalFrame found = findTelaNoDesktop(tela);
            if (found != null) {
                found.moveToFront();
                try {
                    found.setSelected(true);
                } catch (PropertyVetoException ex) {
                    LoggerUtil.severe(ex);
                }
                try {
                    return (HefInternalFrame) found;
                } finally {
                    found = null;
                }
            } else {
                JDesktopPane desktop = telaPrincipal.getDesktop();
                HefLiberacaoTela perfil = findPerfilTela(tela);
                HefInternalFrame frame;
                Class classe;
                try {
                    classe = Class.forName(tela.getClass1());
                    frame = getBeanInstance(classe);
                    if (frame instanceof HefInternalFrame) {
                        frame.setExecutar(perfil.getX());
                        frame.setSalvar(perfil.getWs());
                        frame.setExcluir(perfil.getWr());
                        frame.setCarregar(perfil.getR());
                        frame.setTitle((perfil.getIdtela().getIdmenu().getCodigo()
                                + perfil.getIdtela().getCodigoTela())
                                + " - " + perfil.getIdtela().getTitulo());
                        frame.postConstruct();
                    }
                    if (listener != null) {
                        frame.addInternalFrameListener(listener);
                    }
                    mostraFrame(frame, desktop, frame.getMaximizado());
                    return frame;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    HFExceptionPainel painel = new HFExceptionPainel();
                    painel.setTextError(ex);
                    Modal.addModal(icone.getPrincipal(), painel);
                    return null;
                }
            }
        }
        telaPrincipal.setMessage(Messages.getMessage("erro.abrir.tela"), HFLabelEnum.ERROR_MESSAGE);
        return null;
    }

    /**
     * Monsta um objeto do tipo Frame no JDescktop pane.
     *
     * @param frame a tela
     * @param desktop o desktopPane
     * @param maximizado true para maximizar a tela quando criar
     * @since 1.7
     */
    private void mostraFrame(HefInternalFrame frame, JDesktopPane desktop, Boolean maximizado) {
        try {
            frame.setFrameIcon(icone.getIcon());
            desktop.add(frame);
            desktop.moveToFront(frame);
            if (maximizado) {
                desktop.getDesktopManager().maximizeFrame(frame);
            }
            frame.setSelected(true);
            frame.setVisible(true);
        } catch (PropertyVetoException ex) {
            LoggerUtil.severe(ex);
        }
    }

    private void CarregaPerfilTelas() {
        try {
            ControlaLoginUsuarioRemote usuarioR = (ControlaLoginUsuarioRemote) inc.lookup(EJBConstants.CONTROLA_USUARIO);
            List<HefLiberacaoTela> telas = ZipUtil.Descompacta(usuarioR.getTelasUsuario(usuario.getIdusuarioInfo(), false));
            telasUsuario = new HashMap<>();
            if (telas != null) {
                for (HefLiberacaoTela tela : telas) {
                    telasUsuario.put(tela.getIdtela().getIdmenu().getCodigo() + "" + tela.getIdtela().getCodigoTela(), tela);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            new HFModalPainel(telaPrincipal, "Erro ao Carregar Telas", Messages.getMessage("erro.carregar.telas"),
                    HFModalEnum.CANCEL_MODAL, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    Icone.this.Logoff();
                }
            });
        }
    }

    public <T> T lookup(String interfaceR) throws NamingException {
        return (T) inc.lookup(interfaceR);
    }

    /**
     * Procura se a tela esta disponivel no desktop
     *
     * @param tela a ser procurar
     * @return JInternalFrame encontrado, ou null caso não seja encontrado
     * @since 1.9
     *
     */
    private JInternalFrame findTelaNoDesktop(HefTelas tela) {
        JDesktopPane desktop = telaPrincipal.getDesktop();
        for (JInternalFrame fr : desktop.getAllFrames()) {
            String className = fr.getClass().getName();
            //Remove os esquemas do GUICE quando ele cria a instancia do frame, ser remover da pau
            className = className.contains("$") ? className.substring(0, className.indexOf("$")) : className;
            if (className.equals(tela.getClass1())) {
                try {
                    return fr;
                } finally {
                    desktop = null;
                    tela = null;
                }
            }
        }
        return null;
    }

    /**
     * Procura uma tela atravez da classe
     *
     * @param classe a ser procurada no perfil do usuario
     * @return <code>Srhtela</code> encontrada, null caso a tela não seja
     * encontrada no perfil
     * @since 1.5
     */
    public HefTelas findTela(Class classe) {
        String className = classe.getName();
        className = className.contains("$") ? className.substring(0, className.indexOf("$")) : className;
        for (HefLiberacaoTela pt : telasUsuario.values()) {
            if (pt.getIdtela().getClass1().equals(className)) {
                return pt.getIdtela();
            }
        }
        return null;
    }

    /**
     * Cria um novo frame na tela, e adiciona ele ao Desktop Pane
     *
     * @param frame a ser adiciona
     * @param location a posição do frame, pode ser null
     * @param listener a ação desse frame, pode ser null
     * @return
     * @since 1.5
     * @see #findTela(java.lang.Class)
     * @see #criaFrame(br.com.serhmatica.ejb.entidades.Srhtela, java.awt.Point)
     */
    public <T> T criaFrame(Class<T> frame, Point location, InternalFrameListener listener) {
        HefTelas tela = findTela(frame);
        if (tela == null) {
            new HFModalPainel(telaPrincipal, "Erro ao Abrir Tela", Messages.getMessage("erro.abrir.tela"),
                    HFModalEnum.CANCEL_MODAL);
        }
        return (T) criaFrame(tela, listener);
    }

    /**
     * Cria frame passando se ele sera maximizado ou não
     *
     * @param frame classe a ser criada
     * @return Frame criado
     * @since 1.8
     * @see #criaFrame(java.lang.Class, java.awt.Point,
     * javax.swing.event.InternalFrameListener, boolean)
     */
    public <T> T criaFrame(Class<T> frame) {
        return criaFrame(frame, null, null);
    }

    public void buscaTela(String codTela) {
        HefLiberacaoTela telaLib = telasUsuario.get(codTela);

        if (telaLib == null) {

        } else {
            HefTelas tela = telaLib.getIdtela();
            criaFrame(tela, null);
        }
    }

    private HefLiberacaoTela findPerfilTela(HefTelas tela) {
        return telasUsuario.get(tela.getIdmenu().getCodigo() + "" + tela.getCodigoTela());
    }

    private ImageIcon getIcon() {
        return framesIcon;
    }

    public HefUsuarioInfo getUsuario() {
        return usuario;
    }

    public void setUsuario(HefUsuarioInfo info) {
        usuario = info;
    }

    public TelaPrincipal getPrincipal() {
        return telaPrincipal;
    }

    public List<HefLiberacaoTela> telasUsuario() {
        return new ArrayList<>(telasUsuario.values());
    }

    public boolean isConectado() {
        return conectado;
    }

    public void setConectado(boolean conectado) {
        HefInternalFrame frame = (HefInternalFrame) telaPrincipal.getDesktop().getSelectedFrame();
        if (frame != null) {
            permissoes(conectado && frame.getWrite(), conectado && frame.getRead(),
                    conectado && frame.getExecute(), conectado && frame.getDelete(), conectado, conectado);
        } else {
            permissoes(false, false, false, false, false, false);
        }

        if (!this.conectado && conectado) {
            for (JInternalFrame f : telaPrincipal.getDesktop().getAllFrames()) {
                frame = (HefInternalFrame) f;
                frame.lookup(new BeanEvent(frame, null));
            }
        }
        this.conectado = conectado;
    }

    public void setPrincipalTabRel(List<?> lst) {
        telaPrincipal.setPrincipalTabRel(lst);
    }

}
