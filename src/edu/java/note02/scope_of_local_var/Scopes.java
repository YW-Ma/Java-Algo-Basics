package edu.java.note02.scope_of_local_var;

public class Scopes {
	public static void main(String[] args) {
		// int i = 0; 如果写在这里，那么int i = 5 会说duplicate   【1】
		{ 
			int j = 1;
			int i = 5; 
		}
		int i = 0;  // 可见的地方不重叠，则可以重复定义，同一个scope不可（java的特殊）。
		System.out.println("i: " + i);
		// System.out.println("j: " + j); //j cannot be resolved to a variable    【2】
	}
}
