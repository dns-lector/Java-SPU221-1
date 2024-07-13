package itstep.learning.oop;

import com.google.gson.JsonObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;

public abstract class Literature {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public abstract String getCard();

    public static Literature fromJson(JsonObject jsonObject) throws ParseException {
        Class<?>[] classes = { Book.class, Journal.class, Newspaper.class };
        for (Class<?> cls : classes) {
            // визначаємо всі необхідні поля - за наявністю анотації
            // for(Field field : cls.getDeclaredFields()) {
            //     if( cls.isAnnotationPresent( Required.class ) ) {
            //         // додаємо поле до переліку необхідних
            //     }
            // }
            // визначаємо, чи є у jsonObject усі необхідні поля
            // if(jsonObject.has(...)) {}
            // якщо всі поля є, то знаходимо фабричний метод у cls
            // запускаємо його
        }
        if( jsonObject.has("author") ) {
            // шукаємо метод, позначений як фабрика
            for( Method method : Book.class.getDeclaredMethods() ) {
                if( method.isAnnotationPresent( FromJsonFactory.class ) ) {
                    // даний метод містить відповідну анотацію
                    method.setAccessible( true );
                    try {
                        return (Literature) method.invoke( null, jsonObject );
                    }
                    catch( InvocationTargetException | IllegalAccessException ex ) {
                        System.err.println( ex.getMessage() );
                    }
                }
            }
            // return Book.fromJson( jsonObject );
            throw new ParseException( "@FromJsonFactory not found", 0 );
        }
        else if( jsonObject.has("date") ) {
            return Newspaper.fromJson( jsonObject );
        }
        else if( jsonObject.has("number") ) {
            return Journal.fromJson( jsonObject );
        }
        else {
            throw new ParseException( "Literature type unrecognized", 0 );
        }
    }
}
/*
Д.З. Повторити ООП вправи на проєкті "магазин" з сутностями "товар"
та його реалізаціями.
 */