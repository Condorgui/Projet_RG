/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projet.Controleur;

import Projet.Metier.Attribution;
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

    /**
     * Variable cm de type ClasseModele
     */
    private ClasseModele cm;

    /**
     * Variable pv de type Pvue
     */
    private Pvue pv;

    /**
     * Constructeur par défaut
     */
    public ClasseControleur() {

    }

    /**
     * Constructeur paramétré
     *
     * @param cm le modèle
     * @param pv la vue
     */
    public ClasseControleur(ClasseModele cm, Pvue pv) {

        this.cm = cm;
        this.pv = pv;
    }

    /**
     * Méthode gestion
     *
     */
    public void gestion() {

        int ch = 0;
        do {
            try {

                ch = pv.menuPrincipal();

                switch (ch) {
                    case 1:
                        gestionEnseignants();
                        //  ajoutEnseignant();
                        break;
                    case 2:
                        gestionClasses();
                        break;
                    case 3:
                        gestionAttribution();
                        break;
                    case 4:
                        rechEnseignant();
                        break;
                    case 5:
                        rechAttribution();
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

            } catch (Exception e) {
                // pv.affichageMessage(e);
                pv.affichageMessage(e + "Entrez un nombre valide !");
            }

        } while (ch != 8);
    }

    /**
     * Méthode ajoutClasse Permet l'ajout d'une classe Appelle le formulaire de
     * création d'une classe de la vue Appelle le formulaire d'ajout d'une
     * classe du modèle
     *
     */
    private void ajoutClasse() {
        Classe cl = pv.newClasse();
        String msg = cm.ajouterClasse(cl);
        //ajouterClasse retourne un message 
        pv.affichageMessage(msg);
        //affiche le message retourné

    }

    /**
     * Méthode ajoutEnseignant Permet l'ajout d'un enseignant Appelle le
     * formulaire de création de la vue Appelle le formulaire d'ajout du
     * controleur
     */
    private void ajoutEnseignant() {
        Enseignant es = pv.newEnseignant();
        String msg = cm.ajouterEnseignant(es);
        pv.affichageMessage(msg);

    }

    /**
     * Méthode gestionAttribution Permet de gérer les attribution ajout d'une
     * attribution modification d'une attribution suppression d'une attribution
     */
    private void gestionAttribution() {

        try {

            List<Enseignant> le = cm.tousEns();
            List<Classe> lc = cm.toutesClasses();
            List<Attribution> la = cm.toutesLesAttributions();

            int choix = Integer.parseInt(pv.getMessage(""
                    + "1. Ajouter une attribution"
                    + "      2. Modifier une attribution "
                    + "             3. Supprimer une attribution"));

            if (choix == 1) {
                pv.affichageMessage(" --- Ajout d'une attribution ---");
                Attribution a = pv.newAttribution(cm.toutesClasses(), cm.tousEns(), cm.toutesLesAttributions());
                String msg = cm.ajouterAttribution(a);
                pv.affichageMessage(msg);

            }
            if (choix == 2) {
                pv.affichageMessage(" --- Modification de l'attribution ---");
                pv.affichageMessage(le);
                pv.affichageMessage(lc);
                Attribution aRech = pv.rechAttribution();
                Attribution att = cm.getAttribution(aRech);
                //   pv.affichageMessage(aRech);
                Attribution nvAtt = pv.newAttribution(cm.toutesClasses(), cm.tousEns(), cm.toutesLesAttributions());
                pv.affichageMessage(cm.modifyA(nvAtt, att));

            }
            if (choix == 3) {
                pv.affichageMessage(" --- Suppression de l'attribution ---");
                pv.affichageMessage(le);
                pv.affichageMessage(lc);
                pv.affichageMessage("------ Liste des attributions ----");
                pv.affichageMessage(la); 
                Attribution aRech = pv.rechAttribution();
                Attribution att = cm.getAttribution(aRech);
                pv.affichageMessage(cm.getAttribution(att));
                // pv.affichageMessage(a);
                pv.affichageMessage(cm.deleteA(att));
            }
        } catch (NumberFormatException e) {
            pv.affichageMessage(e.getMessage()+"Entrez un nombre");
        }
    }

    private void rechAttribution() {
        Attribution a = pv.rechAttribution();
        pv.affichageMessage(cm.getAttribution(a));
    }

    /**
     * Méthode rechClasse Appelle le formulaire de recherche d'une classe de la
     * vue Affiche le résultat de la recherche
     */
    private void rechClasse() {
        Classe cl = pv.rechClasse();
        pv.affichageMessage(cm.getClasse(cl));
    }

    /**
     * Méthode rechEnseignant Appelle le formulaire de recherche d'un enseignant
     * de la vue Affiche le résultat de la recherche
     */
    private void rechEnseignant() {
        Enseignant es = pv.rechEnseignant();
        pv.affichageMessage(cm.getEnseignant(es));
    }

    /**
     * Méthode affichage Affichage les enseignant ou les classes récupère les
     * enseignants dans une liste et affiche son contenu récupère les classes
     * dans une liste et affiche son contenu
     *
     */
    private void affichage() {

        List<Enseignant> le = cm.tousEns();
        List<Classe> lc = cm.toutesClasses();
        List<Attribution> la = cm.toutesLesAttributions();

        try {
            int choix = Integer.parseInt(pv.getMessage(""
                    + "1. Affichage des enseignants"
                    + "      2. Affichage des classes"
                    + "             3. Affichage des attributions"));

            if (choix == 1) {

                pv.affichageMessage(le);

            }
            if (choix == 2) {

                pv.affichageMessage(lc);
            }
            if (choix == 3) {

                pv.affichageMessage(la);
            }
        } catch (NumberFormatException e) {
            String message = e.getMessage();
            pv.affichageMessage(message+"Entrez un nombre");
        }

    }

    /**
     * Méthode gestionEnseignant Gère les ajouts, modifications ou suppression
     * des enseignants
     */
    private void gestionEnseignants() {
        List<Enseignant> le = cm.tousEns();
        
        
        int choix = Integer.parseInt(pv.getMessage(""
                + "1. Ajout de l'enseignant"
                + "      2. Modification de l'enseignant"
                + "           3. Suppression de l'enseignant"));

        switch (choix) {
            case 1:
                pv.affichageMessage(" --- Ajout de l'enseignant ---");
                ajoutEnseignant();
                break;

            case 2:
                Enseignant eRech = pv.rechEnseignant();
                Enseignant tmpE = cm.getEnseignant(eRech);
                pv.affichageMessage(le);
                pv.affichageMessage(" --- Modification de l'enseignant ---");
                pv.affichageMessage(tmpE);
                Enseignant nvEns = pv.newEnseignant();
                pv.affichageMessage(cm.modifyE(nvEns, tmpE));
                break;

            case 3:
                eRech = pv.rechEnseignant();
                tmpE = cm.getEnseignant(eRech);
                pv.affichageMessage(le);
                pv.affichageMessage("--- Suppression de l'enseignant ---");
                pv.affichageMessage(tmpE);
                pv.affichageMessage(cm.deleteE(tmpE));
                break;

            default:
                pv.affichageMessage("Entrez un choix valide");
                break;
        }

    }

    /**
     * Méthode gestionClasse Gère les ajouts, modifications ou suppression des
     * classes
     */
    private void gestionClasses() {

        List<Classe> lc = cm.toutesClasses();
        int choix = Integer.parseInt(pv.getMessage(""
                + "1. Ajout de la classe"
                + "       2. Modification de la classe"
                + "               3. Suppression de la classe"
        ));

        switch (choix) {
            case 1:
                ajoutClasse();
                break;
            case 2:
                Classe cRech = pv.rechClasse();
                Classe tmpC = cm.getClasse(cRech);
                pv.affichageMessage(lc);
                pv.affichageMessage("Modification de la classe");
                pv.affichageMessage(tmpC);
                Classe nvClasse = pv.newClasse();
                pv.affichageMessage(cm.modifyC(nvClasse, tmpC));
                break;
            case 3:
                cRech = pv.rechClasse();
                tmpC = cm.getClasse(cRech);
                pv.affichageMessage(lc);
                pv.affichageMessage("--- Suppression de la classe --- ");
                pv.affichageMessage(tmpC);
                pv.affichageMessage(cm.deleteCl(tmpC));
                break;
            default:
                pv.affichageMessage("Entrez un choix valide ");
                break;
        }
    }

}
