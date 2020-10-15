package edu.java.note07.linkedList;

class ListNode {
	public int value; // the storage value
	public ListNode next; // a reference，记录下个抽屉编号的黄色小纸条
	
	public ListNode(int x) { // constructor
		this.value = x;
	}
}

public class Main {
	public static int countNode(ListNode head) {
		// 一般在遍历的时候，要创建 cur ，用来标记当前看到了哪个list node
		// sanity check (尽管后面逻辑会cover它）
		if (head == null) {
			return 0;
		}
		
		int count = 0;
		ListNode cur = head;
		
		while(cur != null) {
			count++;		// 计数器加一
			cur = cur.next; // 打开抽屉，读黄色小纸条
		}
		
		return count;
	}
	
	// Q1 Given a linked list, find the index - k element of it. k starts from 0.
	public static ListNode findNode(ListNode head, int k) {
		if (head == null) {
			return null;
		}
		
		ListNode cur = head;
		int i = 0;
		
		// k --> 可能出现k超过index范围的情况，要考虑。但是只要根据“cur.next要先check”就不会出问题了。
		while(i < k && cur != null) {;
			cur = cur.next; // 只要涉及cur.next，就要先check cur!= null。
			i++;
		}
		
		return cur; // 考虑到返回的是cur，所以while循环不能包括等于的情况。等于时应该已经退出。
	}
	
	// Q2 How to find the middle node of a linked list?
	/*  Analysis:
	 *  middle node - odd是中点（奇数个） oven则是规定为中线偏head的那个node。
	 *  快慢指针法，slow每次走1步，fast每次走2步。
	 *  
	 *  先分析奇数个情况：
	 *  N1	N2	N3	N4	N5	Null	(odd)
	 *  sf
	 *  	s	f
	 *  		s		f	slow 停在答案了，希望停止。此时fast.next == null
	 *  
	 *  再分析偶数个情况：
	 *  N1	N2	N3	N4	N5	N6	Null	(Even)
	 *  sf
	 *  	s	f
	 *  		s		f	slow 停在答案了，希望块慢指针停下。此时fast.next.next == null
	 *  
	 *  分析条件，发现应该用 && 链接 两个 != 
	 */
	public static ListNode midNode(ListNode head) {
		// sanity check - 包括所有parameters
		if (head == null) {
			return null;
		}
		
		ListNode slow = head;
		ListNode fast = head;
		
		while(fast.next != null && fast.next.next != null) {
			// 要先检查odd，再检查even。
			// 尽管这俩是互斥的，但是不能变顺序。因为.next.next前要先看.next是否存在
			slow = slow.next;
			fast = fast.next.next;
		}
		
		return slow; // slow是结果
	}
	
	// Merge two sorted LinkedList into one long sorted LinkedList.
	public static ListNode merge(ListNode head1, ListNode head2) {
		// corner case
		if (head1 == null) {
			return head2;
		} else if (head2 == null) {
			return head1;
		}
		
		// dummy head - a helper first node
		ListNode dummyNode = new ListNode(0);
		ListNode cur1 = head1;
		ListNode cur2 = head2;
		ListNode cur = dummyNode;
		
		// concatenate and move the smaller one if both is not null
		while( cur1 != null && cur2 != null) {
			if (cur1.value < cur2.value) {
				cur.next = cur1;
				cur1 = cur1.next;
			} else {
				cur.next = cur2;
				cur2 = cur2.next;
			}
			// [NOTICE] DON'T FORGET TO MOVE cur
			cur = cur.next;
		}
		
		// post-processing, DO NOT MOVE cur.
		if (cur1 == null) {
			cur.next = cur2;
		}
		if (cur2 == null) {
			cur.next = cur1;
		}
		
		// return head (dummy.next)
		return dummyNode.next;
	}
	
	
	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		
		node1.next = node2;
		node2.next = node3;
		
		System.out.println(countNode(node1));
		System.out.println(midNode(node1).value);
	}
}