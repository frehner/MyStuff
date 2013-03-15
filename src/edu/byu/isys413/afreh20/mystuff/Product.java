package edu.byu.isys413.afreh20.mystuff;

public class Product extends BusinessObject{
//	@BusinessObjectField
//	private String id;
	@BusinessObjectField
	private double price = 0;
	@BusinessObjectField
	private String type;
	@BusinessObjectField
	private int prod_num;
	@BusinessObjectField
	private String name;
	
	public double getCommissionRate(){
		return 0;
	}
	
	public Product(String id){
		super(id);
	}
	/**
	 * @return the id
	 */
//	public String getId() {
//		return id;
//	}
//	/**
//	 * @param id the id to set
//	 */
//	public void setId(String id) {
//		this.id = id;
//	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
		this.setDirty();
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
		this.setDirty();
	}
	/**
	 * @return the prod_num
	 */
	public int getProd_num() {
		return prod_num;
	}
	/**
	 * @param prod_num the prod_num to set
	 */
	public void setProd_num(int prod_num) {
		this.prod_num = prod_num;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
