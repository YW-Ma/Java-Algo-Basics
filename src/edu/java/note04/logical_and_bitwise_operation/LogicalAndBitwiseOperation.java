package edu.java.note04.logical_and_bitwise_operation;

public class LogicalAndBitwiseOperation {
	public static void main(String[] args) {
		if (f() || g()) { // F1
			System.out.println("1");
		} else {
			System.out.println("2");
		}

		if (g() && f()) { // G2
			System.out.println("1");
		} else {
			System.out.println("2");
		}

		if (g() & f()) { // GF2, not short-circuit effect.
			System.out.println("1");
		} else {
			System.out.println("2");
		}
	}

	public static boolean f() {
		System.out.print("F");
		return true;
	}

	public static boolean g() {
		System.out.print("G");
		return false;
	}
}
