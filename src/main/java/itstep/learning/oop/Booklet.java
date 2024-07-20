package itstep.learning.oop;

import com.google.gson.JsonObject;

public class Booklet extends Literature {
    @Required
    private String publisher;

    @Override
    public String getCard() {
        return String.format(
                "Booklet: '%s' Publisher: %s",
                super.getTitle(), this.getPublisher()
        );
    }

    public static Booklet fromJson(JsonObject jsonObject ) {
        Booklet booklet = new Booklet();
        booklet.setTitle( jsonObject.get("title").getAsString() );
        booklet.setPublisher( jsonObject.get("publisher").getAsString() );
        return booklet;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
