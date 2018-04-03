/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classe.Modele;


import java.util.*;

/**
 * 
 */
public class Enseignant {

   public Enseignant(){
       
   }
   
private String matricule; 
private String nom; 
private String prenom; 

public Classe Titulaire;

public Classe Remplacant;

    public Enseignant(String matricule, String nom, String prenom) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
      
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Classe getTitulaire() {
        return Titulaire;
    }

    public void setTitulaire(Classe Titulaire) {
        this.Titulaire = Titulaire;
    }

    public Classe getRemplacant() {
        return Remplacant;
    }

    public void setRemplacant(Classe Remplacant) {
        this.Remplacant = Remplacant;
    }

    @Override
    public String toString() {
        return "Enseignant{" + "matricule=" + matricule + ", nom=" + nom + ", prenom=" + prenom + ", Titulaire=" + Titulaire + ", Remplacant=" + Remplacant + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.matricule);
        return hash;
    }

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