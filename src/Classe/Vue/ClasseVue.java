package Classe.Vue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import Classe.Modele.Classe;
import Classe.Modele.Enseignant;

public class ClasseVue {

    private Scanner sc = new Scanner(System.in);

    public int menuPrincipal() {

        List<String> menu = new ArrayList<>(Arrays.asList(
                "____1. Ajout Enseignant",
                "____2. Ajout Classe",
                "____3. Affichage Classes",
                "____4. Affichage Enseignants",
                "____5. Modifier Enseignant",
                "____6. Modifier Classe",
                "____7. Recherche Enseignant",
                "____8. Recherche d'une classe",
                "9. Quitter"));
        affichageListe(menu);

        int choix;
        do {
            String ch1 = getMsg("Choix ?");
            choix = Integer.parseInt(ch1);
            if (choix > 0 && choix <= menu.size()) {
                break;
            }
            else{
               affichageMessage("Recommencez"); 
            }
            
        } while (true);
        return choix;
    }

    public Classe newClasse() {
        String sigle = getMsg("Entrez le sigle : ");
        String orientation = getMsg("Quelle est l'orientation ? ");
        String annee = getMsg("Quelle est l'année de la classe : ");
        int annee2 = Integer.parseInt(annee);

        Classe c = new Classe(sigle, annee2, orientation);

        return c;
    }

    public Enseignant newEnseignant() {

        String nom = getMsg("Entrez le nom : ");
        String prenom = getMsg("Entrez le prénom : ");
        String matricule = getMsg("Entrez son matricule : ");
        Enseignant e = new Enseignant(matricule, nom, prenom);

        return e;
    }

    public void affClasses(Classe c) {
        
        affichageMessage("Classe : "+c.getSigle());
        affichageMessage("C'est une classe de "+c.getAnnee()+" ère/ème  année");
        //préciser classe 
        affichageMessage("Classe d'orientation : "+c.getOrientation());
        
        affichageMessage("L'enseignant de cette classe :" + c.getE());
        
      
    }
    
 
    public void affEnseignant(Enseignant e) {
        affichageMessage("Nom de l'enseignant :" + e.getNom());
        affichageMessage("Prénom de l'enseignant :" + e.getPrenom());
        affichageMessage("Matricule  :" + e.getMatricule());
        
    }

    public String getMsg() {
        String msg = sc.nextLine();
        return msg;
    }

    public void affichageMessage(Object msg) {
        System.out.println(msg);
    }

    public String getMsg(String msg) {
        affichageMessage(msg);
        return getMsg();
    }

    public void affichageListe(Collection liste) {
        int i = 1;
        for (Object o : liste) {
            affichageMessage((i++) + "." + o);
        }
    }

}
