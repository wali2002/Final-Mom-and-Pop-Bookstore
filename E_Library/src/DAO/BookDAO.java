package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.BookBean;

public class BookDAO {
	private DataSource ds;
	private String query;
	private Map<String, BookBean> rv;

	public BookDAO() throws ClassNotFoundException {
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS"); 
		} catch (NamingException e) {
			e.printStackTrace();
		} 
	}
	///******* Do all the search *****//
	private Map<String, BookBean> Search(String query, String input) throws SQLException {
		rv = new HashMap<String, BookBean>();

		Connection con = this.ds.getConnection(); 
		PreparedStatement p = con.prepareStatement(query); 
		p.setString(1, "%" + input +"%"); //To prevent SQL injection
		ResultSet r = p.executeQuery();
		
		
		int count = 0;
		while (r.next()){
			String bid=r.getString("bid");
			String title=r.getString("title");
			int price =r.getInt("price");
			String author=r.getString("author");
			String category=r.getString("category");
			String url=r.getString("url");
			String about=r.getString("about");
			BookBean bookBean = new BookBean(bid, title, price,
					author, category,url, about);
			count++;
			rv.put(Integer.toString(count), bookBean);
		}
		

		r.close();
		p.close();
		con.close();
		return rv;
	}
	
	///******* Retrieve by title *****//
	public Map<String, BookBean> retrieveByTitle(String title, String category, String sort) throws SQLException {
		String query = null;
		if (category != null && category.equals("null"))
			category = null;
		if (sort != null && sort.equals("null"))
			sort = null;
		
		if (category == null) {
				if (sort == null)
					query = "select * from book where lower(title) like ?";
				else
					query = "select * from book where lower(title) like ? ORDER BY " + sort;
			
		} else {
				if (sort == null)
					query = "select * from book where category='" + category + "' AND lower(title) like ?";
				else
					query = "select * from book where category='" + category + "' AND lower(title) like ? ORDER BY " + sort;
			
		}
		
		return Search(query, title);

	}
	///******* Retrieve by Author *****//
	public Map<String, BookBean> retrieveByAuthor(String author, String category,  String sortBy) throws SQLException {
		if (category != null && category.equals("null"))
			category = null;
		if (sortBy != null && sortBy.equals("null"))
			sortBy = null;
		
		if (category == null) {
				if (sortBy == null)
					query = "select * from book where lower(author) like ?";
				else
					query = "select * from book where lower(author) like ? ORDER BY " + sortBy;
			} 
		
		return Search(query, author);
	}
	public Map<String, BookBean> retrieveByBid(String bid) throws SQLException {
		query = "select * from book where bid like ?";
		return Search(query, bid);
	}
	
	///******* Do an Advance Search *****//
	public Map<String, BookBean> searchAdvance(String title, String author, String category, String sortBy) throws SQLException {
		if (category == null) {

				if (sortBy == null)
					query = "select * from book where lower(title) like ? AND lower(author) like ?";
				else
					query = "select * from book where (lower(title) like ? AND lower(author) like ?) ORDER BY " + sortBy;

		}
		else {
				if (sortBy == null)
					query = "select * from book where category='" + category + "' AND (lower(title) like ? AND lower(author) like ?)";
				else
					query = "select * from book where category='" + category + "' AND (lower(title) like ? AND lower(author) like ?) ORDER BY " + sortBy;
			
		}
		
		rv = new HashMap<String, BookBean>();
		
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		p.setString(1, "%" + title + "%");
		p.setString(2, "%" + author + "%");
		ResultSet r = p.executeQuery();
		
		int count = 0;
		while (r.next()){
			String bid=r.getString("bid");
			String title_retrieve=r.getString("title");
			int price =r.getInt("price");
			String author_retrieve=r.getString("author");
			String category_retrieve=r.getString("category");
			String url=r.getString("url");
			String about=r.getString("about");
			BookBean bookBean = new BookBean(bid, title_retrieve, price,
					author_retrieve, category_retrieve,url, about);
			count++;
			rv.put(Integer.toString(count), bookBean);
		}

		
		r.close();
		p.close();
		con.close();
		return rv;
	}
	
	///******* Search By Category*****//
	public Map<String, BookBean> getCategoryByBooks(String category) throws SQLException {
		if (category.equals("all"))
			query = "select * from book";
		else
			query = "select * from book where lower(category)='" + category + "'"; 
		rv = new HashMap<String, BookBean>();

		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();

		int count = 0;
		while (r.next()){
			while (r.next()){
				String bid=r.getString("bid");
				String title=r.getString("title");
				int price =r.getInt("price");
				String author=r.getString("author");
				String category_retrieve=r.getString("category");
				String url=r.getString("url");
				String about=r.getString("about");
				BookBean bookBean = new BookBean(bid, title, price,author, category_retrieve, url, about);
				count++;
				rv.put(Integer.toString(count), bookBean);
			}

		}
		r.close();
		p.close();
		con.close();
		return rv;
	}
	
	///******* Retrieve all books*****//
	public Map<String, BookBean> retrieveAll(String input, String category,String sortBy) throws SQLException{
		if (category == null) {
				if (sortBy == null)
					query = "select * from book where lower(title) like ? OR lower(author) like ?";
				else
					query = "select * from book where (lower(title) like ? OR lower(author) like ?) ORDER BY " + sortBy;
			
		} else {
				if (sortBy == null)
					query = "select * from book where category='" + category + "' AND (lower(title) like ? OR lower(author) like ?)";
				else
					query = "select * from book where category='" + category + "' AND (lower(title) like ? OR lower(author) like ?) ORDER BY " + sortBy;
		}
		rv = new HashMap<String, BookBean>();
		Connection con = this.ds.getConnection(); 
		PreparedStatement p = con.prepareStatement(query); 
		p.setString(1, "%" + input +"%");
		p.setString(2, "%" + input +"%");
		ResultSet r = p.executeQuery();
		
		int count = 0;
		while (r.next()){
			String bid=r.getString("bid");
			String title=r.getString("title");
			int price =r.getInt("price");
			String author=r.getString("author");
			String category_retrieve=r.getString("category");
			String url=r.getString("url");
			String about=r.getString("about");
			BookBean bookBean = new BookBean(bid, title, price,author, category_retrieve, url, about);
			count++;
			rv.put(Integer.toString(count), bookBean);
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}
	


	

}
