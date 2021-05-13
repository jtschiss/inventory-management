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
                <a class="nav-link" href="addItems.jsp">Add Items</a>
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


<main>
    <c:if test="${requestScope.confirmation != null}">
        <h1 class="success">${requestScope.confirmation}</h1>
    </c:if>

    <form class="d-flex" method="post" action="${pageContext.request.contextPath}/addItem">
        <div class="row-auto mt-3">
            <label class="input-group-text" for="name">Name</label>
            <input type="text" name="name" id="name" placeholder="Name" class="form-control" required>
        </div>
        <div class="row-auto mt-3">
            <label class="input-group-text" for="price">Price</label>
            <input type="text" id="price" name="price" placeholder="x.xx" class="form-control" pattern="^\d{1,5}\.\d\d$" required>
        </div>
        <div class="row-auto mt-3">
            <label class="input-group-text" for="quantity">Quantity</label>
            <input type="text" name="quantity" id="quantity" placeholder="Numerical values only" pattern="^\d+$" class="form-control" required>
        </div>
        <div class="row-auto mt-3">
            <button type="submit" class="btn btn-dark">Add Item</button>
        </div>
    </form>


</main>



</body>
</html>