/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.schernolyas.performance.driver;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Сергей
 */
public class DriverTestProgramTest {
    
    private static MongoDatabase database;
    private static DriverTestProgram instance;
    
    @BeforeClass
    public static void setUpClass() {
        MongoClient mongoClient = new MongoClient();
       database = mongoClient.getDatabase("perf2");
       instance = new DriverTestProgram(database);
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
     * Test of writeToStorage method, of class DriverTestProgram.
     */
    @Test
    public void testWriteToStorage() {
        System.out.println("writeToStorage");
        instance.writeToStorage();
    }

    /**
     * Test of readSequentially method, of class DriverTestProgram.
     */
    @Test
    public void testReadSequentially() {
        System.out.println("readSequentially");
        instance.readSequentially();
    }

    /**
     * Test of readRandomly method, of class DriverTestProgram.
     */
    @Test
    public void testReadRandomly() {
        System.out.println("readRandomly");
        instance.readRandomly();
    }
    
}
