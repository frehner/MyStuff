package edu.byu.isys413.afreh20.mystuff;

public class ConceptualRental extends ConceptualProd{
	
	@BusinessObjectField
	private double priceDay;
	@BusinessObjectField
	private double replacePrice;
	
	public ConceptualRental(String id) {
		super(id);
	}

	/**
	 * @return the priceDay
	 */
	public double getPriceDay() {
		return priceDay;
	}

	/**
	 * @param priceDay the priceDay to set
	 */
	public void setPriceDay(double priceDay) {
		this.priceDay = priceDay;
		setDirty();
	}

	/**
	 * @return the replacePrice
	 */
	public double getReplacePrice() {
		return replacePrice;
	}

	/**
	 * @param replacePrice the replacePrice to set
	 */
	public void setReplacePrice(double replacePrice) {
		this.replacePrice = replacePrice;
		setDirty();
	}
	
}
