package junittest;

import static org.junit.Assert.*;

import org.junit.Test;

import bean.AddressBean;
import bean.BookBean;

public class BookBeanTest {

	@Test
	public void testBookBean() {
		String bid = "b001";
		String title = "Dracula";
		int price = 20;
		String author = "Bram Stolker";
		String category = "Horror";
		String pic_url = "http://31.media.tumblr.com/a9b69cfdc5eb6c4140a6df01f53b7367/tumblr_n2c1eilZpB1qezqpuo1_500.jpg";
		String synopsis = "For testing";
		
		BookBean bean = new BookBean(bid, title, price, author, category, pic_url, synopsis);
		assertEquals(bean.getBid(), bid);
		assertEquals(bean.getTitle(), title);
		assertEquals(bean.getAuthor(), author);
		assertEquals(bean.getPrice(), price);
		assertEquals(bean.getCategory(), category);
		assertEquals(bean.getPic_url(), pic_url);
		assertEquals(bean.getSynopsis(), synopsis);
	}
	
	@Test
	public void testSetBID() {
		String bid = "b001";
		String title = "Dracula";
		int price = 20;
		String author = "Bram Stolker";
		String category = "Horror";
		String pic_url = "http://31.media.tumblr.com/a9b69cfdc5eb6c4140a6df01f53b7367/tumblr_n2c1eilZpB1qezqpuo1_500.jpg";
		String synopsis = "For testing";
		
		BookBean bean = new BookBean(bid, title, price, author, category, pic_url, synopsis);
		bean.setBid("b002");
		assertEquals(bean.getBid(), "b002");
		assertEquals(bean.getTitle(), title);
		assertEquals(bean.getAuthor(), author);
		assertEquals(bean.getPrice(), price);
		assertEquals(bean.getCategory(), category);
		assertEquals(bean.getPic_url(), pic_url);
		assertEquals(bean.getSynopsis(), synopsis);
	}
	
	@Test
	public void testSetTitle() {
		String bid = "b001";
		String title = "Dracula";
		int price = 20;
		String author = "Bram Stolker";
		String category = "Horror";
		String pic_url = "http://31.media.tumblr.com/a9b69cfdc5eb6c4140a6df01f53b7367/tumblr_n2c1eilZpB1qezqpuo1_500.jpg";
		String synopsis = "For testing";
		
		BookBean bean = new BookBean(bid, title, price, author, category, pic_url, synopsis);
		bean.setTitle("Hunger Games");
		assertEquals(bean.getBid(), bid);
		assertEquals(bean.getTitle(), "Hunger Games");
		assertEquals(bean.getAuthor(), author);
		assertEquals(bean.getPrice(), price);
		assertEquals(bean.getCategory(), category);
		assertEquals(bean.getPic_url(), pic_url);
		assertEquals(bean.getSynopsis(), synopsis);
	}
	
	@Test
	public void testSetAuthor() {
		String bid = "b001";
		String title = "Dracula";
		int price = 20;
		String author = "Bram Stolker";
		String category = "Horror";
		String pic_url = "http://31.media.tumblr.com/a9b69cfdc5eb6c4140a6df01f53b7367/tumblr_n2c1eilZpB1qezqpuo1_500.jpg";
		String synopsis = "For testing";
		
		BookBean bean = new BookBean(bid, title, price, author, category, pic_url, synopsis);
		bean.setAuthor("Stephen king");
		assertEquals(bean.getBid(), bid);
		assertEquals(bean.getTitle(), title);
		assertEquals(bean.getAuthor(), "Stephen king");
		assertEquals(bean.getPrice(), price);
		assertEquals(bean.getCategory(), category);
		assertEquals(bean.getPic_url(), pic_url);
		assertEquals(bean.getSynopsis(), synopsis);
	}
	
	@Test
	public void testSetPrice() {
		String bid = "b001";
		String title = "Dracula";
		int price = 20;
		String author = "Bram Stolker";
		String category = "Horror";
		String pic_url = "http://31.media.tumblr.com/a9b69cfdc5eb6c4140a6df01f53b7367/tumblr_n2c1eilZpB1qezqpuo1_500.jpg";
		String synopsis = "For testing";
		
		BookBean bean = new BookBean(bid, title, price, author, category, pic_url, synopsis);
		bean.setPrice(10);
		assertEquals(bean.getBid(), bid);
		assertEquals(bean.getTitle(), title);
		assertEquals(bean.getAuthor(), author);
		assertEquals(bean.getPrice(), 10);
		assertEquals(bean.getCategory(), category);
		assertEquals(bean.getPic_url(), pic_url);
		assertEquals(bean.getSynopsis(), synopsis);
	}
	
	@Test
	public void testSetCategory() {
		String bid = "b001";
		String title = "Dracula";
		int price = 20;
		String author = "Bram Stolker";
		String category = "Horror";
		String pic_url = "http://31.media.tumblr.com/a9b69cfdc5eb6c4140a6df01f53b7367/tumblr_n2c1eilZpB1qezqpuo1_500.jpg";
		String synopsis = "For testing";
		
		BookBean bean = new BookBean(bid, title, price, author, category, pic_url, synopsis);
		bean.setCategory("Action");
		assertEquals(bean.getBid(), bid);
		assertEquals(bean.getTitle(), title);
		assertEquals(bean.getAuthor(), author);
		assertEquals(bean.getPrice(), price);
		assertEquals(bean.getCategory(), "Action");
		assertEquals(bean.getPic_url(), pic_url);
		assertEquals(bean.getSynopsis(), synopsis);
	}
	
	@Test
	public void testSetPICURL() {
		String bid = "b001";
		String title = "Dracula";
		int price = 20;
		String author = "Bram Stolker";
		String category = "Horror";
		String pic_url = "http://31.media.tumblr.com/a9b69cfdc5eb6c4140a6df01f53b7367/tumblr_n2c1eilZpB1qezqpuo1_500.jpg";
		String synopsis = "For testing";
		
		BookBean bean = new BookBean(bid, title, price, author, category, pic_url, synopsis);
		bean.setPic_url("www.hungergamespic.com");
		assertEquals(bean.getBid(), bid);
		assertEquals(bean.getTitle(), title);
		assertEquals(bean.getAuthor(), author);
		assertEquals(bean.getPrice(), price);
		assertEquals(bean.getCategory(), category);
		assertEquals(bean.getPic_url(), "www.hungergamespic.com");
		assertEquals(bean.getSynopsis(), synopsis);
	}
	
	@Test
	public void testSetAbout() {
		String bid = "b001";
		String title = "Dracula";
		int price = 20;
		String author = "Bram Stolker";
		String category = "Horror";
		String pic_url = "http://31.media.tumblr.com/a9b69cfdc5eb6c4140a6df01f53b7367/tumblr_n2c1eilZpB1qezqpuo1_500.jpg";
		String synopsis = "For testing";
		
		BookBean bean = new BookBean(bid, title, price, author, category, pic_url, synopsis);
		bean.setSynopsis("Fight to the Death");
		assertEquals(bean.getBid(), bid);
		assertEquals(bean.getTitle(), title);
		assertEquals(bean.getAuthor(), author);
		assertEquals(bean.getPrice(), price);
		assertEquals(bean.getCategory(), category);
		assertEquals(bean.getPic_url(), pic_url);
		assertEquals(bean.getSynopsis(), "Fight to the Death");
	}

}
