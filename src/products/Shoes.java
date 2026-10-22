package products;

/**
 * A pair of shoes. Adjustment factor for real difference is 0.4.
 */
public class Shoes extends Product {
	private static final double ADJUSTMENT = 0.4;
	
	/**
	 * Creates shoes.
	 * @param originalPrice the original price
	 * @param updatedPrice the updated price
	 */
	public Shoes(double originalPrice, double updatedPrice) {
		super(originalPrice, updatedPrice);
	}

	@Override
	public double calcRealDiff() {
		return ADJUSTMENT * Math.abs( getOriginalPrice() - getUpdatedPrice() );
	}

}
