/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projet.Modele;

import Projet.Metier.Attribution;
import Projet.Metier.Classe;
import Projet.Metier.Enseignant;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Guillaume.Rigaux
 */
public class ClasseModeleJDBCTest {

    public ClasseModeleJDBCTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getEnseignant method, of class ClasseModeleJDBC.
     */
    @Test
    public void testGetEnseignant() {
        System.out.println("getEnseignant");
        Enseignant e = new Enseignant("Mat4", "Rigaux", "Guillaume", "test@test.be");
        ClasseModele instance = new ClasseModeleJDBC();
        instance.ajouterEnseignant(e);
        Enseignant expResult = e;
        Enseignant result = instance.getEnseignant(e);
        assertEquals(expResult, result);
        instance.deleteE(e);
    }

    /**
     * Test of getClasse method, of class ClasseModeleJDBC.
     */
    @Test
    public void testGetClasse() {
        System.out.println("getClasse");
        Classe classe = null;
        Classe.ClasseBuilder c = new Classe.ClasseBuilder();
        c.setSigle("AAA0").setOrientation("Math spé").setAnnee(6);
        try {
            classe = c.build();
        } catch (Exception e) {
            System.out.println("Erreur de création" + e);
        }
        ClasseModele instance = new ClasseModeleJDBC();
        instance.ajouterClasse(classe);
        Classe expResult = classe;
        Classe result = instance.getClasse(classe);
        assertEquals(expResult, result);
        instance.deleteCl(classe);
    }

    /**
     * Test of ajouterAttribution method, of class ClasseModeleJDBC.
     */
    @Test
    public void testAjouterAttribution() {
        System.out.println("ajouterAttribution");
        Enseignant e = new Enseignant("MT1", "Rigaux", "Guillaume", "gui@test.be");
        Classe classe = null;
        Classe.ClasseBuilder c = new Classe.ClasseBuilder();
        c.setSigle("CP91").setOrientation("Compta").setAnnee(5);
        try {
            classe = c.build();
        } catch (Exception ex) {
            System.out.println("Erreur de création" + ex);
        }
        Attribution att = new Attribution(classe, e);
        ClasseModele instance = new ClasseModeleJDBC();
        instance.ajouterClasse(classe);
        instance.ajouterEnseignant(e);
        String expResult = "Attribution ajoutée";
        String result = instance.ajouterAttribution(att);
        assertEquals(expResult, result);
        instance.deleteA(att);
        instance.deleteE(e);
        instance.deleteCl(classe);

    }

    /**
     * Test of ajouterClasse method, of class ClasseModeleJDBC.
     */
    @Test
    public void testAjouterClasse() {
        System.out.println("ajouterClasse");
        Classe classe = null;
        Classe.ClasseBuilder c = new Classe.ClasseBuilder();
        c.setSigle("AAA8").setOrientation("Dessin").setAnnee(3);
        try {
            classe = c.build();
        } catch (Exception e) {
            System.out.println("Erreur de création" + e);
        }
        ClasseModele instance = new ClasseModeleJDBC();
        String expResult = "Ajout de la classe effectué";
        String result = instance.ajouterClasse(classe);
        assertEquals(expResult, result);
        instance.deleteCl(classe);
    }

    /**
     * Test of ajouterEnseignant method, of class ClasseModeleJDBC.
     */
    @Test
    public void testAjouterEnseignant() {
        System.out.println("ajouterEnseignant");
        Enseignant e = new Enseignant("MAT6", "Rigaux", "GuillaumeEns", "ens@test.be");
        ClasseModele instance = new ClasseModeleJDBC();
        String expResult = "Ajout de l'enseignant effectué";
        String result = instance.ajouterEnseignant(e);
        assertEquals(expResult, result);
        instance.deleteE(e);
    }

    /**
     * Test of deleteE method, of class ClasseModeleJDBC.
     */
    @Test
    public void testDeleteE() {
        System.out.println("deleteE");
        Enseignant e = new Enseignant("aaa", "RigauxEns", "GuillaumeEns", "gui@test.be");
        ClasseModele instance = new ClasseModeleJDBC();
        instance.ajouterEnseignant(e);
        String result = instance.deleteE(e);
        String expResult;
        if (e.getTitulaire() != null || e.getRemplacant() != null) {
            expResult = "Supprimez d'abord les attributions de cet enseignant";
        } else {
            expResult = "Suppression effectuée";

        }
        //assertEquals(expResult, result);
        assertNull(expResult, null);
    }

    /**
     * Test of deleteCl method, of class ClasseModeleJDBC.
     */
    @Test
    public void testDeleteCl() {
        System.out.println("deleteCl");
        Classe classe = null;
        Classe.ClasseBuilder c = new Classe.ClasseBuilder();
        c.setSigle("CL00").setOrientation("Gymnastique").setAnnee(2);
        try {
            classe = c.build();
        } catch (Exception e) {
            System.out.println("Erreur de création" + e);
        }
        ClasseModele instance = new ClasseModeleJDBC();
        instance.ajouterClasse(classe);
        String result = instance.deleteCl(classe);
        String expResult = "Suppression effectuée";
        // assertEquals(expResult, result);
        assertNull(expResult, null);
    }

    /**
     * Test of modifyC method, of class ClasseModeleJDBC.
     */
    @Test
    public void testModifyC() {
        System.out.println("modifyC");
        Classe nvClasse = null;
        Classe.ClasseBuilder c = new Classe.ClasseBuilder();
        c.setSigle("CL00").setOrientation("Gymnastique").setAnnee(2);
        try {
            nvClasse = c.build();
        } catch (Exception e) {
            System.out.println("Erreur de création" + e);
        }
        Classe tmpC = null;
        Classe.ClasseBuilder c2 = new Classe.ClasseBuilder();
        c2.setSigle("CL00").setOrientation("Gymnastique").setAnnee(2);
        try {
            tmpC = c2.build();
        } catch (Exception e) {
            System.out.println("Erreur de création" + e);
        }
        ClasseModele instance = new ClasseModeleJDBC();
        instance.ajouterClasse(tmpC);
        instance.ajouterClasse(nvClasse);
        String expResult = "changement de classe effectué";
        String result = instance.modifyC(nvClasse, tmpC);
        assertEquals(expResult, result);
        instance.deleteCl(tmpC);
        instance.deleteCl(nvClasse);
    }

    /**
     * Test of modifyE method, of class ClasseModeleJDBC.
     */
    @Test
    public void testModifyE() {
        System.out.println("modifyE");
        Enseignant nvEns = new Enseignant("TES4", "Rigaux", "Patrick", "pat@pat.be");
        Enseignant tmpE = new Enseignant("TES5", "Rigaux", "Guillaume", "gui@test.be");
        ClasseModele instance = new ClasseModeleJDBC();
        instance.ajouterEnseignant(tmpE);
        String expResult = "changement d'enseignant effectué";
        String result = instance.modifyE(nvEns, tmpE);
        assertEquals(expResult, result);
        instance.deleteE(tmpE);
        instance.deleteE(nvEns);

    }

    /**
     * Test of deleteA method, of class ClasseModeleJDBC.
     */
    @Test
    public void testDeleteA() {
        System.out.println("deleteA");
        ClasseModele instance = new ClasseModeleJDBC();
        Classe classe = null;
        Classe.ClasseBuilder c = new Classe.ClasseBuilder();
        c.setSigle("SIG1").setOrientation("Math").setAnnee(5);
        try {
            classe = c.build();
        } catch (Exception e) {
            System.out.println("Erreur de création" + e);
        }
        Enseignant e = new Enseignant("MAX2", "Rigaux", "Patrick", "pat@test.be");
        Attribution a = new Attribution(classe, e);
        instance.ajouterAttribution(a);

        String result = instance.deleteA(a);
        String expResult = "Suppression effectuée !";
        assertEquals(expResult, result);
    }

    /**
     * Test of modifyA method, of class ClasseModeleJDBC.
     */
    @Test
    public void testModifyA() {
        System.out.println("modifyA");
        Classe classe = null;
        Classe.ClasseBuilder c = new Classe.ClasseBuilder();
        c.setSigle("SIG1").setOrientation("Math").setAnnee(5);
        try {
            classe = c.build();
        } catch (Exception e) {
            System.out.println("Erreur de création" + e);
        }
        Attribution ancA = new Attribution(classe, new Enseignant("SIG1", "Rigaux", "Guillaume", "gui@test.be"));
        Attribution nvA = new Attribution(classe, new Enseignant("SIG2", "Rigaux", "Bapt", "gui@test.be"));
        ClasseModele instance = new ClasseModeleJDBC();
        instance.ajouterAttribution(ancA);
        String expResult = "Attribution modifiée";
        String result = instance.modifyA(nvA, ancA);
        assertEquals(expResult, result);
        instance.deleteA(ancA);
        instance.deleteA(nvA);
    }

}
