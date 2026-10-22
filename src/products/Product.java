package products;

/**
 * Represents a generic clothing product with an original and updated price.
 * Natural ordering is defined by the real price difference (calcRealDiff).
 */
public abstract class Product implements Comparable<Product> {
	private double originalPrice;
	private double updatedPrice;
	
	/**
	 * Creates a product.
	 * @param originalPrice the original price
	 * @param updatedPrice the updated price
	 */
	public Product(double originalPrice, double updatedPrice) {
		this.originalPrice = originalPrice;
		this.updatedPrice = updatedPrice;
	}
		
	/** @return the original price */
	public double getOriginalPrice() {
		return originalPrice;
	}
	
	/** @return the updated price */
	public double getUpdatedPrice() {
		return updatedPrice;
	}
	
	/**
	 * Calculates the adjusted difference between the two prices:
	 * adjustment * |original - updated|. The adjustment depends on the product type.
	 * @return the real price difference
	 */
	public abstract double calcRealDiff();
	
	/**
	 * Compares this product to another by real price difference.
	 * Returns negative if this is smaller, zero if equal, positive if larger.
	 */
	@Override
	public int compareTo(Product other) {
		return Double.compare(this.calcRealDiff(), other.calcRealDiff());
	}
}
