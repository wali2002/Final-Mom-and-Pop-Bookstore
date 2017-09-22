package SOAP;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.BookDAO;
import bean.BookBean;

public class BookCatalog {
	public String getProductInfo(String productId) throws SQLException, ClassNotFoundException {
		//gets the detailed product information for a product as an XML file.
		//here product is book
		BookBean Bbean = new BookBean();
		ArrayList<BookBean> bookList = new ArrayList<BookBean>();
		
		BookDAO Bdao = new BookDAO();
		Bbean = Bdao.retrieveByBid(productId).get("select * from book where bid like ?");
		String info = "";
		String author = Bbean.getAuthor();
		String title = Bbean.getTitle();
		String burl = Bbean.getPic_url();
		String bcategory = Bbean.getCategory();
		int price = Bbean.getPrice();
		if (author != null & title != null & burl != null & bcategory != null){
			//print the information
			info = ("Book Title: " + title +  ", Author: " + author + " Category: "  + bcategory + " Cost: " +  price + " URL: " + burl);
		}
		
		else{
			// book not found
			info = "No Book Found in the System!!!";
		}
		return info; 
		
	}


}
