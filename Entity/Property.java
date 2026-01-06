package Entity;

public class Property{
    private String propertyID;
	private String location;
	private String type;
	private double price;
	private double size;
	private String status;
	
	public Property(){
		
	}
	
	public Property(String propertyID,String location,String type,double price,double size,String status)
	{
	this.propertyID = propertyID;
	this.location = location;
	this.type = type;
	this.price = price;
	this.size = size;
	this.status = status;
	}
public String getPropertyID() 
	{
        return propertyID;
    }
    public void setPropertyID(String propertyID)
	{
        this.propertyID = propertyID;
    }
    public String getLocation() 
	{
        return location;
    }
    public void setLocation(String location)
	{
        this.location = location;
    }
    public String getType()
	{
        return type;
    }
    public void setType(String type)
	{
        this.type = type;
    }
    public double getPrice() 
	{
        return price;
    }
    public void setPrice(double price) 
	{
        this.price = price;
    }
    public double getSize() 
	{
        return size;
    }
    public void setSize(double size)
	{
        this.size = size;
    }
    public String getStatus() 
	{
        return status;
    }
    public void setStatus(String status)
	{
		this.status = status;
	}
}

