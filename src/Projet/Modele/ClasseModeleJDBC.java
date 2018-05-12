/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
        Statement stm = null;
        ResultSet rs = null;
        try {
            stm = dbconnect.createStatement();
            rs = stm.executeQuery(query);
            while (rs.next()) {
                String matricule = rs.getString(1);
                String titulaire = rs.getString(2);
                String remplacant = rs.getString(3);
                String nom = rs.getString(4);
                String prenom = rs.getString(5);
                String mail = rs.getString(6);

                Enseignant e = new Enseignant(matricule, nom, prenom, mail);

                le.add(e);

            }
        } catch (SQLException ex) {
            System.err.println("erreur lors de la recherche de l'enseignant " + ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.err.println("erreur de fermeture de resultset " + ex);
            }
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                System.err.println("erreur de fermeture de statement " + ex);
            }
        }
        return le;
    }

    @Override
    public List<Attribution> toutesLesAttributions() {
        //mode = soit tri par numéro chassis soit par prix soit par prenom

        String query = "select * from ATTRIBUTION";
        List<Attribution> la = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            stm = dbconnect.prepareStatement(query);
            rs = stm.executeQuery();
            while (rs.next()) {
                String matricule = rs.getString(1);
                String sigle = rs.getString(2);

                Enseignant ens = getEnseignant(new Enseignant(matricule));
                Classe classe = null;
                Classe.ClasseBuilder c = new Classe.ClasseBuilder();
                c.setSigle(sigle);
                try {
                    classe = c.build();

                } catch (Exception e) {
                    System.out.println("Erreur de création" + e);
                }
                Attribution a = new Attribution(classe, ens);

                la.add(a);

            }
        } catch (SQLException ex) {
            System.err.println("erreur lors de la recherche de l'enseignant " + ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.err.println("erreur de fermeture de resultset " + ex);
            }
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException ex) {
                System.err.println("erreur de fermeture de statement " + ex);
            }
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
        Statement stm = null;
        ResultSet rs = null;
        try {
            stm = dbconnect.createStatement();
            rs = stm.executeQuery(query);
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
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.err.println("erreur de fermeture de resultset " + e);
            }
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                System.err.println("erreur de fermeture de statement " + e);
            }
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
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = dbconnect.prepareStatement(query);
            pstm.setString(1, aRech.getMatricule());
            rs = pstm.executeQuery();
            if (rs.next()) {
                String matricule = rs.getString(1);
                String cl_titulaire = rs.getString(2);
                String cl_remplacant = rs.getString(3);
                String nom = rs.getString(4);
                String prenom = rs.getString(5);
                String mail = rs.getString(6);

                Enseignant e = new Enseignant(matricule, nom, prenom, mail);
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
            try {
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException e) {
                System.err.println("erreur de fermeture de preparedstatement " + e);
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
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = dbconnect.prepareStatement(query);
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
            try {
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException e) {
                System.err.println("erreur de fermeture de preparedstatement " + e);
            }
        }

    }

    @Override
    public Attribution getAttribution(Attribution aRech) {
        //Faire switch pour recherche sur 2 critères
        String query = "SELECT * FROM ATTRIBUTION WHERE MATRICULE = ? AND SIGLE = ?";
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            pstm = dbconnect.prepareStatement(query);
            pstm.setString(1, aRech.getEnseignant().getMatricule());
            pstm.setString(2, aRech.getClasse().getSigle());

            rs = pstm.executeQuery();
            if (rs.next()) {
                Classe classe = null;
                String sigle = rs.getString(1);
                String matricule = rs.getString(2);
                Enseignant ens = getEnseignant(new Enseignant(matricule));
                Classe.ClasseBuilder c = new Classe.ClasseBuilder();
                c.setSigle(sigle);
                try {
                    classe = c.build();
                } catch (Exception e) {
                    System.out.println("Erreur de création" + e);
                }
                Attribution a = new Attribution(classe, ens);
                return a;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("erreur de recherche de l'attribution " + e);
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.err.println("erreur de fermeture de resultset " + e);
            }
            try {
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException e) {
                System.err.println("erreur de fermeture de preparedstatement " + e);
            }
        }

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
        PreparedStatement pstm = null;
        try {
            pstm = dbconnect.prepareStatement(query);
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
            return "Erreur de PK"+pk;
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
        String query = "insert into ENSEIGNANT(matricule,nom,prenom,mail) values(?,?,?,?)";
        PreparedStatement pstm = null;
        try {
            pstm = dbconnect.prepareStatement(query);
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
            return "Erreur de PK" + pk;
        } catch (SQLException ec) {
            return "Erreur d'ajout de la classe " + ec;
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
        PreparedStatement pstm = null;
        String msg;
        try {
            pstm = dbconnect.prepareStatement(query);
            pstm.setString(1, ens.getMatricule());
            int n = pstm.executeUpdate();
            if (n == 1) {
                msg = "Suppression effectuée ";
            } else {
                msg = "Suppression non effectuée";
            }

        } catch (SQLException e) {
            msg = "erreur lors de la suppression " + e;
        } finally {

            try {
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException e) {
                msg = "erreur de fermeture de preparedstatement " + e;
            }

        }
        return msg;
    }

    @Override
    public String deleteCl(Classe cl) {
        String query = "DELETE FROM CLASSE WHERE SIGLE = ? ";
        PreparedStatement pstm = null;
        String msg;
        try {
            pstm = dbconnect.prepareStatement(query);
            pstm.setString(1, cl.getSigle());
            int n = pstm.executeUpdate();
            if (n == 1) {
                msg = "Suppression effectuée ";
            } else {
                msg = "Suppression non effectuée";
            }

        } catch (SQLException e) {
            msg = "erreur lors de la suppression " + e;
        } finally {

            try {
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException e) {
                msg = "erreur de fermeture de preparedstatement " + e;
            }

        }
        return msg;
    }

    @Override
    public String modifyC(Classe nvClasse, Classe tmpC) {

        //nvClasse = la nouvelle classe 
        //tmpC = l'ancienne classe à modifier 
        String query = "update classe set SIGLE = ?, ANNEE = ? , ORIENTATION = ? where SIGLE = ?";
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String msg;
        int annee = nvClasse.getAnnee();
        String sigle = nvClasse.getSigle();
        String orientation = nvClasse.getOrientation();

        try {
            pstm = dbconnect.prepareStatement(query);
            pstm.setString(1, sigle);
            pstm.setInt(2, annee);
            pstm.setString(3, orientation);
            pstm.setString(4, tmpC.getSigle());
            int n = pstm.executeUpdate();
            if (n == 1) {
                msg = "changement de classe effectué";
            } else {
                msg = "changement de classe non effectué";
            }

        } catch (SQLException e) {
            msg = "erreur lors du changement d'adresse " + e;
        } finally {

            try {
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException e) {
                msg = "erreur de fermeture de preparedstatement " + e;
            }

        }
        return msg;
    }

    @Override
    public String modifyE(Enseignant nvEns, Enseignant tmpE) {

        //nvClasse = la nouvelle classe 
        //tmpC = l'ancienne classe à modifier 
        String query = "update enseignant set MATRICULE = ?, NOM = ? , PRENOM = ?, MAIL = ? where MATRICULE = ?";
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String msg;
        String matricule = nvEns.getMatricule();
        String nom = nvEns.getNom();
        String prenom = nvEns.getPrenom();
        String mail = nvEns.getMail();

        try {
            pstm = dbconnect.prepareStatement(query);
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

        } catch (SQLException e) {
            msg = "erreur lors du changement d'adresse " + e;
        } finally {

            try {
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException e) {
                msg = "erreur de fermeture de preparedstatement " + e;
            }

        }
        return msg;
    }

}
