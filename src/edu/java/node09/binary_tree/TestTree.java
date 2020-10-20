package edu.java.node09.binary_tree;

class TreeNode {
	int value;
	TreeNode left;
	TreeNode right;

	public TreeNode(int value) {
		this.value = value;
	}
}

public class TestTree {
	public static void preOrder(TreeNode root) {
		// base case -- null
		if (root == null) {
			return;
		}

		// recursion rule: print, go left, go right;
		System.out.print(root.value + ", ");
		preOrder(root.left);
		preOrder(root.right);
	}

	public static void preOrderLeaf(TreeNode root) {
		// base case -- node with only one child, or leaf node
		if (root == null) { // if node has only one child, its left or right will be null.
			return;
		}
		if (root.left == null && root.right == null) {
			System.out.print(root.value + ", ");
			return;
		}
		// recursion rule: print, go left, go right;
		System.out.print(root.value + ", ");
		preOrder(root.left);
		preOrder(root.right);
	}

	public static int getHeight(TreeNode root) {
		// base case - null, return 0
		if (root == null) {
			return 0;
		}
		// recursion rule
		int leftHeight = getHeight(root.left);
		int rightHeight = getHeight(root.right);
		// return (leftHeight > rightHeight) ? leftHeight + 1 : rightHeight + 1;
		return Math.max(leftHeight, rightHeight) + 1;
	}

	public static int countNode(TreeNode root) {
		// base case - null
		if (root == null) {
			return 0;
		}
		// recursion rule
		int leftCount = countNode(root.left);
		int rightCount = countNode(root.right);
		return leftCount + rightCount + 1;
	}

	public static boolean isBalanced(TreeNode root) {
		// this is not an efficient one. A more efficient one is followed.
		// base case - null
		if (root == null) {
			return true;
		}
		// recursion rule: (a subtree is unbalanced? heights diff > 1?)
		boolean ifLeftBalance = isBalanced(root.left);
		boolean ifRightBalance = isBalanced(root.right);
		if(!ifLeftBalance || !ifRightBalance) {
			return false;
		} else {
			int leftHeight = getHeight(root.left);
			int rightHeight = getHeight(root.right);
			if (Math.abs(leftHeight - rightHeight) > 1) {
				return false;
			} else {
				return true;
			}
		}
	}
	
	public static int getHeightOrUnbalance(TreeNode root) {
		/* the efficient one,
		 * return -1: unbalanced
		 * return >=0: height
		 * */ 
		// base case - null
		if (root == null) {
			return 0;
		}
		// recursion rule: (a subtree is unbalanced? heights diff > 1?)
		int left = getHeightOrUnbalance(root.left);
		int right = getHeightOrUnbalance(root.right);
		// left or right is unbalanced.
		if(left == -1 || right == -1) {
			return -1;
		} else {
			// diff
			if (Math.abs(left - right) > 1) {
				return -1;
			} else {
				return Math.max(left, right) + 1; // height
			}
		}
	}
	// wrap the getHeightOrUnbalance into a new method.
	public static boolean ifBalancedUpdated(TreeNode root) {
		return getHeightOrUnbalance(root) >= 0;
	}

	public static void main(String[] args) {
		// create a simple tree.
		TreeNode node1 = new TreeNode(8);
		TreeNode node2 = new TreeNode(5);
		TreeNode node3 = new TreeNode(10);
		TreeNode node4 = new TreeNode(15);
		TreeNode node5 = new TreeNode(2);
		TreeNode node6 = new TreeNode(6);
		node1.right = node2;
		node2.left = node3;
		node2.right = node4;
		node3.right = node5;
		node4.right = node6;
		/*
		 *     8 
		 *   /  \ 
		 * null   5 
		 * 		/   \ 
		 * 	   10    15 
		 * 	   / \   / \ 
		 * 	 null 2 null 6
		 * 
		 */

		preOrder(node1); // 8, 5, 10, 2, 15, 6,
		System.out.print("\t preOrder - use null as base case\n");
		preOrderLeaf(node1); // 8, 5, 10, 2, 15, 6,
		System.out.print("\t preOrder - use leaf as base case\n");
		System.out.print(getHeight(node1));
		System.out.print("\t height\n");
		System.out.print(countNode(node1));
		System.out.print("\t count\n");
		System.out.print(isBalanced(node1));
		System.out.print("\t balanced?\n");
		System.out.print(ifBalancedUpdated(node1));
		System.out.print("\t balanced? use updated method\n");
	}
}
