//2020/10/1 Thursday
package edu.java.note01.class_and_instance_var;

//Class
//1. Attributes + Behavior
class Teacher {
	
}
class Student {
	// Constructor
	// 1. construct objects --> new operator will invoke constructors.
	// 2. 标志：(1) name = class name, (2) no return type
	//    所以：Student(){} 也会被认为是定义了。
	// 3. If user defines a constructor, Java won't create default constructor.
	// 4. multiple user-defined constructor (overloading)
	
	public Student(String name, int age, double gpa, boolean enrolled) {
		// shadowing, 上方穿入的name遮盖了同名的class attribute
		this.name = name;
		this.age = age;
		this.gpa = gpa;
		this.enrolled = enrolled;
	}
	
	public Student(String name, int age, boolean enrolled) {
		this.name = name;
		this.age = age;
		this.enrolled = enrolled;
	}
	
	
	// Getters and Setters
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	
	public double getGpa() {
		return this.gpa;
	}
	
	// Attributes - 他们活在class的scope里，没有和function做绑定。
	
	//	public--都可访问（公有）
	//	protected--包内和子类可访问（保护）
	//	不写(default)--包内可访问 （默认）
	//	private--类内可访问（私有）
							    // 零假空 原则。
	public String name;			// default: null
	public int age;
	private double gpa;			// default: 0
	protected boolean enrolled; // default: false
	
	// finalized value, 一旦初始化就不能修改，变量名用全大写
	// Java constants should be all UPPERCASE,
	// where words are separated by underscore character (“_”).
	final String SCHOOL_NAME = "xx Middle School";
	public static String code = "Don't do evil";  // static variable 要用static方法访问（直接访问 or Student.code）
}

//public 规定了可见度，一个java文件只能有一个public class，就是entry point所在的那个。
public class AddStudent {
	public static void main(String[] args) {
		Student jam = new Student("Tom", 15, 4.0, true);
		System.out.println(jam.enrolled);
		jam.getGpa();
	}
}