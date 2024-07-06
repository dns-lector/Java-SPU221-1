package itstep.learning;

import itstep.learning.basics.Arrs;
import itstep.learning.basics.Console;
import itstep.learning.basics.Vars;

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
        new Console().demo();
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