package products;

/**
 * A t-shirt. Adjustment factor for real difference is 0.2.
 */
public class TShirts extends Product {
	private static final double ADJUSTMENT = 0.2;
	
	/**
	 * Creates a t-shirt.
	 * @param originalPrice the original price
	 * @param updatedPrice the updated price
	 */
	public TShirts(double originalPrice, double updatedPrice) {
		super(originalPrice, updatedPrice);
	}

	@Override
	public double calcRealDiff() {
		return ADJUSTMENT * Math.abs( getOriginalPrice() - getUpdatedPrice() );
	}
	
}
