/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projet.Vue.graph;

import Projet.Modele.ClasseModele;
import java.awt.Color;
import java.util.ArrayList;
import Projet.Metier.Classe;
import Projet.Metier.Enseignant;
import java.awt.HeadlessException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Guillaume.Rigaux
 */
public class rechClasse extends javax.swing.JPanel {

    private ClasseModele cm;

    /**
     * Liste des classes
     */
    public List<Classe> classes;

    /**
     * set le modèle 
     *
     * @param cm le modèle à set
     */
    public void setModele(ClasseModele cm) {

        this.cm = cm;
    }

    /**
     *
     */
    public rechClasse() {
        initComponents();
        Color font = new Color(247, 223, 154);
        Color b = new Color(147, 216, 136);
        Color a = new Color(247, 104, 104);
        this.setBackground(font);
        modif.setBackground(b);
        delete.setBackground(a);
    }

    /**
     *
     */
    public void initPanel() {

        classes = new ArrayList<>(cm.toutesClasses());
        listClasse.removeAllItems();

        classes.forEach((c) -> {

            listClasse.addItem(c);
        });

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
        listClasse = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        sigleClasse = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        anneeClasse = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        orientationClasse = new javax.swing.JTextField();
        modif = new javax.swing.JButton();
        delete = new javax.swing.JButton();

        setLayout(new java.awt.GridLayout(5, 3));

        jLabel1.setText("Sélectionner la classe parmis la liste");
        add(jLabel1);

        listClasse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listClasseMouseClicked(evt);
            }
        });
        listClasse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listClasseActionPerformed(evt);
            }
        });
        add(listClasse);

        jLabel3.setText("Entrez le sigle : ");
        add(jLabel3);
        add(sigleClasse);

        jLabel2.setText("Entrez l'année : ");
        add(jLabel2);
        add(anneeClasse);

        jLabel4.setText("Entrez l'orientation : ");
        add(jLabel4);
        add(orientationClasse);

        modif.setText("Modifier");
        modif.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                modifMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                modifMouseExited(evt);
            }
        });
        modif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifActionPerformed(evt);
            }
        });
        add(modif);

        delete.setText("Supprimer");
        delete.setToolTipText("La suppression est définitive  ");
        delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                deleteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                deleteMouseExited(evt);
            }
        });
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        add(delete);
    }// </editor-fold>//GEN-END:initComponents

    private void listClasseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listClasseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_listClasseActionPerformed

    private void listClasseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listClasseMouseClicked
        //initPanel();
    }//GEN-LAST:event_listClasseMouseClicked

    private void modifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifActionPerformed

        Classe classe = null;
        Object cla = listClasse.getSelectedItem();
        Classe aRech = cm.getClasse((Classe) cla);
        Classe tmpC = cm.getClasse(aRech);

        boolean erreur = false;
        boolean erreursigle = false;

        String sigle = sigleClasse.getText().toUpperCase();
        if (sigle.length() > 4) {
            erreur = true;
            sigleClasse.setBackground(Color.red);
            sigleClasse.setText("Le sigle doit contenir 4 caractères maximum !");

        }
        if (sigle.length() > 4) {
            erreursigle = true;
            sigleClasse.setBackground(Color.red);
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
            if (annee < 1 || annee > 8) {
                erreur = true;
                anneeClasse.setBackground(Color.red);
            }
        } catch (NumberFormatException e) {
            erreur = true;
            anneeClasse.setBackground(Color.red);
        }

        if (!erreur) {

            Classe.ClasseBuilder c = new Classe.ClasseBuilder();
            c.setSigle(sigle).setOrientation(orientation).setAnnee(annee);
            try {
                classe = c.build();
            } catch (Exception e) {
                System.out.println("Erreur de création" + e);
            }

            String msg = cm.modifyC(classe, tmpC);
            JOptionPane.showMessageDialog(this, msg, "Résultat", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);

        }
        initPanel();

    }//GEN-LAST:event_modifActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed

        Object cla = listClasse.getSelectedItem();
        Classe aRech = cm.getClasse((Classe) cla);
        Classe tmpC = cm.getClasse(aRech);
        try {
            String msg = cm.deleteCl(tmpC);
            JOptionPane.showMessageDialog(this, msg, "Succès", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(this, "Erreur de suppression", "Erreur", JOptionPane.INFORMATION_MESSAGE);
        }
        initPanel();
    }//GEN-LAST:event_deleteActionPerformed

    private void modifMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modifMouseEntered
        Color b = new Color(138, 254, 71);
        modif.setBackground(b);
    }//GEN-LAST:event_modifMouseEntered

    private void modifMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modifMouseExited
        Color b = new Color(147, 216, 136);
        modif.setBackground(b);
    }//GEN-LAST:event_modifMouseExited

    private void deleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteMouseEntered
        Color a = new Color(243, 49, 49);
        delete.setBackground(a);
    }//GEN-LAST:event_deleteMouseEntered

    private void deleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteMouseExited
        Color a = new Color(247, 104, 104);
        delete.setBackground(a);
    }//GEN-LAST:event_deleteMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField anneeClasse;
    private javax.swing.JButton delete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JComboBox<Classe> listClasse;
    private javax.swing.JButton modif;
    private javax.swing.JTextField orientationClasse;
    private javax.swing.JTextField sigleClasse;
    // End of variables declaration//GEN-END:variables
}
