<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Part Add</title>
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

    <form class="form-horizontal" method = "POST"  action="/AddPartToRepairPost">

        <input type="hidden" id="repairId" name="repairId" value="${repair.repairId}">

        <div class="form-group">
            <label for="partId" class="control-label col-md-2">Car Parts</label>
            <div class="col-xs-3">
                <select id="partId" name="partId" class="form-control" required>
                    <c:if test="${not empty partList}">

                        <c:forEach var="listValue" items="${partList}">
                            <option value="${listValue.partId}">${listValue.name} - ${listValue.cost}</option>
                        </c:forEach>

                    </c:if>
                </select>
            </div>
        </div>


        <div class="form-group">
            <div class="col-md-offset-2 col-md-10">
                <input type="submit" value="Add" class="btn btn-default" />
            </div>
        </div>
    </form>

    <a class="btn btn-default" href="/RepairIndex"><-</a>
</div>
</body>
</html>
