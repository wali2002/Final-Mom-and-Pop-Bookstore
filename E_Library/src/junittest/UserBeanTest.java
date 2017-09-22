package junittest;

import static org.junit.Assert.*;

import org.junit.Test;

import bean.UserBean;

public class UserBeanTest {
	
	@Test
	public void testUserBeanConstructor() {
		String username ="bob123";
		String password= "joe111";
		String fname="bob";
		String lname ="joe";
		int id = 10;
		
		UserBean bean = new UserBean( id,  username,  password, fname,lname) ;
		assertEquals(bean.getUsername(), username);
		assertEquals(bean.getPassword(), password);
		assertEquals(bean.getFname(), fname);
		assertEquals(bean.getLname(), lname);
		assertEquals(bean.getId(), id);
	
	}
	
	@Test
	public void testUsername() {
		String username ="bob123";
		String password= "joe111";
		String fname="bob";
		String lname ="joe";
		int id = 10;
		
		UserBean bean = new UserBean( id,  username,  password, fname,lname) ;
		bean.setUsername("frank111");
		assertEquals(bean.getUsername(), "frank111");
		assertEquals(bean.getPassword(), password);
		assertEquals(bean.getFname(), fname);
		assertEquals(bean.getLname(), lname);
		assertEquals(bean.getId(), id);
	
	}
	
	@Test
	public void testPassword() {
		String username ="bob123";
		String password= "joe111";
		String fname="bob";
		String lname ="joe";
		int id = 10;
		
		UserBean bean = new UserBean( id,  username,  password, fname,lname) ;
		bean.setPassword("1234");;
		assertEquals(bean.getUsername(), username);
		assertEquals(bean.getPassword(), "1234");
		assertEquals(bean.getFname(), fname);
		assertEquals(bean.getLname(), lname);
		assertEquals(bean.getId(), id);
	
	}
	
	@Test
	public void testSetFname() {
		String username ="bob123";
		String password= "joe111";
		String fname="bob";
		String lname ="joe";
		int id = 10;
		
		UserBean bean = new UserBean( id,  username,  password, fname,lname) ;
		bean.setFname("frank");;
		assertEquals(bean.getUsername(), username);
		assertEquals(bean.getPassword(), password);
		assertEquals(bean.getFname(), "frank");
		assertEquals(bean.getLname(), lname);
		assertEquals(bean.getId(), id);
	
	}
	
	@Test
	public void testLname() {
		String username ="bob123";
		String password= "joe111";
		String fname="bob";
		String lname ="joe";
		int id = 10;
		
		UserBean bean = new UserBean( id,  username,  password, fname,lname) ;
		bean.setLname("miller");;
		assertEquals(bean.getUsername(), username);
		assertEquals(bean.getPassword(), password);
		assertEquals(bean.getFname(), fname);
		assertEquals(bean.getLname(), "miller");
		assertEquals(bean.getId(), id);
	
	}
	
	@Test
	public void testId() {
		String username ="bob123";
		String password= "joe111";
		String fname="bob";
		String lname ="joe";
		int id = 10;
		
		UserBean bean = new UserBean( id,  username,  password, fname,lname) ;
		bean.setId(12);
		assertEquals(bean.getUsername(), username);
		assertEquals(bean.getPassword(), password);
		assertEquals(bean.getFname(), fname);
		assertEquals(bean.getLname(), lname);
		assertEquals(bean.getId(), 12);
	}
}
