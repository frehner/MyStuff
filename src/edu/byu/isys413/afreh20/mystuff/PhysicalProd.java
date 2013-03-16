package edu.byu.isys413.afreh20.mystuff;

import java.util.Calendar;

public class PhysicalProd extends Product {
	@BusinessObjectField
	private String location;
	@BusinessObjectField
	private java.util.Date purchase_date;
	@BusinessObjectField
	private double cost = 0;
	@BusinessObjectField
	private String status;
	@BusinessObjectField
	private double commission_rate = 0;
	@BusinessObjectField
	private int pprod_num;
	@BusinessObjectField
	private String store_id;
	@BusinessObjectField
	private String type;

	public PhysicalProd(String id) {
		super(id);
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
		this.setDirty();
	}

	/**
	 * @return the purchase_date
	 */
	public java.util.Date getPurchase_date() {
		return purchase_date;
	}

	/**
	 * @param purchase_date
	 *            the purchase_date to set
	 */
	public void setPurchase_date(java.util.Date purchase_date) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(purchase_date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		java.util.Date realDate = calendar.getTime();

		this.purchase_date = realDate;
		this.setDirty();
	}

	/**
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * @param cost
	 *            the cost to set
	 */
	public void setCost(double cost) {
		this.cost = cost;
		this.setDirty();
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
		this.setDirty();
	}

	/**
	 * @return the commission_rate
	 */
	public double getCommission_rate() {
		return commission_rate;
	}

	@Override
	public double getCommissionRate(){
		return commission_rate;
	}
	
	/**
	 * @param commission_rate
	 *            the commission_rate to set
	 */
	public void setCommission_rate(double commission_rate) {
		this.commission_rate = commission_rate;
		this.setDirty();
	}

	/**
	 * @return the store_id
	 */
	public String getStore_id() {
		return store_id;
	}

	/**
	 * @param store_id
	 *            the store_id to set
	 */
	public void setStore_id(String store_id) {
		this.store_id = store_id;
		this.setDirty();
	}

	/**
	 * @return the pprod_num
	 */
	public int getPprod_num() {
		return pprod_num;
	}

	/**
	 * @param pprod_num
	 *            the pprod_num to set
	 */
	public void setPprod_num(int pprod_num) {
		this.pprod_num = pprod_num;
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
		setDirty();
	}

}
