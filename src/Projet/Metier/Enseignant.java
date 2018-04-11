/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projet.Metier;

import java.util.*;

/**
 *
 * @author Guillaume.Rigaux
 */
public class Enseignant {

    /**
     *
     */
    public Enseignant() {

    }

    /**
     * Matricule de l'enseignant
     */
    private String matricule;
    /**
     * Nom de l'enseignant
     */
    private String nom;
    /**
     * Prénom de l'enseignant
     */
    private String prenom;
    /**
     * Correspond au statut de l'enseignant titulaire
     */
    private Classe Titulaire;
    /**
     * Correspond au statut de l'enseignant remplacant
     */
    private Classe Remplacant;

    /**
     * Constructeur paramétré
     *
     * @param matricule
     * @param nom
     * @param prenom Le statut Titulaire ou Remplacant doit lui être affecté
     */
    public Enseignant(String matricule, String nom, String prenom) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;

    }

    /**
     * Constructeur avec un seul paramètre
     *
     * @param Matricule qui recherchera l'enseignant sur base de son matricule
     */
    public Enseignant(String Matricule) {
        this.matricule = Matricule;
    }

    /**
     * Getter du matricule
     *
     * @return le matricule
     */
    public String getMatricule() {
        return matricule;
    }

    /**
     * Setter du matricule
     *
     * @param matricule définit le matricule
     */
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    /**
     * Getter du nom
     *
     * @return le nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter du nom
     *
     * @param nom qui définit le nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter du prénom
     *
     * @return le prénom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Setter du prénom
     *
     * @param prenom définit le prénom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Getter du titulaire
     *
     * @return le titulaire
     */
    public Classe getTitulaire() {
        return Titulaire;
    }

    /**
     * Setter du titulaire
     *
     * @param Titulaire définit le titulaire
     */
    public void setTitulaire(Classe Titulaire) {
        this.Titulaire = Titulaire;
    }

    /**
     * Getter du remplacant
     *
     * @return le remplacant
     */
    public Classe getRemplacant() {
        return Remplacant;
    }

    /**
     * Setter du remplacant
     *
     * @param Remplacant définit le remplacant
     */
    public void setRemplacant(Classe Remplacant) {
        this.Remplacant = Remplacant;
    }

    /**
     * Méthode toString
     *
     * @return les informations détaillées
     */
    @Override
    public String toString() {
        return " => Enseignant " + nom + " " + prenom + " au matricule " + matricule;
    }

    /**
     * Méthode hashCode
     *
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.matricule);
        return hash;
    }

    /**
     * Méthode equals
     *
     * @param obj
     * @return résultat de la comparaison
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Enseignant other = (Enseignant) obj;
        if (!Objects.equals(this.matricule, other.matricule)) {
            return false;
        }
        return true;
    }

}
