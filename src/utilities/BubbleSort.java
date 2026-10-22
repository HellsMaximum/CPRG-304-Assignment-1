package utilities;

import java.util.Comparator;
/**
 * Bubble sort implementation for sorting Integer arrays in descending order.
 */
public class BubbleSort {

	/**
	 * Sorts the array into descending order using the given comparator.
	 * @param <E> the element type
	 * @param arr the array to sort
	 * @param comp defines the ordering of the elements
	 */
	public static <E> void sort(E[] arr, Comparator<? super E> comp) {
		int n = arr.length;
		for (int pass = 0; pass < n - 1; pass++ ) {
			boolean swapped = false;
			for (int j = 0; j < n - 1 - pass; j++) {
				if(comp.compare(arr[j], arr[j + 1]) < 0) { // left is smaller: wrong for descending
					E temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
					swapped = true;
				}
			}
			if(!swapped) {
				break; //already sorted, stop early
			}
		}
	}
	
	/**
	 * Sorts the array into descending order using natural (compareTo) ordering.
	 * @param <E> the element type
	 * @param arr the array to sort
	 */
    public static <E extends Comparable<? super E>> void sort (E[] arr) {
    	sort(arr, (a, b) -> a.compareTo(b));
    }
}
