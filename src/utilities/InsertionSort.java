package utilities;

import java.util.Comparator;

/**
 * Insertion sort implementation that sorts in descending order.
 */
public class InsertionSort {

	/**
	 * Sorts the array into descending order using the given comparator.
	 * @param <E> the element type
	 * @param arr the array to sort
	 * @param comp defines the ordering of the elements
	 */
	public static <E> void sort( E[] arr, Comparator<? super E> comp )
	{
		// start at index 1, the first item on its own counts as sorted
		// everything to the left of i is already in descending order
		for ( int i = 1; i < arr.length; i++ )
		{
			// save the item we're moving so it doesn't get overwritten
			// while we shift the others
			E key = arr[i];

			// j starts at the item just left of key
			int j = i - 1;

			// keep going left while the item at j is smaller than key
			// compare() returns a negative number when arr[j] < key
			// each smaller item gets shifted one spot right to make room
			while ( j >= 0 && comp.compare( arr[j], key ) < 0 )
			{
				arr[j + 1] = arr[j];
				j--;
			}

			// j stopped at an item bigger than or equal to key (or ran off the front)
			// so key goes right after it
			arr[j + 1] = key;
		}
	}

	/**
	 * Sorts the array into descending order using natural (compareTo) ordering.
	 * @param <E> the element type
	 * @param arr the array to sort
	 */
	public static <E extends Comparable<? super E>> void sort( E[] arr )
	{
		sort( arr, ( a, b ) -> a.compareTo( b ) );
	}
}