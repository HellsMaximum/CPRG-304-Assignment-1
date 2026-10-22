package products;

/**
 * A pair of jeans. Adjustment factor for real difference is 0.5.
 */
public class Jeans extends Product {
	private static final double ADJUSTMENT = 0.5;
	
	/**
	 * Creates jeans.
	 * @param originalPrice the original price
	 * @param updatedPrice the updated price
	 */
	public Jeans(double originalPrice, double updatedPrice) {
		super(originalPrice, updatedPrice);
	}

	@Override
	public double calcRealDiff() {
		return ADJUSTMENT * Math.abs(getOriginalPrice() - getUpdatedPrice());
	}
	
}
