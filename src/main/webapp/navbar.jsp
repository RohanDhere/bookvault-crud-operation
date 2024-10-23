<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .navbar-custo{
            background-color: #343a40; /* Dark background for navbar */
        }
        .navbar-title {
            color: #ffffff; /* White color for title */
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark navbar-custo">
        <div class="container-fluid">
            <a class="navbar-brand navbar-title">Book Management System</a>
            <div class="d-flex ms-auto">
                <a href="LogoutServlet" class="btn btn-danger">Logout</a>
            </div>
        </div>
    </nav>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
