package model;

public class UsedSaleItem {
	private int id;
	private SaleItem saleItem;
	private int quantity, price;
	public UsedSaleItem(int id, SaleItem saleItem, int quantity, int price) {
		super();
		this.id = id;
		this.saleItem = saleItem;
		this.quantity = quantity;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public SaleItem getSaleItem() {
		return saleItem;
	}
	public int getQuantity() {
		return quantity;
	}
	public int getPrice() {
		return price;
	}

	
	
}
