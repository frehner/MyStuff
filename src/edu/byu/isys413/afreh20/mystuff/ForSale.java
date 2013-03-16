package edu.byu.isys413.afreh20.mystuff;

public class ForSale extends PhysicalProd{
	@BusinessObjectField
	boolean isnew = false;
	
	public ForSale(String id){
		super(id);
	}

	/**
	 * @return the isnew
	 */
	public boolean isIsnew() {
		return isnew;
	}

	/**
	 * @param isnew the isnew to set
	 */
	public void setIsnew(boolean isnew) {
		this.isnew = isnew;
		setDirty();
	}
}
