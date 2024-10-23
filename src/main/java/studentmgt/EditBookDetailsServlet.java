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
 * Servlet implementation class EditBookDetailsServlet
 */
@WebServlet("/EditBookDetailsServlet")
public class EditBookDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditBookDetailsServlet() {
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
	        String isbn = request.getParameter("isbn");
	        String title = request.getParameter("title");
	        String author = request.getParameter("author");
	        String publisher = request.getParameter("publisher");
	        double price = Double.parseDouble(request.getParameter("price"));
	        HttpSession session = request.getSession();

	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdata", "root", "root");

	            String query = "UPDATE books SET title=?, author=?, publisher=?, price=? WHERE isbn=?";
	            PreparedStatement stmt = conn.prepareStatement(query);
	            stmt.setString(1, title);
	            stmt.setString(2, author);
	            stmt.setString(3, publisher);
	            stmt.setDouble(4, price);
	            stmt.setString(5, isbn);
	            int rowsUpdated = stmt.executeUpdate();

	            conn.close();

	            if (rowsUpdated > 0) {
	                //response.sendRedirect("ShowAllBooksServlet?status=update-success")
	            	 session.setAttribute("successMessage", "Book details Updated!");
	            	 response.sendRedirect("ShowAllBooksServlet");
	            } 
	        } catch (Exception e) {
	            e.printStackTrace();
	            session.setAttribute("errorMessage", "Something went wrong. Please try again later.");
	            response.sendRedirect("show_all_books.jsp");
	        }
	    }

}
