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
             //       modifyE();
                    break;

                case 4:
              //      modifyC();
                    break;
                case 5:
                    pv.affichageMessage("Matricule à rechercher : ");
              //      rechEnseignant();
                    break;
                case 6:
                    pv.affichageMessage("Sigle à rechercher : ");
               //     rechClasse();
                    break;
                /* case 7:
                    gestionAttribution(); 
                    break;
                 */
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
    /*
    public void vendreVehicule() {
        Voiture oRech = rechVoiture();

        if (oRech != null) {
            gv.affMsg("la voiture trouvée est :");
            gv.affVoiture(oRech);
        } else {
            gv.affMsg("voiture introuvable");
            return;
        }
        if (oRech.estVendue()) {
            gv.affMsg("voiture déjà vendue");
            return;
        }
        Client cliRech = rechClient();
        if (cliRech == null) {
            gv.affMsg("client introuvable");
            return;
        }
        gv.affMsg("le client trouvé est :");
        gv.affClient(cliRech);
        String msg = gm.vendre(oRech, cliRech);
        gv.affMsg(msg);
    }

    public void changementAdresse() {

        Client cliRech = rechClient();
        if (cliRech == null) {
            gv.affMsg("client introuvable");
            return;
        }
        gv.affClient(cliRech);
        String nvadr = gv.getMsg("nouvelle adresse :");
        String msg = gm.changeAdresse(cliRech, nvadr);
        gv.affMsg(msg);
    }

    public Client rechClient() {
        Client cRech = gv.formRechClient();
        return gm.getClient(cRech);
    }

    public Voiture rechVoiture() {
        String numChassis = gv.formRechVoiture();
        return gm.getVoiture(numChassis);
    }

    public void proprietaire() {
        Voiture vRech = rechVoiture();
        if (vRech == null) {
            gv.affMsg("voiture introuvable");
            return;
        }
        Client cRech = gm.getAcheteur(vRech);
        if (cRech == null) {
            gv.affMsg("voiture encore en vente");
            return;
        }
        gv.affClient(cRech);
    }

    public void proprietes() {
        Client cl = rechClient();
        if (cl == null) {
            gv.affMsg("client introuvable ");
            return;
        }
        List<Voiture> lv = gm.getVoituresClient(cl);
        gv.affListe(lv);

    }

    public void listeVoitures() {
        String chs = gv.getMsg("1.tri par chassis 2.tri par prix ");
        int ch = Integer.parseInt(chs);
        List<Voiture> lv = gm.toutesVoitures(ch);
        if (lv == null) {
            gv.affMsg("mode incorrect");
            return;
        }
        gv.affListe(lv);
    }

    public void listeClients() {
        List<Client> lc = gm.tousClients();
        gv.affListe(lc);
    }

}
     */
}
