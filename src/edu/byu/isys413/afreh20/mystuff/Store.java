package edu.byu.isys413.afreh20.mystuff;

import java.util.ArrayList;

public class Store extends BusinessObject {

	@BusinessObjectField
	private int storenum = 0;
	@BusinessObjectField
	private String location;
	// private Employee storeManager;
	@BusinessObjectField
	private String managerid;
	@BusinessObjectField
	private String phone;
	@BusinessObjectField
	private String address;
	// private String city;
	// private String state;
	// private int zip;
//	@BusinessObjectField
//	private ArrayList<Employee> employeeList;
	@BusinessObjectField
	private double salestaxrate = 0;
	@BusinessObjectField
	private String subnetid;

	/**
	 * Creates a store
	 * 
	 * @param id
	 */
	public Store(String id) {
		super(id);
	}

	/**
	 * @return the storenum
	 */
	public int getStorenum() {
		return storenum;
	}

	/**
	 * @param storenum
	 *            the storenum to set
	 */
	public void setStorenum(int storenum) {
		this.storenum = storenum;
		this.setDirty();
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
	 * @return the managerid
	 */
	public String getManagerid() {
		return managerid;
	}

	/**
	 * @param managerid
	 *            the managerid to set
	 */
	public void setManagerid(String managerid) {
		this.managerid = managerid;
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
	 * @return the employeeList
	 */
//	public ArrayList<Employee> getEmployeeList() {
//		return employeeList;
//	}
//
//	/**
//	 * @param employeeList
//	 *            the employeeList to set
//	 */
//	public void setEmployeeList(ArrayList<Employee> employeeList) {
//		this.employeeList = employeeList;
//		this.setDirty();
//	}

	/**
	 * @return the salestaxrate
	 */
	public double getSalestaxrate() {
		return salestaxrate;
	}

	/**
	 * @param salestaxrate
	 *            the salestaxrate to set
	 */
	public void setSalestaxrate(double salestaxrate) {
		this.salestaxrate = salestaxrate;
		this.setDirty();
	}

	/**
	 * @return the subnetid
	 */
	public String getSubnetid() {
		return subnetid;
	}

	/**
	 * @param subnetid
	 *            the subnetid to set
	 */
	public void setSubnetid(String subnetid) {
		this.subnetid = subnetid;
		this.setDirty();
	}
}