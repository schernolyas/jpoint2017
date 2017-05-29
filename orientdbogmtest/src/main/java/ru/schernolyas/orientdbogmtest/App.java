/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.schernolyas.orientdbogmtest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.jboss.logging.Logger;
;

/**
 *
 * @author Sergey Chernolyas
 */
public class App {
    private static final Logger log = Logger.getLogger(App.class);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        log.info("start!");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("orientdb-remote-ogm");
        EntityManager em = factory.createEntityManager();

        Person person = new Person("itsubbotnik2017");
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
        
        log.infof("person id: %s", person.getId());
    }
    
}
