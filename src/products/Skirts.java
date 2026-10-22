package products;

/**
 * A skirt. Adjustment factor for real difference is 0.1.
 */
public class Skirts extends Product {
	private static final double ADJUSTMENT = 0.1;

	/**
	 * Creates skirt.
	 * @param originalPrice the original price
	 * @param updatedPrice the updated price
	 */
	public Skirts( double originalPrice, double updatedPrice ) {
		super( originalPrice, updatedPrice );
	}

	@Override
	public double calcRealDiff()
	{
		return ADJUSTMENT * Math.abs( getOriginalPrice() - getUpdatedPrice() );
	}
}
