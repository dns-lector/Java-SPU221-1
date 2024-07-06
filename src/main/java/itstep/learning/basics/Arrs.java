package itstep.learning.basics;

import java.util.*;

/**
 * Масиви, колекції та цикли
 */
public class Arrs {
    public void demo() {
        System.out.println("Arrays demo");
        int[] arr1 = {1, 2, 3, 4, 5};   // з ініціалізацією
        int[] arr2 = new int[10];       // з розміром
        for (int i = 0; i < arr1.length; i += 1) {
            System.out.print( arr1[i] + " " );
            // Java немає загального .toString(), тому переведення
            // до рядка часто робиться як додавання порожного рядку
            // чи пробілу: 2 + '' -> 2.toString()
        }
        System.out.println();
        for( int element : arr2 ) {  // foreach
            System.out.printf("%d ", element);
        }
        System.out.println();
        int[][] arr2d = {   // багаторозмірні масиви - тільки "рвані"
                {1, 2, 3},
                {4, 5, 6, 0},
                {7, 8}
        };
        for( int[] arr : arr2d ) {   // автовизначення типу немає (var, auto)
            for( int element : arr ) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
        // Колекції
        List<Integer> list1 = new LinkedList<>();
        // List - інтерфейс для колекцій
        // ArrayList, LinkedList - реалізації
        // <> - "diamond operator"
        list1.add(10);
        list1.add(20);
        list1.add(30);
        list1.add(40);
        list1.remove(1);
        for( int element : list1 ) {
            System.out.print(element + " ");
        }
        System.out.println( list1.get(2) );   // list1[2]

        // ~Dictionary
        Map<String, String> map1 = new HashMap<>();
        map1.put("key1", "value1");
        map1.put("key2", "value2");
        map1.put("key4", "value4");
        map1.put("key3", "value3");
        for( String key : map1.keySet() ) {
            System.out.println( key + ": " + map1.get(key) );
        }
    }
}
