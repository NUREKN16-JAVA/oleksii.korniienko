<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>User Management/Details</title>
    <style>
        input[type="button"]{
            width:20px;
        }
    </style>
</head>
<body>
<jsp:useBean id="user" class="ua.nure.kn.kornienko.usermanagement.User" scope="session"/>

<p><b>First name:</b> ${user.firstName}</p>
<p><b>Last name:</b> ${user.lastName}</p>
<p><b>Date of birth:</b> <fmt:formatDate value="${user.dateOfBirth}" type="date" dateStyle="medium"/></p>

<form method="post" action="/details">
    <input type="submit" name="back" value="Back">
</form>
<c:if test="${requestScope.error != null}">
    <script>
        alert("${requestScope.error}")
    </script>
</c:if>
</body>
</html>