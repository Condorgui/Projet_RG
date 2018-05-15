/*
-----NOTES ----
13/05/18 
Les gestions des attributions pour le modèle et le modèleJDBC ont été 
faites avec l'aide de Gaetan Soudant. 
 */
package Projet.Modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import myconnections.DBConnection;
import Projet.Metier.*;
import java.sql.CallableStatement;
import java.sql.*;

/**
 *
 * @author guill
 */
public class ClasseModeleJDBC extends ClasseModele {

    Connection dbconnect;
//Connexion à la BDD

    private static ClasseModeleJDBC cm = null;

    /**
     * Constructeur du JDBC Se charge de la connexion à la BDD
     */
    public ClasseModeleJDBC() {
        dbconnect = DBConnection.getConnection();
        if (dbconnect == null) {
            System.err.println("erreur de connexion => arrêt du pgm");
            //err = canal d'affichage des erreurs, affichera en rouge
            System.exit(1);
        } else {
            System.out.println("Connexion établie");
        }
    }

    public static ClasseModeleJDBC getInstance() {
        if (cm == null) {
            return cm = new ClasseModeleJDBC();
        } else {
            return cm;
        }
    }

    /**
     * Fermeture de la session
     */
    public void close() {
        try {
            dbconnect.close();
        } catch (SQLException e) {
            System.err.println("erreur lors de la fermeture de la connexion " + e);
        }
    }

    @Override
    public void populate() {
        //ne rien faire car données déjà présentes dans DB
    }

    /**
     * Méthode affichage de la liste des enseignants
     *
     * @return la liste
     */
    @Override
    public List<Enseignant> tousEns() {
        //mode = soit tri par numéro chassis soit par prix soit par prenom

        String query = "select * from ENSEIGNANT order by MATRICULE";
        List<Enseignant> le = new ArrayList<>();
        try (Statement stm = dbconnect.createStatement(); ResultSet rs = stm.executeQuery(query)) {

            while (rs.next()) {
                String matricule = rs.getString(1);
                String titulaire = rs.getString(2);
                String remplacant = rs.getString(3);
                String nom = rs.getString(4);
                String prenom = rs.getString(5);
                String mail = rs.getString(6);
                Enseignant e = new Enseignant(matricule, nom, prenom, mail);

                if (titulaire != null || remplacant != null) {
                    if (titulaire != null) {
                        Classe c = getClasse(new Classe(titulaire));
                        e.setTitulaire(c);
                    } else {
                        Classe c = getClasse(new Classe(remplacant));
                        e.setRemplacant(c);
                    }

                }

                le.add(e);

            }
        } catch (SQLException ex) {
            System.err.println("erreur lors de la recherche de l'enseignant " + ex);
        }

        return le;
    }

    @Override
    public List<Attribution> toutesLesAttributions() {
        //mode = soit tri par numéro chassis soit par prix soit par prenom

        String query = "select * from ATTRIBUTION";
        List<Attribution> la = new ArrayList<>();

        try (PreparedStatement stm = dbconnect.prepareStatement(query);
                ResultSet rs = stm.executeQuery();) {

            while (rs.next()) {
                String matricule = rs.getString(1);
                String sigle = rs.getString(2);

                Enseignant ens = getEnseignant(new Enseignant(matricule));
                Classe classe = getClasse(new Classe(sigle));
                Attribution a = new Attribution(classe, ens);

                la.add(a);

            }
        } catch (SQLException ex) {
            System.err.println("erreur lors de la recherche de l'enseignant " + ex);
        }
        return la;
    }

    /**
     * Méthode affichage de la liste des classes
     *
     * @return la liste
     */
    @Override
    public List<Classe> toutesClasses() {

        String query = "select * from CLASSE order by SIGLE";
        List<Classe> lc = new ArrayList<>();

        try (Statement stm = dbconnect.createStatement();
                ResultSet rs = stm.executeQuery(query);) {

            while (rs.next()) {
                String sigle = rs.getString(1);
                int annee = rs.getInt(2);
                String orientation = rs.getString(3);
                Classe classe = null;

                Classe.ClasseBuilder c = new Classe.ClasseBuilder();
                c.setSigle(sigle).setOrientation(orientation).setAnnee(annee);
                try {
                    classe = c.build();

                } catch (Exception e) {
                    System.out.println("Erreur de création" + e);
                }

                lc.add(classe);
            }
        } catch (SQLException e) {
            System.err.println("erreur lors de la recherche de la classe " + e);
        }
        return lc;
    }

    /**
     * Méthode qui recherche un enseignant
     *
     * @param aRech l'enseignant à rechercher
     * @return l'enseignant trouvé sur base du matricule
     */
    @Override
    public Enseignant getEnseignant(Enseignant aRech) {

        String query = "SELECT * FROM ENSEIGNANT WHERE MATRICULE = ?";
        ResultSet rs = null;
        try (PreparedStatement pstm = dbconnect.prepareStatement(query)) {

            pstm.setString(1, aRech.getMatricule());
            rs = pstm.executeQuery();
            if (rs.next()) {
                String matricule = rs.getString(1);
                String titulaire = rs.getString(2);
                String remplacant = rs.getString(3);
                String nom = rs.getString(4);
                String prenom = rs.getString(5);
                String mail = rs.getString(6);

                Enseignant e = new Enseignant(matricule, nom, prenom, mail);

                if (titulaire != null || remplacant != null) {
                    if (titulaire != null) {
                        Classe c = getClasse(new Classe(titulaire));
                        e.setTitulaire(c);
                    } else {
                        Classe c = getClasse(new Classe(remplacant));
                        e.setRemplacant(c);
                    }

                }
                //voir pour faire une méthode
                return e;

            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("erreur de recherche de l'enseignant " + e);
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.err.println("erreur de fermeture de resultset " + e);
            }

        }
    }

    /**
     * Méthode qui recherche une classe
     *
     * @param aRech la classe à rechercher
     * @return la classe trouvée sur base du sigle
     */
    @Override
    public Classe getClasse(Classe aRech) {
        //Faire switch pour recherche sur 2 critères
        Classe classe = null;
        String query = "SELECT * FROM CLASSE WHERE SIGLE = ?";

        ResultSet rs = null;
        try (PreparedStatement pstm = dbconnect.prepareStatement(query);) {

            pstm.setString(1, aRech.getSigle());
            rs = pstm.executeQuery();
            if (rs.next()) {
                String sigle = rs.getString(1);
                int annee = rs.getInt(2);
                String orientation = rs.getString(3);
                Classe.ClasseBuilder c = new Classe.ClasseBuilder();
                c.setSigle(sigle).setOrientation(orientation).setAnnee(annee);
                try {
                    classe = c.build();
                } catch (Exception e) {
                    System.out.println("Erreur de création" + e);
                }
                return classe;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("erreur de recherche de la classe " + e);
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.err.println("erreur de fermeture de resultset " + e);
            }

        }

    }

    @Override
    public Attribution getAttribution(Attribution aRech) {
        //Faire switch pour recherche sur 2 critères
        if (aRech != null) {
            String query = "SELECT * FROM ATTRIBUTION WHERE MATRICULE = ? AND SIGLE = ?";

            ResultSet rs = null;

            try (PreparedStatement pstm = dbconnect.prepareStatement(query)) {

                pstm.setString(1, aRech.getEnseignant().getMatricule());
                pstm.setString(2, aRech.getClasse().getSigle());

                rs = pstm.executeQuery();
                if (rs.next()) {
                    String matricule = rs.getString(1);
                    String sigle = rs.getString(2);

                    Enseignant ens = getEnseignant(new Enseignant(matricule));
                    Classe c = getClasse(new Classe(sigle));
                    Attribution a = new Attribution(c, ens);

                    return a;

                } else {
                    return null;
                }

            } catch (SQLException e) {
                System.err.println("erreur de recherche de l'attribution " + e);

            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                } catch (SQLException e) {
                    System.err.println("erreur de fermeture de resultset " + e);
                }

            }
        }
        return null;
    }

    @Override
    public String ajouterAttribution(Attribution a) {

        String query = "insert into ATTRIBUTION(MATRICULE,SIGLE) values(?,?)";
        if (a != null) {
            try (PreparedStatement pstm = dbconnect.prepareStatement(query)) {
                pstm.setString(1, a.getEnseignant().getMatricule());
                pstm.setString(2, a.getClasse().getSigle());
                pstm.executeUpdate();
                if (a.getEnseignant().getTitulaire() != null) {
                    PreparedStatement pst = dbconnect.prepareStatement("UPDATE ENSEIGNANT SET TITULAIRE = ? WHERE MATRICULE = ?");
                    pst.setString(1, a.getClasse().getSigle());
                    pst.setString(2, a.getEnseignant().getMatricule());
                    pst.executeUpdate();

                } else if (a.getEnseignant().getRemplacant() != null) {
                    PreparedStatement pst = dbconnect.prepareStatement("UPDATE ENSEIGNANT SET REMPLACANT = ? WHERE MATRICULE = ?");
                    pst.setString(1, a.getClasse().getSigle());
                    pst.setString(2, a.getEnseignant().getMatricule());
                    pst.executeUpdate();
                }
                return "Attribution ajoutée";
            } catch (SQLIntegrityConstraintViolationException Pk) {
                return "Erreur de PK " + Pk;
            } catch (SQLException e) {
                System.err.println("Erreur d'ajout de l'attribution " + e);
            }
        }
        return "Attribution null";
    }

    /**
     * Méthode qui ajoute une classe
     *
     * @param c la classe à ajouter
     * @return msg le résultat de l'ajout
     */
    @Override
    public String ajouterClasse(Classe c) {
        String msg;
        String query = "insert into CLASSE(sigle,annee,orientation) values(?,?,?)";

        try (PreparedStatement pstm = dbconnect.prepareStatement(query);) {

            pstm.setString(1, c.getSigle());
            pstm.setInt(2, c.getAnnee());
            pstm.setString(3, c.getOrientation());
            int n = pstm.executeUpdate();
            if (n == 1) {
                msg = "Ajout de la classe effectué";
            } else {
                msg = "Classe non ajoutée ";
            }
        } catch (SQLIntegrityConstraintViolationException pk) {
            return "Erreur de PK (" + pk + ")";
        } catch (SQLException ec) {
            return "Erreur d'ajout de la classe " + ec;
        }
        return msg;
    }

    /**
     * Méthode qui ajoute un enseignant
     *
     * @param e l'enseignant à ajouter
     * @return msg le résultat de l'ajout
     */
    @Override
    public String ajouterEnseignant(Enseignant e) {
        String msg;
        String query = "insert into ENSEIGNANT(matricule,nom,prenom,mail) values(?,?,?,?) ";
        try (PreparedStatement pstm = dbconnect.prepareStatement(query)) {

            pstm.setString(1, e.getMatricule());
            pstm.setString(2, e.getNom());
            pstm.setString(3, e.getPrenom());
            pstm.setString(4, e.getMail());
            int n = pstm.executeUpdate();
            if (n == 1) {
                msg = "Ajout de l'enseignant effectué";
            } else {
                msg = "Enseignant non ajouté ";
            }
        } catch (SQLIntegrityConstraintViolationException pk) {
            return "Erreur de PK (" + pk + ")";
        } catch (SQLException ec) {
            return "Erreur d'ajout de l'enseignant" + ec;
        }

        return msg;
    }

    /**
     * Méthode deleteE supprime un enseignant sur base de son matricule
     *
     * @param ens
     * @return
     */
    @Override
    public String deleteE(Enseignant ens) {
        String query = "DELETE FROM ENSEIGNANT WHERE MATRICULE = ? ";

        String msg;
        try (PreparedStatement pstm = dbconnect.prepareStatement(query)) {

            pstm.setString(1, ens.getMatricule());
            int n = pstm.executeUpdate();
            if (n == 1) {
                msg = "Suppression effectuée ";
            } else {
                msg = "Suppression non effectuée";
            }

        } catch (SQLException e) {
            msg = "erreur lors de la suppression " + e;
        }
        return msg;
    }

    @Override
    public String deleteCl(Classe cl) {
        String query = "DELETE FROM CLASSE WHERE SIGLE = ? ";

        String msg;
        try (PreparedStatement pstm = dbconnect.prepareStatement(query)) {

            pstm.setString(1, cl.getSigle());
            int n = pstm.executeUpdate();
            if (n == 1) {
                msg = "Suppression effectuée ";
            } else {
                msg = "Suppression non effectuée";
            }

        } catch (SQLException e) {
            msg = "erreur lors de la suppression " + e;
        }
        return msg;
    }

    @Override
    public String modifyC(Classe nvClasse, Classe tmpC) {
        boolean flag;
        //nvClasse = la nouvelle classe 
        //tmpC = l'ancienne classe à modifier 
        String query = "update classe set SIGLE = ?, ANNEE = ? , ORIENTATION = ? where SIGLE = ?";
        ResultSet rs = null;
        String msg;
        int annee = nvClasse.getAnnee();
        String sigle = nvClasse.getSigle();
        String orientation = nvClasse.getOrientation();

        try (PreparedStatement pstm = dbconnect.prepareStatement(query)) {

            pstm.setString(1, sigle);
            pstm.setInt(2, annee);
            pstm.setString(3, orientation);
            pstm.setString(4, tmpC.getSigle());
            int n = pstm.executeUpdate();
            if (n == 1) {
                msg = "changement de classe effectué";
                flag = false;
            } else {
                flag = true;
                msg = "changement de classe non effectué";
            }

        } catch (SQLIntegrityConstraintViolationException pk) {
            return "Erreur de PK (" + pk + ")";
        } catch (SQLException e) {
            msg = "erreur  " + e;
        }
        return msg;

    }

    @Override
    public String modifyE(Enseignant nvEns, Enseignant tmpE) {

        //nvClasse = la nouvelle classe 
        //tmpC = l'ancienne classe à modifier 
        String query = "update enseignant set MATRICULE = ?, NOM = ? , PRENOM = ?, MAIL = ? where MATRICULE = ?";

        String msg;
        String matricule = nvEns.getMatricule();
        String nom = nvEns.getNom();
        String prenom = nvEns.getPrenom();
        String mail = nvEns.getMail();

        try (PreparedStatement pstm = dbconnect.prepareStatement(query)) {

            pstm.setString(1, matricule);
            pstm.setString(2, nom);
            pstm.setString(3, prenom);
            pstm.setString(4, mail);
            pstm.setString(5, tmpE.getMatricule());
            int n = pstm.executeUpdate();
            if (n == 1) {
                msg = "changement d'enseignant effectué";
            } else {
                msg = "changement d'enseignant non effectué";
            }

        } catch (SQLIntegrityConstraintViolationException pk) {
            return "Erreur de PK" + pk;
        } catch (SQLException e) {
            msg = "erreur " + e;
        }
        return msg;
    }

    @Override
    public String deleteA(Attribution aDel) {

        String query = "DELETE FROM ATTRIBUTION WHERE MATRICULE = ? AND SIGLE = ? ";
        PreparedStatement pstm = null;
        String msg;
        try {
            pstm = dbconnect.prepareStatement(query);
            pstm.setString(1, aDel.getEnseignant().getMatricule());
            pstm.setString(2, aDel.getClasse().getSigle());
            pstm.executeUpdate();
            pstm = dbconnect.prepareStatement("UPDATE ENSEIGNANT SET TITULAIRE = NULL WHERE MATRICULE = ?");
            pstm.setString(1, aDel.getEnseignant().getMatricule());
            pstm.executeUpdate();
            pstm = dbconnect.prepareStatement("UPDATE ENSEIGNANT SET REMPLACANT = NULL WHERE MATRICULE = ?");
            pstm.setString(1, aDel.getEnseignant().getMatricule());
            pstm.executeUpdate();

            return "Suppression effectuée !";

        } catch (SQLException e) {
            msg = "erreur lors de la suppression " + e;
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException e) {
                System.err.println("Erreur de fermeture du preparedStatement " + e);
            }
        }
        return "Erreur de suppression";
    }

}
