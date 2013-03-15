package edu.byu.isys413.afreh20.mystuff;

public class DebitCredit extends BusinessObject{
	public DebitCredit(String id){
		super(id);
	}
	@BusinessObjectField
	private boolean isdebit = false;
	@BusinessObjectField
	private String genledger_name;
	@BusinessObjectField
	private double amount =  0;
	@BusinessObjectField
	private String journalentry_id;
	@BusinessObjectField
	private boolean batchRun = false;
	
	/**
	 * @return the isdebit
	 */
	public boolean isIsdebit() {
		return isdebit;
	}
	/**
	 * @param isdebit the isdebit to set
	 */
	public void setIsdebit(boolean isdebit) {
		this.isdebit = isdebit;
		this.setDirty();
	}
	/**
	 * @return the genledger_name
	 */
	public String getGenledger_name() {
		return genledger_name;
	}
	/**
	 * @param genledger_name the genledger_name to set
	 */
	public void setGenledger_name(String genledger_name) {
		this.genledger_name = genledger_name;
		this.setDirty();
	}
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
	 * @return the journalentry_id
	 */
	public String getJournalentry_id() {
		return journalentry_id;
	}
	/**
	 * @param journalentry_id the journalentry_id to set
	 */
	public void setJournalentry_id(String journalentry_id) {
		this.journalentry_id = journalentry_id;
		this.setDirty();
	}
	/**
	 * @return the batchRun
	 */
	public boolean isBatchRun() {
		return batchRun;
	}
	/**
	 * @param batchRun the batchRun to set
	 */
	public void setBatchRun(boolean batchRun) {
		this.batchRun = batchRun;
		this.setDirty();
	}
}
