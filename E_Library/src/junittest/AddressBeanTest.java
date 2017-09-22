package junittest;


import static org.junit.Assert.*;

import org.junit.Test;

import bean.AddressBean;

public class AddressBeanTest {

	@Test
	public void testConstructor() {
		int id = 2;
		String street = "123 Yonge St";
		String province = "Ontario";
		String city = "Toronto";
		String country = "Canada";
		String zip = "L5B3R5";
		String phone = "231-323-1235";
		AddressBean bean = new AddressBean(id, street, city, province,
				 country, zip, phone);
		assertEquals(bean.getId(), id);
		assertEquals(bean.getStreet(), street);
		assertEquals(bean.getCity(), city);
		assertEquals(bean.getProvince(), province);
		assertEquals(bean.getCountry(), country);
		assertEquals(bean.getZip(), zip);
		assertEquals(bean.getPhone(), phone);
	}
		@Test
		public void testSetID() {
			int id = 2;
			String street = "123 Yonge St";
			String province = "Ontario";
			String city = "Toronto";
			String country = "Canada";
			String zip = "L5B3R5";
			String phone = "231-323-1235";
			AddressBean bean = new AddressBean(id, street, city, province,
					 country, zip, phone);
			bean.setId(3);
			assertEquals(bean.getId(), 3);
			assertEquals(bean.getStreet(), street);
			assertEquals(bean.getCity(), city);
			assertEquals(bean.getProvince(), province);
			assertEquals(bean.getCountry(), country);
			assertEquals(bean.getZip(), zip);
			assertEquals(bean.getPhone(), phone);
	}
		
		@Test
		public void testSetStreet() {
			int id = 2;
			String street = "555 Bay St";
			String province = "Ontario";
			String city = "Toronto";
			String country = "Canada";
			String zip = "L5B3R5";
			String phone = "231-323-1235";
			AddressBean bean = new AddressBean(id, street, city, province,
					 country, zip, phone);
			bean.setStreet("123 spooner st");
			assertEquals(bean.getId(), id);
			assertEquals(bean.getStreet(), "123 spooner st");
			assertEquals(bean.getCity(), city);
			assertEquals(bean.getProvince(), province);
			assertEquals(bean.getCountry(), country);
			assertEquals(bean.getZip(), zip);
			assertEquals(bean.getPhone(), phone);
		
	}
		
		@Test
		public void testSetCity() {
			int id = 2;
			String street = "555 Bay St";
			String province = "Ontario";
			String city = "Toronto";
			String country = "Canada";
			String zip = "L5B3R5";
			String phone = "231-323-1235";
			AddressBean bean = new AddressBean(id, street, city, province,
					 country, zip, phone);
			bean.setCity("Hamilton");
			assertEquals(bean.getId(), id);
			assertEquals(bean.getStreet(), street);
			assertEquals(bean.getCity(), "Hamilton");
			assertEquals(bean.getProvince(), province);
			assertEquals(bean.getCountry(), country);
			assertEquals(bean.getZip(), zip);
			assertEquals(bean.getPhone(), phone);

		
	}
		
		@Test
		public void testSetProvince() {
			int id = 2;
			String street = "555 Bay St";
			String province = "Ontario";
			String city = "Toronto";
			String country = "Canada";
			String zip = "L5B3R5";
			String phone = "231-323-1235";
			AddressBean bean = new AddressBean(id, street, city, province,
					 country, zip, phone);
			bean.setProvince("ON");;
			assertEquals(bean.getId(), id);
			assertEquals(bean.getStreet(), street);
			assertEquals(bean.getCity(), city);
			assertEquals(bean.getProvince(), "ON");
			assertEquals(bean.getCountry(), country);
			assertEquals(bean.getZip(), zip);
			assertEquals(bean.getPhone(), phone);

		
	}
		
		@Test
		public void testSetCountry() {
			int id = 2;
			String street = "555 Bay St";
			String province = "Ontario";
			String city = "Toronto";
			String country = "Canada";
			String zip = "L5B3R5";
			String phone = "231-323-1235";
			AddressBean bean = new AddressBean(id, street, city, province,
					 country, zip, phone);
			bean.setCountry("Germany");
			assertEquals(bean.getId(), id);
			assertEquals(bean.getStreet(), street);
			assertEquals(bean.getCity(), city);
			assertEquals(bean.getProvince(), province);
			assertEquals(bean.getCountry(), "Germany");
			assertEquals(bean.getZip(), zip);
			assertEquals(bean.getPhone(), phone);

		
	}
		
		@Test
		public void testSetZIP() {
			int id = 2;
			String street = "555 Bay St";
			String province = "Ontario";
			String city = "Toronto";
			String country = "Canada";
			String zip = "L5B3R5";
			String phone = "231-323-1235";
			AddressBean bean = new AddressBean(id, street, city, province,
					 country, zip, phone);
			bean.setZip("L6P4A4");
			assertEquals(bean.getId(), id);
			assertEquals(bean.getStreet(), street);
			assertEquals(bean.getCity(), city);
			assertEquals(bean.getProvince(), province);
			assertEquals(bean.getCountry(), country);
			assertEquals(bean.getZip(), "L6P4A4");
			assertEquals(bean.getPhone(), phone);

		
	}
		
		@Test
		public void testSetPhone() {
			int id = 2;
			String street = "555 Bay St";
			String province = "Ontario";
			String city = "Toronto";
			String country = "Canada";
			String zip = "L5B3R5";
			String phone = "231-323-1235";
			AddressBean bean = new AddressBean(id, street, city, province,
					 country, zip, phone);
			bean.setPhone("416-555-5555");
			assertEquals(bean.getId(), id);
			assertEquals(bean.getStreet(), street);
			assertEquals(bean.getCity(), city);
			assertEquals(bean.getProvince(), province);
			assertEquals(bean.getCountry(), country);
			assertEquals(bean.getZip(), zip);
			assertEquals(bean.getPhone(), "416-555-5555");

		
	}

}
