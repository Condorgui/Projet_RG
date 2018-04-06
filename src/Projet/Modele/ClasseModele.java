/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projet.Modele;

import Projet.Metier.Enseignant;
import Projet.Metier.Classe;
import java.util.*;

/**
 *
 * @author Guillaume.Rigaux
 */
public class ClasseModele {

    /**
     * liste de tous les clients
     */
    protected List<Enseignant> tousLesEns = new ArrayList<>();
    /**
     * liste de toutes les voitures
     */
    protected List<Classe> toutesLesClasses = new ArrayList<>();

    /**
     * constructeur par défaut
     */
    public ClasseModele() {

    }

    public String ajouterClasse(Classe c) {
        if (c == null) {
            return "Classe inexistante";
        }
        toutesLesClasses.add(c);
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

    public Classe getClasse(String sigle) {
        Classe rech = new Classe(sigle);
        int p = toutesLesClasses.indexOf(rech);
        if (p < 0) {
            return null;
        } else {
            return toutesLesClasses.get(p);
        }
    }

    public Enseignant getEnseignant(Enseignant aRech) {
        int p = tousLesEns.indexOf(aRech);
        if (p < 0) {
            return null;
        } else {
            return tousLesEns.get(p);
        }
    }

    /**
     * méthode permettant de retrouver tous les clients
     *
     * @return tous les clients de la liste
     */
    public List<Enseignant> tousEns() {
        return tousLesEns;

    }

    /*
     * méthode permettant de trouver toutes les voitures d'un client
     *
     * @param c client propriétaire
     * @return toutes les voitures du client
     
    public List<Enseignant> getVoituresClient(Client c) {
        List<Voiture> listev = new ArrayList<>();
        if (c == null) {
            return listev;
        }
        if (mesVoitures.isEmpty()) {
            return listev;
        }

        for (Voiture v : mesVoitures) {
            if (!v.estVendue()) {
                continue;
            }
            Client ca = v.getAcheteur();
            if (ca.equals(c)) {
                listev.add(v);
            }
        }
        if (listev.isEmpty()) {
            return null;
        }
        return listev;
    }

    
     * méthode retournant l'acheteur d'une voiture
     *
     * @param v voiture achetée
     * @return propriétaire ou null si encore en vente
     
    public Client getAcheteur(Voiture v) {
        if (!v.estVendue()) {
            return null;
        }
        return v.getAcheteur();
    }

    /**
     * méthode retournant toutes les voitures triées
     *
     * @param mode mode de tri (1 ou 2)
     * @return liste des voitures triées
     
    public List<Voiture> toutesVoitures(int mode) {
        switch (mode) {
            case 1:
                mesVoitures.sort(new ChassisComparator());
                //Collections.sort(mesVoitures,new ChassisComparator());
                break;
            case 2:
                mesVoitures.sort(new PrixComparator());
                break;
            default:
                return null;

        }
        return mesVoitures;

    }

    /**
     * méthode permettant de changer l'adresse d'un client
     *
     * @param cl client dont on désire changer l'adresse
     * @param adr nouvelle adresse
     * @return diagnostic du changement
     
    public String changeAdresse(Client cl, String adr) {
        cl.setAdresse(adr);
        return "changement d'adresse effectué";
    }

    /**
     * méthode permettant de vendre une voiture à un client
     *
     * @param v voiture à vendre
     * @param cl acheteur
     * @return diagnostic de la vente
     
    public String vendre(Voiture v, Client cl) {
        String msg = v.vendre(cl);
        return msg;
    }

    /**
     * méthode permettant de supprimer une voiture
     *
     * @param v voiture à supprimer
     * @return diagnostic de la suppression
     
    public String suppVoiture(Voiture v) {
        boolean ok = mesVoitures.remove(v);
        if (ok) {
            return "voiture supprimée";
        } else {
            return "voiture introuvable ou suppression impossible";
        }
    }

    /**
     * méthode permettant de supprimer un client
     *
     * @param cl client à supprimer
     * @return diagnostic de la suppression
     
    public String suppClient(Client cl) {
        List<Voiture> lv = getVoituresClient(cl);
        if (!lv.isEmpty()) {
            return "suppression impossible car le client possède des voitures,supprimez d'abord ces voitures";
        }
        boolean ok = mesClients.remove(cl);
        if (!ok) {
            return "client introuvable ou suppression impossible";
        }
        return "client supprimé";
    }

    /**
     * méthode permettant d'alimenter artificiellement les listes de base
     
    public void populate() {
        mesVoitures.addAll(Arrays.asList(
                new Voiture("AXR345", "Citroën", "c3", 2010, 105000, 3500),
                new Voiture("BZR443", "VW", "Passat", 2008, 205000, 5000),
                new Voiture("XYZ322", "Kia", "Rio", 2012, 80000, 4000))
        );
        mesClients.addAll(Arrays.asList(
                new Client("Lenoir", "Eric", "0456778899", "Mons"),
                new Client("Levert", "Aline", "0478223344", "BXL"),
                new Client("Lerouge", "Carine", "0498662277", "La Louvière"))
        );
        mesVoitures.get(1).vendre(mesClients.get(2));
*/
    }

