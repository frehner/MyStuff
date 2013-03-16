package edu.byu.isys413.afreh20.mystuff;

public class ForRent extends PhysicalProd{
	@BusinessObjectField
	int timesRented = 0;
	
	public ForRent(String id){
		super(id);
	}
	
	/**
	 * @return the timesRented
	 */
	public int getTimesRented() {
		return timesRented;
	}

	/**
	 * @param timesRented the timesRented to set
	 */
	public void setTimesRented(int timesRented) {
		this.timesRented = timesRented;
	}
	
}
