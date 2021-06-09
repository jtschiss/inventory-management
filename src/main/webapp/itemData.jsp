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

    <title>Joah's Inventory</title>
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

    <div class="container align-items-center">


        <div class="row mb-3 mt-3">
            <table class="table">
                <tbody>
                <c:if test="${requestScope.item != null}">
                    <tr>
                        <th scope="row">Item Number</th>
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
                        <td>
                            <form class="d-flex" method="post" action="${pageContext.request.contextPath}/updateQuantity">
                                <div class="col-auto">
                                    <input type="text" name="quantity" value="${requestScope.item.quantity}" class="form-control" required>
                                </div>
                                <div class="col-auto">
                                    <input type="hidden" name="id" value="${requestScope.item.id}">
                                </div>
                                <div class="col-auto">
                                    <button type="submit" class="btn btn-dark">Update Quantity</button>
                                </div>
                            </form>


                        </td>
                    </tr>
                    <tr>
                        <th scope="row">Locations</th>
                        <td>

                                        <c:if test="${requestScope.locations != null}">
                                            <c:if test="${requestScope.locations.size() > 0}">
                                            <table class="table">
                                                    <thead>
                                                        <tr>
                                                            <th scope="row">Section</th>
                                                            <th scope="row">Shelf</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach var="location" items="${requestScope.locations}">
                                                            <tr>
                                                                <td>${location.section}</td>
                                                                <td>${location.shelf}</td>
                                                                <td><a href="${pageContext.request.contextPath}/deleteLocation?location_id=${location.id}&item_id=${item.id}">Delete</a></td>
                                                            </tr>
                                                        </c:forEach>
                                                </tbody>
                                            </table>
                                            </c:if>
                                        </c:if>

                            <form class="d-flex" method="post" action="${pageContext.request.contextPath}/addItemToLocation">
                                <div class="col-auto">
                                    <input type="text" name="section" placeholder="Section" class="form-control" required>
                                </div>
                                <div class="col-auto">
                                    <input type="text" name="shelf" placeholder="Shelf" class="form-control" required>
                                </div>
                                <div class="col-auto">
                                    <input type="hidden" name="id" value="${requestScope.item.id}">
                                </div>
                                <div class="col-auto">
                                    <button type="submit" class="btn btn-dark">Add Location</button>
                                </div>
                            </form>

                        </td>
                    </tr>
                </c:if>

                </tbody>

            </table>
        </div>






    </div>
</body>
</html>
