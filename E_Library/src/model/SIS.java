package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import bean.AddressBean;
import bean.BookBean;
import bean.CartBean;
import bean.ReviewBean;
import bean.UserBean;
import DAO.AddressDAO;
import DAO.BookDAO;
import DAO.RegisterDAO;
import DAO.LoginDAO;
import DAO.CartDAO;
import DAO.PurchaseDAO;
import DAO.ReportDAO;
import DAO.ReviewDAO;

public class SIS {
		private BookDAO bookDAO;
		private RegisterDAO registerDAO;
		private LoginDAO loginDAO;
		private CartDAO cartDAO;
		private PurchaseDAO purchaseDAO;
		private AddressDAO addressDAO;
		private ReviewDAO reviewDAO;
		private ReportDAO reportDAO;
		private StringBuffer sb; 
		private AddressBean address;
		private UserBean user;
		private CartBean cart;
		String results;
		private static final int ALL = 0;
		private static final int TITLE = 1;
		private static final int AUTHOR = 2;
		private static final int BID = 3;
		
		private Map<String, BookBean> map;
		private Map<String, ReviewBean> reviewMap;
		private BookBean bean;
		private StringBuffer argument;
		
		public SIS() throws ClassNotFoundException {
			bookDAO = new BookDAO();
			registerDAO = new RegisterDAO();
			loginDAO = new LoginDAO();
			cartDAO = new CartDAO();
			purchaseDAO = new PurchaseDAO();
			addressDAO = new AddressDAO();
			reviewDAO = new ReviewDAO();
			reportDAO = new ReportDAO();
		}
		

		public Map<String, BookBean> retrieveBook(int searchBy, String input, String category,  String sortBy) throws Exception {
			switch(searchBy){
			case 0:
				return bookDAO.retrieveAll(input, category, sortBy);
			
			case 1: return bookDAO.retrieveByTitle(input, category,  sortBy);
				
			case 2:	return bookDAO.retrieveByAuthor(input, category, sortBy);
			
			case 3:	return bookDAO.retrieveByBid(input);
			case 4:
				String temp[] = input.split("!!");
				String title = temp[0];
				String author;
				if (temp.length < 2)
					author = "";
				else
					author = temp[1];
				return bookDAO.searchAdvance(title, author, category,sortBy);
			default: return null;
			}
		}

		public UserBean addNewUserInfo(String username, String pwd, String fName, String lName, String street, String province,
				String city, String country, String zip, String phoneNumber) throws Exception {
			return registerDAO.addNewUser(username, pwd, fName, lName, street, province, city, country, zip, phoneNumber);
		}
		public UserBean loginInfoUser(String userName, String password) throws Exception {
			return loginDAO.loginUser(userName, password);
		}
		public CartBean retrieveInformationBook(String bid) throws Exception {
			return cartDAO.retrieveInformation(bid);
		}
		public String submitOrderRequest(int uid, String status, ArrayList<CartBean> list) throws Exception{
			return purchaseDAO.submitOrder(uid, status, list);
		}
		public void updatePurchaseStatus() throws Exception{
			purchaseDAO.updateStatus();
		}
		public AddressBean getAddress(int id) throws Exception{
			return addressDAO.retrieveAddress(id);
		}
		public void updateUserInforomation(int userID, String password, String street, String city, String province, String country,
				String zip, String phone) throws Exception {
			addressDAO.setAddress(userID, password, street, city, province, country, zip, phone);
		}
		public Map<String, ReviewBean> retrieveBookReviews(String bid) throws Exception {
			return reviewDAO.BookReviews(bid);
		}

		public ArrayList<CartBean> generateReports(String month) throws Exception {
			return reportDAO.generatedReport(month);
		}

		public Map<String, BookBean> getCategoryBooks(String category) throws Exception {
			return bookDAO.getCategoryByBooks(category);
		}
		
		public StringBuffer generateReport (String month) {
			
			try {
				ArrayList<CartBean> list = generateReports(month);
				Map<String, Integer> map = new HashMap<String, Integer>();
				
				for (CartBean bean: list) {
					if (!(map.containsKey(bean.getBid())))
						map.put(bean.getBid(), 1);
					else {
						int value = map.get(bean.getBid()).intValue();
						value++;
						map.put(bean.getBid(), value);
					}
				}
				
				for (String bid: map.keySet()) {
					
					for (CartBean bean: list) {
						if (bean.getBid().equals(bid)) {
							sb.append("<tr><td><img src=\"" + bean.getPic_url() + "\" style=\"width: 200px; height: 250px;\"></img></td>"
									+ "<td>" + bid + "</td>"
									+ "<td>" + bean.getTitle_retrieve() + "</td>"
									+ "<td>" + map.get(bid) + "</td></tr>");
							break;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return sb;
		}
		
		public StringBuffer getBookSearchResult(String searchBy, String keyword, String category, String sortBy) {
			argument = new StringBuffer();
			try {
				if (searchBy.equals("all")) {
					map = retrieveBook(ALL, keyword.toLowerCase(), category, sortBy);
				} else if (searchBy.equals("title")) {
					map = retrieveBook(TITLE, keyword.toLowerCase(), category, sortBy);				
				} else if (searchBy.equals("author")) {
					map = retrieveBook(AUTHOR, keyword.toLowerCase(), category,  sortBy);				
				} else {
					map = retrieveBook(4, keyword.toLowerCase(), category, sortBy);
				}
				argument.append(printResult(map.size(), searchBy, keyword, category,sortBy));

				for(int i = 0; i < map.size(); i++ ) {
					int count = i + 1;
					bean = map.get("" + count);
					
					reviewMap = retrieveBookReviews(bean.getBid());
					int totalRating = 0;
					int avgRating = 0;
					int counter = 0;
					for (ReviewBean rating: reviewMap.values()) {
						counter++;
						totalRating += rating.getRating();
					}
					if (counter != 0)
						avgRating = totalRating / counter;
					
					argument.append("<br/><tr><td>");
					argument.append("<br/><a href=\"/E_Library/?bookID=" + bean.getBid() + "\" style=\"margin-left: 10px;\">Add to Cart</a><br/>");
					argument.append("<br/><img src=\"" + bean.getPic_url() +"\" alt=\"book1 image\" width=\"150\" height=\"200\" style=\"float:left; padding: 10px;\">"); //image
					argument.append("<br/></img><h3>" + bean.getTitle() + "</h3></a>"); //title
					argument.append("<br/><h5><i>By: <a href=\"" + "/E_Library/?page=2&searchList=author&searchText=" + bean.getAuthor() +"\">" + bean.getAuthor() +"</a></i></h5>"); //author
					argument.append(""); //space
					argument.append("<br/><h5>Rating:"); //rating
					if(avgRating == 1) {
						argument.append("<img src=\"images/1.png\" width=\"90\" height=\"20\" alt=\"1 Star\"></h5>");

					} else if(avgRating == 2) {
						argument.append("<img src=\"images/2.png\" width=\"90\" height=\"20\" alt=\"2 Star\"></h5>");

					} else if(avgRating == 3) {
						argument.append("<img src=\"images/3.png\" width=\"90\" height=\"20\" alt=\"3 Star\"></h5>");

					} else if(avgRating == 4) {
						argument.append("<img src=\"images/4.png\" width=\"90\" height=\"20\" alt=\"4 Star\"></h5>");

					} else if(avgRating == 5) {
						argument.append("<img src=\"images/5.png\" width=\"90\" height=\"20\" alt=\"5 Star\"></h5>");

					} else {
						argument.append("<img src=\"images/0.png\" width=\"90\" height=\"20\" alt=\"0 Start\"></h5>");
					}
					argument.append("");
					argument.append("<h5>Price: $" + bean.getPrice() + "<br /></h5>");
					argument.append("</td></tr>");
				}			
			} catch (Exception e) {
				System.out.println("NULL MAP RETURNED!");
				e.printStackTrace();
			}		
			return argument;
		}
		
		
		public StringBuffer getBookDetail(String bid, boolean signedIn) {
			argument = new StringBuffer();
			
			try {
				map = retrieveBook(BID, bid, null, null);
				bean = map.get("1");
				
				argument.append("<tr><td style=\"width: 30%;\" rowspan=\"7\">");
				argument.append("<img src=\"" + bean.getPic_url() + "\" class=\"bookImage2\" alt=\"book image\"></td></tr>");
				argument.append("<tr><td><h2><b>");
				argument.append(bean.getTitle() + "</b></h2></td></tr><tr><td><i> By: "
						+ "<a href=\"" + "/E_Library/?page=2&searchList=author&searchText=" + bean.getAuthor() +"\">");
				argument.append(bean.getAuthor() + "</a></i><br></br></td></tr>");
				argument.append("<tr><td>" + bean.getSynopsis() + "<br></br></td></tr>");
				argument.append("<tr><td><b>PRICE: $</b>");
				argument.append("<f:formatNumber type=\"currency\">");
				argument.append((double)bean.getPrice() + "</f:formatNumber><br></br></td></tr><tr><td>");
				argument.append("Rating: ");
				
				reviewMap = retrieveBookReviews(bean.getBid());
				int totalRating = 0, counter = 0, avgRating = 0;
				for(ReviewBean rating : reviewMap.values()) {
					totalRating += rating.getRating();
					counter++;
				}
				
				
				if (counter != 0)
					avgRating = totalRating / counter;
				
				if(avgRating == 1) {
					argument.append("<img src=\"images/1.png\" width=\"90\" height=\"20\" alt=\"1 Star\"></td></tr>");

				} else if(avgRating == 2) {
					argument.append("<img src=\"images/2.png\" width=\"90\" height=\"20\" alt=\"2 Star\"></td></tr>");

				} else if(avgRating == 3) {
					argument.append("<img src=\"images/3.png\" width=\"90\" height=\"20\" alt=\"3 Star\"></td></tr>");

				} else if(avgRating == 4) {
					argument.append("<img src=\"images/4.png\" width=\"90\" height=\"20\" alt=\"4 Star\"></td></tr>");

				} else if(avgRating == 5) {
					argument.append("<img src=\"images/5.png\" width=\"90\" height=\"20\" alt=\"5 Star\"></td></tr>");

				} else {
					argument.append("<img src=\"images/0.png\" width=\"90\" height=\"20\" alt=\"0 Star\"></td></tr>");
				}
				argument.append("</td></tr>");		
				argument.append("<tr><td style=\"border-bottom:1px solid #ddd;\"><form action=\"\"><input type=\"hidden\" name=\"page\" + value=\"3\" />"
						+ "<button type=\"submit\" id=\"addItem\" name=\"addItem\" "
						+ "onclick=\"added()\" style=\"background-color: #b30000; cursor: pointer; border: none; "
						+ "color: white; padding: 10px 15px; text-align: center; font-size: 15px;\">"
						+ "Add to Cart</button></form></td></tr></table>");
				argument.append("<table><tr><td>"
						+ "<table>"
						+ "<tr><td><b>Rate the Book!</b> <br /></td></tr>"
						+ "<tr><td>");
				if (!signedIn)
					argument.append("<font color=\"#595959\" size=\"3\"><i>Please log in to add a review.</i></font></td></tr>");
				else
					argument.append("<form action=\"\"><tr><td>"
						+ "<input type=\"hidden\" name=\"bid\" value=\"" + bid + "\"/>"
						+ "<input type=\"hidden\" name=\"page\" value=\"3\"/>"
						+ "<input type=\"radio\" name=\"rating\" value=\"1\" checked> <img src=\"images/1.png\" width=\"46\" height=\"10\" alt=\"1 Star\"> &#160;"
						+ "<input type=\"radio\" name=\"rating\" value=\"2\"> <img src=\"images/2.png\" width=\"46\" height=\"10\" alt=\"2 Star\"> &#160;"
						+ "<input type=\"radio\" name=\"rating\" value=\"3\"> <img src=\"images/3.png\" width=\"46\" height=\"10\" alt=\"3 Star\"> &#160;"
						+ "<input type=\"radio\" name=\"rating\" value=\"4\"> <img src=\"images/4.png\" width=\"46\" height=\"10\" alt=\"4 Star\"> &#160;"
						+ "<input type=\"radio\" name=\"rating\" value=\"5\"> <img src=\"images/5.png\" width=\"46\" height=\"10\" alt=\"5 Star\"> "
						+ "</td></tr><tr><td>Write a review: </td></tr><tr><td>"
						+ "<textarea name=\"review\" rows=\"6\" cols=\"50\" maxlength=\"1000\" placeholder=\"Maxmimum 1000 characters...\"></textarea></td></tr>"
						+ "<tr><td><button type=\"submit\" name=\"submitReview\">Submit</button></td></tr></form></td></tr></table></td>"
						+ "<td><table>"
						+ "<tr><td><b>Reviews:</b></td></tr>"); 
				
				if (reviewMap.values().isEmpty())
					argument.append("<tr><td><font color=\"#595959\" size=\"2\"><i>No Reviews... (Yet!)</i></font></td></tr>");
				else {
					for(ReviewBean review : reviewMap.values()) {
						
						argument.append("<tr><td><font color=\"\" size=\"2\"><i>" + review.getUsername() + "</i></font>&#160;");
						if(review.getRating() == 1) {
							argument.append("<font color=\"#595959\" size=\"2\">"
									+ "<img src=\"images/1.png\" width=\"68\" height=\"15\" alt=\"1 Star\">"
									+ "</td></tr></font>");
						} else if(review.getRating() == 2) {
							argument.append("<font color=\"#595959\" size=\"2\">"
									+ "<img src=\"images/2.png\" width=\"68\" height=\"15\" alt=\"2 Star\">"
									+ "</td></tr></font>");
						} else if(review.getRating() == 3) {
							argument.append("<font color=\"#595959\" size=\"2\">"
									+ "<img src=\"images/3.png\" width=\"68\" height=\"15\" alt=\"3 Star\">"
									+ "</td></tr></font>");
						} else if(review.getRating() == 4) {
							argument.append("<font color=\"#595959\" size=\"2\">"
									+ "<img src=\"images/4.png\" width=\"68\" height=\"15\" alt=\"4 Star\">"
									+ "</td></tr></font>");
						} else if(review.getRating() == 5) {
							argument.append("<font color=\"#595959\" size=\"2\">"
									+ "<img src=\"images/5.png\" width=\"68\" height=\"15\" alt=\"5 Star\">"
									+ "</td></tr></font>");
						} else {
						}
						argument.append("</td></tr><tr><td><font color=\"#595959\" size=\"2\">" + review.getReview() + "</font><hr /></td></tr>");
					}
				}
				argument.append("</table></td></tr>");
				
			} catch (Exception e) {
				
			}
			return argument;
		}
		
		
		private StringBuffer printResult(int mapSize, String searchBy, String keyword, String category,  String sortBy) {
			StringBuffer argument = new StringBuffer();
			
			if (searchBy.isEmpty()) {
				String temp[] = keyword.split("!!");
				String title = temp[0];
				String author;
				if (temp.length < 2)
					author = "";
				else
					author = temp[1];
				argument.append("<tr><td>" + map.size() + " results for <b>title: \"" + title + "\" AND author: \"" + author + "\"</b>");
			}
			else 
				//Normal Search
				argument.append("<tr><td>" + map.size() + " results for <b>" + searchBy + ": \"" + keyword + "\"</b>");
			
			if (category != null && !category.equals("null"))
				argument.append("<i>: " + category + "</i>");
			if (sortBy != null && !sortBy.equals("null"))
				argument.append("<i>: (Sorted By: " + sortBy + ")</i>");
			else
				argument.append("<i>: (Sorted By: Relevance)</i>");
			argument.append("</td></tr>");
			
			return argument;
		}
		public StringBuffer getCategories(String category) {
			argument = new StringBuffer();
			
			try {
				map = getCategoryBooks(category);

				argument.append("<tr><td><b>" + category + " books:</b>");
				argument.append("<br /><i>" + map.size() + " Results</i></td></tr>");
				
				for(int i = 0; i < map.size(); i++ ) {
					int count = i + 1;
					bean = map.get("" + count);
					int totalRating = 0;
					int avgRating = 0;
					int counter = 0;
					reviewMap = retrieveBookReviews(bean.getBid());
					
					for(ReviewBean rating : reviewMap.values()) {
						totalRating = totalRating + rating.getRating();
						counter++;
					}
					if(counter != 0) avgRating = (totalRating/counter);
					
						argument.append("<tr><td>");
						argument.append("<br/><a href=\"/E_Library/?bookID=" + bean.getBid() + "\" style=\"margin-left: 10px;\"\">Add to Cart</a><br/>");
						argument.append("<br/><img src=\"" + bean.getPic_url() +"\" alt=\"book1 image\" width=\"150\" height=\"200\" style=\"float:left; padding: 10px;\">");
						argument.append("<br/></img><h3>" + bean.getTitle() + "</h3></a>");
						argument.append("<br/><h5><i>By: <a href=\"\">" + bean.getAuthor() +"</a></i></h5>");
						argument.append("");
						argument.append("<br/><h5>Rating:");
						if(avgRating == 1) {
							argument.append("<img src=\"images/1.png\" width=\"90\" height=\"20\" alt=\"1 Star\"></h5>");
		
						} else if(avgRating == 2) {
							argument.append("<img src=\"images/2.png\" width=\"90\" height=\"20\" alt=\"2 Star\"></h5>");
		
						} else if(avgRating == 3) {
							argument.append("<img src=\"images/3.png\" width=\"90\" height=\"20\" alt=\"3 Star\"></h5>");
		
						} else if(avgRating == 4) {
							argument.append("<img src=\"images/4.png\" width=\"90\" height=\"20\" alt=\"4 Star\"></h5>");
		
						} else if(avgRating == 5) {
							argument.append("<img src=\"images/5.png\" width=\"90\" height=\"20\" alt=\"5 Star\"></h5>");
		
						} else {
							argument.append("no rating</h5>");
						}
						argument.append("<h5>Price: $" + bean.getPrice() + "<br /></h5>");
						argument.append("</td></tr>");

				}
			} catch (Exception e) {
				System.out.println("SOMETHINGS WRONG");
				e.printStackTrace();
			}		
			return argument;
		}
	


		public UserBean registerUser(String userName, String fName, String lName, String phone, String password,
				String street, String province, String city, String country, String zip) {
			
			try {
				user = addNewUserInfo(userName, password, fName, lName, street, province, city, country, zip, phone);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return user;

		}
		
		public UserBean loginUsers(String userName, String password) {
			try {
				user = loginInfoUser(userName, password);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return user;
		}
		
		public CartBean retrieveInformation(String bid) {
			try {
				cart = retrieveInformationBook(bid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return cart;
		}
		
		public String submitOrder(int uid, String status, ArrayList<CartBean> list) {
			try {
				results = submitOrderRequest(uid, status, list);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return results;
		}
		

		public void updateStatus()  {
			try {
				updatePurchaseStatus();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		public AddressBean retrieveAddress(int userID) {
			try {
				address = getAddress(userID);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return address;
		}
		
		public void updateUserInfo(int userID, String password, String street, String city, String province, String country, 
				String zip, String phone) {
			try {
				updateUserInforomation(userID, password, street, city, province, country, zip, phone);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
