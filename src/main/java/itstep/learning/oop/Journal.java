package itstep.learning.oop;

import com.google.gson.JsonObject;

@Periodic
public class Journal extends Literature implements Copyable {
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String getCard() {
        return String.format(
                "Journal: '%s' No %d",
                super.getTitle(), this.getNumber()
        );
    }

    public static Journal fromJson( JsonObject jsonObject ) {
        Journal journal = new Journal();
        journal.setTitle( jsonObject.get("title").getAsString() );
        journal.setNumber( jsonObject.get("number").getAsInt() );
        return journal;
    }
}
