/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.schernolyas.performance.ogm;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Сергей
 */
public class OgmTestProgramTest {

    private static EntityManager em;
    private static OgmTestProgram instance;

    @BeforeClass
    public static void setUpClass() {
        EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("mongo-ogm");
        em = FACTORY.createEntityManager();
        instance = new OgmTestProgram(em);

    }

    @AfterClass
    public static void tearDownClass() {
        em.close();
    }

    @Before
    public void setUp() {
        em.clear();
    }

    @After
    public void tearDown() {
        em.clear();
    }

    /**
     * Test of writeToStorage method, of class OgmTestProgram.
     */
    @Test
    public void testWriteToStorage() {
        System.out.println("writeToStorage");
        instance.writeToStorage();

    }

    /**
     * Test of readSequentially method, of class OgmTestProgram.
     */
    @Test
    public void testReadSequentially() {
        System.out.println("readSequentially");
        instance.readSequentially();

    }

    /**
     * Test of readRandomly method, of class OgmTestProgram.
     */
    @Test
    public void testReadRandomly() {
        System.out.println("readRandomly");
        instance.readRandomly();
    }

}
