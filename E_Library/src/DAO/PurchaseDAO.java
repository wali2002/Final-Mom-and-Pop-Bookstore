package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.UserBean;
import bean.CartBean;

public class PurchaseDAO {

	DataSource ds;
	public PurchaseDAO() throws ClassNotFoundException {

		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	//****** Update PO****//
	public void updateStatus() throws SQLException {
		String query;
		Connection con = this.ds.getConnection();
		Statement stmt = con.createStatement();
		query = "update po set status='processed' where status='ordered'";
		stmt.executeUpdate(query);
		query = "update po set status='denied' where mod(ind, 3) = 0";
		stmt.executeUpdate(query);

	}

	public String submitOrder(int uid, String status, ArrayList<CartBean> list) throws SQLException {
		String result = "";
		try {
			Connection con = this.ds.getConnection();
			Statement statement = con.createStatement();
			
			ResultSet r = statement.executeQuery("select count(*) from po");
			r.next();
		    int count_row_po = r.getInt(1);
		    if(count_row_po%3 == 0)
		    	result = "Authorization Failed.";
		    else
		    	result = "Order Successfull.";
		    r.close();
		    
		    ResultSet r1 = statement.executeQuery("select count(*) from poitem");
		    r1.next();
		    int count_row_poitem = r1.getInt(1);
		    r1.close();    
		    count_row_po++;
			statement.executeUpdate("insert into po values (" + count_row_po + ", " + uid + ", '" + status + "')");
			
			for (CartBean cart: list) {
			    count_row_poitem++;
				statement.executeUpdate("insert into poitem values (" + count_row_poitem + ", '" + cart.getBid() 
						+ "', " + cart.getPrice() + ")");
			}
			
		    statement.close();
			con.close();

		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}


}
