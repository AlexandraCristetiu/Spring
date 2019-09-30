<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
       <body>
            <p>
            Insert
            </p>
            <form action="/mvc/user/insert" method= "POST">
                <p>Name: <input type="text" name = "name"/></p>
                <p>Email: <input type="text" name = "email"/></p>
                <p><input type ="submit" name = "submit"/></p>
            </form>
        </body>
</html>