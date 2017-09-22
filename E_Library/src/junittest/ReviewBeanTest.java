package junittest;

import static org.junit.Assert.*;

import org.junit.Test;

import bean.ReviewBean;


public class ReviewBeanTest {
	
	@Test
	public void testVisitBeanConstructor() {
		String review = "great";
		String bid = "b001";
		int rating = 5; 
		String username = "frank111";
		
		ReviewBean bean= new ReviewBean(bid,  username,  rating, review);
		assertEquals(bean.getUsername(), username);
		assertEquals(bean.getBid(), bid);
		assertEquals(bean.getRating(),rating);
		assertEquals(bean.getUsername(),username);
	}
	
	@Test
	public void testUsername() {
		String review = "great";
		String bid = "b001";
		int rating = 5; 
		String username = "frank111";
		
		ReviewBean bean= new ReviewBean(bid,  username,  rating, review);
		bean.setUsername("baker");
		assertEquals(bean.getReview(), review);
		assertEquals(bean.getBid(), bid);
		assertEquals(bean.getRating(),rating);
		assertEquals(bean.getUsername(),"baker");
	
	}
	
	@Test
	public void testrating() {
		String review = "great";
		String bid = "b001";
		int rating = 5; 
		String username = "frank111";
		ReviewBean bean= new ReviewBean(bid,  username,  rating, review);
		bean.setRating(5);
		assertEquals(bean.getUsername(), username);
		assertEquals(bean.getBid(), bid);
		assertEquals(bean.getRating(),5);
		assertEquals(bean.getReview(),review);
	
	}
	
	@Test
	public void testReview() {
		String review = "great";
		String bid = "b001";
		int rating = 5; 
		String username = "frank111";
		
		ReviewBean bean= new ReviewBean(bid,  username,  rating, review);
		bean.setReview("bad");
		assertEquals(bean.getUsername(), username);
		assertEquals(bean.getBid(), bid);
		assertEquals(bean.getRating(),rating);
		assertEquals(bean.getReview(),"bad");	
	}
	
	
	@Test
	public void testbid() {
		String review = "great";
		String bid = "b001";
		int rating = 5; 
		String username = "frank111";
		ReviewBean bean= new ReviewBean(bid,  username,  rating, review);
		bean.setBid("b011");
		assertEquals(bean.getUsername(), username);
		assertEquals(bean.getBid(), "b011");
		assertEquals(bean.getRating(),rating);
		assertEquals(bean.getReview(),review);
	}
}
