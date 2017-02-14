/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.schernolyas.cassandraogmtest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.jboss.logging.Logger;

/**
 *
 * @author Sergey Chernolyas
 */
public class App {
    private static Logger log = Logger.getLogger(App.class);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        log.info("Begin to work");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("cassandra-ogm");
        EntityManager em = factory.createEntityManager();

        Person person = new Person("name1");
        em.getTransaction().begin();
        em.persist(person);
        log.info("Begin to commit");
        em.getTransaction().commit();
        log.info("finish");
        System.exit(0);
    }

}
