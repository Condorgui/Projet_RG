/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projet.Vue;

import Projet.Metier.Classe;
import Projet.Metier.Enseignant;
import Projet.Metier.Attribution;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collection;
import java.util.Scanner;

/**
 *
 * @author Guillaume.Rigaux
 */
public class Pvue {

    private final Scanner sc = new Scanner(System.in);

    /**
     * Menu principal
     *
     * @return le choix de la section du menu
     */
    public int menuPrincipal() {

        List<String> menu;
        menu = new ArrayList<>(Arrays.asList(
                " Gestion des Enseignants",
                " Gestion des Classes",
                " Gestion des attributions",
                " Recherche Enseignant",
                " Recherche d'une attribution",
                " Recherche d'une classe",
                " Affichage",
                "Quitter"));
        affichageListe(menu);

        int choix;
        do {
            String ch1 = getMessage("Choix ?");
            choix = Integer.parseInt(ch1);
            if (choix > 0 && choix <= menu.size()) {
                break;
            }

        } while (true);
        return choix;
    }

    /**
     * Méthode newClasse encode les informations d'une classe
     *
     * @return c = la classe créée sur base de l'encodage
     */
    public Classe newClasse() {
        Classe classe = null;
        String sigle = getMessage("Entrez le sigle : ");
        String orientation = getMessage("Quelle est l'orientation ? ");
        String annee = getMessage("Quelle est l'année de la classe : ");
        int annee2 = Integer.parseInt(annee);

        Classe.ClasseBuilder c = new Classe.ClasseBuilder();
        c.setSigle(sigle).setOrientation(orientation).setAnnee(annee2);
        try {
            classe = c.build();
        } catch (Exception e) {
            System.out.println("Erreur de création" + e);
        }
        return classe;
    }

    /**
     * Méthode newEnseignant encode les informations d'un enseignant
     *
     * @return e = l'enseignant crée sur base de l'encodage
     */
    public Enseignant newEnseignant() {

        boolean flag;
        do {

            String nom = getMessage("Entrez le nom : ");
            String prenom = getMessage("Entrez le prénom : ");
            String matricule = getMessage("Entrez son matricule : ");
            if (matricule.trim().equals("") || nom.trim().equals("") || prenom.trim().equals("")) {
                affichageMessage("Entrez les caractères correctement ");
                flag = true;
            } else {
                flag = false;
                Enseignant e = new Enseignant(matricule, nom, prenom);
                return e;
            }
        } while (flag);

        return null;

    }

    /**
     * Méthode création d'une attribution
     *
     * @param toutesLesClasses liste des classes pour associer l'attributions
     * @param tousEns liste des enseignants pour associer l'attributions
     * @param toutesLesAttributions liste des attributions Actualise le statut
     * titulaire/remplacant de l'enseignant
     * @return la nouvelle attribution
     */
    //Méthode attribution faite sur base et avec l'aide de Gaetan Soudan
    //La mienne ne prenait pas en compte le doublon de titulaires
    public Attribution newAttribution(List<Classe> toutesLesClasses, List<Enseignant> tousEns, List<Attribution> toutesLesAttributions) {

        affichageListe(toutesLesClasses);
        boolean flag;
        boolean drap = false;
        String choix = getMessage("Choisissez la classe : ");

        int chC = 0, chE = 0, chA = 0;
        do {
            int chx = Integer.parseInt(choix);
            if (chx > 0 && chx <= toutesLesClasses.size()) {
                chC = chx - 1;
            } else {
                drap = true;
            }
        } while (drap);

        affichageListe(tousEns);
        do {
            choix = getMessage("Choisissez l'enseignant : ");
            if (choix.trim().equals("")) {
                affichageMessage("Veuillez entrer un choix correct");
                flag = true;
            } else {
                flag = false;
            }
        } while (flag);
        do {
            int chx = Integer.parseInt(choix);
            if (chx > 0 && chx <= tousEns.size()) {
                chE = chx - 1;
            } else {
                affichageMessage("Veuillez entrer un choix correct");
                drap = true;
            }
        } while (drap);

        do {
            choix = getMessage(" --- 1 pour les titulaires "
                    + "\n --- 2 pour les remplaçants");
            if (choix.trim().equals("")) {
                affichageMessage("Veuillez entrer un choix correct");
                flag = true;
            } else {
                flag = false;
            }
        } while (flag);

        try {
            int chx = Integer.parseInt(choix);
            if (chx >= 1 && chx <= 2) {
                chA = chx;
            } else {
                drap = true;
            }
        } catch (NumberFormatException e) {
            affichageMessage(e + "Veuillez entrer un choix correct");
            drap = true;
        }
        if (drap) {

            return newAttribution(toutesLesClasses, tousEns, toutesLesAttributions);

        } else {

            Classe c = toutesLesClasses.get(chC);
            Enseignant e = tousEns.get(chE);

            if ((e.getTitulaire() != null || e.getRemplacant() != null)) {
                affichageMessage("Cet enseignant est déjà titulaire ou remplacant");
                return null;
            } else {
                if (chA == 1) {
                    for (Attribution a : toutesLesAttributions) {
                        Enseignant eAtt = a.getEnseignant();
                        if (eAtt.getTitulaire() == c) {
                            affichageMessage("Il y a déjà un titulaire attitré");
                            return null;
                        }
                        if (eAtt == e && !eAtt.getMatricule().equals(e.
                                getMatricule())) {
                            return null;
                        }
                    }
                    e.setTitulaire(c);
                } else if (chA == 2) {
                    for (Attribution a : toutesLesAttributions) {
                        Enseignant eAtt = a.getEnseignant();
                        if (eAtt == e && !eAtt.getMatricule().equals(e.getMatricule())) {
                            return null;
                        }
                    }
                    e.setRemplacant(c);
                }
            }
            Attribution a = new Attribution(c, e);

            return a;
        }
    }

    /**
     * Méthode affClasses Affiche les informations d'une classe == toString
     * Classe
     *
     * @param c = la classe à afficher
     */
    public void affClasses(Classe c) {

        affichageMessage("Classe : " + c.getSigle());
        affichageMessage("C'est une classe de " + c.getAnnee() + " ère/ème  année");
        //préciser classe 
        affichageMessage("Classe d'orientation : " + c.getOrientation());

        affichageMessage("L'enseignant de cette classe :" + c.getE());

    }

    /**
     * Méthode affEnseignant Affiche les informations d'un enseignant ==
     * toString Enseignant
     *
     * @param e = l'enseignant à afficher
     */
    public void affEnseignant(Enseignant e) {
        affichageMessage("Nom de l'enseignant :" + e.getNom());
        affichageMessage("Prénom de l'enseignant :" + e.getPrenom());
        affichageMessage("Matricule  :" + e.getMatricule());

    }

    /**
     * Méthode getMessage permet à l'utilisateur d'encoder le message
     *
     * @return le message entré
     */
    public String getMessage() {
        String msg = sc.nextLine();
        return msg;
    }

    /**
     * Méthode afficheMessage permet d'afficher le message reçu en paramètre
     *
     * @param msg == message à afficher
     */
    public void affichageMessage(Object msg) {
        System.out.println(msg);
    }

    /**
     * Surcharge de getMessage Permet d'afficher un message et d'en encoder un
     *
     * @param msg le message à afficher
     * @return la méthode getMessage pour l'encodage
     */
    public String getMessage(String msg) {
        affichageMessage(msg);
        return getMessage();
    }

    /**
     * Méthode affichageListe Permet d'afficher une liste
     *
     * @param liste la liste à afficher
     */
    public void affichageListe(Collection liste) {
        int i = 1;
        for (Object o : liste) {
            affichageMessage((i++) + "." + o);
        }
    }

    /**
     * Méthode rechClass Permet de rechercher une classe sur base de son sigle
     *
     * @return cRech == la classe trouvée
     */
    public Classe rechClasse() {

        Classe cRech = null;
        String sigle = getMessage("Quel est le sigle à rechercher ? ");
        Classe.ClasseBuilder c = new Classe.ClasseBuilder();
        c.setSigle(sigle);
        try {
            cRech = c.build();
            return cRech;
        } catch (Exception e) {
            System.out.println("Erreur de création" + e);
        }

        return null;
    }

    /**
     * Méthode rechEnseignant Permet de retrouver un enseignant sur base de son
     * matricule
     *
     * @return eRech == l'enseignant trouvé
     */
    public Enseignant rechEnseignant() {
        boolean flag;
        do {
            String matricule = getMessage("Matricule de l'enseignant à rechercher :");
            if (matricule.trim().equals("")) {
                affichageMessage("Entrez les caractères correctement ");
                flag = true;
            } else {
                flag = false;
                Enseignant eRech = new Enseignant(matricule);
                return eRech;
            }
        } while (flag);

        return null;

    }

    /**
     * Méthode rechAttribution Recherche l'attribution sur base du matricule et
     * du sigle de l'enseignant
     *
     * @return aRech == l'attribution trouvée
     */
    public Attribution rechAttribution() {

        Classe cRech = null;
        Enseignant ens = null;
        boolean flag;
        do {
            String matricule = getMessage("Recherchez le matricule : ");
            if (matricule.trim().equals("")) {
                affichageMessage("Entrez les caractères correctement");
                flag = true;
            } else {
                flag = false;
                ens = new Enseignant(matricule);
            }

        } while (flag);
        String sigle = getMessage("Recherchez le sigle : ");
        Classe.ClasseBuilder c = new Classe.ClasseBuilder();
        c.setSigle(sigle);
        try {
            cRech = c.build();
        } catch (Exception e) {
            System.out.println("Erreur de création" + e);
        }

        Attribution aRech = new Attribution(cRech, ens);
        return aRech;
    }

}
