<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="studentmgt.Book" %>
<jsp:include page="modal.jsp" />
<%

    String prn = (String) session.getAttribute("prn");
    if (prn == null) {
        response.sendRedirect("login.jsp"); // Redirect to login if session is invalid
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Show All Books</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <style>
    
    
.table-container {
    margin-top: 60px; /* Add margin to avoid overlap with the navbar */
    overflow-x: auto; /* Allow horizontal scrolling if content overflows */
    overflow-y: hidden; /* Prevent vertical scroll if it's not needed */
    width: 100%; /* Ensure the container takes full width */
}

        .search-box {
            margin: 20px 0;
            text-align: center;
        }
        .table-custom {
            border: 2px solid #000000;
        }
        .table-custom th, .table-custom td {
            border: 1px solid #343a40;
        }
        .table-custom th {
            background-color:#343a40 ;
            color: #000000;
        }
        .search-box input {
            border: 2px solid #343a40;
        }
    </style>
    <script>
        // Function to filter books based on input
        function filterBooks() {
            const input = document.getElementById('searchInput').value.toLowerCase();
            const table = document.getElementById('booksTable');
            const rows = table.getElementsByTagName('tr');

            for (let i = 1; i < rows.length; i++) { // Skip the header row
                const cells = rows[i].getElementsByTagName('td');
                let found = false;

                for (let j = 0; j < cells.length; j++) {
                    if (cells[j].textContent.toLowerCase().includes(input)) {
                        found = true;
                        break;
                    }
                }

                rows[i].style.display = found ? '' : 'none';
            }
        }

        
        // Function to open the edit modal and fill in the book details
        function openEditModal(isbn, title, author, publisher, price) {
            $('#editIsbn').val(isbn);
            $('#editTitle').val(title);
            $('#editAuthor').val(author);
            $('#editPublisher').val(publisher);
            $('#editPrice').val(price);
            $('#editBookModal').modal('show');
        }
    </script>
</head>
<body>
<jsp:include page="navbar.jsp" />
    <div class="container">
       <h2 class="text-center" style="margin-top: 20px;">All Books</h2>

        <div class="search-box">
            <input type="text" id="searchInput" onkeyup="filterBooks()" placeholder="Search by Title..." class="form-control" style="width: 50%; margin: auto;">
        </div>

        <div class="table-container">
            <table class="table table-bordered table-striped table-custom" id="booksTable">
                <thead class="thead-light">
                    <tr>
                        <th>ISBN</th>
                        <th>Title</th>
                        <th>Author</th>
                        <th>Publisher</th>
                        <th>Price</th>
                        <th>Actions</th> <!-- New Actions Column -->
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<Book> bookList = (List<Book>) request.getAttribute("bookList");
                        if (bookList != null) {
                            for (Book book : bookList) {
                    %>
                    <tr>
                        <td><%= book.getIsbn() %></td>
                        <td><%= book.getTitle() %></td>
                        <td><%= book.getAuthor() %></td>
                        <td><%= book.getPublisher() %></td>
                        <td><%= book.getPrice() %></td>
                        <td>
                            <button class="btn btn-warning" onclick="openEditModal('<%= book.getIsbn() %>', '<%= book.getTitle() %>', '<%= book.getAuthor() %>', '<%= book.getPublisher() %>', '<%= book.getPrice() %>')">Edit</button>
                            <a href="DeleteBookServlet?isbn=<%= book.getIsbn() %>" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this book?');">Delete</a>
                        </td>
                    </tr>
                    <%
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="6" class="text-center">No books found.</td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Edit Book Modal -->
    <div class="modal fade" id="editBookModal" tabindex="-1" aria-labelledby="editBookModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="EditBookDetailsServlet" method="post">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editBookModalLabel">Edit Book Details</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="editIsbn" class="form-label">ISBN</label>
                            <input type="text" class="form-control" id="editIsbn" name="isbn" readonly>
                        </div>
                        <div class="mb-3">
                            <label for="editTitle" class="form-label">Title</label>
                            <input type="text" class="form-control" id="editTitle" name="title" required>
                        </div>
                        <div class="mb-3">
                            <label for="editAuthor" class="form-label">Author</label>
                            <input type="text" class="form-control" id="editAuthor" name="author" required>
                        </div>
                        <div class="mb-3">
                            <label for="editPublisher" class="form-label">Publisher</label>
                            <input type="text" class="form-control" id="editPublisher" name="publisher" required>
                        </div>
                        <div class="mb-3">
                            <label for="editPrice" class="form-label">Price</label>
                            <input type="number" class="form-control" id="editPrice" name="price" required>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Save changes</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
