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
 * Servlet implementation class DeleteBookServlet
 */
@WebServlet("/DeleteBookServlet")
public class DeleteBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("isbn");
        HttpSession session = request.getSession();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdata", "root", "root");

            String query = "DELETE FROM books WHERE isbn=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, isbn);
            int rowsDeleted = stmt.executeUpdate();

            conn.close();

            if (rowsDeleted > 0) {
            	
            	 session.setAttribute("successMessage", "Book Deleted!");
            	 response.sendRedirect("ShowAllBooksServlet");
            } 
           
        }
          catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errorMessage", "Something went wrong. Please try again later.");
            response.sendRedirect("show_all_books.jsp");
        }
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
