<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>User Management/Add</title>
    <style>
        input[type="button"]{
            width:20px;
        }
    </style>
</head>
<body>
<form method="post" action="/add">
    <table>
        <tr>
            <th>First name</th>
            <td><input type="text" name="firstName"></td>
        </tr>
        <tr>
            <th>Last name</th>
            <td><input type="text" name="lastName"></td>
        </tr>
        <tr>
            <th>Date of birth</th>
            <td><input type="text" name="dateOfBirth"></td>
        </tr>
    </table>
    <input type="submit" name="ok" value="Ok">
    <input type="submit" name="cancel" value="Cancel">
</form>
<c:if test="${requestScope.error != null}">
    <script>
        alert("${requestScope.error}")
    </script>
</c:if>
</body>
</html>