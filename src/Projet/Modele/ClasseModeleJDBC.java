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

/**
 *
 * @author guill
 */
public class ClasseModeleJDBC extends ClasseModele {

    Connection dbconnect;
//Connexion à la BDD

    /**
     *
     */
    public ClasseModeleJDBC() {
        dbconnect = DBConnection.getConnection();
        if (dbconnect == null) {
            System.err.println("erreur de connexion => arrêt du pgm");
            //err = canal d'affichage des erreurs, affichera en rouge
            System.exit(1);
        }
    }

    /**
     *
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
     *
     * @param mode
     * @return
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
                String nom = rs.getString(4);
                String prenom = rs.getString(5);

                Enseignant e = new Enseignant(matricule, nom, prenom);

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

    /**
     *
     * @param mode
     * @return
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
                String nom = rs.getString(4);
                String prenom = rs.getString(5);
                //     String cl_titulaire = rs.getString(3);
                
                //     String cl_remplacant = rs.getString(5);
                Enseignant e = new Enseignant(nom, prenom, matricule);
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
                //     String cl_titulaire = rs.getString(3);
                String orientation = rs.getString(3);
                //     String cl_remplacant = rs.getString(5);
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
    public String ajouterClasse(Classe c) {
        String msg;
        String query = "insert into CLASSE(annee,sigle,orientation) values(?,?,?)";
        PreparedStatement pstm = null;
        try {
            pstm = dbconnect.prepareStatement(query);
            pstm.setInt(1, c.getAnnee());
            pstm.setString(2, c.getSigle());
            pstm.setString(3, c.getOrientation());
            int n = pstm.executeUpdate();
            if (n == 1) {
                msg = "Ajout de la classe effectué";
            } else {
                msg = "Classe non ajoutée ";
            }
        } catch (SQLException e) {
            msg = "erreur lors de l'ajout " + e;
        } finally {

            try {
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException e) {
                System.err.println("erreur de fermeture de preparedstatement " + e);
            }
        }
        return msg;
    }

    @Override
    public String ajouterEnseignant(Enseignant e) {
        String msg;
        String query = "insert into ENSEIGNANT(matricule,nom,prenom) values(?,?,?)";
        PreparedStatement pstm = null;
        try {
            pstm = dbconnect.prepareStatement(query);
            pstm.setString(1, e.getMatricule());
            pstm.setString(2, e.getNom());
            pstm.setString(3, e.getPrenom());
            int n = pstm.executeUpdate();
            if (n == 1) {
                msg = "Ajout de l'enseignant effectué";
            } else {
                msg = "Enseignant non ajouté ";
            }
        } catch (SQLException ex) {
            msg = "erreur lors de l'ajout " + ex;
        } finally {

            try {
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException ex) {
                System.err.println("erreur de fermeture de preparedstatement " + ex);
            }
        }
        return msg;
    }
   
    

    public String deleteE(Enseignant ens, String matricule) {
        String query = "DELETE FROM ENSEIGNANT WHERE MATRICULE = ? ";
        PreparedStatement pstm = null;
        String msg;
        try {
            pstm = dbconnect.prepareStatement(query);
            pstm.setString(1, matricule);
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
   

}
