<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Owner Edit</title>
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
    <form class="form-horizontal" method = "POST"  action="/OwnerEditPost">
        <h1>Owner edit</h1>

        <div class="form-group">
            <label for="Username" class="control-label col-md-2">Email</label>
            <div class="col-xs-3">
                <input type="email" name="username" value="${owner.username}" id="Username"  class="form-control" required readonly>
            </div>
        </div>

        <div class="form-group">
            <label for="FirstName" class="control-label col-md-2">First Name</label>
            <div class="col-xs-3">
                <input type="text" name="firstName" value="${owner.firstName}" id="FirstName" class="form-control" required>
            </div>
        </div>

        <div class="form-group">
            <label for="LastName" class="control-label col-md-2">Last Name</label>
            <div class="col-xs-3">
                <input type="text" name="lastName" value="${owner.lastName}" id="LastName" class="form-control"  required>
            </div>
        </div>

        <div class="form-group">
            <label for="PhoneNumber" class="control-label col-md-2">Phone Number</label>
            <div class="col-xs-3">
                <input type="text" name="phoneNr" value="${owner.phoneNr}" id="PhoneNumber" class="form-control">
            </div>
        </div>

        <input type="hidden" id="orderId" name="ownerId" value="${owner.ownerId}">

        <div class="form-group">
            <div class="col-md-offset-2 col-md-10">
                <input type="submit" value="Save" class="btn btn-default" />
            </div>
        </div>
    </form>
</div>
</body>
</html>
