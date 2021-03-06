<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        var Msg ='<%=request.getParameter("eType")%>';
        if (Msg != "null") {
                  alert("The user with the given e-mail already exists.");
                   }
    </script>
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


<form class="form-horizontal" method = "POST"  action="/RegistrationPOST">
    <h4>Owner Create</h4>
    <hr />

    <div class="form-group">
        <label for="Username" class="control-label col-md-2">Email</label>
        <div class="col-xs-3">
            <input type="email" name="username" id="Username" placeholder="Podaj email username" class="form-control" required>
        </div>
    </div>

    <div class="form-group">
        <label for="Password" class="control-label col-md-2">Hasło</label>
        <div class="col-xs-3">
            <input type="password" name="password" id="Password" placeholder="Podaj hasło" class="form-control" required>
        </div>
    </div>

    <div class="form-group">
        <label for="FirstName" class="control-label col-md-2">First Name</label>
        <div class="col-xs-3">
        <input type="text" name="firstName" id="FirstName" class="form-control" required>
        </div>
    </div>

    <div class="form-group">
        <label for="LastName" class="control-label col-md-2">Last Name</label>
        <div class="col-xs-3">
        <input type="text" name="lastName" id="LastName" class="form-control"  required>
        </div>
    </div>

    <div class="form-group">
        <label for="PhoneNumber" class="control-label col-md-2">Phone Number</label>
            <div class="col-xs-3">
        <input type="text" name="phoneNr" id="PhoneNumber" class="form-control">
        </div>
    </div>


    <div class="form-group">
        <div class="col-md-offset-2 col-md-10">
            <input type="submit" value="Create" class="btn btn-default" />
        </div>
    </div>

</form>
</body>
</html>
