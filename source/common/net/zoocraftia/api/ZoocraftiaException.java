package net.zoocraftia.api;

public class ZoocraftiaException extends RuntimeException {
	
	private final String err;
	
	public ZoocraftiaException()
	{
		super();
		err = "unknown";
	}
	
	public ZoocraftiaException(String err)
	{
		super(err);
		this.err = err;
	}
	
	public String getError()
	{
		return err;
		
	}
	
}
