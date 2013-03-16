package edu.byu.isys413.afreh20.mystuff;

import java.util.Date;

public class Rental extends RevenueSource{
	
//	-Date Out-Date In-Date Due-Work Order #-Reminder Sent (b)
	@BusinessObjectField
	private Date dateOut;
	@BusinessObjectField
	private Date dateIn;
	@BusinessObjectField
	private Date dateDue;
	@BusinessObjectField
	private boolean reminderSent = false;
	@BusinessObjectField
	private int workordernum;
	@BusinessObjectField
	private String forrentid;
	@BusinessObjectField
	private int numDays;
	
	public Rental(String id) {
		super(id);
	}

	/**
	 * @return the dateOut
	 */
	public Date getDateOut() {
		return dateOut;
	}

	/**
	 * @param dateOut the dateOut to set
	 */
	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
		setDirty();
	}

	/**
	 * @return the dateIn
	 */
	public Date getDateIn() {
		return dateIn;
	}

	/**
	 * @param dateIn the dateIn to set
	 */
	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
		setDirty();
	}

	/**
	 * @return the dateDue
	 */
	public Date getDateDue() {
		return dateDue;
	}

	/**
	 * @param dateDue the dateDue to set
	 */
	public void setDateDue(Date dateDue) {
		this.dateDue = dateDue;
		setDirty();
	}

	/**
	 * @return the reminderSent
	 */
	public boolean isReminderSent() {
		return reminderSent;
	}

	/**
	 * @param reminderSent the reminderSent to set
	 */
	public void setReminderSent(boolean reminderSent) {
		this.reminderSent = reminderSent;
		setDirty();
	}

	/**
	 * @return the workordernum
	 */
	public int getWorkordernum() {
		return workordernum;
	}

	/**
	 * @param workordernum the workordernum to set
	 */
	public void setWorkordernum(int workordernum) {
		this.workordernum = workordernum;
		setDirty();
	}

	/**
	 * @return the forrentid
	 */
	public String getForrentid() {
		return forrentid;
	}

	/**
	 * @param forrentid the forrentid to set
	 */
	public void setForrentid(String forrentid) {
		this.forrentid = forrentid;
		setDirty();
	}

	/**
	 * @return the numDays
	 */
	public int getNumDays() {
		return numDays;
	}

	/**
	 * @param numDays the numDays to set
	 */
	public void setNumDays(int numDays) {
		this.numDays = numDays;
		setDirty();
	}
}
