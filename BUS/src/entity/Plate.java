package entity;

public class Plate {

	String id;
	String name;
	String description;
	String picture;
	double price;
	int time;
	
	public boolean equals(Plate plate){
			return(this.getId().equals(plate.getId()) && this.getName().equals(plate.getName()) && this.getDescription().equals(plate.getDescription()) && this.getPicture().equals(plate.getPicture()) && this.getTime()==plate.getTime() && this.getPrice()==plate.getPrice());
		}
	public Plate() {
		super();
	}

	public Plate(String id, String name, String description, String picture,
			double price, int time) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.picture = picture;
		this.price = price;
		this.time = time;
	}

	public double getPrice(){
		return price;
	}
	
	public void setPrice(double price){
		this.price=price;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

}
