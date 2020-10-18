package edu.java.note08.sorting;

public class Main {
	
	// �����汾��merge sort������in-place��
	public static int[] mergeSort(int[] array) {
		// sanity check (null, 0) with base case(1)
		if (array == null || array.length <= 1) { // <=1 better than == 0 == 1
			return array;
		}
		
		// recursion -- ��Ϊ��һ��problem���Լ�subproblems�����û����õĽӿڲ�һ����������Ҫoverloadһ����
		return mergeSort(array, 0, array.length - 1);
	}
	
	// left �� right ��������ָ��Ҫ������һ���֡�
	private static int[] mergeSort(int[] array, int left, int right) {	
		// base case - ֻ��һ��Ԫ�ص�ʱ���˳�recursion��
		if (left == right) {
			return new int[] {array[left]};
		}
		
		// recursion rule
		  // subproblems
		int mid = left + (right - left) / 2; // ��ֹoverflow���㷨
		int[] leftResult = mergeSort(array, left, mid);
		int[] rightResult = mergeSort(array, mid + 1, right); //�������Ӳ����н���
		
		  // construct return value
		return merge(leftResult, rightResult);
	}
	
	private static int[] merge(int[] leftResult, int[] rightResult) {
		// ��Ҫ����index��˭С�ƶ�˭�� ��Ҫ����һ���µķ������顣
		int i = 0; // left index
		int j = 0; // right index
		int[] result = new int[leftResult.length + rightResult.length];
		int k = 0; // result index
		
		while (i < leftResult.length && j < rightResult.length) { 
			// i, j ��Ҫ�ں���Χ�ڣ�һ��ѭ��������������һ�������
			// ����һ����һ����ʣ��ʣ��һ�����߶����
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
		// ����Ҫif�ж����ĸ�case����Ϊ��while�˳��󣬲����ϵ��Ǹ�case������롣
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
