package itstep.learning.basics;

/**
 * Типи даних та змінні
 */
public class Vars {
    public void demo() {
        System.out.println( "Types and Variables" );
        /*
        Аналогічно до C# в Java є типи-значення (прімітиви) та типи-референси
        На відміну від C#, користувач може створювати лише референсні типи.
         */
        byte  b1 = 1;   // 8 bit  | числові типи - всі знакові (від -127..+128 для byte)
        short s1 = 2;   // 16     | беззнакових модифікацій не існує
        int   i1 = 3;   // 32     |
        long  l1 = 4;   // 64     |
        long  l2 = 100000000000L;
        int   iDef;     // принцип значень за замовчанням - діє, буде 0

        float f1 = 5.0f;   // 32 bit
        double d1 = 6.0;   // 64
        double d2 = 1.5E-3;

        char c1 = 'A';  // UTF-16

        boolean bo1 = true;

        d1 = d2 = 0.1;     // присвоєння - це вираз, тобто повертає результат
        if(bo1 = false){}  // це дозволяє його використання у інших виразах
        if((d1 = 2.0) > 1.0){}

        // Для всіх прімітивів є референсні аналоги, які ідуть замість boxing/unboxing
        Byte b2 = -1;
        Short s2 = -2;
        Integer i2 = -3;
        Long  l3 = -4L;    // і т.д.

        String str1 = "hello";                          // String - immutable, всі операції утворюють нові об'єкти
        String str2 = "hello";                          // Порівняння (==) референсних об'єктів -
        String str3 = new String( "hello" );            // відбувається саме за референсами, а
        if (str1 == str2) {                             // не за значеннями.
            System.out.println("str1 == str2");         // Оператори не перевантажуються
        }                                               // > str1 == str2
        else {                                          // > str1 != str3
            System.out.println("str1 != str2");         // Поведінка String - референсна, це
        }                                               // пояснює нерівність str1 та str3
        if (str1 == str3) {                             // Ріність str1 та str2 пояснюється
            System.out.println("str1 == str3");         // String-Pooling: всі однакові рядки, що
        }                                               // є у коді компілятор вважає за одну
        else {                                          //
            System.out.println("str1 != str3");         //
        }                                               // Для порівняння вмісту об'єктів - метод
        if (str1.equals(str3)) {                        // .equals()
            System.out.println("str1 equals str3");
        }
        else {
            System.out.println("str1 !equals str3");
        }
        final int i3 = 10;    // для створення констант - final
    }
}
