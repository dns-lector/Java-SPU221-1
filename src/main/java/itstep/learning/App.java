package itstep.learning;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonReader;
import itstep.learning.basics.Arrs;
import itstep.learning.basics.Console;
import itstep.learning.basics.Vars;
import itstep.learning.fs.FileDemo;
import itstep.learning.oop.OopDemo;

import java.io.StringReader;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        // System.out.println( "Hello World!" );
        // new Vars().demo();
        // new Arrs().demo();
        // new Console().demo();
         new OopDemo().run();
//        new FileDemo().run();

    }
}
/*
- Встановлюємо Idea
- Завантажуємо JDK 1.8
- Створюємо новий проєкт за архетипом Maven ...quickstart
   у додаткових налаштуваннях зазначаємо Group-id itstep.learning
- Налаштовуємо конфігурацію запуску
   Edit Configuration -- Add new -- Application -- [вибрати головний клас - App]
 */