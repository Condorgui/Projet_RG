/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projet.Vue.graph;

import Projet.Vue.graph.*;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Guillaume.Rigaux
 */
public class panelAccueil extends javax.swing.JPanel {

    private final JLabel logo;

    /**
     * Creates new form panelAccueil
     */
    public panelAccueil() {
        initComponents();
        logo = new JLabel();
        
        

        logo.setIcon(new ImageIcon(getClass().getResource("/Images/logo1.png")));
        add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 130, 10));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textField1 = new java.awt.TextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        textField1.setText("textField1");

        jTextPane1.setBackground(new java.awt.Color(255, 204, 204));
        jTextPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jTextPane1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jTextPane1.setText("Panneau d'administration Condorcet école primaire");
        jTextPane1.setToolTipText("");
        jTextPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        jTextPane1.setName("Accueil"); // NOI18N
        jTextPane1.setOpaque(false);
        jTextPane1.setSelectedTextColor(new java.awt.Color(204, 51, 0));
        jTextPane1.setSelectionColor(new java.awt.Color(255, 102, 51));
        jScrollPane4.setViewportView(jTextPane1);

        jButton1.setText("Entrer");

        jButton2.setText("Quitter");

        setLayout(new java.awt.GridLayout(5, 5, 2, 5));
        add(jSeparator1);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextPane jTextPane1;
    private java.awt.TextField textField1;
    // End of variables declaration//GEN-END:variables
}
