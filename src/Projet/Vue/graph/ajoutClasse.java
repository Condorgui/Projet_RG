/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projet.Vue.graph;

import Projet.Metier.*;
import java.awt.Color;
import Projet.Modele.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Guillaume.Rigaux
 */
public class ajoutClasse extends javax.swing.JPanel {

    private ClasseModele cm;

    /**
     *
     * @param cm
     */
    public void setModele(ClasseModele cm) {

        this.cm = cm;
    }

    /**
     * Creates new form ajoutClasse
     */
    public ajoutClasse() {
        initComponents();
        Color font = new Color(247, 223, 154);
        Color b = new Color(147, 216, 136);
        Color a = new Color(247, 104, 104);
        this.setBackground(font);
        valider.setBackground(b);
        annuler.setBackground(a);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        sigleClasse = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        orientationClasse = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        anneeClasse = new javax.swing.JTextField();
        valider = new javax.swing.JButton();
        annuler = new javax.swing.JButton();

        setLayout(new java.awt.GridLayout(4, 2, 2, 2));

        jLabel2.setText("Entrez le sigle de la classe :");
        add(jLabel2);

        sigleClasse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sigleClasseActionPerformed(evt);
            }
        });
        add(sigleClasse);

        jLabel3.setText("Entrez l'orientation de la classe : ");
        add(jLabel3);

        orientationClasse.setToolTipText("");
        orientationClasse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orientationClasseActionPerformed(evt);
            }
        });
        add(orientationClasse);

        jLabel1.setText("Entrez l'année de la classe : ");
        add(jLabel1);

        anneeClasse.setToolTipText("");
        add(anneeClasse);

        valider.setText("Valider");
        valider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validerActionPerformed(evt);
            }
        });
        add(valider);

        annuler.setText("Annuler");
        annuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annulerActionPerformed(evt);
            }
        });
        add(annuler);
    }// </editor-fold>//GEN-END:initComponents

    private void sigleClasseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sigleClasseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sigleClasseActionPerformed

    private void orientationClasseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orientationClasseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_orientationClasseActionPerformed

    private void annulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annulerActionPerformed
        sigleClasse.setText("");
        orientationClasse.setText("");
        anneeClasse.setText("");
        sigleClasse.setBackground(Color.white);
        orientationClasse.setBackground(Color.white);
        anneeClasse.setBackground(Color.white);
        // TODO add your handling code here:
    }//GEN-LAST:event_annulerActionPerformed

    private void validerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validerActionPerformed
        sigleClasse.setBackground(Color.white);
        orientationClasse.setBackground(Color.white);
        anneeClasse.setBackground(Color.white);

        boolean erreur = false;
        String sigle = sigleClasse.getText().toUpperCase();
        if (sigle.trim().equals("")) {
            erreur = true;
            sigleClasse.setBackground(Color.red);
        }
        if (sigle.length() > 4) {
            erreur = true;
            sigleClasse.setBackground(Color.red);
            sigleClasse.setText("Le sigle doit contenir 4 caractères maximum !");

        }
        String orientation = orientationClasse.getText();
        if (orientation.trim().equals("")) {
            erreur = true;
            orientationClasse.setBackground(Color.red);
        }
        int annee = 0;
        String year = anneeClasse.getText();
        try {
            annee = Integer.parseInt(year);
        } catch (NumberFormatException e) {
            erreur = true;
            anneeClasse.setBackground(Color.red);
        }

        if (!erreur) {
            Classe classe = null;
            Classe.ClasseBuilder c = new Classe.ClasseBuilder();
            c.setSigle(sigle).setOrientation(orientation).setAnnee(annee);
            try {
                classe = c.build();
            } catch (Exception e) {
                System.out.println("Erreur de création" + e);
            }
            //Pas oublier modèle
            String msg = cm.ajouterClasse(classe);
            JOptionPane.showMessageDialog(this, msg, "Résultat", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_validerActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField anneeClasse;
    private javax.swing.JButton annuler;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField orientationClasse;
    private javax.swing.JTextField sigleClasse;
    private javax.swing.JButton valider;
    // End of variables declaration//GEN-END:variables
}
