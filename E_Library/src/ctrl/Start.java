package ctrl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.CartBean;
import bean.UserBean;
import bean.AddressBean;
import model.SIS;

/**
 * Sabir Rustaqi
 * Gleb Sitiugin
 * Jaspuneet Sidhu
 * Nisha Sharma
 */
@WebServlet({ "/Start", "/RegisterUser", "/Login", "/ShoppingCart", "/Category", "/myAccount", "/Help",
	"/ForgotUserName", "/ContactUs", "/ForgotPassword", "/AboutUs", "/Purchase" })
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/***************** PRIVATE VARIABLES *******************/
	private SIS sis;
	private UserBean currentUser; // User Bean
	private AddressBean currentAddress; // Address Bean
	private String page; // Page String
	private String bookID; // Book's ID
	private String currentBID; // For Add To Cart
	private String category; // For Category Serach
	private String priceRange; // For the Range of Price
	private String sortBy; // For Sorting
	private StringBuffer result; // For Buffer Results

	private ArrayList<CartBean> cartList = new ArrayList<CartBean>();
	private int numberOfItemsChosen = 0;
	private double totalCost = 0.0;
	/*********** PARAM NAMES **************/
	private static final String MODEL = "model";
	private static final String SEARCHBY = "searchList";
	private static final String KEYWORD = "searchText";
	private static final String ADVANCED = "advancedSearch";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private static final String CATEGORY = "category";
	private static final String PRICERANGE = "priceRange";
	private static final String SORTBY = "sortBy";
	private static final String ADVTITLE = "advT";
	private static final String ADVAUTHOR = "advA";
	private static final String ADVCATEGORY = "advC";
	private static final String ADVPRICERANGE = "advP";
	private static final String ADVSEPERATOR = "!!";
	/************* FINAL ATTRIBUTES FOR ACCOUNT PAGE *************/
	private static final String ACCOUNTCHANGEBUTTON = "saveAccountChange";
	private static final String ACCPASSWORD = "pass";
	private static final String ACCPHONE = "phone";
	private static final String ACCSTREET = "address";
	private static final String ACCCITY = "acity";
	private static final String ACCPROVINCE = "aprovince";
	private static final String ACCCOUNTRY = "acountry";
	private static final String ACCZIP = "azip";
	/************* FINAL ATTRIBUTES FOR REVIEW UPDATE *************/
	private static final String REVIEWBUTTON = "submitReview";
	private static final String RATING = "rating";
	private static final String REVIEW = "review";
	
	
	/************* ORDER AS IN BOOKDAO CLASS *************/
	private static final String HOME = "1";
	private static final String SEARCH = "2";
	private static final String DETAIL = "3";
	private static final String ADMIN = "4";
	
	/************* ATTRIBUTES NEEDED FOR START SERVLET *************/
	private static final String PARAMKEY = "keyword";
	private static final String PARAMSEARCHBY = "searchBy";
	private static final String PARAMCATEGORY = "category";
	private static final String PARAMPRICERANGE = "priceRange";
	private static final String PARAMSORTBY = "sortBy";
	private static final String CURRENTUSER = "username"; 
	private static final String CURRENTADDRESS = "address";
	private static final String PARAMISADVANCED = "advanced";

	/**************
	 * LIST OF BOOKS ADDED TO CART WITH THEIR INFORMATION
	 ************/
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Start() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		try {
			sis = new SIS();
		} catch (Exception e) {
			throw new ServletException();
		}
		
		this.getServletContext().setAttribute(MODEL, sis);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		page = request.getParameter("page");
		bookID = request.getParameter("bookID");
		category = request.getParameter(CATEGORY);
		priceRange = request.getParameter(PRICERANGE);
		sortBy = request.getParameter(SORTBY);

		if (bookID != null || category != null || priceRange != null || sortBy != null) 
			page = SEARCH;
		
		if (bookID != null) {
			session.setAttribute("currentBID", bookID);
			request.getServletContext().setAttribute("visitPage", "view");		}

		/**************************
		 * ECommerceProject/Account (For My Account Page)
		 ************************/
		if (request.getRequestURI().equals("/E_Library/myAccount")) {
			if (request.getParameter(ACCOUNTCHANGEBUTTON) != null) {
				//   1. Customer is updating account information
				sis.updateUserInfo(currentUser.getId(), request.getParameter(ACCPASSWORD), 
						request.getParameter(ACCSTREET), request.getParameter(ACCCITY), request.getParameter(ACCPROVINCE), 
						request.getParameter(ACCCOUNTRY), request.getParameter(ACCZIP), request.getParameter(ACCPHONE));
				request.getServletContext().getRequestDispatcher("/Account.jspx").forward(request, response);
				return;
			} else if (request.getParameter(SEARCHBY) != null) {
				String url = "/E_Library/?" + request.getQueryString();
				response.sendRedirect(url);
				return;
			} else {
				//   3. Customer is opening account page
				currentAddress = sis.retrieveAddress(currentUser.getId());
				request.getServletContext().setAttribute(CURRENTADDRESS, currentAddress);
				request.getServletContext().getRequestDispatcher("/Account.jspx").forward(request, response);
				return;
			}
		}
		
		/**************************
		 * ECommerceProject/Login (For Login Page)
		 ************************/

		if (request.getRequestURI().equals("/E_Library/Login")) {
			if (request.getParameter("a") != null) {
				// For Admin its 0.
				String buttonOption = request.getParameter("a");
				String month = request.getParameter("month");
				if (month == null)
					month = "01";
				String month2 = "";
				switch (month) {
				case "01": month2 = "January";
					break;
				case "02" : month2 = "February";
					break;
				case "03" : month2 = "March";
					break;
				case "04" : month2 = "April";
					break;
				case "05" : month2 = "May";
					break;
				case "06" : month2 = "June";
					break;
				case "07" : month2 = "July";
					break;
				case "08" : month2 = "August";
					break;
				case "09" : month2 = "Septempber";
					break;
				case "10" : month2 = "October";
					break;
				case "11" : month2 = "November";
					break;
				case "12" : month2 = "December";
					break;
				}
				
				if (buttonOption.equals("1")) {
					// For generating the Report 1
					request.setAttribute("a", "1");
					request.setAttribute("month", month2);
					StringBuffer a = sis.generateReport(request.getParameter("month"));
					request.getServletContext().setAttribute("report", a);

				} else if (buttonOption.equals("2")) {
					// For generating the Report 2
					request.setAttribute("a", "2");
				} else if (buttonOption.equals("3")) {
					// For generating the Report 3
					request.setAttribute("a", "3");
				} 
				request.getServletContext().getRequestDispatcher("/Admin.jspx").forward(request, response);;
				return;
			}
			if (request.getParameter("Login") != null) {
				// CASE 1: WHEN CUSTOMER IS TRYING TO LOGIN
				currentUser = sis.loginUsers(request.getParameter(USERNAME), request.getParameter(PASSWORD));
				if (currentUser != null) {
					// IF THE LOGIN IS SUCCESSFUL
					request.getServletContext().setAttribute("error", "");
					if (currentUser.getUsername().equals("admin")) {
						// CHECKING IF THE LOGGED IN USER IS AN ADMIN
						request.getServletContext().setAttribute(CURRENTUSER, currentUser);
						request.setAttribute("a", "4");
						request.getServletContext().getRequestDispatcher("/Admin.jspx").forward(request, response);;
						return;
					} else {
						// OTHERWISE THE LOGGED IN USER IS A REGULAR CUSTOMER
						request.getServletContext().setAttribute(CURRENTUSER, currentUser);
						response.sendRedirect("/E_Library/");
						return;
					}
				} else {
					// IF THE LOGIN IS NOT SUCCESSFUL
					request.getServletContext().setAttribute("error", "Login not successful! Wrong username or password!");
					request.getServletContext().getRequestDispatcher("/ExistingUser.jspx").forward(request, response);
					return;
				}
			} 
			else if (request.getParameter("out") != null) {
				// CASE 2: WHEN THE CUSTOMER IS TRYING TO LOGOUT
				currentUser = null;
				numberOfItemsChosen = 0;
				totalCost = 0;
				cartList.clear();
				request.getServletContext().setAttribute(CURRENTUSER, null);
				response.sendRedirect("/E_Library/");

				return;
			}
			else if (!request.getParameterMap().isEmpty()) {
				// CASE 3: WHEN THE CUSTOMER IS SEARCHING FOR A BOOK
				String url = "/E_Library/?" + request.getQueryString();
				response.sendRedirect(url);
				return;
			}
			else {
				// CASE 4: WHEN THE CUSTOMER OPENS THE LOGIN PAGE
				request.getServletContext().getRequestDispatcher("/ExistingUser.jspx").forward(request, response);;
				return;
			}
		}
		
		/***************************
		 * ECommerceProject/RegisterUser (RegisterUser Page)
		 **************************/
		else if (request.getRequestURI().equals("/E_Library/RegisterUser")) {
			if (request.getParameter("Register") != null) {
				//   1. Customer is trying to register (Passed all error checking) 
				currentUser = sis.registerUser(request.getParameter("username"), request.getParameter("fname"),
						request.getParameter("lname"), request.getParameter("phonenumber"),
						request.getParameter("password"), request.getParameter("street"),
						request.getParameter("province"), request.getParameter("city"),
						request.getParameter("country"), request.getParameter("postalCode"));
				currentUser = sis.loginUsers(currentUser.getUsername(), currentUser.getPassword());
				this.getServletContext().setAttribute(CURRENTUSER, currentUser);
				
				response.sendRedirect("/E_Library/");
				return;
			} else {
				//   2. Customer opened the register page
				request.getServletContext().getRequestDispatcher("/RegisterUser.jspx").forward(request, response);;
				return;
			}
		}
		
		/*************************
		 * ECommerceProject/ShoppingCart (ShoppingCart Page)
		 ************************/
		if (request.getRequestURI().equals("/E_Library/ShoppingCart")) {
			if (request.getParameterMap().isEmpty()) {
				//   1. User wants to see their shopping cart
				//      ---Set attributes
				request.setAttribute("item", cartList);
				request.setAttribute("cost", totalCost);
				request.setAttribute("numberofitems", numberOfItemsChosen);
				request.getServletContext().getRequestDispatcher("/ShoppingCart.jspx").forward(request, response);
				return;
			} else {
				//  2. Display the Book Search Result Page
				String url = "/E_Library/?" + request.getQueryString();
				response.sendRedirect(url);
				return;
			}
		}
		
		/***************************
		 * ECommerceProject/Purchase (Purchase Page)
		 ************************/

		if (request.getRequestURI().equals("/E_Library/Purchase")) {
			if (request.getParameterMap().isEmpty()) {
				// CASE 1: WHEN THE USER WANTS TO PURCHASE THE ORDER
				// CASE 1.1: IS THE USER LOGGED IN? EXAMINED IN THE
				// /Purchase.jspx
				request.getServletContext().getRequestDispatcher("/Purchase.jspx").forward(request, response);
				return;
			} else {
				// CASE 2: DISPLAYING THE BOOK SEARCH RESULT PAGE
				String url = "/E_Library/?" + request.getQueryString();
				response.sendRedirect(url);
				return;
			}
		}
		if (request.getParameter("submitOrder") != null) {
			String result = "";
			if (!cartList.isEmpty()) {
				result = sis.submitOrder(currentUser.getId(), "ordered", cartList);
				
				for (CartBean bean: cartList) {
					session.setAttribute("currentBID", bean.getBid());
					request.getServletContext().setAttribute("visitPage", "purchase");
					//reportModel.updateVisitEvent(currentDate(), request.getServletContext().getAttribute("visitevent").toString(),
							//request.getServletContext().getAttribute("visitPage").toString());
				}
			}
			sis.updateStatus();
			cartList.clear();
			numberOfItemsChosen = 0;
			totalCost = 0;
			
			
			request.setAttribute("showResult", result);
			request.getRequestDispatcher("/Purchase.jspx").forward(request, response);
			return;
		}
		
		/***************************
		 * ECommerceProject/Category (Category Page)
		 ***************************/
		if (request.getRequestURI().equals("/E_Library/Category")) {
			if (request.getParameter("a") != null) {
				result = sis.getCategories(request.getParameter("a"));
				request.setAttribute("category", result);
				request.getServletContext().getRequestDispatcher("/Category.jspx").forward(request, response);
				return;
			}
			else {
				// CASE 2: DISPLAYING THE BOOK SEARCH RESULT PAGE
				String url = "/E_Library/?" + request.getQueryString();
				response.sendRedirect(url);
				return;
			}
		}
		
		/***************************
		 * ECommerceProject/AboutUS (About Us Page)
		 **************************/
		else if (request.getRequestURI().equals("/E_Library/AboutUs")) {
			if (request.getParameterMap().isEmpty()) {
				// CASE 1: DISPLAYING THE ABOUT US PAGE
				request.getServletContext().getRequestDispatcher("/AboutUs.jspx").forward(request, response);
				return;
			}
			else {
				// CASE 2: DISPLAYING THE BOOK SEARCH RESULT PAGE
				String url = "/E_Library/?" + request.getQueryString();
				response.sendRedirect(url);
				return;
			}
		}
		
		/********* ECommerceProject/ContactUs (Contact Us Page) *********/
		else if (request.getRequestURI().equals("/E_Library/ContactUs")) {
			if (request.getParameterMap().isEmpty()) {
				// CASE 1: DISPLAYING THE CONTACT US PAGE
				request.getServletContext().getRequestDispatcher("/ContactUs.jspx").forward(request, response);
				return;
			}
			else {
				// CASE 2: DISPLAYING THE BOOK SEARCH RESULT PAGE
				String url = "/E_Library/?" + request.getQueryString();
				response.sendRedirect(url);
				return;
			}
		}
		
		/******************************
		 * ECommerceProject/AboutUS (Help Page)
		 ****************************/
		else if (request.getRequestURI().equals("/E_Library/Help")) {
			if (request.getParameterMap().isEmpty()) {
				// CASE 1: DISPLAYING THE CONTACT US PAGE
				request.getServletContext().getRequestDispatcher("/Help.jspx").forward(request, response);
				return;
			}
			else {
				// CASE 2: DISPLAYING THE BOOK SEARCH RESULT PAGE
				String url = "/E_Library/?" + request.getQueryString();
				response.sendRedirect(url);
				return;
			}
		}
		
		/***************************
		 * ECommerceProject (Book Search Operation)
		 ***************************/
		else {
		if (page == null) {
			// REDIRECTING TO THE HOME PAGE
			request.getServletContext().getRequestDispatcher("/Home.jspx").forward(request, response);
			return;
		}
		else if (page.equals(HOME)) {  			
			// ONCE YOU ARE AT THE HOME PAGE
			// CASE 1: WHEN THE USER SEARCHES FOR A BOOK BASED ON ADVANCED
			// SEARCH OR CATEGORY SEARCH ETC.
			if (request.getParameter(ADVANCED) == null && request.getParameter(SEARCHBY) != null) {
				String category, priceRange, sortBy;
				if (request.getParameter(CATEGORY) == null || request.getParameter(CATEGORY).equals("null")
						|| request.getParameter(CATEGORY).equals("All")) 
					category = null;
				else
					category = request.getParameter(CATEGORY);
				if (request.getParameter(SORTBY) == null || request.getParameter(SORTBY).equals("null")
						|| request.getParameter(SORTBY).equals("relevance"))
					sortBy = null;
				else
					sortBy = request.getParameter(SORTBY);
				if (request.getParameter(PRICERANGE) == null || request.getParameter(PRICERANGE).equals("null") 
						|| request.getParameter(PRICERANGE).equals("All"))
					priceRange = null;
				else
					priceRange = request.getParameter(PRICERANGE);

				result = sis.getBookSearchResult(request.getParameter(SEARCHBY), request.getParameter(KEYWORD),
						category,sortBy);
				
				request.setAttribute("replace2", result);
				request.getServletContext().setAttribute(PARAMKEY, request.getParameter(KEYWORD).replace("+", " "));
				request.getServletContext().setAttribute(PARAMSEARCHBY, request.getParameter(SEARCHBY));
				request.getServletContext().setAttribute(PARAMCATEGORY, request.getParameter(CATEGORY));
				request.getServletContext().setAttribute(PARAMPRICERANGE, request.getParameter(PRICERANGE));
				request.getServletContext().setAttribute(PARAMSORTBY, request.getParameter(SORTBY));
				if (request.getParameter(SEARCHBY).isEmpty())
					request.getServletContext().setAttribute(PARAMISADVANCED, "yes");
				else
					request.getServletContext().setAttribute(PARAMISADVANCED, "no");
				
				request.getServletContext().getRequestDispatcher("/SearchResult.jspx").forward(request, response);
				return;
			}		
			
			// CASE 2: ADVANCED SEARCH
			if (request.getParameter(ADVANCED) != null) {
				String adv_title = request.getParameter(ADVTITLE);
				String adv_author = request.getParameter(ADVAUTHOR);
				String adv_category = request.getParameter(ADVCATEGORY);
				String adv_price = request.getParameter(ADVPRICERANGE);

				String key = (adv_title + ADVSEPERATOR + adv_author).replace(" ", "+");

				String url = "/E_Library/?page=2&searchList=" + 
						"&searchText=" + key + 
						"&category=" + adv_category + 
						"&priceRange=" + adv_price +
						"&sortBy=relevance";
				response.sendRedirect(url);
				return;
			}
		}
		else if (page.equals(SEARCH)) {
			/*********Book Search Page********/
			// CASE 1: WHEN THE USER SEARCHES FOR A BOOK
			if (request.getParameter(ADVANCED) == null && request.getParameter(SEARCHBY) != null) {
				String category, priceRange, sortBy;
				if (request.getParameter(CATEGORY) == null || request.getParameter(CATEGORY).equals("null")
						|| request.getParameter(CATEGORY).equals("All")) 
					category = null;
				else
					category = request.getParameter(CATEGORY);
				if (request.getParameter(SORTBY) == null || request.getParameter(SORTBY).equals("null")
						|| request.getParameter(SORTBY).equals("relevance"))
					sortBy = null;
				else
					sortBy = request.getParameter(SORTBY);
				if (request.getParameter(PRICERANGE) == null || request.getParameter(PRICERANGE).equals("null") 
						|| request.getParameter(PRICERANGE).equals("All"))
					priceRange = null;
				else
					priceRange = request.getParameter(PRICERANGE);
				
				result = sis.getBookSearchResult(request.getParameter(SEARCHBY), request.getParameter(KEYWORD),
							category, sortBy);
				
				request.setAttribute("replace2", result);
				request.getServletContext().setAttribute(PARAMKEY, request.getParameter(KEYWORD).replace("+", " "));
				request.getServletContext().setAttribute(PARAMSEARCHBY, request.getParameter(SEARCHBY));
				request.getServletContext().setAttribute(PARAMCATEGORY, request.getParameter(CATEGORY));
				request.getServletContext().setAttribute(PARAMPRICERANGE, request.getParameter(PRICERANGE));
				request.getServletContext().setAttribute(PARAMSORTBY, request.getParameter(SORTBY));
				if (request.getParameter(SEARCHBY).isEmpty())
					request.getServletContext().setAttribute(PARAMISADVANCED, "yes");
				else
					request.getServletContext().setAttribute(PARAMISADVANCED, "no");
				
				request.getServletContext().getRequestDispatcher("/SearchResult.jspx").forward(request, response);
				return;
			}		
			
			// CASE 2: WHEN THE USER DOES THE ADVANCED SEARCH
			if (request.getParameter(ADVANCED) != null) {
				String adv_title = request.getParameter(ADVTITLE);
				String adv_author = request.getParameter(ADVAUTHOR);
				String adv_category = request.getParameter(ADVCATEGORY);
				String adv_price = request.getParameter(ADVPRICERANGE);

				String key = (adv_title + ADVSEPERATOR + adv_author).replace(" ", "+");

				String url = "/E_Library/?page=2&searchList=" + 
						"&searchText=" + key + 
						"&category=" + adv_category + 
						"&priceRange=" + adv_price +
						"&sortBy=relevance";
				response.sendRedirect(url);
				return;
			}
			
			// CASE 3: WHEN THE USER WANTS TO SEE THE BOOK DETAILS
			if (bookID != null) {
				currentBID = bookID;
				result = sis.getBookDetail(bookID, (currentUser != null));
				request.setAttribute("replace3", result);
				request.getServletContext().getRequestDispatcher("/BookDetail.jspx").forward(request, response);
				return;
			}
			
			// LIST OF THE RESULTS
			if (category != null) {
				String key = ((String)request.getServletContext().getAttribute(PARAMKEY)).replace(" ", "+");
				String url = "/E_Library/?page=2&searchList=" + request.getServletContext().getAttribute(PARAMSEARCHBY) + 
						"&searchText=" + key + 
						"&category=" + category + 
						"&priceRange=" + request.getServletContext().getAttribute(PARAMPRICERANGE) +
						"&sortBy=" + request.getServletContext().getAttribute(PARAMSORTBY);
				response.sendRedirect(url);
				return;
			}
			
			if (priceRange != null) {
				String key = ((String)request.getServletContext().getAttribute(PARAMKEY)).replace(" ", "+");
				String url = "/E_Library/?page=2&searchList=" + request.getServletContext().getAttribute(PARAMSEARCHBY) + 
						"&searchText=" + key + 
						"&category=" + request.getServletContext().getAttribute(PARAMCATEGORY) + 
						"&priceRange=" + priceRange +
						"&sortBy=" + request.getServletContext().getAttribute(PARAMSORTBY);
				response.sendRedirect(url);
				return;
			}
			if (sortBy != null) {
				String key = ((String)request.getServletContext().getAttribute(PARAMKEY)).replace(" ", "+");
				String url = "/E_Library/?page=2&searchList=" + request.getServletContext().getAttribute(PARAMSEARCHBY) + 
						"&searchText=" + key + 
						"&category=" + request.getServletContext().getAttribute(PARAMCATEGORY) + 
						"&priceRange=" + request.getServletContext().getAttribute(PARAMPRICERANGE) +
						"&sortBy=" + sortBy;
				response.sendRedirect(url);
				return;
			}
		}
		else if (page.equals(DETAIL)) {
			/*********Book Detail Page********/
			// CASE 1: WHEN THE USER CLICKS ON ADD TO CART BUTTON
			if (request.getParameter("addItem") != null) {
				CartBean bean = sis.retrieveInformation(currentBID);
				cartList.add(bean);
				totalCost += bean.getPrice();
				numberOfItemsChosen++;
				session.setAttribute("currentBID", currentBID);
				request.getServletContext().setAttribute("visitPage", "cart");

				request.getServletContext().getRequestDispatcher("/Home.jspx").forward(request, response); //TODO
				return;
			}

			// CASE 2: WHEN THE USER SEARCHES FOR THE BOOKS
			if (request.getParameter(ADVANCED) == null && request.getParameter(SEARCHBY) != null) {
				String category,  sortBy;
				if (request.getParameter(CATEGORY) == null || request.getParameter(CATEGORY).equals("null")
						|| request.getParameter(CATEGORY).equals("All")) 
					category = null;
				else
					category = request.getParameter(CATEGORY);
				if (request.getParameter(SORTBY) == null || request.getParameter(SORTBY).equals("null")
						|| request.getParameter(SORTBY).equals("relevance"))
					sortBy = null;
				else
					sortBy = request.getParameter(SORTBY);	
				result = sis.getBookSearchResult(request.getParameter(SEARCHBY), request.getParameter(KEYWORD),
						category,sortBy);
				
				request.setAttribute("replace2", result);
				request.getServletContext().setAttribute(PARAMKEY, request.getParameter(KEYWORD).replace("+", " "));
				request.getServletContext().setAttribute(PARAMSEARCHBY, request.getParameter(SEARCHBY));
				request.getServletContext().setAttribute(PARAMCATEGORY, request.getParameter(CATEGORY));
				request.getServletContext().setAttribute(PARAMPRICERANGE, request.getParameter(PRICERANGE));
				request.getServletContext().setAttribute(PARAMSORTBY, request.getParameter(SORTBY));
				if (request.getParameter(SEARCHBY).isEmpty())
					request.getServletContext().setAttribute(PARAMISADVANCED, "yes");
				else
					request.getServletContext().setAttribute(PARAMISADVANCED, "no");
				
				request.getServletContext().getRequestDispatcher("/SearchResult.jspx").forward(request, response);
				return;
			}		
			
			
			// CASE 4: WHEN THE USER SEARCHES THROUGH THE ADVANCED SEARCH
			if (request.getParameter(ADVANCED) != null) {
				String adv_title = request.getParameter(ADVTITLE);
				String adv_author = request.getParameter(ADVAUTHOR);
				String adv_category = request.getParameter(ADVCATEGORY);
				String adv_price = request.getParameter(ADVPRICERANGE);

				String key = (adv_title + ADVSEPERATOR + adv_author).replace(" ", "+");

				String url = "/E_Library/?page=2&searchList=" + 
						"&searchText=" + key + 
						"&category=" + adv_category + 
						"&priceRange=" + adv_price +
						"&sortBy=relevance";
				response.sendRedirect(url);
				return;
			}
		}
	}
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);	
	}
	
	public String currentDate(){
		String date = new SimpleDateFormat("MMddyyyy").format(new Date());
		return date;
	}
}
