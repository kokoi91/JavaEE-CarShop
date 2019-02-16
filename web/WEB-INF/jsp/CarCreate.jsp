<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Car Create</title>
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
    <form class="form-horizontal" method = "POST"  action="/createCarPost">
        <h1>Add car</h1>
        <div class="form-group">
            <label  for="Model"  class="control-label col-md-2">Model</label>
            <div class="col-xs-3">
                <input type="text" name="model" id="Model" class="form-control" required>
            </div>
        </div>

        <div class="form-group">
            <label for="Year_" class="control-label col-md-2">Year</label>
            <div class="col-xs-3">
                <input type="text" name="Year_" id="Year_" class="form-control" required>
            </div>
        </div>

        <input type="hidden" id="ownerId" name="ownerId" value="${OwnerId}">


        <div class="form-group">
            <label for="Name" class="control-label col-md-2">Name</label>
            <div class="col-xs-3">
                <input type="text" name="name" id="Name" class="form-control" required>
            </div>
        </div>

        <div class="form-group">
            <label for="DateOfBought" class="control-label col-md-2">Date of bought</label>
            <div class="col-xs-3">
                <input type="date" name="DateOfBought" id="DateOfBought" class="form-control" required>
            </div>
        </div>


        <div class="form-group">
            <div class="col-md-offset-2 col-md-10">
                <input type="submit" value="Add" class="btn btn-default" />
            </div>
        </div>
    </form>
</div>
</body>
</html>
