/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.schernolyas.performance.driver;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexModel;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import org.bson.BsonDocument;
import org.bson.Document;
import ru.schernolyas.performance.TestProgram;
import static ru.schernolyas.performance.TestProgram.ROWS_COUNT;

/**
 *
 * @author Сергей
 */
public class DriverTestProgram implements TestProgram {

    private MongoDatabase database;
    private MongoCollection<Document> authorsCollection;
    private MongoCollection<Document> booksCollection;

    public DriverTestProgram(MongoDatabase database) {
        this.database = database;
        this.authorsCollection = database.getCollection("Author");
        this.booksCollection = database.getCollection("Book");
    }

    @Override
    public void writeToStorage() {
        System.err.println(authorsCollection.createIndex(new BasicDBObject("_id", 1)));
        Map<String,Object> map = new HashMap<>();
        map.put("_id", 1);
        map.put("author_id", 1);
        System.err.println(booksCollection.createIndex(new BasicDBObject("author_id", 1) ));
        
        LongStream.rangeClosed(1, ROWS_COUNT).forEachOrdered((long authorIndex) -> {
            Document author = new Document();
            author.put("_id", authorIndex);
            author.put("name", "name" + authorIndex);
            author.put("yearOfBirth", "name" + authorIndex);
            authorsCollection.insertOne(author);

            IntStream.range(1, 10).forEach((int bookIndex) -> {
                Document book = new Document();
                book.put("title", "title" + bookIndex);
                book.put("author_id", authorIndex);
                booksCollection.insertOne(book);
            });
        });

    }

    @Override
    public void readSequentially() {
        LongStream.rangeClosed(1, ROWS_COUNT).forEachOrdered((long authorIndex) -> {
            MongoCursor<Document> authorsCursor = authorsCollection.find(new BasicDBObject("_id", authorIndex)).iterator();
            while (authorsCursor.hasNext()) {
                readAuthor(authorsCursor.next());

            }
            MongoCursor<Document> booksCursor = booksCollection.find(new BasicDBObject("author_id", authorIndex)).iterator();
            while (booksCursor.hasNext()) {
                readBook(booksCursor.next());
            }
        });
    }

    @Override
    public void readRandomly() {
        Random random = new Random();
        LongStream.generate(() -> {
            return 1 + (long) (random.nextDouble() * (ROWS_COUNT - 1));
        }).limit(ROWS_COUNT).forEach((long authorIndex) -> {
            MongoCursor<Document> authorsCursor = authorsCollection.find(new BasicDBObject("_id", authorIndex)).iterator();
            while (authorsCursor.hasNext()) {
                readAuthor(authorsCursor.next());
            }
            MongoCursor<Document> booksCursor = booksCollection.find(new BasicDBObject("author_id", authorIndex)).iterator();
            while (booksCursor.hasNext()) {
                readBook(booksCursor.next());
            }
        });
    }

    private void readAuthor(Document doc) {
        doc.get("_id");
        doc.get("yearOfBirth");
        doc.get("name");
    }
    private void readBook(Document doc) {
        doc.get("_id");
        doc.get("title");
        doc.get("author_id");
    }

}
