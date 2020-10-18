package edu.java.note08.sorting;

public class Main {
	
	// 初级版本的merge sort，不是in-place的
	public static int[] mergeSort(int[] array) {
		// sanity check (null, 0) with base case(1)
		if (array == null || array.length <= 1) { // <=1 better than == 0 == 1
			return array;
		}
		
		// recursion -- 因为第一个problem（以及subproblems）和用户调用的接口不一样，所以需要overload一个。
		return mergeSort(array, 0, array.length - 1);
	}
	
	// left 和 right 用来负责指出要处理哪一部分。
	private static int[] mergeSort(int[] array, int left, int right) {	
		// base case - 只有一个元素的时候，退出recursion。
		if (left == right) {
			return new int[] {array[left]};
		}
		
		// recursion rule
		  // subproblems
		int mid = left + (right - left) / 2; // 防止overflow的算法
		int[] leftResult = mergeSort(array, left, mid);
		int[] rightResult = mergeSort(array, mid + 1, right); //两摞卷子不能有交集
		
		  // construct return value
		return merge(leftResult, rightResult);
	}
	
	private static int[] merge(int[] leftResult, int[] rightResult) {
		// 需要两个index，谁小移动谁。 需要构造一个新的返回数组。
		int i = 0; // left index
		int j = 0; // right index
		int[] result = new int[leftResult.length + rightResult.length];
		int k = 0; // result index
		
		while (i < leftResult.length && j < rightResult.length) { 
			// i, j 都要在合理范围内，一次循环中最多打破其中一个情况。
			// 所以一定有一个会剩，剩下一个或者多个。
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
		// 不需要if判断是哪个case，因为从while退出后，不符合的那个case不会进入。
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
	
	public static void main(String[] args) {
		int[] arr = new int[] {-1, -7, 5, 3};
		selectionSort(arr);
		System.out.print(arr[1]);
	}
}
