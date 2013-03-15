package edu.byu.isys413.afreh20.mystuff;

public class Sale extends RevenueSource {
	public Sale(String id) {
		super(id);
	}

	@BusinessObjectField
	private double quantity = 0;
	@BusinessObjectField
	private String product_id;

	/**
	 * @return the quantity
	 */
	public double getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(double quantity) {
		this.quantity = quantity;
		this.setDirty();
	}

	/**
	 * @return the product_id
	 */
	public String getProduct_id() {
		return product_id;
	}

	/**
	 * @param product_id
	 *            the product_id to set
	 */
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
		this.setDirty();
	}
}
