package edu.byu.isys413.afreh20.mystuff;

import java.util.Calendar;

public class JournalEntry extends BusinessObject{
	public JournalEntry(String id){
		super(id);
	}
	@BusinessObjectField
	private java.util.Date date;
	@BusinessObjectField
	private String transaction_id;
	/**
	 * @return the date
	 */
	public java.util.Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
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
