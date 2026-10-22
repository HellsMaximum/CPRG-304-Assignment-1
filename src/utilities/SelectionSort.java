package utilities;

import java.util.Comparator;

/**
 * Selection sort implementation that sorts in descending order.
 */
public class SelectionSort {
	/**
	 * Sorts the array into descending order using the given comparator.
	 * @param <E> the element type
	 * @param arr the array to sort
	 * @param comp defines the ordering of the elements
	 */
	public static <E> void sort( E[] arr, Comparator<? super E> comp )
	{
		int n = arr.length;
		for ( int i = 0; i < n - 1; i++ )
		{
			int maxIndex = i;
			for ( int j = i + 1; j < n; j++ ) {
				if ( comp.compare( arr[j], arr[maxIndex] ) > 0 ) {  // found a bigger one
				
					maxIndex = j;
				}
			}
			if ( maxIndex != i ) {
				E temp = arr[i];
				arr[i] = arr[maxIndex];
				arr[maxIndex] = temp;
			}
		}
	}
	/**
	 * Sorts the array into descending order using natural (compareTo) ordering.
	 * @param <E> the element type
	 * @param arr the array to sort
	 */
	public static <E extends Comparable<? super E>> void sort( E[] arr ) {
		sort( arr, ( a, b ) -> a.compareTo( b ) );
	}
}
