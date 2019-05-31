/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hefesto.hefestocliente.core.relatorio;

import com.Hefesto.core.entidades.HefRelatorio;
import com.Hefesto.core.relatorios.objetos.RelatorioFilaObj;
import com.hefesto.hefestocliente.Core.utils.MessageIcon;
import com.hefesto.hefestocliente.core.BeanEvent;
import com.hefesto.hefestocliente.core.HefInternalFrame;
import com.hefesto.hefestocliente.core.Messages;
import com.hefesto.hefestocliente.core.relatorioBean.RelatorioViewerBean;
import com.hefesto.hefestocliente.core.relatorioListner.RelatorioViewerListner;
import com.hefesto.hefestocomponentes.HFBaloon.HFBallonUtil;
import com.hefesto.hefestocomponentes.HFPair.HFPair;
import com.hefesto.hefestocomponentes.HFTable.utils.HFTableUtil;
import java.util.ArrayList;
import java.util.List;
import org.japura.modal.Modal;

/**
 *
 * @author jr
 */
public class RelatorioViewer extends HefInternalFrame {

    private int count = 0;
    private int countCol = 0;
    private int COD = count++;
    private int DESC = count++;
    private int DTGER = count++;
    private int STATUS = count++;
    private int NUPAGINAS = count++;
    private int OBJ = count++;
    private int COL_SEL = countCol++;
    private int COL_NAME = countCol++;
    RelatorioViewerListner listner;
    List<Object> relDataSource;

    public RelatorioViewer() {
        initComponents();
        setmessageLabel(messageLAbel);
        principalTooltipeInsertComponents(viewButton);
        principalTooltipeInsertComponents(pdfButton);
        principalTooltipeInsertComponents(excelButton);
        RelatorioViewerBean bean = icone.getBeanInstance(RelatorioViewerBean.class);
        addBeanListner(bean);
        listner = bean;
        lookup(new BeanEvent(this, null));
        carregar(new BeanEvent(this, null));
        new HFTableUtil(listaRelatorios).colorize().hideColumn(OBJ);
        new HFTableUtil(listaColunas).colorize().setarCheckBox().setarCheckBoxSelectable(COL_SEL);

    }

    /**
     * Creates new form HFCadastroTelas public HFCadastroTelas(HFTelaPrincipal
     * principalframe, HefUsu usuario) { super(principalframe, usuario); }
     *
     *
     *
     * /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        viewButton = new javax.swing.JButton();
        excelButton = new javax.swing.JButton();
        pdfButton = new javax.swing.JButton();
        processandoPainel = new javax.swing.JPanel();
        jProgressBar1 = new javax.swing.JProgressBar();
        camposExportar = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaColunas = new com.hefesto.hefestocomponentes.HFTable.HFTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jpane = new javax.swing.JPanel();
        messageLAbel = new com.hefesto.hefestocomponentes.HFLabel.HFLabel();
        jPanel5 = new javax.swing.JPanel();
        usuariosCadastradosJp = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaRelatorios = new com.hefesto.hefestocomponentes.HFTable.HFTable();

        viewButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hefesto/hefestocliente/icones/view.png"))); // NOI18N
        viewButton.setToolTipText("Visualizar Relatório");
        viewButton.setName(""); // NOI18N
        viewButton.setOpaque(false);
        viewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewButtonActionPerformed(evt);
            }
        });

        excelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hefesto/hefestocliente/icones/xlsfile.png"))); // NOI18N
        excelButton.setToolTipText("Exportar para Excel");
        excelButton.setName(""); // NOI18N
        excelButton.setOpaque(false);
        excelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excelButtonActionPerformed(evt);
            }
        });

        pdfButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hefesto/hefestocliente/icones/pdffile.png"))); // NOI18N
        pdfButton.setToolTipText("Exportar em PDF");
        pdfButton.setName(""); // NOI18N
        pdfButton.setOpaque(false);
        pdfButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pdfButtonActionPerformed(evt);
            }
        });

        processandoPainel.setBorder(javax.swing.BorderFactory.createTitledBorder("Exportando"));

        jProgressBar1.setIndeterminate(true);

        javax.swing.GroupLayout processandoPainelLayout = new javax.swing.GroupLayout(processandoPainel);
        processandoPainel.setLayout(processandoPainelLayout);
        processandoPainelLayout.setHorizontalGroup(
            processandoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(processandoPainelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                .addContainerGap())
        );
        processandoPainelLayout.setVerticalGroup(
            processandoPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, processandoPainelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        camposExportar.setBorder(javax.swing.BorderFactory.createTitledBorder("Colunas"));

        listaColunas.setColunas(new String[] {"Sel.", "Coluna"});
        jScrollPane1.setViewportView(listaColunas);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hefesto/hefestocliente/icones/cancel.png"))); // NOI18N
        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hefesto/hefestocliente/icones/checkmark.png"))); // NOI18N
        jButton2.setText("Exportar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout camposExportarLayout = new javax.swing.GroupLayout(camposExportar);
        camposExportar.setLayout(camposExportarLayout);
        camposExportarLayout.setHorizontalGroup(
            camposExportarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)
            .addGroup(camposExportarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1))
        );
        camposExportarLayout.setVerticalGroup(
            camposExportarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(camposExportarLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(camposExportarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setAutoscrolls(true);

        jpane.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jpaneLayout = new javax.swing.GroupLayout(jpane);
        jpane.setLayout(jpaneLayout);
        jpaneLayout.setHorizontalGroup(
            jpaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(messageLAbel, javax.swing.GroupLayout.DEFAULT_SIZE, 848, Short.MAX_VALUE)
        );
        jpaneLayout.setVerticalGroup(
            jpaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpaneLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(messageLAbel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        usuariosCadastradosJp.setBorder(javax.swing.BorderFactory.createTitledBorder("Relatório Gerados"));

        listaRelatorios.setColunas(new String[] {"Código", "Descrição", "Data Geração", "Status", "Nº Paginas", "OBJ"});
        listaRelatorios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaRelatoriosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(listaRelatorios);

        javax.swing.GroupLayout usuariosCadastradosJpLayout = new javax.swing.GroupLayout(usuariosCadastradosJp);
        usuariosCadastradosJp.setLayout(usuariosCadastradosJpLayout);
        usuariosCadastradosJpLayout.setHorizontalGroup(
            usuariosCadastradosJpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        usuariosCadastradosJpLayout.setVerticalGroup(
            usuariosCadastradosJpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usuariosCadastradosJpLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(usuariosCadastradosJp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(usuariosCadastradosJp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void hFComboBox1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hFComboBox1FocusLost

    }//GEN-LAST:event_hFComboBox1FocusLost

    private void hFComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hFComboBox1ActionPerformed

    }//GEN-LAST:event_hFComboBox1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    }//GEN-LAST:event_jTable1MouseClicked

    private void listaRelatoriosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaRelatoriosMouseClicked
        if (evt.getClickCount() == 2) {
            if (listaRelatorios.getSelectedRow() > -1) {
                HefRelatorio rel = (HefRelatorio) listaRelatorios.getValorColunaDaLinhaSelecionada(OBJ);
                listner.visualizar(new BeanEvent(this, rel.getIdrelatorio()));
            }
        }
    }//GEN-LAST:event_listaRelatoriosMouseClicked

    private void viewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewButtonActionPerformed
        if (listaRelatorios.getSelectedRow() > -1) {
            HefRelatorio rel = (HefRelatorio) listaRelatorios.getValorColunaDaLinhaSelecionada(OBJ);
            listner.visualizar(new BeanEvent(this, rel.getIdrelatorio()));
        }
    }//GEN-LAST:event_viewButtonActionPerformed

    private void pdfButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pdfButtonActionPerformed
        if (listaRelatorios.getSelectedRow() > -1) {
            HefRelatorio rel = (HefRelatorio) listaRelatorios.getValorColunaDaLinhaSelecionada(OBJ);
            listner.exportarPDF(new BeanEvent(this, rel.getIdrelatorio()));
        }
    }//GEN-LAST:event_pdfButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        HFPair<List<Object>, List<String>> pair = new HFPair<>();
        List<String> columns = new ArrayList<>();
        Boolean sel = false;

        for (int i = 0; i < listaColunas.getRowCount(); i++) {
            if ((Boolean) listaColunas.getValueAT(i, COL_SEL)) {
                sel = true;
                columns.add((String) listaColunas.getValueAT(i, COL_NAME));
            }
        }
        if (sel) {
            pair.setOne(relDataSource);
            pair.setTwo(columns);
            listner.exportarExcel(new BeanEvent(this, pair));
            Modal.closeModal(camposExportar);
        } else {
            HFBallonUtil.showTimedBallon(camposExportar, Messages.getMessage("relatorio.excel.erro.coluna"), MessageIcon.WARNING.getIcon());
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Modal.closeModal(camposExportar);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void excelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excelButtonActionPerformed
        if (listaRelatorios.getSelectedRow() > -1) {
            HefRelatorio rel = (HefRelatorio) listaRelatorios.getValorColunaDaLinhaSelecionada(OBJ);
            relDataSource = listner.carregarColunas(new BeanEvent(this, rel.getIdrelatorio()));
            Modal.addModal(this, camposExportar);
        }
    }//GEN-LAST:event_excelButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel camposExportar;
    private javax.swing.JButton excelButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jpane;
    private com.hefesto.hefestocomponentes.HFTable.HFTable listaColunas;
    private com.hefesto.hefestocomponentes.HFTable.HFTable listaRelatorios;
    private com.hefesto.hefestocomponentes.HFLabel.HFLabel messageLAbel;
    private javax.swing.JButton pdfButton;
    private javax.swing.JPanel processandoPainel;
    private javax.swing.JPanel usuariosCadastradosJp;
    private javax.swing.JButton viewButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void processar() {
        carregar(new BeanEvent(this, null));
    }

    @Override
    public void salvar() {
        RelatorioFilaObj obj = new RelatorioFilaObj();
        obj.setTela(icone.findTela(this.getClass()));
        obj.setTitulo(this.getTitle());
        obj.setUsuario(icone.getUsuario());
//        salvar(new BeanEvent(this, obj));
    }

    public void montaTabelaRelatorio(List<?> lst) {
        listaRelatorios.removeTable();
        listaRelatorios.addListRows(lst);
    }

    public void setExportando(Boolean exportando) {
        if (exportando) {
            Modal.addModal(this, processandoPainel);
        } else {
            Modal.closeModal(processandoPainel);
        }

    }

    public void montaTabelaColunas(List<?> lista) {
        listaColunas.removeTable();
        listaColunas.addListRows(lista);
    }
}