package edu.java.exercise.temp;

public class Main {
	public static int firstOccur(int[] array, int target) {
		if (array == null || array.length == 0) {
			return -1;
	    }
	    int left = 0;
	    int right = array.length - 1;
	    while (left < right - 1) {
	      int mid = left + (right - left) / 2;
	      if (array[mid] >= target) right = mid;
	      else left = mid;
	    }
	    if (array[left] == target) return left;
	    if (array[right] == target) return right;
	    return -1;
	  }
	
	public static void main(String[] args) {
		int[] arr = new int[] {1,4,7,10};
		System.out.println(firstOccur(arr, 7));
	}
}