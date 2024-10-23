package studentmgt;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddBookServlet
 */
@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.sendRedirect("login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String title = request.getParameter("title");
	        String author = request.getParameter("author");
	        String isbn = request.getParameter("isbn");
	        String publisher = request.getParameter("publisher");
	        String price = request.getParameter("price");

	        HttpSession session = request.getSession(); // Get the session

	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdata", "root", "root");

	            String query = "INSERT INTO books (isbn, title, author, publisher, price) VALUES (?, ?, ?, ?, ?)";
	            PreparedStatement stmt = conn.prepareStatement(query);
	            stmt.setString(1, isbn);
	            stmt.setString(2, title);
	            stmt.setString(3, author);
	            stmt.setString(4, publisher);
	            stmt.setString(5, price);

	            int row = stmt.executeUpdate();

	            if (row > 0) {
	                session.setAttribute("successMessage", "Book Added!");
	            } 

	            stmt.close();
	            conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	            session.setAttribute("errorMessage", "Failed to Add Book!");
	        }

	        // Redirect to the dashboard
	        response.sendRedirect("dashboard.jsp");
	    }
	

}
