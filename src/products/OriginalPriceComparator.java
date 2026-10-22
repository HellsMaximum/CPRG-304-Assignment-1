package products;

/**
 * Compares two products by original price.
 * @param a the first product
 * @param b the second product
 * @return negative if a is cheaper, zero if equal, positive if a is more expensive
 */
import java.util.Comparator;

/**
 * Compares products by original price.
 */
public class OriginalPriceComparator implements Comparator<Product> {
	@Override
	public int compare( Product a, Product b ) {
		return Double.compare(a.getOriginalPrice(), b.getOriginalPrice());
	}
}
