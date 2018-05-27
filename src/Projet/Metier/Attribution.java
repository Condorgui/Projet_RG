
package Projet.Metier;

import java.util.Objects;

/**
 *
 * @author Guillaume.Rigaux
 */
public class Attribution {

    /**
     * Classe attribuée
     */
    protected Classe Classe;

    /**
     * Enseignant attribué
     */
    protected Enseignant Enseignant;

    /**
     * Constructeur par défaut non paramétré
     */
    public Attribution() {
        
        

    }
    
    /**
     * Constructeur paramétré 
     * @param classe
     * @param enseignant
     */
    public Attribution (Classe classe, Enseignant enseignant){
        
        this.Classe = classe; 
        this.Enseignant = enseignant; 
    }

    /**
     * Getter de la classe
     *
     * @return la classe
     */
    public Classe getClasse() {
        return Classe;
    }

    /**
     * Setter de la classe
     *
     * @param Classe
     */
    public void setClasse(Classe Classe) {
        this.Classe = Classe;
    }

    /**
     * Getter de l'enseignant
     *
     * @return l'enseignant
     */
    public Enseignant getEnseignant() {
        return Enseignant;
    }

    /**
     * Setter de l'enseignant
     *
     * @param enseignant
     */
    public void setEnseignant(Enseignant enseignant) {
        this.Enseignant = enseignant;
    }

    /**
     * Méthode hashCode
     *
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.Enseignant);
        return hash;
    }

    /**
     * Méthode equals param obj
     *
     * @param obj
     * @return résultat de comparaison de l'obj
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
        final Attribution other = (Attribution) obj;
        return Objects.equals(this.Enseignant, other.Enseignant);
    }

    /**
     * Méthode toString
     *
     * @return les informations détaillées
     */
    @Override
    public String toString() {
        return " "+Classe+" son enseignant est : "+Enseignant + "\n";
    }

}
