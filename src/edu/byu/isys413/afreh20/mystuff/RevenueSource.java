package edu.byu.isys413.afreh20.mystuff;

public class RevenueSource extends BusinessObject{
	public RevenueSource(String id){
		super(id);
	}
	@BusinessObjectField
	private double chargeamt = 0;
	@BusinessObjectField
	private String type;
	@BusinessObjectField
	private String transaction_id;
	/**
	 * @return the chargeamt
	 */
	public double getChargeamt() {
		return chargeamt;
	}
	/**
	 * @param chargeamt the chargeamt to set
	 */
	public void setChargeamt(double chargeamt) {
		this.chargeamt = chargeamt;
		this.setDirty();
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
		this.setDirty();
	}
	/**
	 * @return the transaction_id
	 */
	public String getTransaction_id() {
		return transaction_id;
	}
	/**
	 * @param transaction_id the transaction_id to set
	 */
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
		this.setDirty();
	}
}
