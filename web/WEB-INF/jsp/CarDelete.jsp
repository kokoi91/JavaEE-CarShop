<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Car Delete</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="index.jsp">HOME</a>
                        <% if (session.getAttribute("OwnerId") != null ) { %>
                <li>
                    <a data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        CARS
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <li><a href="/CarIndex">My cars</a></li>
                        <li><a href="/CarCreate">Add car</a></li>
                    </ul>
                </li>

                <li>
                    <a  data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        REPAIRS
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <li><a href="/RepairIndex">My repairs</a></li>
                        <li><a href="/RepairCreate">Add repair</a></li>
                    </ul>
                </li>

                <% } %>
                <li><a href="/PartIndex">
                    PARTS
                </a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <% if (session.getAttribute("OwnerId") == null ) { %>
                <li><a href="/Registration">Sign up</a></li>
                <li><a href ="/Login">Log in</a></li>
                <% } else {%>
                <li><a href="/OwnerDetails">Hello ${Username} </a></li>
                <li><a href ="/Logout">Logout</a></li>
                <% } %>
            </ul>
        </div>
    </div>
</div>
<div class="jumbotron">

    <h1>Delete car </h1>
    <dl class="dl-horizontal">

        <dt> Model          </dt>
        <dd>   ${car.model}  </dd>

        <dt>  Year</dt>
        <dd>  ${car.year_} </dd>

        <dt> Name </dt>
        <dd> ${car.name} </dd>

        <dt> Date of bought </dt>
        <dd> ${car.dateOfBought} </dd>

    </dl>

    <form method="POST">

        <input type="hidden" id="carId" name="carId" value="${car.carId}">

        <input type="submit" value="Delete"  class="btn btn-default" formaction="/CarDeletePost" />
        <a class="btn btn-default" href="/CarIndex"><-</a>
    </form>

</div>
</body>
</html>
