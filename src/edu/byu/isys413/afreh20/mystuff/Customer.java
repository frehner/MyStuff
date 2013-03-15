package edu.byu.isys413.afreh20.mystuff;

public class Customer extends BusinessObject {
	public Customer(String id) {
		super(id);
	}

	@BusinessObjectField
	private String firstname;
	@BusinessObjectField
	private String lastname;
	@BusinessObjectField
	private String phone;
	@BusinessObjectField
	private String email;
	@BusinessObjectField
	private String address;
	@BusinessObjectField
	private String password;
	@BusinessObjectField
	private String confirmation;
	@BusinessObjectField
	private boolean isconfirmed;

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname
	 *            the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
		this.setDirty();
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname
	 *            the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
		this.setDirty();
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
		this.setDirty();
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
		this.setDirty();
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
		this.setDirty();
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
		this.setDirty();
	}

	/**
	 * @return the confirmation
	 */
	public String getConfirmation() {
		return confirmation;
	}

	/**
	 * @param confirmation the confirmation to set
	 */
	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
		this.setDirty();
	}

	/**
	 * @return the isconfirmed
	 */
	public boolean isIsconfirmed() {
		return isconfirmed;
	}

	/**
	 * @param isconfirmed the isconfirmed to set
	 */
	public void setIsconfirmed(boolean isconfirmed) {
		this.isconfirmed = isconfirmed;
		this.setDirty();
	}
}
