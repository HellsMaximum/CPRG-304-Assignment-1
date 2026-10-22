package products;

/**
 * A dress. Adjustment factor for real difference is 0.6.
 */
public class Dresses extends Product {
	private static final double ADJUSTMENT = 0.6;
	
	/**
	 * Creates dress.
	 * @param originalPrice the original price
	 * @param updatedPrice the updated price
	 */
	public Dresses(double originalPrice, double updatedPrice) {
		super(originalPrice, updatedPrice);
	}

	@Override
	public double calcRealDiff() {
		return ADJUSTMENT * Math.abs( getOriginalPrice() - getUpdatedPrice() );
	}

}
