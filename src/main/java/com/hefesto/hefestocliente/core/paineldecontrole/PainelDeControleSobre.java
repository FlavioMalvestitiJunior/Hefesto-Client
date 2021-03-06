/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hefesto.hefestocliente.core.paineldecontrole;

import com.hefesto.hefestocliente.core.HefInternalFrame;
import com.hefesto.hefestocliente.core.Messages;

/**
 *
 * @author Flavio
 */
public class PainelDeControleSobre extends HefInternalFrame {

    /**
     * Creates new form PainelDeControle
     */
    public PainelDeControleSobre() {
        initComponents();
        setSalvar(false);
        setAbrir(false);
        setCarregar(false);
        setExecutar(false);
        setExcluir(false);
        setNovo(false);
        setResizable(false);

        desc.setText("<html><b>Sistema " + Messages.getMessage("nome.sistema") + " Versão: " + Messages.getMessage("versao.sistema")
                + "<br />Java: </b>" + System.getProperty("java.version") + "; " + System.getProperty("java.vm.name")
                + "<br /><b>Sistema:</b> " + System.getProperty("os.name") + " versão: " + System.getProperty("os.version") + " executando em " + System.getProperty("os.arch")
                + "<br />Conectado na Base de Dados " + Messages.getMessage("host")
                + "<br />© 2015 Hefesto Resources."
                + "<br />Todos os direitos reservados.</html>");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        desc = new javax.swing.JTextPane();

        setBackground(new java.awt.Color(255, 255, 255));
        setRequestFocusEnabled(false);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hefesto/hefestocliente/icones/logo.png"))); // NOI18N
        jLabel1.setOpaque(true);

        desc.setEditable(false);
        desc.setContentType("text/html"); // NOI18N
        jScrollPane1.setViewportView(desc);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane desc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
