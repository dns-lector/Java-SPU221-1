package itstep.learning.oop;

import com.google.gson.JsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Periodic
public class Newspaper extends Literature {
    private static final SimpleDateFormat dottedFormat =
            new SimpleDateFormat( "dd.MM.yyyy" );
    @Required
    private Date date;

    public Newspaper( String title, Date date ) {
        this.setDate( date );
        super.setTitle( title );
    }
    public Newspaper( String title, String dottedDate ) throws ParseException {
        this.setDate( dottedDate );
        super.setTitle( title );
    }

    public String getDateDotted() {
        return dottedFormat.format( date );
    }

    public void setDate( String dottedDate ) throws ParseException {
        date = dottedFormat.parse( dottedDate ) ;
    }

    public void setDate( Date date ) {
        this.date = date;
    }

    @Override
    public String getCard() {
        return String.format(
                "Newspaper: '%s' (%s)",
                super.getTitle(), this.getDateDotted()
        );
    }

    public static Literature fromJson( JsonObject jsonObject ) throws ParseException {
        return new Newspaper(
                jsonObject.get("title").getAsString(),
                jsonObject.get("date").getAsString()
        );
    }
}
