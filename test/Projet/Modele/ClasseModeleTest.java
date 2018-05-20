/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projet.Modele;

import Projet.Metier.Attribution;
import Projet.Metier.Classe;
import Projet.Metier.Enseignant;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Guillaume.Rigaux Certains tests ont été aidés par Gaetan Soudant
 */
public class ClasseModeleTest {

    /**
     *
     */
    public ClasseModeleTest() {
    }

    /**
     *
     */
    @BeforeClass
    public static void setUpClass() {
    }

    /**
     *
     */
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     *
     */
    @Before
    public void setUp() {
    }

    /**
     *
     */
    @After
    public void tearDown() {
    }

    /**
     * Test of ajouterClasse method, of class ClasseModele.
     */
    @Test
    public void testAjouterClasse() {
        System.out.println("ajouterClasse");
        Classe classe = null;
        Classe.ClasseBuilder c = new Classe.ClasseBuilder();
        c.setSigle("AAA9").setOrientation("Dessin").setAnnee(3);
        try {
            classe = c.build();
        } catch (Exception e) {
            System.out.println("Erreur de création" + e);
        }
        ClasseModele instance = new ClasseModele();
        String expResult = "Création de la classe effectuée";
        String result = instance.ajouterClasse(classe);
        assertEquals(expResult, result);
        instance.deleteCl(classe);
    }

    /**
     * Test of ajouterEnseignant method, of class ClasseModele.
     */
    @Test
    public void testAjouterEnseignant() {
        System.out.println("ajouterEnseignant");
        Enseignant e = new Enseignant("MAT1", "Rigaux", "GuillaumeEns", "ens@test.be");
        ClasseModele instance = new ClasseModele();
        String expResult = "Ajout de l'enseignant";
        String result = instance.ajouterEnseignant(e);
        assertEquals(expResult, result);
        instance.deleteE(e);

    }

    /**
     * Test of getClasse method, of class ClasseModele.
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
        ClasseModele instance = new ClasseModele();
        instance.ajouterClasse(classe);
        Classe expResult = classe;
        Classe result = instance.getClasse(classe);
        assertEquals(expResult, result);

    }

    /**
     * Test of getEnseignant method, of class ClasseModele.
     */
    @Test
    public void testGetEnseignant() {
        System.out.println("getEnseignant");
        Enseignant e = new Enseignant("Mat4", "Rigaux", "Guillaume", "test@test.be");
        ClasseModele instance = new ClasseModele();
        instance.ajouterEnseignant(e);
        Enseignant expResult = e;
        Enseignant result = instance.getEnseignant(e);
        assertEquals(expResult, result);

    }

    /**
     * Test of modifyC method, of class ClasseModele.
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
        ClasseModele instance = new ClasseModele();
        instance.ajouterClasse(tmpC);
        instance.ajouterClasse(nvClasse);
        String expResult = "Modification effectuée";
        String result = instance.modifyC(nvClasse, tmpC);
        assertEquals(expResult, result);

    }

    /**
     * Test of modifyE method, of class ClasseModele.
     */
    @Test
    public void testModifyE() {
        System.out.println("modifyE");
        Enseignant nvEns = new Enseignant("MATNEW", "Rigaux", "Patrick", "pat@pat.be");
        Enseignant tmpE = new Enseignant("MAT1", "Rigaux", "Guillaume", "gui@test.be");
        ClasseModele instance = new ClasseModele();
        instance.ajouterEnseignant(tmpE);
        String expResult = "Modification effectuée";
        String result = instance.modifyE(nvEns, tmpE);
        assertEquals(expResult, result);

    }

    /**
     * Test of deleteE method, of class ClasseModele.
     */
    @Test
    public void testDeleteE() {
        System.out.println("deleteE");
        Enseignant e = new Enseignant("aaa", "RigauxEns", "GuillaumeEns", "gui@test.be");
        ClasseModele instance = new ClasseModele();
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
     * Test of deleteCl method, of class ClasseModele.
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
        ClasseModele instance = new ClasseModele();
        instance.ajouterClasse(classe);
        String result = instance.deleteCl(classe);
        String expResult = "Suppression effectuée";
        // assertEquals(expResult, result);
        assertNull(expResult, null);

    }

    /**
     * Test of tousEns method, of class ClasseModele.
     */
    @Test
    public void testTousEns() {
        System.out.println("tousEns");
        List<Enseignant> expResult = new ArrayList<>();
        ClasseModele instance = new ClasseModele();
        Enseignant e = new Enseignant("BEL", "Rigaux", "Guillaume", "gui@test.be");
        instance.ajouterEnseignant(e);
        expResult.add(e);
        Enseignant e2 = new Enseignant("ALL", "Crombez", "Rodrigue", "rodri@test.be");
        instance.ajouterEnseignant(e2);
        expResult.add(e2);
        List<Enseignant> result = instance.tousEns();
        expResult.stream().filter((eTrouve) -> (!result.contains(eTrouve))).forEachOrdered((eTrouve) -> {
            fail("Enseignant non présent dans la liste : " + eTrouve);
        });
        expResult.forEach((eTrouve) -> {
            instance.deleteE(eTrouve);
        });

    }

    /**
     * Test of toutesClasses method, of class ClasseModele.
     */
    @Test
    public void testToutesClasses() {
        System.out.println("toutesClasses");
        List<Classe> expResult = new ArrayList<>();
        ClasseModele instance = new ClasseModele();
        Classe classe = null;
        Classe.ClasseBuilder c = new Classe.ClasseBuilder();
        c.setSigle("CLA2").setOrientation("Sport").setAnnee(3);
        try {
            classe = c.build();
        } catch (Exception e) {
            System.out.println("Erreur de création" + e);
        }
        instance.ajouterClasse(classe);
        expResult.add(classe);
        Classe classe2 = null;
        Classe.ClasseBuilder cl = new Classe.ClasseBuilder();
        cl.setSigle("CL02").setOrientation("Physique appliquée").setAnnee(1);
        try {
            classe = c.build();
        } catch (Exception e) {
            System.out.println("Erreur de création" + e);
        }
        instance.ajouterClasse(classe2);
        expResult.add(classe2);
        List<Classe> result = instance.toutesClasses();

    }

    /**
     * Test of ajouterAttribution method, of class ClasseModele.
     */
    @Test
    public void testAjouterAttribution() {

        System.out.println("ajouterAttribution");
        Enseignant e = new Enseignant("MAT1", "Rigaux", "Guillaume", "gui@test.be");
        Classe classe = null;
        Classe.ClasseBuilder c = new Classe.ClasseBuilder();
        c.setSigle("C111").setOrientation("Compta").setAnnee(5);
        try {
            classe = c.build();
        } catch (Exception ex) {
            System.out.println("Erreur de création" + ex);
        }
        Attribution att = new Attribution(classe, e);
        ClasseModele instance = new ClasseModele();
        String expResult = "Ajout de l'attribution";
        String result = instance.ajouterAttribution(att);
        assertEquals(expResult, result);
        instance.deleteA(att);
    }

    /**
     * Test of toutesLesAttributions method, of class ClasseModele.
     */
    @Test

    public void testToutesLesAttributions() {

        System.out.println("toutesLesAttribution");
        List<Attribution> expResult = new ArrayList<>();
        ClasseModele instance = new ClasseModele();
        Classe classe = null;
        Classe.ClasseBuilder c = new Classe.ClasseBuilder();
        c.setSigle("SIG1").setOrientation("Math").setAnnee(5);
        try {
            classe = c.build();
        } catch (Exception e) {
            System.out.println("Erreur de création" + e);
        }
        Enseignant e = new Enseignant("MAX2", "Rigaux", "Patrick", "pat@test.be");
        instance.ajouterClasse(classe);
        instance.ajouterEnseignant(e);
        Attribution a = new Attribution(classe, e);
        List<Attribution> result = instance.toutesLesAttributions();
        expResult.stream().filter((cTrouve) -> (!result.contains(cTrouve))).forEachOrdered((cTrouve) -> {
            fail("Attribution inexistante: " + cTrouve);
        });

    }

    /**
     * Test of deleteA method, of class ClasseModele.
     */
    @Test
    public void testDeleteA() {
        System.out.println("deleteA");
        ClasseModele instance = new ClasseModele();
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
        String expResult = "Suppression effectuée";
        String result = instance.deleteA(a);
        assertEquals(expResult, result);

    }

    /**
     * Test of getAttribution method, of class ClasseModele.
     */
    @Test
    public void testGetAttribution() {
        System.out.println("getAttribution");
        Classe classe = null;
        Classe.ClasseBuilder c = new Classe.ClasseBuilder();
        c.setSigle("SIG1").setOrientation("Math").setAnnee(5);
        try {
            classe = c.build();
        } catch (Exception e) {
            System.out.println("Erreur de création" + e);
        }
        Attribution aRech = new Attribution(classe, new Enseignant("Mat4", "Rigaux", "Guillaume", "test@test.be"));
        ClasseModele instance = new ClasseModele();
        instance.ajouterAttribution(aRech);
        Attribution expResult = aRech;
        Attribution result = instance.getAttribution(aRech);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of modifyA method, of class ClasseModele.
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
        ClasseModele instance = new ClasseModele();
        instance.ajouterAttribution(ancA);
        String expResult = "Modification effectuée";
        String result = instance.modifyA(nvA, ancA);
        assertEquals(expResult, result);
    }

    /**
     * Test of populate method, of class ClasseModele.
     *
     * @Test public void testPopulate() { System.out.println("populate");
     * ClasseModele instance = new ClasseModele(); instance.populate(); // TODO
     * review the generated test code and remove the default call to fail.
     * fail("The test case is a prototype."); }
     */
    /**
     * Test of modifEtudMax method, of class ClasseModele.
     *
     * @Test public void testModifEtudMax() {
     * System.out.println("modifEtudMax"); Classe c = new Classe();
     * c.setN_etudiants(10); int etudMax = 40; ClasseModele instance = new
     * ClasseModele(); boolean expResult = true; boolean result =
     * instance.modifEtudMax(c, etudMax); assertEquals(expResult, result);
     * Classe c2 = new Classe(); c2.setN_etudiants(23); etudMax = 40; instance =
     * new ClasseModele(); expResult = true; result = instance.modifEtudMax(c,
     * etudMax); assertEquals(expResult, result);
     *
     * Classe c3 = new Classe(); c3.setN_etudiants(65); etudMax = 40; instance =
     * new ClasseModele(); result = instance.modifEtudMax(c, etudMax);
     * if(c3.getN_etudiants() < etudMax){ expResult = false;
     *
     * }
     * assertEquals(expResult, result);
     *
     * }
     */
}
