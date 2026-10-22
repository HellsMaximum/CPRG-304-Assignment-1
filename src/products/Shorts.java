package products;

/**
 * A pair of shorts. Adjustment factor for real difference is 0.3.
 */
public class Shorts extends Product {
	private static final double ADJUSTMENT = 0.3;
	
	/**
	 * Creates shorts.
	 * @param originalPrice the original price
	 * @param updatedPrice the updated price
	 */
	public Shorts(double originalPrice, double updatedPrice) {
		super(originalPrice, updatedPrice);
	}

	@Override
	public double calcRealDiff() {
		return ADJUSTMENT * Math.abs( getOriginalPrice() - getUpdatedPrice() );
	}

}
