package itstep.learning.oop;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Library {
    private final List<Literature> funds;

    public Library() {
        Gson gson = new Gson();
        JsonArray jsonArray = null;
        try( InputStreamReader reader = new InputStreamReader(
                Objects.requireNonNull(
                    this.getClass().getClassLoader().getResourceAsStream("library.json")
        ))) {
            jsonArray = gson.fromJson(reader, JsonElement.class).getAsJsonArray();
        }
        catch (IOException ex) {
            System.err.println( ex.getMessage() );
        }

        funds = new ArrayList<>();
        for(int i = 0; i < jsonArray.size(); ++i) {
            try {
                funds.add( Literature.fromJson(
                        jsonArray.get(i).getAsJsonObject()
                ) );
            }
            catch( ParseException ex ) {
                System.err.println( ex.getMessage() );
            }
        }


    }

    public void printFunds() {
        for( Literature fund : funds ) {
            System.out.println( fund.getCard() );
        }
    }

    public void printCopyable() {
        System.out.println( "--- Copyable funds: ---");
        for( Literature fund : funds ) {
            if( fund instanceof Copyable ) {
                System.out.println( fund.getCard() );
            }
        }
    }

    public void printPeriodic() {
        System.out.println( "--- Periodic funds: ---");
        for( Literature fund : funds ) {
            if( fund.getClass().isAnnotationPresent( Periodic.class ) ) {
                System.out.println( fund.getCard() );
            }
        }
    }
}
