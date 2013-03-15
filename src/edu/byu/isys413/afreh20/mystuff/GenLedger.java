package edu.byu.isys413.afreh20.mystuff;

public class GenLedger extends BusinessObject {
	public GenLedger(String id) {
		super(id);
	}

	@BusinessObjectField
	private String name;
	@BusinessObjectField
	private double balance = 0;
	@BusinessObjectField
	private String type;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
		this.setDirty();
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance
	 *            the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
		this.setDirty();
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
		this.setDirty();
	}
	
	public void addDebit(double amt){
		this.balance += amt;
		this.setDirty();
	}
	
	public void addCredit(double amt){
		this.balance -= amt;
		this.setDirty();
	}
}
