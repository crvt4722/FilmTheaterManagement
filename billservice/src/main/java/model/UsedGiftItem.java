package model;

public class UsedGiftItem {
	private int id;
	private GiftItem giftItem;
	private int quantity;
	public UsedGiftItem(int id, GiftItem giftItem, int quantity) {
		super();
		this.id = id;
		this.giftItem = giftItem;
		this.quantity = quantity;
	}
	public int getId() {
		return id;
	}
	public GiftItem getGiftItem() {
		return giftItem;
	}
	public int getQuantity() {
		return quantity;
	}
	
	
}
