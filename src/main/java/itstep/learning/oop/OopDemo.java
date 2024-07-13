package itstep.learning.oop;

public class OopDemo {
    public void run() {
        System.out.println("OOP");
        Library library = new Library();
        library.printFunds();
        library.printCopyable();
        library.printPeriodic();
    }
}
/*
Library  <>------> Literature [Title]
                 /     |      \
            Book    Newspaper   Journal
          [Author]   [Date]     [Number]
 */