package DAO;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.CartBean;
public class ReportDAO {
	DataSource ds;
	
	public ReportDAO() throws ClassNotFoundException {
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	//***** generate a report*****//
	public ArrayList<CartBean> generatedReport(String month) throws SQLException {
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<CartBean> generatedList = new ArrayList<CartBean>();
		String query1 = "select * from visitevent v where v.eventtype='purchase'";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query1);
		ResultSet r = p.executeQuery();
		while(r.next()){
	
			String date = r.getString("day");
			String mo = date.substring(0, 2);
			if(month.equals(mo)){
				list.add(r.getString("bid"));
			}
			
		}
		r.close();
		p.close();
		for (String bid : list){
			String query = "select * FROM BOOK B where B.bid='" +bid+"'";
			p = con.prepareStatement(query);
			r = p.executeQuery();
			while (r.next()) {
				
				String bid_retrieve =r.getString("bid");
				String url = r.getString("url");
				String title=r.getString("title");
				int price= r.getInt("price");
				generatedList.add( new CartBean(bid_retrieve,url, 
						title, 0, price));
			}
		
		}
	
		r.close();
		p.close();
		con.close();
		
		for(CartBean b : generatedList){
			System.out.println("BID: " + b.getBid());
		}
		
		return generatedList;
	}
	
	
}