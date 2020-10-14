package edu.java.note06.binary_search;

public class Main {
	/*
	 * 1. 要保证搜索空间能每次减少，不能有死循环。
	 * 		（比如mid +- 1的时候要考虑）
	 * 		（只剩1、2个元素时可能出现死循环，要考虑） 
	 * 2. 要保证target不能被rule out，一定不能在被扔掉的search space里。
	 * 		（比如i<=j 与 mid +- 1的时候要考虑）
	 */
	public static int binarySearch(int[] a, int target) {
		// sanity check
		if (a == null || a.length == 0) { // 其实算法可以解决empty情况
			return -1;
		}

		// initialize left and right as search space
		int left = 0;
		int right = a.length - 1;
		
		while (left <= right) { // until no element
			// int mid = (left + right) / 2; but this sum may overflow.
			int mid = left + (right - left) / 2;	
			if (a[mid] == target) {		 // case 1
				return mid;
			} else if (a[mid] > target) {// case 2
				right = mid - 1;
			} else { 					 // case 3
				left = mid + 1;
			}
		}
		return -1;
	}
	
	
	public static int firstOccur(int[] array, int target) {
		// sanity check
		if (array == null || array.length == 0) {
			return -1;
		}
		
		int left = 0;
		int right = array.length;
		while (left < right-1) { 
		// left == right-1 (2元素)  left == right （1元素）都会退出
			int mid = left + (right - left) / 2;
			if (array[mid] == target) {
				right = mid; // 为了不删掉，所以不用-1.
			} else if (array[mid] < target) {
				left = mid;  // 要右边所以处理左边，为了对称就不为+1了，实际上可以。
			} else {
				right = mid; // 为了对称所以不用-1. 发现 可以合并到array[mid] >= target里面。
			}
		}
		
		// post-process
		if (array[left] == target) {
			return left;
		}
		if (array[right] == target) {
			return right;
		}
		return -1;
	}

	public static int closest(int[] array, int target) {
		// sanity check
		if (array == null || array.length == 0) {
			return -1;
		}
		// Initialize
		int left = 0;
		int right = array.length - 1;
		
		while (left < right - 1) {
			// 由于不打算把mid删掉，所以要避免死循环的情况出现。
			int mid = left + (right - left) / 2;
			if (array[mid] == target) {
				return mid;
			} else if (array[mid] < target) {
				left = mid;
			} else {
				right = mid;
			}
		}
		
		// post-process
		if (Math.abs(array[right]-target) <= Math.abs(array[left]-target)) {
			return right;
		}
		return left; // 不写else没关系
	}
	
	public static int lastOccur(int[] A, int T) {
		// find the last occurrence of T in A
		// or return -1 if there is no such index
		int left = 0;
		int right = A.length - 1;
		while (left < right - 1) { // until only two element (avoid infinite loop)
			int mid = left + (right - left) / 2; // middle
			if (A[mid] == T) {
				left = mid;  // no mid + 1, because this may be the only one.
			} else if (A[mid] > T) { // focus on left part
				right = mid - 1;
			} else { // focus on right part
				left = mid + 1;
			}
		}
		if(A[right] == T) {
			return right;
		}
		if(A[left] == T) {
			return left;
		}
		return -1;
	}
	
	public static int lastOccur_WithBug(int[] A, int T) {
		// find the last occurrence of T in A
		// or return -1 if there is no such index
		int left = 0;
		int right = A.length - 1;
		int mid = -1;
		while (left < right) { // until 1 empty (BUG: arise infinite loop) 
			/*
			 * v 2,3,3,5
			 * i 0,1,2,3
			 *       R L
			 *       M      infinite loop: two consecutive index: [(n) + (n+1)] / 2 == n;  and then left = n, remain n;
			 *              ! fail to decrease the search space, when there are only two elements and left = mid;
			 */
			mid = left + (right - left) / 2;
			if (A[mid] == T) {
				left = mid;
			} else if (A[mid] > T) {
				right = mid - 1;	// BUG: remove target accidentally.
			} else {
				left = mid + 1;
			}
		}
		if(A[mid] == T) { // mid will be (index of target) + 1;
			return mid;
		}
		return -1;
	}
	
	public static int[] search(int[][] matrix, int target) {
		// sanity check
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return new int[] {-1, -1};
		}
		
		int m = matrix.length;    // #row
		int n = matrix[0].length; // #column
		
		int left = 0;
		int right = m * n - 1;
		
		while(left <= right) { // 用的经典版本，until empty
			int mid = left + (right - left) / 2;
			int row = mid / n;
			int col = mid % n;
			if (target == matrix[row][col]) {
				return new int[] {row, col};
			} else if (target < matrix[row][col]) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		
		return new int[] {-1, -1};
	}
	
	public static void main(String[] args) {
		int[] a = new int[] { 2, 3, 3, 5 };
		System.out.println(binarySearch(a, 2));
		System.out.println(lastOccur(a, 1));
		// System.out.println(lastOccur_WithBug(a, 3));
		System.out.println(20_0000_0000 + 15_0000_0000); //overflow -794967296
		System.out.println(closest(a,4));
	}
}