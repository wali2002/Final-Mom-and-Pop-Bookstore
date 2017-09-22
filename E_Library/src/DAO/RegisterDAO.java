package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.UserBean;

public class RegisterDAO {
	DataSource ds;
	UserBean user;

	public RegisterDAO() throws ClassNotFoundException {

		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	//****** add a new user****//
	public UserBean addNewUser(String username, String pwd, String fName, String lName, String street, String province, String city,
			String country, String zip, String phonenumber) throws SQLException {
		try {
			Connection con = this.ds.getConnection();
			Statement statement = con.createStatement();
			
			ResultSet r = statement.executeQuery("select count(*) from customer");
			r.next();
		    int count_row = r.getInt(1);   
		    r.close();
		 
		    count_row++;
			statement.executeUpdate("INSERT INTO customer VALUES (" + count_row + ", '" + username + "', '" + pwd 
					+ "', '" + fName + "', '" + lName + "')");
			user = new UserBean(count_row, username, pwd, fName, lName);
			
			statement.executeUpdate("INSERT INTO address " + "VALUES (" + count_row + ", '" + street + "', '" + province 
					+ "', '" + city + "', '" + country  + "', '" + zip  + "', '" + phonenumber + "')");
			
			
			statement.close();
			con.close();

		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}
		return user;
	}

}
