/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projet.Controleur;

import Projet.Metier.Classe;
import Projet.Metier.Enseignant;
import Projet.Modele.ClasseModele;
import Projet.Vue.Pvue;
import java.util.List;

/**
 *
 * @author Guillaume.Rigaux
 */
public class ClasseControleur {

    private ClasseModele cm;
    private Pvue pv;

    public ClasseControleur() {

    }

    public ClasseControleur(ClasseModele cm, Pvue pv) {

        this.cm = cm;
        this.pv = pv;
    }

    public void gestion() {

        //TODO rendre cohérent la liste des switch cases et la liste des éléments du menu
        int ch;
        do {
            ch = pv.menuPrincipal();

            switch (ch) {
                case 1:
                    ajoutEnseignant();
                    break;
                case 2:
                    ajoutClasse();
                    break;
                case 3:
                    modifyE();
                    break;

                case 4:
                    modifyC();
                    break;
                case 5:

                    rechEnseignant();
                    break;
                case 6:

                    rechClasse();
                    break;
                case 7:
                    affichage();
                    break;

                case 8:
                    pv.affichageMessage("Fin");
                    break;
                default:
                    pv.affichageMessage("Choix invalide");
            }
        } while (ch != 8);

    }

    public void ajoutClasse() {
        Classe cl = pv.newClasse();
        String msg = cm.ajouterClasse(cl);
        pv.affichageMessage(msg);

    }

    public void ajoutEnseignant() {
        Enseignant es = pv.newEnseignant();
        String msg = cm.ajouterEnseignant(es);
        pv.affichageMessage(msg);

    }

    private void rechClasse() {
        Classe cl = pv.rechClasse();
        pv.affichageMessage(cm.getClasse(cl));
    }

    private void rechEnseignant() {
        Enseignant es = pv.rechEnseignant();
        pv.affichageMessage(cm.getEnseignant(es));
    }

    private void modifyC() {

        int choix = Integer.parseInt(pv.getMessage(""
                + "1. Modification de la classe"
                + "       2. Suppression de la classe"));
        Classe cRech = pv.rechClasse();
        Classe tmpC = cm.getClasse(cRech);
        if (choix == 1) {

            pv.getMessage("Modification de la classe");

            pv.affichageMessage(tmpC);
            Classe nvClasse = pv.newClasse();
            pv.affichageMessage(cm.modifyC(nvClasse, tmpC));

        } else if (choix == 2) {
            pv.affichageMessage("--- Suppression de la classe --- ");
            pv.affichageMessage(tmpC);
            pv.affichageMessage(cm.deleteCl(tmpC));
        }
    }

    private void modifyE() {
        int choix = Integer.parseInt(pv.getMessage(""
                + "1. Modification de l'enseignant"
                + "      2. Suppression de l'enseignant"));
        Enseignant eRech = pv.rechEnseignant();
        Enseignant tmpE = cm.getEnseignant(eRech);
        if (choix == 1) {

            pv.getMessage(" --- Modification de l'enseignant ---");

            pv.affichageMessage(tmpE);
            Enseignant nvEns = pv.newEnseignant();
            pv.affichageMessage(cm.modifyE(nvEns, tmpE));

        } else if (choix == 2) {
            pv.affichageMessage("--- Suppression de l'enseignant ---");
            pv.affichageMessage(tmpE);
            pv.affichageMessage(cm.deleteE(tmpE));
        }

    }

    private void affichage() {
        int choix = Integer.parseInt(pv.getMessage(""
                + "1. Affichage des enseignants"
                + "      2. Affichage des classes"));

        if (choix == 1) {

            List<Enseignant> le = cm.tousEns();
            pv.affichageMessage(le);

        } else if (choix == 2) {
            List<Classe> lc = cm.toutesClasses();
            pv.affichageMessage(lc);
        }

    }

    
}
