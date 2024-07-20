package itstep.learning.oop;

import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

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
        // Class<?> matchedClass = null;
        List<Map.Entry<Class<?>, List<String>>> matchedEntries = new ArrayList<>();
        for( Map.Entry<Class<?>, List<String>> entry : getLitClassesWithRequiredFields().entrySet() ) {
             Class<?> cls = entry.getKey();
             // визначаємо, чи є у jsonObject усі необхідні поля
             boolean isMatch = true;
             for( String requiredField : entry.getValue() ) {
                 if( ! jsonObject.has(requiredField) ) {
                     isMatch = false;
                 }
             }
            if( isMatch ) {
                matchedEntries.add( entry );
            }
        }
        if( matchedEntries.isEmpty() ) {
            throw new ParseException( "Literature type unrecognized for " + jsonObject.toString(), 0 );
        }
        int maxRequires = matchedEntries.stream().mapToInt(e -> e.getValue().size()).max().getAsInt();
        long cnt = matchedEntries.stream().filter(e -> e.getValue().size() == maxRequires).count();
        if( cnt > 1 ) {
            throw new ParseException(
                    "Multiple matches for " +
                            matchedEntries.stream()
                                    .map(e -> e.getKey().getName())
                                    .collect(Collectors.joining())
                    , 0 );
        }
        Class<?> matchedClass = matchedEntries.stream()
                .filter(e -> e.getValue().size() == maxRequires)
                .findFirst()
                .get()
                .getKey();

                // знаходимо фабричний метод у matchedClass
        Method factoryMethod = null;
        for( Method method : matchedClass.getDeclaredMethods() ) {
            if( method.isAnnotationPresent( FromJsonFactory.class ) ) {
                factoryMethod = method;
            }
        }
        if( factoryMethod == null ) {  // метод з анотацією не знайшли, шукаємо "fromJson(JsonObject jsonObject)"
            try {
                factoryMethod = matchedClass.getDeclaredMethod( "fromJson", JsonObject.class );
            }
            catch( NoSuchMethodException ignore ) { }
        }
        if( factoryMethod == null ) {   // "fromJson" також не знайдено
            throw new ParseException( "Не знайдено фабричного методу " + matchedClass.getName(), 0 );
        }

        // запускаємо фабричний метод
        factoryMethod.setAccessible( true );
        try {
            return (Literature) factoryMethod.invoke( null, jsonObject );
        }
        catch( InvocationTargetException | IllegalAccessException ex ) {
            throw new ParseException( ex.getMessage(), 0 );
        }
    }

    private static Set<Class<?>> classes;
    private static Set<Class<?>> getLiteratureClasses() {
        return getLiteratureClasses(false);
    }
    private static Set<Class<?>> getLiteratureClasses(boolean forceReload) {
        if( classes == null || forceReload ) {
            String packageName = "itstep.learning.oop";
            try(
                    InputStream stream = Objects.requireNonNull(
                            ClassLoader.getSystemClassLoader()
                                    .getResourceAsStream( packageName.replaceAll( "[.]", "/" ) ));
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream))
            ) {
                classes = reader.lines()
                        .filter( line -> line.endsWith(".class") )
                        .map( className -> {
                            try {
                                return Class.forName( packageName + "."
                                        + className.substring(0, className.lastIndexOf('.')));
                            } catch (ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .filter( cls -> !Modifier.isAbstract(cls.getModifiers()) && Literature.class.isAssignableFrom(cls) )
                        .collect( Collectors.toSet() );
            }
            catch (IOException ex) {
                System.err.println( ex.getMessage() );
            }
        }
        return classes;
    }

    private static Map<Class<?>, List<String>> classesWithFields;
    private static Map<Class<?>, List<String>> getLitClassesWithRequiredFields() {
        if( classesWithFields == null ) {
            classesWithFields = new HashMap<>();
            for( Class<?> cls : getLiteratureClasses() ) {
                List<String> requiredFields = new ArrayList<>();
                for( Field field : cls.getDeclaredFields() ) {
                    if( field.isAnnotationPresent( Required.class ) ) {
                        requiredFields.add( field.getName() );
                    }
                }
                classesWithFields.put( cls, requiredFields );
            }
        }
        return classesWithFields;
    }
}
/*
Д.З. Повторити ООП вправи на проєкті "магазин" з сутностями "товар"
та його реалізаціями.
Забезпечити
- збереження товарів у JSON файлі та його передачу до збірки (ресурсів)
- автоматичне визначення типу товару за переліком необхідних полів
- фабричне створення товарів з аналізу фабричних методів усіх наявних класів товарів
   (перелік класів визначати шляхом сканування пакету)
 */