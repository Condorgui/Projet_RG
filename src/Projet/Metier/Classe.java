/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projet.Metier;

import java.util.Objects;

/**
 *
 * @author Guillaume.Rigaux
 */
public class Classe {

    private String sigle;
    private int annee;
    private String orientation;
    private Enseignant e;

    public Classe() {
        sigle = "";
        annee = 0;
        orientation = "";
        e = null;
    }
public Classe(String sigle){
    this.sigle = sigle; 
}
    public Enseignant getE() {
        return e;
    }

    public void setE(Enseignant e) {
        this.e = e;
    }

    public Classe(String Sigle, int Annee, String Orientation) {
        this.sigle = Sigle;
        this.annee = Annee;
        this.orientation = Orientation;
    }

    public String getSigle() {
        return sigle;
    }

    public void setSigle(String Sigle) {
        this.sigle = Sigle;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int Annee) {
        this.annee = Annee;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String Orientation) {
        this.orientation = Orientation;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.sigle);
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
        final Classe other = (Classe) obj;
        if (!Objects.equals(this.sigle, other.sigle)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Classe de "+annee+" ème année et d'orientation "+orientation+" dont l'enseignant est : "+e;
       
    }

}
