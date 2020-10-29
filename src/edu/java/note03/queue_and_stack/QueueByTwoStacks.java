package edu.java.note03.queue_and_stack;

import java.util.Deque;
import java.util.LinkedList;

// implementing a queue by using two stacks.
// The queue should provide:
// size(), isEmpty(), offer(), poll(), and peek() operations
// When the queue is empty, poll(), peek() should return null

// Solution:
//  [ 5 4 3 2 1 ]			[			]
//  [  			]			[ 1 2 3 4 5 ]  
//  [ 7 6 		]			[ 3 4 5		]  1 2
//  [ 8 7 6 	]			[ 5			]  3 4

//  [ 			]			[ 6 7 8     ]  5
//  [ 			]			[ 8 		]  6 7
//  top		bottom        top       bottom
//     stack 1              stack 2

// when you want to get something, look to stack 2,
// if stack 2 is empty,
// move 'all' elements from stack 1 to stack 2.

public class QueueByTwoStacks {
	private Deque<Integer> in;
	private Deque<Integer> out;
	
	// constructor
	public QueueByTwoStacks() {
		in = new LinkedList<>();
		out = new LinkedList<>(); 
	}
	// [1] utilities::
	// 1. move, move all elements from in stack to out stack.
	//    only move when out stack is empty.
	private void move() {
		if (out.isEmpty()) {
			while (!in.isEmpty()) {
				out.offerFirst(in.pollFirst());
			}
		}
	}
	
	// 2. size, sum up and return size of these two stacks
	public int size() {
		return in.size() + out.size();
	}
	
	// 3. isEmpty, check if the size() is empty.
	public boolean isEmpty() {
		return in.size() == 0 && out.size() == 0;
	}
	
	// [2] methods:
	// 1. poll, pollFirst the top of (out)
	// move all elements from (in) if (out) is empty.
	public Integer poll() {
		this.move(); // check if out is empty is the duty of move itself.
		if (out.isEmpty()) {
			return null;
		} else {
			return out.pollFirst();
		}
	}
	
	// 2. offer, offerFirst() a value into (in)
	public void offer(int value) {
		in.offerFirst(value);
	}
	
	// 3. peek, peekFirst() the top of (out)
	// move all elements from (in) if (out) is empty.
	public Integer peek() {
		this.move();
		return out.isEmpty() ? null : out.peekFirst();
	}
}
