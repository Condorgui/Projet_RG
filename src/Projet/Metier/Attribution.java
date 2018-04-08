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
public class Attribution {

    protected Classe Classe;
    protected Classe Enseignant;

    public Attribution() {

    }

    public Classe getClasse() {
        return Classe;
    }

    public void setClasse(Classe Classe) {
        this.Classe = Classe;
    }

    public Classe getEnseignant() {
        return Enseignant;
    }

    public void setEnseignant(Classe Enseignant) {
        this.Enseignant = Enseignant;
    }

    @Override
    public String toString() {
        return "Attribution{" + "Classe=" + Classe + ", Enseignant=" + Enseignant + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.Enseignant);
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
        final Attribution other = (Attribution) obj;
        return Objects.equals(this.Enseignant, other.Enseignant);
    }
    

}