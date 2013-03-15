package edu.byu.isys413.afreh20.mystuff;

public class Payment extends BusinessObject{
	public Payment(String id){
		super(id);
	}
	@BusinessObjectField
	private double amount = 0;
	@BusinessObjectField
	private double pay_change = 0;
	@BusinessObjectField
	private String type;
	@BusinessObjectField
	private String transaction_id;
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
		this.setDirty();
	}
	/**
	 * @return the pay_change
	 */
	public double getPay_change() {
		return pay_change;
	}
	/**
	 * @param pay_change the pay_change to set
	 */
	public void setPay_change(double pay_change) {
		this.pay_change = pay_change;
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
