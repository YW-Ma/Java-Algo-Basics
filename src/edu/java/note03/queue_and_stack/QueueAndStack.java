package edu.java.note03.queue_and_stack;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Deque;

public class QueueAndStack {
	public static void main(String[] args) {
		// Create an empty queue
		Queue<Integer> queue = new LinkedList<>();
		// Add elements
		queue.offer(1);
		queue.offer(2);
		// Remove an element from the [front]
		queue.poll();
		// Take a look at the front element
		int frontElement = queue.peek();
		// Get the size of the queue
		int queueSize = queue.size();
		// Determine whether the queue is empty
		boolean isEmptyQueue = queue.isEmpty();
		System.out.println("A Queue: front element is " + frontElement + " with size of " + queueSize);
		
		
		// Create an empty stack [Double end queue]
		Deque<Integer> stack = new LinkedList<>();
		// Add elements
		stack.push(1);
		stack.push(2);
		// Remove an element from the [top]
		stack.pop();
		// Take a look at the [top] element
		int topElement = stack.peek();
		// Get the size of the stack
		int stackSize = stack.size();
		// Determine whether the queue is empty
		boolean isEmptyStack = stack.isEmpty();
		System.out.println("A stack(implemented with deque): top element is " + topElement + " with size of " + stackSize);
	}
}
