package Projet.Metier;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**

 */
import java.util.*;

/**
 *
 * @author guill
 */
public abstract class Sujet {
    
    /**
     *
     */
    protected ArrayList<Observateur> observateurs = new ArrayList<Observateur>();
    
    /**
     * ajoute aux observateurs
     * @param o l'observateur à ajouter
     */
    public void ajoute(Observateur o){
        observateurs.add(o);
    }
    
    /**
     * retire de la liste observateur
     * @param o l'observateur à retirer
     */
    public void retire(Observateur o){
        observateurs.remove(o);
    }
    
    /**
     * Notification à envoyer
     * @param texte le texte à envoyer aux obs
     */
    public void notifie(String texte){
        observateurs.forEach((o) -> {
            o.actualise(texte);
        });
    }
}
