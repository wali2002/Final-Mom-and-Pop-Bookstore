package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.AddressBean;
import bean.BookBean;
import bean.UserBean;

public class AddressDAO {
	private DataSource ds;


	public AddressDAO() throws ClassNotFoundException {
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS"); 
		} catch (NamingException e) {
			e.printStackTrace();
		} 
	}

	////**** set the address of the user *****////
	public void setAddress(int id, String password, String street, String city, String province, String country, String zip, String phone) 
			throws SQLException {
		String query = "UPDATE address SET street='" + street + "', city='" + city + "', province='" + province +
				"', country='" + country + "', zip='" + zip + "', phone='" + phone + "' WHERE userid=" + id;
		
		
		Connection con = this.ds.getConnection();
		PreparedStatement statement = con.prepareStatement(query); 
	    statement.executeUpdate(query);
	    
	    query = "UPDATE customer SET password=? WHERE userid=?";
	    statement.executeUpdate(query);
	    statement.setString(1, password);
	    statement.setInt(2, id);
	   	statement.close();
		con.close();
	}
	//****** retrieve Address of Customer *****//
	public AddressBean retrieveAddress(int id) throws SQLException {
		String query = "select * FROM address A where A.userid=?";

		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		p.setInt(1, id);
		ResultSet r = p.executeQuery();
		AddressBean customer_address = null;
		while (r.next()) {
			int user_id = r.getInt("USERID");
			String street = r.getString("STREET");
			String province = r.getString("PROVINCE");
			String city = r.getString("CITY");
			String country =r.getString("COUNTRY");
			String zip = r.getString("ZIP");
			String phone =r.getString("PHONE");
			customer_address = new AddressBean(user_id,street , province, city, country, zip, phone);
		}

		r.close();
		p.close();
		con.close();
		return customer_address;
	}
}
