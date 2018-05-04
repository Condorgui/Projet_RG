/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

/**
 *
 * @author Michel
 */
public class Classe {

    private String sigle;
    private int annee;
    private String orientation;
    private int n_etudiants;
    private Enseignant e;


    private Classe(ClasseBuilder cb) {
     this.sigle = sigle; 
     this.annee = annee; 
     this.orientation = orientation; 
     this.n_etudiants = n_etudiants; 
     this.e = e; 
             
    }
//Plus que des getters 
    
    public String getSigle() {
        return sigle;
    }

    public int getAnnee() {
        return annee;
    }

    public String getOrientation() {
        return orientation;
    }

    public int getN_etudiants() {
        return n_etudiants; 
    }

    public String getEnseignant() {
        return e;
    }


     @Override
    public String toString() {
        return  sigle + " Classe de " + annee +" ème année et d'orientation " + orientation +"\n";

    }

    public static class ClasseBuilder {

        private String sigle;
        private String orientation;
        private int n_etudiants; 
        private int annee;
        private Enseignant e;

        public ClasseBuilder() {
        }
//Les setters se trouvent dans le personne builder
        public ClasseBuilder setSigle(String sigle) {
            this.sigle = sigle;
            return this;
        }

        public ClasseBuilder setOrientation(String orientation) {
            this.orientation = orientation
            return this;
        }

        public ClasseBuilder setDn(String dn) {
            this.dn = dn;
            return this;
            //L'objet se retourne lui même via this
        }

        public ClasseBuilder setCp(int cp) {
            this.cp = cp;
            return this;
        }

        public ClasseBuilder setLoc(String loc) {
            this.loc = loc;
            return this;
        }

        public ClasseBuilder setRue(String rue) {
            this.rue = rue;
            return this;
        }

        public ClasseBuilder setNum(String num) {
            this.num = num;
            return this;
        }

        public ClasseBuilder setTel(String tel) {
            this.tel = tel;
            return this;
        }

        public Classe build() throws Exception {
            if(nom==null || prenom==null)throw new Exception("informations de base manquantes");
            if(nom.trim().equals("")|| prenom.trim().equals("")) throw new Exception("informations de base manquantes");
            return new Personne(this);
        }

    }

}
