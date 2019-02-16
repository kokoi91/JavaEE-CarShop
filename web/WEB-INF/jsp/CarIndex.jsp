<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Car Index</title>
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

    <h1>My cars</h1>

    <c:if test="${not empty carsList}">
        <table class="table">
            <tr>
                <th>Model</th>
                <th>Year</th>
                <th>Name</th>
                <th>Date of bought</th>
                <th></th>
            </tr>
            <c:forEach var="listValue" items="${carsList}">

                <tr>
                    <td>${listValue.model}</td>
                    <td>${listValue.year_}</td>
                    <td>${listValue.name}</td>
                    <td>${listValue.dateOfBought}</td>
                    <td>
                        <form method="POST">

                            <input type="hidden" id="ownerId" name="carId" value="${listValue.carId}">

                            <input type="submit" value="Edit"  class="btn btn-default" formaction="/CarEdit" />
                            <input type="submit" value="Details"  class="btn btn-default" formaction="/CarDetails" />
                            <input type="submit" value="Delete"  class="btn btn-default" formaction="/CarDelete" />
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

</div>
</body>
</html>
