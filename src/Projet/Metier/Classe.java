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
    private int n_etudiants;
    private Enseignant e;

    /**
     * constructeur avec un seul paramètre 
     * @param sigle le sigle 
     */
    public Classe(String sigle){
        this.sigle = sigle; 
    }

    private Classe(ClasseBuilder cb) {
        this.sigle = cb.sigle;
        this.annee = cb.annee;
        this.orientation = cb.orientation;
        // this.n_etudiants = n_etudiants;
        // this.e = e
    }
//Plus que des getters 

    /**
     * getSigle
     * @return le sigle
     */
    public String getSigle() {
        return sigle;
    }

    /**
     * getAnnee
     * @return l'année
     */
    public int getAnnee() {
        return annee;
    }

    /**
     * getOrientation
     * @return l'orientation
     */
    public String getOrientation() {
        return orientation;
    }

    /**
     * getN_etudiants
     * @return le nombre d'étudiants
     */
    public int getN_etudiants() {
        return n_etudiants;
    }

    /**
     * getE
     * @return l'enseignant
     */
    public Enseignant getE() {
        return e;
    }

    /**
     * Méthode toString
     *
     * @return les informations détaillées
     */
    @Override
    public String toString() {
        return sigle + " Classe de " + annee + " ème année et d'orientation " + orientation + "\n";

    }

    /**
     * Hashcode
     *
     * @return le hash
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.sigle);
        return hash;
    }

    /**
     * Constructeur ClasseBuilder
     *
     */
    public static class ClasseBuilder {

        private String sigle;
        private String orientation;
        private int n_etudiants;
        private int annee;
        private Enseignant e;

        /**
         *
         */
        public ClasseBuilder() {
        }
//Les setters se trouvent dans le personne builder

        /**
         * Setter du sigle du classBuilder
         *
         * @param sigle le sigle
         * @return le sigle
         */
        public ClasseBuilder setSigle(String sigle) {
            this.sigle = sigle;
            return this;
        }

        /**
         * Setter de l'orientation du classBuilder
         *
         * @param orientation l'orientation
         * @return l'orientation
         */
        public ClasseBuilder setOrientation(String orientation) {
            this.orientation = orientation;
            return this;
        }

        /**
         * Setter du n_etudiants du classBuilder
         *
         * @param n_etudiants nbr etudiant
         * @return n_etudiants
         */
        public ClasseBuilder setN_etudiants(int n_etudiants) {
            this.n_etudiants = n_etudiants;
            return this;
            //L'objet se retourne lui même via this
        }

        /**
         * Setter de l'enseignant du classBuilder
         *
         * @param e l'enseignant à set
         * @return l'enseignant
         */
        public ClasseBuilder setEnseignant(Enseignant e) {
            this.e = e;
            return this;
        }

        /**
         * Setter de l'année du classbuilder
         *
         * @param annee l'annee a set
         * @return l'annee
         */
        public ClasseBuilder setAnnee(int annee) {
            this.annee = annee;
            return this;
        }

        /**
         * builder des informations essentielles
         *
         * @return @throws Exception
         */
        public Classe build() throws Exception {
            if (sigle == null) {
                throw new Exception("informations de base manquantes");
            }
            if (sigle.trim().equals("")) {
                throw new Exception("informations de base manquantes");
            }
            return new Classe(this);
        }

    }

    /**
     * Méthode equals
     *
     * @param obj l'objet à comparer
     * @return résultat de la comparaison de l'obj
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
        final Classe other = (Classe) obj;
        return Objects.equals(this.sigle, other.sigle);
    }

}


/* ANCIENNE CLASSE
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

package Projet.Metier;

import java.util.Objects;

/**
 *
 * @author Guillaume.Rigaux
 
public class Classe {

    /**
     * Sigle de la classe
     
    private String sigle;

    /**
     * Année de la classe
     
    private int annee;
    /**
     * Orientation de la classe
     
    
    private int n_etudiants; 
    
    private String orientation;
    /**
     * Enseignant attitré à la classe
     
    private Enseignant e;

    /**
     * Constructeur par défaut
     
    public Classe() {
        sigle = "";
        annee = 0;
        orientation = "";
        e = null;
       // this.n_etudiants = 0; 
        
    }

    /**
     * Constructeur paramétré
     *
     * @param Sigle
     * @param Annee
     * @param Orientation
     
    public Classe(String Sigle, int Annee, String Orientation) {
        this.sigle = Sigle;
        this.annee = Annee;
        this.orientation = Orientation;
      //  this.n_etudiants = 0;
    }

    
    /**
     * Constructeur avec un seul paramètre
     *
     * @param sigle qui recherchera la classe sur base de son sigle unique
     
    public Classe(String sigle) {
        this.sigle = sigle;
    }

    /**
     * Getter de l'enseignant
     *
     * @return l'enseignant de la classe
     
    public Enseignant getE() {
        return e;
    }

    /**
     * Setter de l'enseignant
     *
     * @param e définit l'enseignant
     
    public void setE(Enseignant e) {
        this.e = e;
    }

    /**
     * Getter du sigle
     *
     * @return le sigle de la classe
    
    public String getSigle() {
        return sigle;
    }

    /**
     * Setter du sigle
     *
     * @param Sigle définit le sigle
     
    public void setSigle(String Sigle) {
        this.sigle = Sigle;
    }

    /**
     * Getter de l'année
     *
     * @return l'année de la classe
     
    public int getAnnee() {
        return annee;
    }

    /**
     * Setter de l'année
     *
     * @param Annee définit l'année
     
    public void setAnnee(int Annee) {
        this.annee = Annee;
    }

    /**
     * Getter de l'orientation
     *
     * @return l'orientation de la classe
     
    public String getOrientation() {
        return orientation;
    }

    /**
     * Setter de l'orientation
     *
     * @param Orientation définit l'orientation
     
    public void setOrientation(String Orientation) {
        this.orientation = Orientation;
    }

    /**
     *
     * @return
     
    public int getN_etudiants() {
        return n_etudiants;
    }

    /**
     *
     * @param n_etudiants
     
    public void setN_etudiants(int n_etudiants) {
        this.n_etudiants = n_etudiants;
    }

    
    /**
     * Méthode hashCode
     *
     * @return hash
     
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.sigle);
        return hash;
    }

    /**
     * Méthode equals
     *
     * @param obj
     * @return résultat de la comparaison de l'obj
     
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
        return Objects.equals(this.sigle, other.sigle);
    }

    /**
     * Méthode toString
     *
     * @return les informations détaillées
     
    @Override
    public String toString() {
        return  sigle + " Classe de " + annee +" ème année et d'orientation " + orientation +"\n";

    }

}
 */
