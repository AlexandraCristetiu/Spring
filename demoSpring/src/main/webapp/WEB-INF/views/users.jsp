
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/css/users.css">
</head>
<body>
<p>Hello</p>
<p>
    <c:forEach var = "u" items = "${users}">
    <li>
     <a href="/mvc/user/${u.id}/delete">
     <img class = "deleteUser"/>
     </a>
     <span><c:out value = "${u.name}"/> </li></span>
    </c:forEach>
</p>
</body>
</html>