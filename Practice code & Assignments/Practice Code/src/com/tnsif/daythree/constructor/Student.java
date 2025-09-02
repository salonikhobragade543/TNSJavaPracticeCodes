<<<<<<< HEAD
package com.tnsif.daythree.constructor;

public class Student {

	// default constructor
	public Student()
	{
		System.out.println("Default Constructor for student");
	}
	
	//parametrized constructor
	
	public Student(int id, String name)
	{
		System.out.println("Parametrized Constructor");
		System.out.println(id+" "+name);
	}
	public static void main(String[] args) {

		Student obj1 = new Student(); 
		Student obj2 = new Student(101, "Sakshi");
		
		
	}

}
=======
package com.tnsif.daythree.constructor;

public class Student {

	// default constructor
	public Student()
	{
		System.out.println("Default Constructor for student");
	}
	
	//parametrized constructor
	
	public Student(int id, String name)
	{
		System.out.println("Parametrized Constructor");
		System.out.println(id+" "+name);
	}
	public static void main(String[] args) {

		Student obj1 = new Student(); 
		Student obj2 = new Student(101, "Sakshi");
		
		
	}

}
>>>>>>> a990786 (Added MyPersonalMusicPlayer project as normal folder, not submodule)
