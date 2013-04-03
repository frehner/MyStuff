package edu.byu.isys413.afreh20.mystuff;

public class Picture extends BusinessObject{
	public Picture(String id){
		super(id);
	}
	@BusinessObjectField
	private String caption;
	@BusinessObjectField
	private String pic;
	@BusinessObjectField
	private String picname;
	@BusinessObjectField
	private String customerid;
	/**
	 * @return the caption
	 */
	public String getCaption() {
		return caption;
	}
	/**
	 * @param caption the caption to set
	 */
	public void setCaption(String caption) {
		this.caption = caption;
		this.setDirty();
	}
	/**
	 * @return the pic
	 */
	public String getPic() {
		return pic;
	}
	/**
	 * @param pic the pic to set
	 */
	public void setPic(String pic) {
		this.pic = pic;
		this.setDirty();
	}
	/**
	 * @return the picname
	 */
	public String getPicname() {
		return picname;
	}
	/**
	 * @param picname the picname to set
	 */
	public void setPicname(String picname) {
		this.picname = picname;
		this.setDirty();
	}
	/**
	 * @return the customerid
	 */
	public String getCustomerid() {
		return customerid;
	}
	/**
	 * @param customerid the customerid to set
	 */
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
		this.setDirty();
	}
	
	
	
}
