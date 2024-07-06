package itstep.learning.basics;

import java.util.Scanner;

/**
 * Введення та виведення даних у консолі
 */
public class Console {
    public void demo() {
        System.out.print("Введення та виведення даних у консолі\n");
        // для введення використовується робота з потоком введення System.in
        // Оскільки пряма робота з потоком незручна, вживають "оболонки" на кшталт Scanner
        Scanner scanner = new Scanner( System.in );
        // System.out.print( "Your name: " );
        // String name = scanner.nextLine();  // next() - наступне "слово" - до пробілу
        // System.out.printf( "Hello, %s!\n", name );
        System.out.print( "Enter numbers: " );
        int sum = 0;
        // while( scanner.hasNextInt() ) {  // зависає - потік консолі не закінчується ніколи
        //     sum += scanner.nextInt();
        // }
        String[] strs = scanner.nextLine().split(" ");
        for (String str : strs) {
            sum += Integer.parseInt( str );
        }
        System.out.printf("Sum: %d\n", sum);
    }
}
/*
Д.З. Реалізувати англо-український словник:
є вбудована база слів, користувач може ввести слово і
одержати переклад.
Також може доповнити базу, ввівши слово та переклад.
Інтерфейс - консольний.
 */