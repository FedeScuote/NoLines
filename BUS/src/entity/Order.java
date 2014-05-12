package entity;

import java.util.List;

public class Order {
	int id;
	List items;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List getItems() {
		return items;
	}

	public void setItems(List items) {
		this.items = items;
	}

	public void addItem() {

	}

	public int calculatePrice() {
		return 0;
	}

	public int calculateTime() {
		return 0;
	}

}
