/**
 * 
 */
package edu.byu.isys413.afreh20.mystuff;

/**
 * @author
 *
 */
public class StoreProd extends BusinessObject{
	@BusinessObjectField
	private double quantity = 0;
	@BusinessObjectField
	private String location;
	@BusinessObjectField
	private String store_id;
	@BusinessObjectField
	private String cprod_id;
	
	public StoreProd(String id){
		super(id);
	}

	/**
	 * @return the quantity
	 */
	public double getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(double quantity) {
		this.quantity = quantity;
		setDirty();
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
		setDirty();
	}

	/**
	 * @return the store
	 */
//	public Store getStore() {
//		return store;
//	}
//
//	/**
//	 * @param store the store to set
//	 */
//	public void setStore(Store store) {
//		this.store = store;
//		//setDirty();
//	}
//
//	/**
//	 * @return the cprod
//	 */
//	public ConceptualProd getCprod() {
//		return cprod;
//	}
//
//	/**
//	 * @param cprod the cprod to set
//	 */
//	public void setCprod(ConceptualProd cprod) {
//		this.cprod = cprod;
//		//setDirty();
//	}

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
		setDirty();
	}

	/**
	 * @return the cprod_id
	 */
	public String getCprod_id() {
		return cprod_id;
	}

	/**
	 * @param cprod_id the cprod_id to set
	 */
	public void setCprod_id(String cprod_id) {
		this.cprod_id = cprod_id;
		setDirty();
	}
	
//	public void setNewStore(String store_id){
//		try {
//			this.setstore_id(store_id);
//			this.setStore(StoreDAO.getInstance().read(store_id));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
//	public void setNewCProd(String cprod_id){
//		try{
//			this.setcprod_id(cprod_id);
//			this.setCprod(ConceptualProdDAO.getInstance().read(cprod_id));
//		}catch (Exception e){
//			e.printStackTrace();
//		}
//	}
}
