<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="modal.jsp" />
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #e0f7fa; /* Sky blue */
        }
        .form-container {
            margin-top: 5%;
            background-color: #ffffff; /* White cloud */
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>
   <div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6 form-container">
            <h2 class="text-center">Student Registration</h2>

            <!-- Error Message Display (only red text, no box) -->
            <c:if test="${not empty ErrorMessage}">
                <p style="color: red; font-size: 18px; text-align: center;">
                    ${ErrorMessage}
                </p>
            </c:if>

            <form action="RegisterServlet" method="post">
                <div class="form-group">
                    <label for="prn">PRN No.</label>
                    <input type="text" class="form-control" id="prn" name="prn" required>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <div class="input-group">
                        <input type="password" class="form-control" id="password" name="password" required>
                        <button type="button" class="btn btn-outline-secondary" onclick="togglePassword('password')">Show</button>
                    </div>
                </div>
                <div class="form-group">
                    <label for="confirm_password">Confirm Password</label>
                    <div class="input-group">
                        <input type="password" class="form-control" id="confirm_password" name="confirm_password" required>
                        <button type="button" class="btn btn-outline-secondary" onclick="togglePassword('confirm_password')">Show</button>
                    </div>
                </div>
                <div class="form-group mt-3 text-center">
                    <button type="submit" class="btn btn-primary btn-block" style="width: 50%;">Register</button>
                </div>
            </form>
            <div class="text-center mt-3">
                <p>Already have an account? <a href="login.jsp">Log In</a></p>
            </div>
        </div>
    </div>
</div>

    <script>
        function togglePassword(id) {
            var element = document.getElementById(id);
            if (element.type === "password") {
                element.type = "text";
            } else {
                element.type = "password";
            }
        }
    </script>
</body>
</html>
