/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.schernolyas.performance.ogm;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import javax.persistence.EntityManager;
import ru.schernolyas.performance.TestProgram;

/**
 *
 * @author Сергей
 */
public class OgmTestProgram implements TestProgram {

    private EntityManager em;

    public OgmTestProgram(EntityManager em) {
        this.em = em;
    }

    @Override
    public void writeToStorage() {
        em.getTransaction().begin();
        LongStream.rangeClosed(1, ROWS_COUNT).forEachOrdered((long authorIndex) -> {
            Author author = new Author();
            author.setId(authorIndex);
            author.setName("name"+authorIndex);
            author.setYearOfBirth((int)authorIndex);
            em.persist(author);

            List<Book> books = IntStream.range(1, 10).mapToObj((int bookIndex) -> {
                Book book = new Book();
                book.setAuthor(author);
                book.setTitle("title" + bookIndex);
                em.persist(book);
                return book;
            }).collect(Collectors.toList());
            author.setBooks(books);
            em.merge(author);
        });
        em.getTransaction().commit();
    }

    @Override
    public void readSequentially() {
        em.getTransaction().begin();
        LongStream.rangeClosed(1, ROWS_COUNT).forEachOrdered((long authorIndex) -> {
            Author author = em.find(Author.class, authorIndex);
            author.getBooks().forEach((Book book) -> {
            });
        });
        em.getTransaction().commit();
    }

    @Override
    public void readRandomly() {
        Random random = new Random();
        em.getTransaction().begin();

        LongStream.generate(() -> {
            return 1 + (long)(random.nextDouble()*(ROWS_COUNT - 1));
        }).limit(ROWS_COUNT).forEach((long authorIndex) -> {
            Author author = em.find(Author.class, authorIndex);
            if (author != null) {
                author.getBooks().forEach((Book book) -> {
                });
            }
        });
        em.getTransaction().commit();
    }

}
