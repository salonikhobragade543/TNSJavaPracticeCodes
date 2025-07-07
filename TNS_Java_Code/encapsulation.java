public class encapsulation {
    // Private fields (data hiding)
    private String name;
    private int age;

    // Public getter for name
    public String getName() {
        return name;
    }

    // Public setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Public getter for age
    public int getAge() {
        return age;
    }

    // Public setter for age
    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        }
    }

    // Main method to test encapsulation
    public static void main(String[] args) {
        encapsulation person = new encapsulation();
        person.setName("John");
        person.setAge(25);

        System.out.println("Name: " + person.getName());
        System.out.println("Age: " + person.getAge());
    }
}
