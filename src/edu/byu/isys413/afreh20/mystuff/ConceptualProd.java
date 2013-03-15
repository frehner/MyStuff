package edu.byu.isys413.afreh20.mystuff;

public class ConceptualProd extends Product{
	
	// @BusinessObjectField
	// private double price;
//	@BusinessObjectField
//	private String name;
	@BusinessObjectField
	private String description;
	@BusinessObjectField
	private String manufacturer;
	@BusinessObjectField
	private double average_cost = 0;
	@BusinessObjectField
	private int cprod_num = 0;
	@BusinessObjectField
	private double commission_rate = 0;
	@BusinessObjectField
	private String store_id;
	
	public ConceptualProd(String id){
		super(id);
	}
	
	@Override
	public double getCommissionRate(){
		return commission_rate;
	}

	/**
	 * @return the price
	 */
//	public double getPrice() {
//		return price;
//	}
//
//	/**
//	 * @param price the price to set
//	 */
//	public void setPrice(double price) {
//		this.price = price;
//		setDirty();
//	}

//	/**
//	 * @return the name
//	 */
//	public String getName() {
//		return name;
//	}
//
//	/**
//	 * @param name the name to set
//	 */
//	public void setName(String name) {
//		this.name = name;
//		setDirty();
//	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
		setDirty();
	}

	/**
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * @param manufacturer the manufacturer to set
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
		setDirty();
	}

	/**
	 * @return the averageCost
	 */
//	public double getAverageCost() {
//		return averageCost;
//	}
//
//	/**
//	 * @param averageCost the averageCost to set
//	 */
//	public void setAverageCost(double averageCost) {
//		this.averageCost = averageCost;
//		setDirty();
//	}

	/**
	 * @return the cprod_num
	 */
	public int getCprod_num() {
		return cprod_num;
	}

	/**
	 * @param cprod_num the cprod_num to set
	 */
	public void setCprod_num(int cprod_num) {
		this.cprod_num = cprod_num;
		setDirty();
	}

	/**
	 * @return the average_cost
	 */
	public double getAverage_cost() {
		return average_cost;
	}

	/**
	 * @param average_cost the average_cost to set
	 */
	public void setAverage_cost(double average_cost) {
		this.average_cost = average_cost;
		this.setDirty();
	}

	/**
	 * @return the commission_rate
	 */
	public double getCommission_rate() {
		return commission_rate;
	}

	/**
	 * @param commission_rate the commission_rate to set
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
	 * @param store_id the store_id to set
	 */
	public void setStore_id(String store_id) {
		this.store_id = store_id;
		this.setDirty();
	}
	
	
	
}
