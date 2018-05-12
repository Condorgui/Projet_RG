/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projet.Vue.graph;

import java.awt.Color;
import javax.swing.JOptionPane;
import occases.modele.GarageModele;
import occases.modele.Voiture;

/**
 *
 * @author Michel
 */
public class EncodeEnseignant extends javax.swing.JPanel {

    private GarageModele gm;

    /**
     * Creates new form EncodeVoiture
     */

    public EncodeEnseignant() {
        initComponents();
    }

    public void setModele(GarageModele gm) {
        this.gm = gm;
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
        tfnum = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfmarque = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfmod = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfkm = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tfprix = new javax.swing.JTextField();
        javax.swing.JButton btok = new javax.swing.JButton();
        btclear = new javax.swing.JButton();

        setLayout(new java.awt.GridLayout(7, 2));

        jLabel1.setText("Numéro de Châssis");
        add(jLabel1);
        add(tfnum);

        jLabel2.setText("Marque");
        add(jLabel2);

        tfmarque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfmarqueActionPerformed(evt);
            }
        });
        add(tfmarque);

        jLabel3.setText("Modèle");
        add(jLabel3);

        tfmod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfmodActionPerformed(evt);
            }
        });
        add(tfmod);

        jLabel4.setText("KM");
        add(jLabel4);
        add(tfkm);

        jLabel5.setText("Année");
        add(jLabel5);
        add(tfan);

        jLabel6.setText("Prix");
        add(jLabel6);
        add(tfprix);

        btok.setText("OK");
        btok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btokActionPerformed(evt);
            }
        });
        add(btok);

        btclear.setText("CLEAR");
        btclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btclearActionPerformed(evt);
            }
        });
        add(btclear);
    }// </editor-fold>//GEN-END:initComponents

    private void tfmarqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfmarqueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfmarqueActionPerformed

    private void tfmodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfmodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfmodActionPerformed

    private void btokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btokActionPerformed
        tfnum.setBackground(Color.white);
        tfmarque.setBackground(Color.white);
        tfmod.setBackground(Color.white);
        tfkm.setBackground(Color.white);
        tfprix.setBackground(Color.white);
        tfan.setBackground(Color.white);
        boolean erreur = false;
        String numch = tfnum.getText();
        if (numch.trim().equals("")) {
            erreur = true;
            tfnum.setBackground(Color.red);
        }
        String marque = tfmarque.getText();
        if (marque.trim().equals("")) {
            erreur = true;
            tfmarque.setBackground(Color.red);
        }
        String modele = tfmod.getText();
        if (modele.trim().equals("")) {
            erreur = true;
            tfmod.setBackground(Color.red);
        }
        String kms = tfkm.getText();
        String prixs = tfprix.getText();
        String ans = tfan.getText();
        int km = 0;
        double prix = 0;
        int an = 0;
        try {
            km = Integer.parseInt(kms);
        } catch (NumberFormatException e) {
            erreur = true;
            tfkm.setBackground(Color.red);
        }

        try {
            an = Integer.parseInt(ans);
        } catch (NumberFormatException e) {
            erreur = true;
            tfan.setBackground(Color.red);
        }

        try {
            prix = Double.parseDouble(prixs);
        } catch (NumberFormatException e) {
            erreur = true;
            tfprix.setBackground(Color.red);
        }
        if (!erreur) {
            Voiture v = new Voiture(numch, marque, modele, km, an, prix);
            String msg = gm.ajouterVoiture(v);
            JOptionPane.showMessageDialog(this, msg, "Résultat", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_btokActionPerformed

    private void btclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btclearActionPerformed
        tfnum.setText("");
        tfmarque.setText("");
        tfmod.setText("");
        tfkm.setText("");
        tfprix.setText("");
        tfan.setText("");
        tfnum.setBackground(Color.white);
        tfmarque.setBackground(Color.white);
        tfmod.setBackground(Color.white);
        tfkm.setBackground(Color.white);
        tfprix.setBackground(Color.white);
        tfan.setBackground(Color.white);
    }//GEN-LAST:event_btclearActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btclear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField tfan;
    private javax.swing.JTextField tfkm;
    private javax.swing.JTextField tfmarque;
    private javax.swing.JTextField tfmod;
    private javax.swing.JTextField tfnum;
    private javax.swing.JTextField tfprix;
    // End of variables declaration//GEN-END:variables

}
