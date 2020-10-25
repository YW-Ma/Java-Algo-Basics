package edu.java.note08.sorting;

import java.util.Random;

public class Main {
	
	// This is the primitive version, not the in-place one.
	public static int[] mergeSort(int[] array) {
		// sanity check (null, 0) with base case(1)
		if (array == null || array.length <= 1) { // <=1 better than == 0 == 1
			return array;
		}
		
		// recursion -- å› ä¸ºç¬¬ä¸€ä¸ªproblemï¼ˆä»¥å�Šsubproblemsï¼‰å’Œç”¨æˆ·è°ƒç”¨çš„æŽ¥å�£ä¸�ä¸€æ ·ï¼Œæ‰€ä»¥éœ€è¦�overloadä¸€ä¸ªã€‚
		return mergeSort(array, 0, array.length - 1);
	}
	
	// left\right specify on which part we need to do mergesort.
	private static int[] mergeSort(int[] array, int left, int right) {	
		// base case - exit recursion when there are only one element
		if (left == right) {
			return new int[] {array[left]};
		}
		
		// recursion rule
		  // subproblems
		int mid = left + (right - left) / 2; // prevent overflow
		int[] leftResult = mergeSort(array, left, mid);
		int[] rightResult = mergeSort(array, mid + 1, right); // left / right part should not overlap.
		
		  // construct return value
		return merge(leftResult, rightResult);
	}
	
	private static int[] merge(int[] leftResult, int[] rightResult) {
		// éœ€è¦�ä¸¤ä¸ªindexï¼Œè°�å°�ç§»åŠ¨è°�ã€‚ éœ€è¦�æž„é€ ä¸€ä¸ªæ–°çš„è¿”å›žæ•°ç»„ã€‚
		// move the index whose value is smaller.
		int i = 0; // left index
		int j = 0; // right index
		int[] result = new int[leftResult.length + rightResult.length]; // return value
		int k = 0; // result index
		
		while (i < leftResult.length && j < rightResult.length) { 
			// i, j éƒ½è¦�åœ¨å�ˆç�†èŒƒå›´å†…ï¼Œä¸€æ¬¡å¾ªçŽ¯ä¸­æœ€å¤šæ‰“ç ´å…¶ä¸­ä¸€ä¸ªæƒ…å†µã€‚
			// æ‰€ä»¥ä¸€å®šæœ‰ä¸€ä¸ªä¼šå‰©ï¼Œå‰©ä¸‹ä¸€ä¸ªæˆ–è€…å¤šä¸ªã€‚
			// Both i, j should be limited within valid region. One loop only add one index,
			// So one loop can break only one of them. i.e. another array remains some elements unvisited. --> need post-process 
			if (leftResult[i] < rightResult[j]) { 
				// case 1 left is smaller
				result[k] = leftResult[i];
				i++;
			} else { 
				// case 2 right is smaller
				result[k] = rightResult[j];
				j++;
			}
			k++;
		}
		// possible situation:
		// case a: left result is done (empty)
		// case b: right result is done (empty)
		// ä¸�éœ€è¦�ifåˆ¤æ–­æ˜¯å“ªä¸ªcaseï¼Œå› ä¸ºä»Žwhileé€€å‡ºå�Žï¼Œä¸�ç¬¦å�ˆçš„é‚£ä¸ªcaseä¸�ä¼šè¿›å…¥ã€‚
		while (j < rightResult.length) {
			result[k] = rightResult[j];
			k++;
			j++;
		}
		
		while (i < leftResult.length) {
			result[k] = leftResult[i];
			k++;
			i++;
		}
		
		return result;
	}
	
	public static void selectionSort(int[] array) {
		for( int i = 0; i < array.length - 1; i++) {
			// i < length - 1, because the last element(length - 1) 
			// will be the biggest one.
			int minIndex = i;
			for( int j = i + 1; j < array.length; j++) {
				// j should cover all unsorted elements, including length - 1
				if (array[j] < array[minIndex]) {
					minIndex = j;
				}
			}
			swap(array, minIndex, i);
		}
	}
	
	private static void swap(int[] array, int x, int y) {
		int temp = array[x];
		array[x] = array[y];
		array[y] = temp;
	}
	
	
	// quick sort (wrapper) åŒ…è£…ç›’
	public static void quickSort(int[] array) {
		// sanity check.
		if (array == null || array.length <= 1) {
			return;
		}
		quickSort(array, 0, array.length - 1);
	}
	
	private static Random random = new Random(); // used to choose pivot.
	// random.nextInt(x) --> return a random number in [0, x) å�‡åŒ€åˆ†å¸ƒ
	// æŽ¥å�£ä¸�å¤ªç�†æƒ³ï¼Œå¦‚æžœéœ€è¦�[a,b] éœ€è¦�æŠŠaæ��å�–å‡ºæ�¥ï¼Œå¹¶ä¸”æ‰‹åŠ¨å�˜æˆ�inclusiveã€‚
	
	// quick sort (recursive part) é€’å½’éƒ¨åˆ†
	public static void quickSort(int[] array, int left, int right) {
		/* please sort [left, right] part of the array.
		 * 
		 * 1. base case 2. choose pivot 3. swap pivot to right, 
		 * 4. partition into red/blue   5. swap pivot back,
		 * 6. recursion rule,  handle red and blue teams.
		*/
		// [1] base case
		if (left >= right) { // refer to the analysis in my note
			return;
		}
		
		// [2] choose pivot, a random number in [left, right]
		int pivotIndex = left + random.nextInt(right - left + 1); 
		// å¸¸è§�é—®é¢˜1ï¼šå¦‚æžœå†™ left + random.next(right - left) + 1, é‚£ä¹ˆå·¦è¾¹å°±è¿‡äº†ï¼Œ[1, left + right]
		// å¸¸è§�é—®é¢˜2ï¼šå¦‚æžœå†™ left + random.next(right - left), é‚£ä¹ˆå�³è¾¹æ²¡æœ‰inclusiveï¼Œ[1, left + right)
		// æŽ¥å�£é—®é¢˜ï¼šä¸�èƒ½å†™ random.next(left, right + 1); æ²¡æœ‰è¿™ä¸ªæŽ¥å�£ã€‚å�ªèƒ½äº§ç”Ÿ[0, n)
		
		// [3] swap pivot to right..
		swap(array, pivotIndex, right); // not right-1, right is the rightmost element.
		
		// [4] partition into red/blue, need i and j to mark three parts.
		int i = left;		// we want to point i & j to unchecked part.
		int j = right - 1;
		while (i <= j) {
			if (array[i] < array[right]) {
				// case 1, notice, not pivotIndex. it's 'right' now!!!!
				i++;
			} else {
				// case 2 array[i] >= array[right]
				swap(array, i, j);
				j--;
			}
		}
		int ii = 0;
		boolean k = (ii=5) > 3;
		// [5] swap pivot back..
		swap(array, right, i); // not pivotIndex, but right
		
		// [6] recursion rule, handle red and blue teams
		/* ä¸€å¼€å§‹çš„På�¯èƒ½æ˜¯. At the beginning, P's location may be:
		 * xxxxxPxxxxxx
		 * æ‰§è¡Œè¿‡ç¨‹ä¸­æ—¶. During the execution, it is:
		 * xxxxxxxxxxxP
		 * äº¤æ�¢å›žåŽ»å�Žï¼Œå�¯èƒ½æ˜¯. After swapping back, it may be:
		 * xxPxxxxxxxxx,
		 * å�ªæœ‰iæ‰�èƒ½æ­£ç¡®æ ‡å¿—Pçš„ä½�ç½®ã€‚ So only i can mark the P correctly.
		 * */
		quickSort(array, left, i - 1);
		quickSort(array, i + 1, right);
	}
	
	
	public static void main(String[] args) {
		int[] arr = new int[] {-1, -7, 5, 3};
		quickSort(arr);
		System.out.print(arr[2]);
	}
}
