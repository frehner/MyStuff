package edu.byu.isys413.afreh20.mystuff;

import java.util.Calendar;
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
		
		Calendar calendar = Calendar.getInstance();

	    calendar.setTime(dateOut);
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);

	    java.util.Date realDate = calendar.getTime();
		
		this.dateOut = realDate;
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
		Calendar calendar = Calendar.getInstance();

	    calendar.setTime(dateIn);
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);

	    java.util.Date realDate = calendar.getTime();
		
		this.dateOut = realDate;
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
		Calendar calendar = Calendar.getInstance();

	    calendar.setTime(dateDue);
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);

	    java.util.Date realDate = calendar.getTime();
		
		this.dateDue = realDate;
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
