<!DOCTYPE html>
<html>
<head>
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

        .generic-container {
            margin-top: 100px;
            width: 100%;
            padding: 10px;
        }
    </style>

</head>
<body ng-app="myApp" class="ng-cloak">

<%--boostrap--%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<!-- AngularJS -->
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.js"></script>
<script src="/static/js/app.js"></script>
<script src="/static/js/controller/wineController.js"></script>
<script src="/static/js/service/wineService.js"></script>

<div class="menu">
    <div class="rounded">
        <a href="/">Home</a>
        <a href="/winelist">Wine List</a>
        <a href="/winecrud">Update</a>
    </div>
</div>

<div class="container" style="width:100%" ng-controller="WineController as ctrl">
    <div class="generic-container">
        <div class="rounded">
            <h2>Wine List</h2>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>name</th>
                    <th>description</th>
                    <th>country</th>
                    <th>quantity</th>
                </tr>
                </thead>

                <tbody>
                <tr ng-repeat="wine in ctrl.wines">
                    <td>{{wine.name}}</td>
                    <td>{{wine.description}}</td>
                    <td>{{wine.country}}</td>
                    <td>{{wine.quantity}}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>