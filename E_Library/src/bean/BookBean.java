package bean;

public class BookBean {
	
	private String bid;
	private String title;
	private int price;
	private String author;
	private String category;
	private String pic_url;
	private String synopsis;

	
	public BookBean(String bid, String title, int price, String author, String category, String pic_url,
			String synopsis) {
		super();
		this.bid = bid;
		this.title = title;
		this.price = price;
		this.author = author;
		this.category = category;
		this.pic_url = pic_url;
		this.synopsis = synopsis;
	}
	public BookBean()
	{
		
	}

	public String getPic_url() {
		return pic_url;
	}


	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}


	public String getSynopsis() {
		return synopsis;
	}


	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}


	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	

}
