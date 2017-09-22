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

import bean.ReviewBean;
import bean.UserBean;

public class ReviewDAO {
	DataSource ds;
	ReviewBean book;

	public ReviewDAO() throws ClassNotFoundException {

		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public Map<String, ReviewBean> BookReviews(String bid) throws SQLException {
		String query = "select * from review where bid='" + bid + "'"; 
		HashMap<String, ReviewBean> review_books= new HashMap<String, ReviewBean>();
		
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		
		int count = 0;
		while (r.next()) {
			query = "select username from CUSTOMER where userid=" + r.getInt("USERID");
			PreparedStatement p1 = con.prepareStatement(query);
			ResultSet r1 = p1.executeQuery();
			
			r1.next();
			String bid_retrieve=r.getString("BID");
			String username=r1.getString("USERNAME");
			int rating=r.getInt("RATING");
			String review=r.getString("REVIEW");
			
			book = new ReviewBean(bid_retrieve,username , rating, review); 
			count++;
			review_books.put(Integer.toString(count), book);
		}
		
		r.close();
		p.close();
		con.close();
		return review_books;
	}
	
	
}
