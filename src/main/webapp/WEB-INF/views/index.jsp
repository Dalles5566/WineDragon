<!DOCTYPE html>
<html>
<head>
    <title>Wine Dragon</title>
    <style>
        body {
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
        }

        .menu {
            overflow: hidden;
            margin: auto;
            width: 50%;
            padding: 10px;
        }

        .menu a {
            float: left;
            color: black;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 17px;
        }

        .menu a:hover {
            background-color: #ddd;
            color: black;
            border-radius: 8px;
        }

        .welcome {
            margin: auto;
            width: 50%;
            padding: 10px;
            font-size: xxx-large;
        }
    </style>
</head>
<body>
<%--boostrap--%>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<!-- AngularJS -->
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.js"></script>
<script src="/static/js/app.js"></script>
<script src="/static/js/controller/wineController.js"></script>
<script src="/static/js/service/wineService.js"></script>


<div class="menu">
    <a href="/">Home</a>
    <a href="/winelist">Wine List</a>
    <a href="/winecrud">Update</a>
</div>

<div class="welcome">
    Welcome to Wine Dragon
</div>

</body>
</html>
