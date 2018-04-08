/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projet.Modele;

import Projet.Metier.Enseignant;
import Projet.Metier.Classe;
import Projet.Metier.Attribution;
import java.util.*;

/**
 *
 * @author Guillaume.Rigaux
 */
public class ClasseModele {

    private List<Enseignant> tousLesEns;
    private List<Classe> toutesLesClasses;
    private List<Attribution> toutesLesAttributions;

    /**
     * constructeur par défaut
     */
    public ClasseModele() {

        tousLesEns = new ArrayList<>();
        toutesLesClasses = new ArrayList<>();
        toutesLesAttributions = new ArrayList<>();

    }

    public String ajouterClasse(Classe c) {
        if (c == null) {
            return "Classe inexistante";
        }
        if (toutesLesClasses.contains(c)) {
            return "Classe existe déjà";
        } else {
            toutesLesClasses.add(c);
        }
        return "Création de la classe effectuée";
    }

    public String ajouterEnseignant(Enseignant e) {
        if (e == null) {
            return "Enseignant inexistant";
        }
        if (tousLesEns.contains(e)) {
            return "Enseignant déjà crée";
        }
        tousLesEns.add(e);
        return "Ajout de l'enseignant";
    }

    public Classe getClasse(Classe cl) {

        int i = toutesLesClasses.indexOf(cl);
        if (i < 0) {
            return null;
        } else {
            return toutesLesClasses.get(i);
        }
    }

    public Enseignant getEnseignant(Enseignant aRech) {
        int i = tousLesEns.indexOf(aRech);
        if (i < 0) {
            return null;
        } else {
            return tousLesEns.get(i);
        }
    }

    public String modifyC(Classe nvClasse, Classe tmpC) {

        int i = toutesLesClasses.indexOf(tmpC);
        if (i < 0) {
            return "Classe introuvable";

        } else {
            toutesLesClasses.set(i, nvClasse);
        }
        return "Modification effectuée";

    }

    public String modifyE(Enseignant nvEns, Enseignant tmpE) {
        int i = tousLesEns.indexOf(tmpE);
        if (i < 0) {
            return "Enseignant introuvable";

        } else {
            tousLesEns.set(i, nvEns);
        }
        return "Modification effectuée";

    }

    public String deleteE(Enseignant es) {
        int i = tousLesEns.indexOf(es);
        if (i < 0) {
            return "Enseignant introuvable";

        } else {
            tousLesEns.remove(i);
        }
        return "Suppression effectuée";
    }

    public String deleteCl(Classe cl) {
        int i = toutesLesClasses.indexOf(cl);
        if (i < 0) {
            return "Classe introuvable";

        } else {
            toutesLesClasses.remove(i);
        }
        return "Suppression effectuée";
    }

    public List<Enseignant> tousEns() {
        return tousLesEns;
    }

    public List<Classe> toutesClasses() {

        return toutesLesClasses;

    }

}
