package itstep.learning.fs;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;

public class FileDemo {
    public void run() {
        System.out.println("File demo");
        // java.io.File - основа для роботи як з FS, так і з даними
        String dirName = "./subdirectory";
        File dir = new File(dirName);  // створення об'єкту File не впливає
        // на файлову систему (у ній нічого не створюється)
        if (dir.isDirectory()){
            System.out.println(dirName + " exists");
        }
        else {
            dir.mkdir();
            System.out.println(dirName + " created");
        }
        // try-with-resource аналог using(C#) - з автоматичним закриттям ресурсів
        // try(){}catch...
        try( InputStream inputStream = Objects.requireNonNull(
                this.getClass().getClassLoader().getResourceAsStream("library.json")
        )) {
                System.out.println( readStream(inputStream) );
        }
        catch (IOException ex) {
            System.err.println( ex.getMessage() );
        }

        String packageName = "itstep.learning.oop";
        try(
            InputStream stream = Objects.requireNonNull(
                    ClassLoader.getSystemClassLoader()
                        .getResourceAsStream( packageName.replaceAll( "[.]", "/" ) ));
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream))
        ) {
            reader.lines()
                    .filter( line -> line.endsWith(".class") )
                    .forEach( System.out::println );
        }
        catch (IOException ex) {
            System.err.println( ex.getMessage() );
        }
        // FileDemo.class == Class.forName("FileDemo")
    }

    private String readStream( InputStream inputStream ) throws IOException {
        ByteArrayOutputStream byteBuilder = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 16];
        int length;
        while( ( length = inputStream.read(buffer) ) != -1 ) {
            byteBuilder.write( buffer, 0, length );
        }
        return byteBuilder.toString( StandardCharsets.UTF_8.name() );
    }
}
/*
Робота з файлами
поділяється на дві задачі
- робота з файловою системою (створення, видалення, копіювання файлів/директорій)
- зберігання даних у файлах

! Особливості при роботі з файлами
- робоча директорія: проєкт запускається командним рядком, у якому може бути зазначена
   "домашня" директорія ("./")
   це може бути директорія
   = з головним файлом (App.class)
   = з виконавчим файлом (java.exe)
   = коренева директорія проєкту
- будь-який з варіантів вище не є досконалим з точки зору передачі готового проєкту,
   бажано працювати з внутрішніми директоріями у "збірці" (target).
   Це реалізується ідеєю ресурсів - спец. директорією "resources" у складі проєкту:
   її склад автоматично копіюється у збірку.
- звернення до ресурсів здійснюється через спец. інструмент - завантажувач класів.
   Кожен об'єкт може надати інформацію про свого завантажувача, який насправді є
   спільним для всіх класів.
 */