/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projet.Vue.graph;

import Projet.Metier.Attribution;
import Projet.Metier.Classe;
import Projet.Metier.Enseignant;
import Projet.Modele.ClasseModele;
import java.awt.Color;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

/**
 *
 * @author Guillaume.Rigaux
 */
public class rechAttrib extends javax.swing.JPanel {

    /**
     * Creates new form rechAttrib
     */
    public List<Enseignant> enseignants;

    /**
     *
     */
    public List<Classe> classes;

    /**
     *
     */
    public List<Attribution> attributions;

    private ClasseModele cm;

    /**
     *
     * @param cm
     */
    public void setModele(ClasseModele cm) {

        this.cm = cm;
    }

    /**
     *
     */
    public rechAttrib() {
        initComponents();
        this.setBackground(Color.ORANGE);
    }

    /**
     *
     */
    public void initPanel() {

        classes = new ArrayList<>(cm.toutesClasses());
        enseignants = new ArrayList<>(cm.tousEns());
        attributions = new ArrayList<>(cm.toutesLesAttributions());
        ButtonGroup group = new ButtonGroup();
        group.add(btnRemp);
        group.add(btnTitu);
        listEnseignant.removeAllItems();
        listClasse.removeAllItems();
        listAtt.removeAllItems();

        enseignants.forEach((Enseignant e) -> {
            listEnseignant.addItem(e);
        });
        /* for (Classe c : classes){
            listClasses.addItem("Classe :  " + c.getSigle() + " de " + c.getAnnee() + "ème/ère année " + " et d'orientation " + c.getOrientation());
        };
         */
        classes.forEach((c) -> {
            listClasse.addItem(c);
        });
        attributions.forEach((a) -> {
            listAtt.addItem(a);
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
        listAtt = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        listClasse = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        listEnseignant = new javax.swing.JComboBox<>();
        btnTitu = new javax.swing.JCheckBox();
        btnRemp = new javax.swing.JCheckBox();
        jSeparator2 = new javax.swing.JSeparator();
        modifAtt = new javax.swing.JButton();
        suppAtt = new javax.swing.JButton();

        setLayout(new java.awt.GridLayout(13, 3));

        jLabel1.setText("Sélectionner l'attribution à modifier parmis la liste :");
        add(jLabel1);

        listAtt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listAttMouseClicked(evt);
            }
        });
        listAtt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listAttActionPerformed(evt);
            }
        });
        add(listAtt);
        add(jSeparator1);

        jLabel2.setText("Choisissez la nouvelle attribution : ");
        add(jLabel2);

        jLabel3.setText("Liste des classes : ");
        add(jLabel3);

        add(listClasse);

        jLabel4.setText("Liste des enseignants : ");
        add(jLabel4);

        add(listEnseignant);

        btnTitu.setText("Titulaire");
        add(btnTitu);

        btnRemp.setText("Remplacant");
        add(btnRemp);
        add(jSeparator2);

        modifAtt.setText("Modifier");
        modifAtt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifAttActionPerformed(evt);
            }
        });
        add(modifAtt);

        suppAtt.setText("Supprimer");
        suppAtt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suppAttActionPerformed(evt);
            }
        });
        add(suppAtt);
    }// </editor-fold>//GEN-END:initComponents

    private void listAttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listAttActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_listAttActionPerformed

    private void listAttMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listAttMouseClicked
        //initPanel();
    }//GEN-LAST:event_listAttMouseClicked

    private void modifAttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifAttActionPerformed
        Object ens = listEnseignant.getSelectedItem();
        Enseignant eRech = cm.getEnseignant((Enseignant) ens);
        Enseignant nvEns = cm.getEnseignant(eRech);

        Classe classe = null;
        Object cla = listClasse.getSelectedItem();
        Classe aRech = cm.getClasse((Classe) cla);
        Classe nvClasse = cm.getClasse(aRech);

        Object att = listAtt.getSelectedItem();
        Attribution attri = cm.getAttribution((Attribution) att);
        Enseignant eAtt = attri.getEnseignant();

        boolean error = false;

        if (btnTitu.isSelected()) {
            for (Attribution a : attributions) {

                if (eAtt.getTitulaire() != null) {
                    if (eAtt.getTitulaire().equals(nvClasse)) {
                        error = true;
                        JOptionPane.showMessageDialog(this, "Il y a déjà un titulaire attitré", "Titulaire", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
            if (!error) {
                eRech.setTitulaire(null);
                nvEns.setTitulaire(nvClasse);

            }
        } else if (btnRemp.isSelected()) {
            eRech.setRemplacant(null);
            nvEns.setRemplacant(nvClasse);

        } /*else if (!btnTitu.isSelected() && !btnRemp.isSelected()) {

            error = true;
            JOptionPane.showMessageDialog(this, "Sélectionner titulaire ou remplacant", "Erreur", JOptionPane.ERROR_MESSAGE);

        }*/
        if (!error) {
            Attribution nvAttribution = new Attribution(nvClasse, nvEns);
            String msg = cm.modifyA(nvAttribution, attri);

            JOptionPane.showMessageDialog(this, msg, "Succès", JOptionPane.INFORMATION_MESSAGE
            );
        }
        initPanel(); 

    }//GEN-LAST:event_modifAttActionPerformed

    private void suppAttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suppAttActionPerformed
        Object att = listAtt.getSelectedItem();
        Attribution attri = cm.getAttribution((Attribution) att);

        try {
            String msg = cm.deleteA(attri);
            JOptionPane.showMessageDialog(this, msg, "Succès", JOptionPane.INFORMATION_MESSAGE);
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(this, "Erreur de suppression", "Erreur", JOptionPane.ERROR_MESSAGE);

        }

        initPanel(); 

    }//GEN-LAST:event_suppAttActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox btnRemp;
    private javax.swing.JCheckBox btnTitu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JComboBox<Attribution> listAtt;
    private javax.swing.JComboBox<Classe> listClasse;
    private javax.swing.JComboBox<Enseignant> listEnseignant;
    private javax.swing.JButton modifAtt;
    private javax.swing.JButton suppAtt;
    // End of variables declaration//GEN-END:variables
}
