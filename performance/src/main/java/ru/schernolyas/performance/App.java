/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.schernolyas.performance;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static ru.schernolyas.performance.TestProgram.ROWS_COUNT;
import ru.schernolyas.performance.ogm.Author;
import ru.schernolyas.performance.ogm.Book;

/**
 *
 * @author osboxes
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("mongo-ogm");
        EntityManager em = FACTORY.createEntityManager();
        long started = System.currentTimeMillis();
        em.getTransaction().begin();
        for (long authorIndex = 1; authorIndex < ROWS_COUNT; authorIndex++) {
            Author author = em.find(Author.class, authorIndex);
            for (Book book : author.getBooks()) {
                
            }
        }
        /*LongStream.rangeClosed(1, ROWS_COUNT).forEach((long authorIndex) -> {
            Author author = em.find(Author.class, authorIndex);
            author.getBooks().forEach((Book book) -> {
            });
        }); */
        em.getTransaction().commit();
        long finish = System.currentTimeMillis();
        System.out.println("duration: "+(finish-started ));
    }

}
