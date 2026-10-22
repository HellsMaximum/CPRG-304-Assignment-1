package products;

/**
 * A jacket. Adjustment factor for real difference is 0.7.
 */
public class Jackets extends Product {
	private static final double ADJUSTMENT = 0.7;
	
	/**
	 * Creates jacket.
	 * @param originalPrice the original price
	 * @param updatedPrice the updated price
	 */
	public Jackets(double originalPrice, double updatedPrice) {
		super(originalPrice, updatedPrice);
	}

	@Override
	public double calcRealDiff() {
		return ADJUSTMENT * Math.abs( getOriginalPrice() - getUpdatedPrice() );
	}

}
