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
     * Test of getInstance method, of class ClasseModeleJDBC.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        ClasseModeleJDBC expResult = null;
        ClasseModeleJDBC result = ClasseModeleJDBC.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of close method, of class ClasseModeleJDBC.
     */
    @Test
    public void testClose() {
        System.out.println("close");
        ClasseModeleJDBC instance = new ClasseModeleJDBC();
        instance.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of populate method, of class ClasseModeleJDBC.
     */
    @Test
    public void testPopulate() {
        System.out.println("populate");
        ClasseModeleJDBC instance = new ClasseModeleJDBC();
        instance.populate();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of tousEns method, of class ClasseModeleJDBC.
     */
    @Test
    public void testTousEns() {
        System.out.println("tousEns");
        ClasseModeleJDBC instance = new ClasseModeleJDBC();
        List<Enseignant> expResult = null;
        List<Enseignant> result = instance.tousEns();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toutesLesAttributions method, of class ClasseModeleJDBC.
     */
    @Test
    public void testToutesLesAttributions() {
        System.out.println("toutesLesAttributions");
        ClasseModeleJDBC instance = new ClasseModeleJDBC();
        List<Attribution> expResult = null;
        List<Attribution> result = instance.toutesLesAttributions();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toutesClasses method, of class ClasseModeleJDBC.
     */
    @Test
    public void testToutesClasses() {
        System.out.println("toutesClasses");
        ClasseModeleJDBC instance = new ClasseModeleJDBC();
        List<Classe> expResult = null;
        List<Classe> result = instance.toutesClasses();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEnseignant method, of class ClasseModeleJDBC.
     */
    @Test
    public void testGetEnseignant() {
        System.out.println("getEnseignant");
        Enseignant aRech = null;
        ClasseModeleJDBC instance = new ClasseModeleJDBC();
        Enseignant expResult = null;
        Enseignant result = instance.getEnseignant(aRech);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClasse method, of class ClasseModeleJDBC.
     */
    @Test
    public void testGetClasse() {
        System.out.println("getClasse");
        Classe aRech = null;
        ClasseModeleJDBC instance = new ClasseModeleJDBC();
        Classe expResult = null;
        Classe result = instance.getClasse(aRech);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAttribution method, of class ClasseModeleJDBC.
     */
    @Test
    public void testGetAttribution() {
        System.out.println("getAttribution");
        Attribution aRech = null;
        ClasseModeleJDBC instance = new ClasseModeleJDBC();
        Attribution expResult = null;
        Attribution result = instance.getAttribution(aRech);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ajouterAttribution method, of class ClasseModeleJDBC.
     */
    @Test
    public void testAjouterAttribution() {
        System.out.println("ajouterAttribution");
        Attribution a = null;
        ClasseModeleJDBC instance = new ClasseModeleJDBC();
        String expResult = "";
        String result = instance.ajouterAttribution(a);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ajouterClasse method, of class ClasseModeleJDBC.
     */
    @Test
    public void testAjouterClasse() {
        System.out.println("ajouterClasse");
        Classe c = null;
        ClasseModeleJDBC instance = new ClasseModeleJDBC();
        String expResult = "";
        String result = instance.ajouterClasse(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ajouterEnseignant method, of class ClasseModeleJDBC.
     */
    @Test
    public void testAjouterEnseignant() {
        System.out.println("ajouterEnseignant");
        Enseignant e = null;
        ClasseModeleJDBC instance = new ClasseModeleJDBC();
        String expResult = "";
        String result = instance.ajouterEnseignant(e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteE method, of class ClasseModeleJDBC.
     */
    @Test
    public void testDeleteE() {
        System.out.println("deleteE");
        Enseignant ens = null;
        ClasseModeleJDBC instance = new ClasseModeleJDBC();
        String expResult = "";
        String result = instance.deleteE(ens);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteCl method, of class ClasseModeleJDBC.
     */
    @Test
    public void testDeleteCl() {
        System.out.println("deleteCl");
        Classe cl = null;
        ClasseModeleJDBC instance = new ClasseModeleJDBC();
        String expResult = "";
        String result = instance.deleteCl(cl);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modifyC method, of class ClasseModeleJDBC.
     */
    @Test
    public void testModifyC() {
        System.out.println("modifyC");
        Classe nvClasse = null;
        Classe tmpC = null;
        ClasseModeleJDBC instance = new ClasseModeleJDBC();
        String expResult = "";
        String result = instance.modifyC(nvClasse, tmpC);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modifyE method, of class ClasseModeleJDBC.
     */
    @Test
    public void testModifyE() {
        System.out.println("modifyE");
        Enseignant nvEns = null;
        Enseignant tmpE = null;
        ClasseModeleJDBC instance = new ClasseModeleJDBC();
        String expResult = "";
        String result = instance.modifyE(nvEns, tmpE);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteA method, of class ClasseModeleJDBC.
     */
    @Test
    public void testDeleteA() {
        System.out.println("deleteA");
        Attribution aDel = null;
        ClasseModeleJDBC instance = new ClasseModeleJDBC();
        String expResult = "";
        String result = instance.deleteA(aDel);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modifyA method, of class ClasseModeleJDBC.
     */
    @Test
    public void testModifyA() {
        System.out.println("modifyA");
        Attribution nvA = null;
        Attribution tmpA = null;
        ClasseModeleJDBC instance = new ClasseModeleJDBC();
        String expResult = "";
        String result = instance.modifyA(nvA, tmpA);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
