package bean;

public class CartBean {

	private String title_retrieve;
	private int price;
	private String category_retrieve;
	private String bid;
	private String pic_url;
	private int quantity;
	
	public CartBean(String bid, String pic_url,String title_retrieve, int quantity, int price) {
		this.title_retrieve = title_retrieve;
		this.price = price;
		this.bid = bid;
		this.pic_url = pic_url;
		this.quantity = quantity;
	}
	

	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public String getPic_url() {
		return pic_url;
	}


	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}


	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getTitle_retrieve() {
		return title_retrieve;
	}

	public void setTitle_retrieve(String title_retrieve) {
		this.title_retrieve = title_retrieve;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCategory_retrieve() {
		return category_retrieve;
	}

	public void setCategory_retrieve(String category_retrieve) {
		this.category_retrieve = category_retrieve;
	}

}
