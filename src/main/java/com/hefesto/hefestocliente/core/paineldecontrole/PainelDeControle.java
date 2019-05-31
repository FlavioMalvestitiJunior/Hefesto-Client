/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hefesto.hefestocliente.core.paineldecontrole;

import com.hefesto.hefestocliente.Core.paineldecontroleListner.PainelDeControleListner;
import com.hefesto.hefestocliente.core.HefInternalFrame;
import com.hefesto.hefestocliente.core.Icone;
import com.hefesto.hefestocliente.core.paineldecontroleBean.PainelDeControleBean;

/**
 *
 * @author Flavio
 */
public class PainelDeControle extends HefInternalFrame {

    /**
     * Creates new form PainelDeControle
     */
    Icone icone = Icone.getInstance();
    PainelDeControleListner listner = icone.getBeanInstance(PainelDeControleBean.class);

    public PainelDeControle() {
        initComponents();
        setmessageLabel(hFLabel1);
        setSalvar(false);
        setAbrir(false);
        setCarregar(false);
        setExecutar(false);
        setExcluir(false);
        setNovo(false);

    }

    @Override
    public void postConstruct() {
        if (!icone.getUsuario().getIdperfil().getIdadministrador()
                && !icone.getUsuario().getIdperfil().getIdgerente()
                && !icone.getUsuario().getIdperfil().getIdadministradorEmpresa()) {
            userControlPanel.remove(libTelasBtn);
            userControlPanel.remove(perfilBtn);
            systemControlPanel.remove(menuBtn);
            systemControlPanel.remove(telaBtn);
            systemControlPanel.remove(estatisticasBtn);
            systemControlPanel.remove(filiaisBtn);
            systemControlPanel.remove(empresaBtn);
        }
        if (icone.getUsuario().getIdperfil().getIdgerente()) {
            systemControlPanel.remove(menuBtn);
            systemControlPanel.remove(telaBtn);
            systemControlPanel.remove(estatisticasBtn);
            systemControlPanel.remove(filiaisBtn);
            systemControlPanel.remove(empresaBtn);
        }

        if (icone.getUsuario().getIdperfil().getIdadministradorEmpresa()) {
            systemControlPanel.remove(menuBtn);
            systemControlPanel.remove(telaBtn);
            systemControlPanel.remove(estatisticasBtn);
            systemControlPanel.remove(empresaBtn);
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

        jPanel1 = new javax.swing.JPanel();
        hFLabel1 = new com.hefesto.hefestocomponentes.HFLabel.HFLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jToolBar1 = new javax.swing.JToolBar();
        filarelatoriosBtn = new javax.swing.JButton();
        processosBtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jToolBar2 = new javax.swing.JToolBar();
        jScrollPane3 = new javax.swing.JScrollPane();
        userControlPanel = new javax.swing.JToolBar();
        contausuarioBtn = new javax.swing.JButton();
        libTelasBtn = new javax.swing.JButton();
        perfilBtn = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        systemControlPanel = new javax.swing.JToolBar();
        menuBtn = new javax.swing.JButton();
        telaBtn = new javax.swing.JButton();
        empresaBtn = new javax.swing.JButton();
        filiaisBtn = new javax.swing.JButton();
        estatisticasBtn = new javax.swing.JButton();
        sobreBtn = new javax.swing.JButton();

        setRequestFocusEnabled(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(hFLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(hFLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jToolBar1.setBackground(new java.awt.Color(239, 239, 239));
        jToolBar1.setBorder(javax.swing.BorderFactory.createTitledBorder("Add-ons"));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setAutoscrolls(true);

        filarelatoriosBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hefesto/hefestocliente/icones/filarelatorios.png"))); // NOI18N
        filarelatoriosBtn.setToolTipText("Fila de Relatórios");
        filarelatoriosBtn.setFocusable(false);
        filarelatoriosBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        filarelatoriosBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        filarelatoriosBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filarelatoriosBtnActionPerformed(evt);
            }
        });
        jToolBar1.add(filarelatoriosBtn);

        processosBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hefesto/hefestocliente/icones/processos.png"))); // NOI18N
        processosBtn.setToolTipText("Processos");
        processosBtn.setEnabled(false);
        processosBtn.setFocusable(false);
        processosBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        processosBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(processosBtn);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hefesto/hefestocliente/icones/star.png"))); // NOI18N
        jButton1.setToolTipText("O que Há de Novo?");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jScrollPane1.setViewportView(jToolBar1);

        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jToolBar2.setBackground(new java.awt.Color(249, 249, 249));
        jToolBar2.setBorder(javax.swing.BorderFactory.createTitledBorder("Aparência"));
        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);
        jToolBar2.setAutoscrolls(true);
        jScrollPane2.setViewportView(jToolBar2);

        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        userControlPanel.setBackground(new java.awt.Color(249, 249, 249));
        userControlPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Usuários"));
        userControlPanel.setFloatable(false);
        userControlPanel.setRollover(true);
        userControlPanel.setAutoscrolls(true);

        contausuarioBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hefesto/hefestocliente/icones/user.png"))); // NOI18N
        contausuarioBtn.setToolTipText("Contas de Usuário");
        contausuarioBtn.setFocusable(false);
        contausuarioBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        contausuarioBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        contausuarioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contausuarioBtnActionPerformed(evt);
            }
        });
        userControlPanel.add(contausuarioBtn);

        libTelasBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hefesto/hefestocliente/icones/liberacaotelas.png"))); // NOI18N
        libTelasBtn.setToolTipText("Liberação de Telas");
        libTelasBtn.setFocusable(false);
        libTelasBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        libTelasBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        libTelasBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                libTelasBtnActionPerformed(evt);
            }
        });
        userControlPanel.add(libTelasBtn);

        perfilBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hefesto/hefestocliente/icones/perfil.png"))); // NOI18N
        perfilBtn.setToolTipText("Perfis de Usuário");
        perfilBtn.setFocusable(false);
        perfilBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        perfilBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        perfilBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                perfilBtnActionPerformed(evt);
            }
        });
        userControlPanel.add(perfilBtn);

        jScrollPane3.setViewportView(userControlPanel);

        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        systemControlPanel.setBackground(new java.awt.Color(239, 239, 239));
        systemControlPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Sistema"));
        systemControlPanel.setFloatable(false);
        systemControlPanel.setRollover(true);
        systemControlPanel.setAutoscrolls(true);

        menuBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hefesto/hefestocliente/icones/menu.png"))); // NOI18N
        menuBtn.setToolTipText("Menus");
        menuBtn.setFocusable(false);
        menuBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        menuBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        menuBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBtnActionPerformed(evt);
            }
        });
        systemControlPanel.add(menuBtn);

        telaBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hefesto/hefestocliente/icones/telas.png"))); // NOI18N
        telaBtn.setToolTipText("Telas do Sistema");
        telaBtn.setFocusable(false);
        telaBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        telaBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        telaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telaBtnActionPerformed(evt);
            }
        });
        systemControlPanel.add(telaBtn);

        empresaBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hefesto/hefestocliente/icones/company.png"))); // NOI18N
        empresaBtn.setFocusable(false);
        empresaBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        empresaBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        empresaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empresaBtnActionPerformed(evt);
            }
        });
        systemControlPanel.add(empresaBtn);

        filiaisBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hefesto/hefestocliente/icones/companies_add.png"))); // NOI18N
        filiaisBtn.setToolTipText("Filiais");
        filiaisBtn.setFocusable(false);
        filiaisBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        filiaisBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        filiaisBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filiaisBtnActionPerformed(evt);
            }
        });
        systemControlPanel.add(filiaisBtn);

        estatisticasBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hefesto/hefestocliente/icones/estatisticas.png"))); // NOI18N
        estatisticasBtn.setToolTipText("Estatisticas");
        estatisticasBtn.setEnabled(false);
        estatisticasBtn.setFocusable(false);
        estatisticasBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        estatisticasBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        estatisticasBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estatisticasBtnActionPerformed(evt);
            }
        });
        systemControlPanel.add(estatisticasBtn);

        sobreBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hefesto/hefestocliente/icones/sobre.png"))); // NOI18N
        sobreBtn.setToolTipText("Sobre");
        sobreBtn.setFocusable(false);
        sobreBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        sobreBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        sobreBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sobreBtnActionPerformed(evt);
            }
        });
        systemControlPanel.add(sobreBtn);

        jScrollPane4.setViewportView(systemControlPanel);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jScrollPane3)
            .addComponent(jScrollPane2)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void estatisticasBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estatisticasBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_estatisticasBtnActionPerformed

    private void filarelatoriosBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filarelatoriosBtnActionPerformed
        listner.filaRelatorios();
    }//GEN-LAST:event_filarelatoriosBtnActionPerformed

    private void telaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telaBtnActionPerformed
        listner.telas();
    }//GEN-LAST:event_telaBtnActionPerformed

    private void menuBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBtnActionPerformed
        listner.menus();
    }//GEN-LAST:event_menuBtnActionPerformed

    private void perfilBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_perfilBtnActionPerformed
        listner.perfilUsuario();
    }//GEN-LAST:event_perfilBtnActionPerformed

    private void libTelasBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_libTelasBtnActionPerformed
        listner.liberacaoTelas();
    }//GEN-LAST:event_libTelasBtnActionPerformed

    private void contausuarioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contausuarioBtnActionPerformed
        listner.usuario();
    }//GEN-LAST:event_contausuarioBtnActionPerformed

    private void filiaisBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filiaisBtnActionPerformed
        listner.filial();
    }//GEN-LAST:event_filiaisBtnActionPerformed

    private void sobreBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sobreBtnActionPerformed
        listner.sobre();
    }//GEN-LAST:event_sobreBtnActionPerformed

    private void empresaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empresaBtnActionPerformed
        listner.empresa();
    }//GEN-LAST:event_empresaBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        listner.notasVersao();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton contausuarioBtn;
    private javax.swing.JButton empresaBtn;
    private javax.swing.JButton estatisticasBtn;
    private javax.swing.JButton filarelatoriosBtn;
    private javax.swing.JButton filiaisBtn;
    private com.hefesto.hefestocomponentes.HFLabel.HFLabel hFLabel1;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JButton libTelasBtn;
    private javax.swing.JButton menuBtn;
    private javax.swing.JButton perfilBtn;
    private javax.swing.JButton processosBtn;
    private javax.swing.JButton sobreBtn;
    private javax.swing.JToolBar systemControlPanel;
    private javax.swing.JButton telaBtn;
    private javax.swing.JToolBar userControlPanel;
    // End of variables declaration//GEN-END:variables

}
