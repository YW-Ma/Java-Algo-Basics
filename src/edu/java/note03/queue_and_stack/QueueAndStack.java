package edu.java.note03.queue_and_stack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Deque;

public class QueueAndStack {
	
	public static int maxInQueue1(Queue<Integer> queue) {
		// queue will remain untouched after calling this function.
		int max = queue.peek();
		for(int i : queue) {
			if (max < i) {
				max = i;
			}
		}
		return max;
	}
	
	public static int maxInQueue2(Queue<Integer> queue) {
		// Due to the pass-by-value feature, after calling this function,
		// this queue will become empty.
		int max = queue.poll();
		while(!queue.isEmpty()) {
			max = Math.max(max, queue.poll());
		}
		return max;
	}
	
	public static int sumOfStack1(Deque<Integer> stack) {
	    int sum = 0;
	    for(int i : stack) {
	      sum += i;
	    }
	    return sum;
	}
	
	public static int sumOfStack2(Deque<Integer> stack) {
		// Due to the pass-by-value feature, after calling this function,
		// this stack will become empty.
	    int sum = 0;
	    while(!stack.isEmpty()) {
	    	sum += stack.pop();
	    }
	    return sum;
	}
	
	public static void main(String[] args) {
		// Create an empty queue
		Queue<Integer> queue = new LinkedList<>();
		// Add elements
		queue.offer(10);
		queue.offer(2);
		queue.offer(3);
		queue.offer(4);
		queue.offer(5);
		queue.offer(6);
		queue.offer(9);
		// Remove an element from the [front]
		queue.poll();
		// Take a look at the front element
		int frontElement = queue.peek();
		// Get the size of the queue
		int queueSize = queue.size();
		// Determine whether the queue is empty
		boolean isEmptyQueue = queue.isEmpty();
		System.out.println("A Queue: front element is " + frontElement + " with size of " + queueSize);
		
		System.out.println(maxInQueue1(queue));
		System.out.println(maxInQueue2(queue));

		// Create an empty stack [Double end queue]
		Deque<Integer> stack = new LinkedList<>();
		// Add elements
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		// Remove an element from the [top]
		stack.pop();
		// Take a look at the [top] element
		int topElement = stack.peek();
		// Get the size of the stack
		int stackSize = stack.size();
		// Determine whether the queue is empty
		boolean isEmptyStack = stack.isEmpty();
		System.out.println(
				"A stack(implemented with deque): top element is " + topElement + " with size of " + stackSize);
	
		System.out.println(sumOfStack1(stack)); // 10
		System.out.println(sumOfStack2(stack)); // 10, also clear the stack.
		System.out.println(sumOfStack1(stack)); // 0
		System.out.println(9_0000_0012); // 0
	}
}
