/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hefesto.hefestocliente.core;

import com.hefesto.hefestocomponentes.HFComboBox.HFComboBox;
import com.hefesto.hefestocomponentes.HFDataField.HFDataField;
import com.hefesto.hefestocomponentes.HFFormattedTextField.HFFormattedTextField;
import com.hefesto.hefestocomponentes.HFLabel.HFLabel;
import com.hefesto.hefestocomponentes.HFLabel.HFLabelEnum;
import com.hefesto.hefestocomponentes.HFNumberField.HFNumberField;
import com.hefesto.hefestocomponentes.HFPassWordField.HFPasswordField;
import com.hefesto.hefestocomponentes.HFTextField.HFTextField;
import java.awt.Component;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Flavio
 */
public abstract class HefInternalFrame extends javax.swing.JInternalFrame implements BeanListner {

    /**
     * Creates new form HefInternalFrame
     */
    public HefInternalFrame() {
        initComponents();
        setIconifiable(true);
        setMaximizable(true);
        setClosable(true);
        setResizable(true);
        setNovo(true);
        comp = new ArrayList<>();
        Fechar();
    }
    private List<Component> comp;

    private List<BeanListner> listners = new ArrayList<>();
    HFLabel messageLabel = new HFLabel();
    protected com.hefesto.hefestocliente.core.Icone icone = com.hefesto.hefestocliente.core.Icone.getInstance();
    private Boolean write = false;
    private Boolean read = true;
    private Boolean execute = false;
    private Boolean delete = false;
    private Boolean pesquisar = false;
    Boolean abrir = false;
    Boolean novo = false;
    private Boolean Maximizado = false;

    public void addBeanListner(BeanListner listner) {
        listners.add(listner);
    }

    public void setMessageLabel(HFLabel messageLabel) {
        this.messageLabel = messageLabel;
    }

    public void setSalvar(Boolean podeSalvar) {
        write = podeSalvar;
        icone.podeSalvar(write);
    }

    public void setExecutar(Boolean podeExecutar) {
        execute = podeExecutar;
        icone.podeExecutar(execute);
    }

    public void setExcluir(Boolean podeExcluir) {
        delete = podeExcluir;
        icone.podeExcluir(delete);
    }

    public void setCarregar(Boolean podeCarregar) {
        read = podeCarregar;
        icone.podeExcluir(read);
    }

    public void setAbrir(Boolean podeAbrir) {
        abrir = podeAbrir;
        icone.podeAbrir(abrir);
    }

    public void setNovo(Boolean podeNovo) {
        novo = podeNovo;
        icone.podeNovo(novo);
    }

    public void Limpar() {
        LimpaComponentes(this.getRootPane().getContentPane().getComponents());
        messageLabel.setIcon(null);
        messageLabel.setText(null);
    }

    public void LimpaComponente(JComponent component) {
        LimpaComponentes(component.getComponents());
    }

    public void LimpaComponentes(Component[] component) {
        for (Object obj : component) {
            LimpaCampos(obj);
        }
    }

    protected void LimpaCampos(Object obj) {

        // pesquisa pelos containers
        if (obj instanceof JPanel) {
            JPanel f = (JPanel) obj;
            LimpaComponentes(f.getComponents());
        } else if (obj instanceof JScrollPane) {
            JScrollPane f = (JScrollPane) obj;
            LimpaComponentes(f.getComponents());
        } else if (obj instanceof JViewport) {
            JViewport f = (JViewport) obj;
            LimpaComponentes(f.getComponents());
        } else if (obj instanceof JTabbedPane) {
            JTabbedPane f = (JTabbedPane) obj;
            LimpaComponentes(f.getComponents());
        }

        //limpa os componentes
        if (obj instanceof HFTextField) {
            HFTextField f = (HFTextField) obj;
            f.setText(null);
            f = null;
        } else if (obj instanceof HFComboBox) {
            HFComboBox f = (HFComboBox) obj;
            f.LimparComponente();
        } else if (obj instanceof HFDataField) {
            HFDataField f = (HFDataField) obj;
            f.setText(null);
            f = null;
//        } else if (obj instanceof SrhJFormatField) {
//            SrhJFormatField f = (SrhJFormatField) obj;
//            f.setValue(null);
//            f.setText(null);
//            f.hideError();
//            f = null;
        } else if (obj instanceof HFNumberField) {
            HFNumberField f = (HFNumberField) obj;
            f.setValue(null);
            //f.setValue(null);
            //f.setText(null);
//            f.hideError();
            f = null;
        }//Adicionado versão 1.2.1
        else if (obj instanceof JCheckBox) {
            JCheckBox f = (JCheckBox) obj;
            f.setSelected(false);
            f = null;
        } else if (obj instanceof JTextArea) {
            JTextArea f = (JTextArea) obj;
            f.setText(null);
            f = null;
        } else if (obj instanceof HFPasswordField) {
            HFPasswordField f = (HFPasswordField) obj;
            f.setText(null);
            f = null;
        } else if (obj instanceof HFFormattedTextField) {
            HFFormattedTextField f = (HFFormattedTextField) obj;
            f.limpar();
            f = null;
        }
    }

    private void Fechar() {
        this.addInternalFrameListener(new InternalFrameListener() {

            public void addListner(BeanListner listner) {
                if (!listners.contains(listner)) {
                    getListners().add(listner);
                }
            }

            @Override
            public void internalFrameOpened(InternalFrameEvent e) {
                getIcone().removeToolBarMainFunctions();
                getIcone().addToolBarMainFunctions(getComp());
                getIcone().permissoes(getWrite(), getRead(), getExecute(), getDelete(), novo, abrir);
            }

            @Override
            public void internalFrameClosing(InternalFrameEvent e) {

            }

            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                getIcone().removeToolBarMainFunctions();
                getIcone().permissoes(false, false, false, false, false, false);
                HefInternalFrame framea = (HefInternalFrame) icone.getPrincipal().getDesktop().getSelectedFrame();
                if (framea != null) {
                    getIcone().addToolBarMainFunctions(framea.getComp());
                }
            }

            @Override
            public void internalFrameIconified(InternalFrameEvent e) {
                getIcone().removeToolBarMainFunctions();
                getIcone().permissoes(false, false, false, false, false, false);
            }

            @Override
            public void internalFrameDeiconified(InternalFrameEvent e) {
                getIcone().removeToolBarMainFunctions();
                getIcone().addToolBarMainFunctions(getComp());
                getIcone().permissoes(getWrite(), getRead(), getExecute(), getDelete(), novo, abrir);
            }

            @Override
            public void internalFrameActivated(InternalFrameEvent e) {
                getIcone().removeToolBarMainFunctions();
                getIcone().addToolBarMainFunctions(getComp());
                getIcone().permissoes(getWrite(), getRead(), getExecute(), getDelete(), novo, abrir);
            }

            @Override
            public void internalFrameDeactivated(InternalFrameEvent e) {
            }
        });

    }

    public void principalTooltipeInsertComponents(Component c) {
        comp.add(c);
    }

    /**
     * Executa os metodos processar de todos os listners contidos na tela
     *
     * @param evt
     */
    @Override
    public void processar(BeanEvent evt) {
        listners.stream().forEach((listner) -> {
            listner.processar(evt);
        });
    }

    /**
     * Executa os metodos salvar de todos os listners contidos na tela
     *
     * @param evt
     */
    @Override
    public void salvar(BeanEvent evt) {
        listners.stream().forEach((listner) -> {
            listner.salvar(evt);
        });
    }

    /**
     * Executa os metodos carregar de todos os listners contidos na tela
     *
     * @param evt
     */
    @Override
    public void carregar(BeanEvent evt) {
        listners.stream().forEach((listner) -> {
            listner.carregar(evt);
        });
    }

    /**
     * Executa os metodos excluir de todos os listners contidos na tela
     *
     * @param evt
     */
    @Override
    public void excluir(BeanEvent evt) {
        for (BeanListner listner : listners) {
            listner.excluir(evt);
        }
    }

    /**
     * Executa o Lookup dos Beans
     *
     * @param evt
     */
    @Override
    public void lookup(BeanEvent evt) {
        for (BeanListner listner : listners) {
            listner.lookup(evt);
        }
    }

    public void setmessageLabel(HFLabel label) {
        messageLabel = label;
    }

    public void throwError(Exception ex) {
        try {
            ex.printStackTrace();
            messageLabel.setMessage(ex.getMessage(), HFLabelEnum.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ShowMessage(String message, HFLabelEnum tipeMessage) {
        try {
            messageLabel.setMessage(message, tipeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void abrir(BeanEvent evt) {
        for (BeanListner listner : listners) {
            listner.abrir(evt);
        }
    }

    /**
     * Faz lookup no EJB
     *
     * @param o
     */
    public void adicionaObservador(Observer o) {
        for (BeanListner l : listners) {
            l.adicionaObservador(o);
        }
    }

    public void salvar() {
    }

    public void novo() {
    }

    public void excluir() {
    }

    public void carregar() {
    }

    public void processar() {
    }

    public void abrir() {
    }

    public void postConstruct() {
    }

    public void setaCursorComponentes(Cursor cursor) {
        setaCursor(this.getRootPane().getContentPane().getComponents(), cursor);
    }

    /**
     * Metodo que efetivamente seta o cursor, usa de recursivade para percorrer
     * todos os compoenentes com paineis no frame.
     *
     * @param components Array De compoenentes
     * @param cursor a ser definido
     * @since 1.5
     */
    protected void setaCursor(Component[] components, Cursor cursor) {
        for (Component c : components) {
            if (c instanceof JPanel || c instanceof JScrollPane || c instanceof JViewport || c instanceof JTabbedPane) {
                JComponent jc = (JComponent) c;
                setaCursor(jc.getComponents(), cursor);
                jc = null;
            } else {
                if (c instanceof JTextComponent && cursor.equals(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR))) {
                    c.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
                } else if (c instanceof JComboBox && cursor.equals(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR))) {
                    JComboBox combo = (JComboBox) c;
                    if (combo.getEditor() != null && combo.getEditor().getEditorComponent() != null) {
                        combo.getEditor().getEditorComponent().setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
                    }
                    combo = null;
                } else if (c instanceof JComboBox) {
                    JComboBox combo = (JComboBox) c;
                    if (combo.getEditor() != null && combo.getEditor().getEditorComponent() != null) {
                        combo.getEditor().getEditorComponent().setCursor(cursor);
                    }
                    combo = null;
                } else {
                    c.setCursor(cursor);
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    public List<Component> getComp() {
        return comp;
    }

    public void setComp(List<Component> comp) {
        this.comp = comp;
    }

    public List<BeanListner> getListners() {
        return listners;
    }

    public void setListners(List<BeanListner> listners) {
        this.listners = listners;
    }

    public com.hefesto.hefestocliente.core.Icone getIcone() {
        return icone;
    }

    public void setIcone(com.hefesto.hefestocliente.core.Icone icone) {
        this.icone = icone;
    }

    public Boolean getWrite() {
        return write;
    }

    public void setWrite(Boolean write) {
        this.write = write;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public Boolean getExecute() {
        return execute;
    }

    public void setExecute(Boolean execute) {
        this.execute = execute;
    }

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }

    public Boolean getMaximizado() {
        return Maximizado;
    }

    public void setMaximizado(Boolean Maximizado) {
        this.Maximizado = Maximizado;
    }

    public boolean podePesquisar() {
        return pesquisar;

    }

    public void setPodePesquisar(boolean pesquisar) {
        this.pesquisar = pesquisar;

    }

    public void pesquisar() {
    }
}
