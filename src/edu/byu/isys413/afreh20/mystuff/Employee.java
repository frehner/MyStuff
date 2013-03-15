/////////////////////////////////////////////////////////////////
///   This file is an example of an Object Relational Mapping in
///   the ISys Core at Brigham Young University.  Students
///   may use the code as part of the 413 course in their
///   milestones following this one, but no permission is given
///   to use this code is any other way.  Since we will likely
///   use this code again in a future year, please DO NOT post
///   the code to a web site, share it with others, or pass
///   it on in any way.

package edu.byu.isys413.afreh20.mystuff;

import java.util.Calendar;

/**
 * An employee. Example BO that has inheritance.
 * 
 * @author Conan C. Albrecht <conan@warp.byu.edu>
 * @version 1.1
 */
// public class Employee extends Person {
public class Employee extends BusinessObject {

	// @BusinessObjectField
	// private String username = null;
	// @BusinessObjectField
	// private String password = null;
	@BusinessObjectField
	private java.util.Date birthdate = null;
	@BusinessObjectField
	private double salary = 0.0;
	@BusinessObjectField
	private double favoriteNumber = 0.0;
	@BusinessObjectField
	private int IQ = 0;
	@BusinessObjectField
	private double distance = 0;
	// private java.util.Date hireDate = null;
	@BusinessObjectField
	private String phone = null;
	@BusinessObjectField
	private String firstname = null;
	// private String mname = null;
	@BusinessObjectField
	private String lastname = null;
	// private Store store = null;
	@BusinessObjectField
	private String storeId = null;
	@BusinessObjectField
	private int empNum;
	@BusinessObjectField
	private java.util.Date hiredate = null;

	/** Creates a new instance of BusinessObject */
	public Employee(String id) {
		super(id);
	}// constructor

	/**
	 * Returns the username.
	 * 
	 * @return the username
	 */
//	public String getUsername() {
//		return username;
//	}

	/**
	 * Sets the username.
	 * 
	 * @param username
	 *            the username to set
	 */
//	public void setUsername(String username) {
//		this.username = username;
//		setDirty();
//	}

	/**
	 * Returns the password.
	 * 
	 * @return the password
	 */
//	public String getPassword() {
//		return password;
//	}

	/**
	 * Sets the password.
	 * 
	 * @param password
	 *            the password to set
	 */
//	public void setPassword(String password) {
//		this.password = password;
//		setDirty();
//	}

	/**
	 * @return the birthdate
	 */
	public java.util.Date getBirthdate() {
		return birthdate;
	}

	/**
	 * @param birthdate
	 *            the birthdate to set
	 */
	public void setBirthdate(java.util.Date birthdate) {
		Calendar calendar = Calendar.getInstance();

	    calendar.setTime(birthdate);
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);

	    java.util.Date realDate = calendar.getTime();

	    this.birthdate = realDate;
		setDirty();
	}

	/**
	 * @return the salary
	 */
	public double getSalary() {
		return salary;
	}

	/**
	 * @param salary
	 *            the salary to set
	 */
	public void setSalary(double salary) {
		this.salary = salary;
		setDirty();
	}

	/**
	 * @return the favoriteNumber
	 */
	public double getFavoriteNumber() {
		return favoriteNumber;
	}

	/**
	 * @param favoriteNumber
	 *            the favoriteNumber to set
	 */
	public void setFavoriteNumber(double favoriteNumber) {
		this.favoriteNumber = favoriteNumber;
		setDirty();
	}

	/**
	 * @return the IQ
	 */
	public int getIQ() {
		return IQ;
	}

	/**
	 * @param IQ
	 *            the IQ to set
	 */
	public void setIQ(int IQ) {
		this.IQ = IQ;
		setDirty();
	}

	/**
	 * @return the distance
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * @param distance
	 *            the distance to set
	 */
	public void setDistance(double distance) {
		this.distance = distance;
		setDirty();
	}

	/**
	 * @return the hireDate
	 */
//	public java.util.Date getHireDate() {
//		return hireDate;
//	}
//
//	/**
//	 * @param hireDate
//	 *            the hireDate to set
//	 */
//	public void setHireDate(java.util.Date hireDate) {
//		this.hireDate = hireDate;
//		setDirty();
//	}

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
		setDirty();
	}

	/**
	 * @return the fname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param fname
	 *            the fname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
		setDirty();
	}

	/**
	 * @return the mname
	 */
//	public String getMname() {
//		return mname;
//	}

	/**
	 * @param mname
	 *            the mname to set
	 */
//	public void setMname(String mname) {
//		this.mname = mname;
//		setDirty();
//	}

	/**
	 * @return the lname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lname
	 *            the lname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
		setDirty();
	}

	/**
	 * @return the empNum
	 */
	public int getEmpNum() {
		return empNum;
	}

	/**
	 * @param empNum
	 *            the empNum to set
	 */
	public void setEmpNum(int empNum) {
		this.empNum = empNum;
		setDirty();
	}

	/**
	 * @return the store
	 */
//	public Store getStore() {
//		return store;
//	}

	/**
	 * @param store
	 *            the store to set
	 */
//	public void setStore(Store store) {
//		this.store = store;
//		setDirty();
//	}

	/**
	 * @return the storeId
	 */
	public String getStoreId() {
		return storeId;
	}

	/**
	 * @param storeId
	 *            the storeId to set
	 */
	public void setStoreId(String storeId) {
		this.storeId = storeId;
		setDirty();
	}

	/**
	 * @return the hiredate
	 */
	public java.util.Date getHiredate() {
		return hiredate;
	}

	/**
	 * @param hiredate the hiredate to set
	 */
	public void setHiredate(java.util.Date hiredate) {
		Calendar calendar = Calendar.getInstance();

	    calendar.setTime(birthdate);
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);

	    java.util.Date realDate = calendar.getTime();

	    this.hiredate = realDate;
		setDirty();
		
	}
	
//	public void setNewStore(Store st){
//		//removes this employee from the current store
//		this.getStore().removeEmployeeFromList(this);
//		
//		//adds employee to the new store
//		st.addEmployeeToList(this);
//		this.setStoreId(st.getId());
//		this.setStore(st);
//	}

}// class
