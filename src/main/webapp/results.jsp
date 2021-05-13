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
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Joah's Inventory</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                    <a class="nav-link active" aria-current="page" href="index.jsp">Home</a>
                    <a class="nav-link" href="#">Features</a>
                    <a class="nav-link" href="#">Pricing</a>
                    <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
                </div>
            </div>
            <form class="d-flex" method="post" action="${pageContext.request.contextPath}/ItemSearch">
                <div class="col-auto">
                    <input type="text" name="search" placeholder="Search for an item" class="form-control">
                </div>
                <div class="col-auto">
                    <button type="submit" class="btn btn-dark">Search</button>
                </div>
            </form>
        </div>

    </nav>

    <div class="container">

        <div class="row">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Item</th>
                    <th scope="col">Price</th>
                    <th scope="col">Quantity</th>
                </tr>
                </thead>
                <tbody>

                <c:if test="${requestScope.results != null}">
                    <c:if test="${requestScope.results.size() > 0}">
                        <c:forEach var="item" items="${requestScope.results}">
                            <tr>
                                <th scope="row">${item.id}</th>
                                <th scope="row"><a href="${pageContext.request.contextPath}/itemData?id=${item.id}">${item.name}</a></th>
                                <th scope="row">$${item.price}</th>
                                <th scope="row">${item.quantity}</th>
                            </tr>
                        </c:forEach>
                    </c:if>
                </c:if>

                </tbody>
            </table>

        </div>

    </div>
</body>
</html>
