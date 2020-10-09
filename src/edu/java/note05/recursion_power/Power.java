package edu.java.note05.recursion_power;

public class Power {
	public static long power(int a, int b) {
		// Assumption: b is non-negative
		// base case & corner case
		if (b == 0) { // b == 0 (including 0^0)
			return 1;
		}
		// shortcut: if a == 0
		if (a == 0) {
			return 0;
		}
		// recursion rule: power(a,b) = power(a,b / 2) * power(a,b / 2) and (* a) if
		// odd; (I will use "half" instead)
		// do not reduce the problem by reducing a, it's far more complicated than
		// reducing b.
		long half = power(a, b / 2);
		return b % 2 == 0 ? half * half : half * half * a;
	}
	
	public static void main(String[] args) {
		System.out.println(power(5,10));
	}
}