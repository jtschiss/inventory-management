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

    <h1>Joah's Inventory Management</h1>

    <form method="post" action="${pageContext.request.contextPath}/ItemSearch">
        <input type="text" name="search" placeholder="Search for an item">
        <input class="input-group-text" id="basic-addon1" type="submit" value="Search">
    </form>

</body>
</html>