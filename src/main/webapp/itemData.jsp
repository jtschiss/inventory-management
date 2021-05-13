<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">

    <title>Hello, world!</title>
</head>
<body>
    <div class="container">

        <table class="table">
            <tbody>
                <c:if test="${requestScope.item != null}">
                    <c:if test="${requestScope.item.size() > 0}">
                        <tr>
                            <th scope="row">ID</th>
                            <td>${requestScope.item.id}</td>
                        </tr>
                        <tr>
                            <th scope="row">Item</th>
                            <td>${requestScope.item.name}</td>
                        </tr>
                        <tr>
                            <th scope="row">Price</th>
                            <td>$${requestScope.item.price}</td>
                        </tr>
                        <tr>
                            <th scope="row">Quantity</th>
                            <td>${requestScope.item.quantity}</td>
                        </tr>

                    </c:if>
                </c:if>

            </tbody>

        </table>





    </div>
</body>
</html>
