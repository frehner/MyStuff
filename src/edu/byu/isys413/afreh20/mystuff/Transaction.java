package edu.byu.isys413.afreh20.mystuff;

import java.util.ArrayList;
import java.util.Calendar;

public class Transaction extends BusinessObject {
	public Transaction(String id) {
		super(id);
	}

	@BusinessObjectField
	private java.util.Date date;
	@BusinessObjectField
	private double subtotal = 0;
	@BusinessObjectField
	private double tax = 0;
	@BusinessObjectField
	private double total = 0;
	@BusinessObjectField
	private String store_id;
	@BusinessObjectField
	private String customer_id;
	@BusinessObjectField
	private String employee_id;
	@BusinessObjectField
	private double commissionTotal = 0;
	
	private ArrayList<RevenueSource> revSource = new ArrayList<RevenueSource>();
	
	public void addFakeRevs(){
		for(int i=0;i<4;i++){
			try {
				Sale s = BusinessObjectDAO.getInstance().create("Sale", "sale"+i);
				s.setChargeamt(33);
				s.setQuantity(1);
				s.setType("Sale");
				revSource.add(s);
			} catch (DataException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @return the date
	 */
	public java.util.Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(java.util.Date date) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		java.util.Date realDate = calendar.getTime();

		this.date = realDate;
		this.setDirty();
	}

	/**
	 * @return the subtotal
	 */
	public double getSubtotal() {
		return subtotal;
	}

	/**
	 * @param subtotal
	 *            the subtotal to set
	 */
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
		this.setDirty();
	}

	/**
	 * @return the tax
	 */
	public double getTax() {
		return tax;
	}

	/**
	 * @param tax
	 *            the tax to set
	 */
	public void setTax(double tax) {
		this.tax = tax;
		this.setDirty();
	}

	/**
	 * @return the total
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(double total) {
		this.total = total;
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
	 * @return the customer_id
	 */
	public String getCustomer_id() {
		return customer_id;
	}

	/**
	 * @param customer_id
	 *            the customer_id to set
	 */
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
		this.setDirty();
	}

	/**
	 * @return the employee_id
	 */
	public String getEmployee_id() {
		return employee_id;
	}

	/**
	 * @param employee_id
	 *            the employee_id to set
	 */
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
		this.setDirty();
	}

	/**
	 * @return the revSource
	 */
	public ArrayList<RevenueSource> getRevSource() {
		return revSource;
	}

	/**
	 * @param revSource the revSource to set
	 */
	public void setRevSource(ArrayList<RevenueSource> revSource) {
		this.revSource = revSource;
		this.setDirty();
	}
	
	/**
	 * @param revSource the revSource to set
	 */
	public void addRevSource(RevenueSource revs) {
		this.revSource.add(revs);
//		this.setDirty();
	}

	/**
	 * @return the commissionTotal
	 */
	public double getCommissionTotal() {
		return commissionTotal;
	}

	/**
	 * @param commissionTotal the commissionTotal to set
	 */
	public void setCommissionTotal(Double commissionTotal) {
		this.commissionTotal += commissionTotal;
		this.setDirty();
	}

}
