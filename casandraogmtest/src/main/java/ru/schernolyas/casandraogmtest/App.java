/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.schernolyas.casandraogmtest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Сергей
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("cassandra-ogm");
        EntityManager em = factory.createEntityManager();

        Person person = new Person(1L, "name1");
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
    }

}
