package itstep.learning.oop;

import com.google.gson.JsonObject;

public class Book extends Literature implements Copyable {
    @Required
    private String author;
    @Required
    private String publisher;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String getCard() {
        return String.format(
                "Book: %s '%s' (Publisher: %s)",
                this.getAuthor(), super.getTitle(), this.getPublisher()
        );
    }

    @FromJsonFactory
    public static Literature fromJsonFactory( JsonObject jsonObject ) {
        Book book = new Book();
        book.setTitle( jsonObject.get("title").getAsString() );
        book.setAuthor( jsonObject.get("author").getAsString() );
        book.setPublisher( jsonObject.get("publisher").getAsString() );
        return book;
    }
}
/*
Literature [title, getTitle]
Book [ [title, getTitle] author, getAuthor, getTitle ]
 */