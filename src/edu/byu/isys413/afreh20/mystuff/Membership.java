package edu.byu.isys413.afreh20.mystuff;

public class Membership extends BusinessObject{
	@BusinessObjectField
	private String creditcard;
	@BusinessObjectField
	private String customerid;
	
	public Membership(String id) {
		super(id);
	}

	/**
	 * @return the creditcard
	 */
	public String getCreditcard() {
		return creditcard;
	}

	/**
	 * @param creditcard the creditcard to set
	 */
	public void setCreditcard(String creditcard) {
		this.creditcard = creditcard;
		setDirty();
	}

	/**
	 * @return the customerid
	 */
	public String getCustomerid() {
		return customerid;
	}

	/**
	 * @param customerid the customerid to set
	 */
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
		setDirty();
	}

}
