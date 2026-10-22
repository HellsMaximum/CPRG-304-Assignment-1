package utilities;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Merge sort implementation that sorts in descending order.
 */
public class MergeSort {
	/**
	 * Sorts the array into descending order using the given comparator.
	 * @param <E> the element type
	 * @param arr the array to sort
	 * @param comp defines the ordering of the elements
	 */
	public static <E> void sort(E[] arr, Comparator<? super E> comp) {
		if(arr.length < 2) {
			return; //nothing to sort
		}
		E[] temp = Arrays.copyOf(arr, arr.length); //helper array, created once
		mergeSort( arr, temp, 0, arr.length - 1, comp);
	}
	
	/**
	 * Sorts the array into descending order using natural (compareTo) ordering.
	 * @param <E> the element type
	 * @param arr the array to sort
	 */
	public static <E extends Comparable<? super E>> void sort(E[] arr) {
		sort(arr, (a, b) -> a.compareTo(b));
	}
	
	private static <E> void mergeSort(E[] arr, E[] temp, int low, int high, Comparator<? super E> comp) {
		if(low >= high) {
			return; //base case: 0 or 1 element is already sorted
		}
		
		int mid = low + (high - low) / 2;
		mergeSort(arr, temp, low, mid, comp); //sort the left half
		mergeSort(arr, temp, mid + 1, high, comp); //sort the right half
		merge(arr, temp, low, mid, high, comp); //combine the two sorted halves
	}
	private static <E> void merge(E[] arr, E[] temp, int low, int mid, int high, Comparator<? super E> comp) {
		for(int k = low; k <= high; k++) {
			temp[k] = arr[k];
		}
		
		int i = low; //front of left half
		int j = mid + 1; //front of right half
		int k = low; //next position to fill in arr
		
		while(i <= mid && j <= high) {
			if(comp.compare(temp[i], temp[j]) >= 0) { //left is bigger or equal so take it
				arr[k++] = temp[i++];
			}
			else {
				arr[k++] = temp[j++]; //right is bigger so take it
			}
		}
		
		//after one half runs out copy the rest in
		while(i <= mid) {
			arr[k++] = temp[i++];
		}
		while(j <= high) {
			arr[k++] = temp[j++];
		}
	}
}
