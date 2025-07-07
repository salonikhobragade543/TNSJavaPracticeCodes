abstract class Person {
    int age;

    Person(int age) {
        this.age = age;
    }

    abstract void career();

    void normal() {
        System.out.println("This is a normal method.");
    }

    static void hello() {
        System.out.println("Hello from Person!");
    }
}

class Son extends Person {
    Son(int age) {
        super(age);
    }

    void career() {
        System.out.println("I want to be an engineer. Age: " + age);
    }
}

class Daughter extends Person {
    Daughter(int age) {
        super(age);
    }

    void career() {
        System.out.println("I want to be a doctor. Age: " + age);
    }
}

public class Abstract {
    public static void main(String[] args) {
        Son son = new Son(30);
        son.career();
        son.normal();

        Person daughter = new Daughter(28);
        daughter.career();

        Person.hello();
        // Person mom = new Person(45); // Not allowed, Person is abstract
    }
}
