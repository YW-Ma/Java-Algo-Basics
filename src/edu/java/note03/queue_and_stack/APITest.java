package edu.java.note03.queue_and_stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

public class APITest {
	public static void testQueue() {
		// Create, add, and remove.
		Deque<Integer> queue = new LinkedList<>();
		Random r = new Random(0); // set seed as 0
		for (int i = 0; i < 5; i++) {
			queue.offer(r.nextInt(100));
		}
		System.out.println("queue head is " + queue.peek());
		while (!queue.isEmpty()) {
			System.out.print(queue.poll() + " ");
		}
		System.out.print("\n");
	}
	
	public static void testStack() {
		Deque<Integer> stack = new LinkedList<>();
		Random r = new Random(0); // the same seed
		for (int i = 0; i < 5; i++) {
			stack.offerFirst(r.nextInt(100));
		}
		System.out.println("stack top is " + stack.peekFirst());
		while (!stack.isEmpty()) {
			System.out.print(stack.pollFirst() + " ");
		}
	}
	
	public static void main(String args[]) {
		testQueue();
		testStack();
	}
}
