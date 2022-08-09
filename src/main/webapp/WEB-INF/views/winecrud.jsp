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


        .table {
            border: 1px solid black;
        }

        .top-container {
            margin-top: 20px;
            width: 50%;
            padding: 10px;
        }

        #nameText, #nameDescription,#nameCountry, #nameQuantity, #addButton, #resetButton {
            margin-top: 10px;
            margin-right: 10px;
            margin-bottom:10px;
        }

        .bottom-container {
            float: bottom;
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
    <a href="/">Home</a>
    <a href="/winelist">Wine List</a>
    Welcome, ${username}!
</div>

<div class="container" style="width:100%" ng-controller="WineController as ctrl">

    <div class="top-container">
        <form id="addWineForm" name="addWineForm">

            <div id="textFormContainer">

                <div id="nameText " class="form-control">
                    <label class="form-label" for="newWineName">name</label>
                    <input type="text" ng-model="wine.name" id="newWineName" placeholder="Enter wine's name" required>
                    <div class="has-error" ng-show="addWineForm.$dirty">
                        <span ng-show="addWineForm.newWineName.$error.required">This is a required field</span>
                        <span ng-show="addWineForm.newWineName.$invalid">This field is invalid </span>
                    </div>
                </div>

                <div id="nameDescription" class="form-control">
                    <label class="form-label" for="newWineDescription">description</label>
                    <input type="text" ng-model="wine.description" id="newWineDescription"
                           placeholder="Enter Description">
                </div>

                <div id="nameCountry" class="form-control">
                    <label class="form-label" for="newWineCountry">country</label>
                    <input type="text" ng-model="wine.country" id="newWineCountry" placeholder="Enter Country">
                </div>

                <div id="nameQuantity" class="form-control">
                    <label class="form-label" for="newWineQuantity" >quantity</label>
                    <input type="number" ng-model="wine.quantity" id="newWineQuantity" placeholder="Enter Quantity" required="required" min="1">
                </div>

                <div id = "addButton">
                    <button type="button" ng-click="ctrl.create(wine)" ng-disabled="addWineForm.$invalid"
                            class="btn btn-outline-success">saved
                    </button>
                </div>

                <div id = "resetButton">
                    <button type="button" ng-click="ctrl.reset()" ng-disabled="addWineForm.$pristine"
                            class="btn btn-outline-warning">Reset Form
                    </button>
                </div>
            </div>
        </form>
    </div>

    <div class="bottom-container">
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
                <form id="updateWineForm" name="updateWineForm">
                    <label class="form-label" for="id"></label>
                    <input type="hidden" ng-model="wine.id" id="id" readonly>

                    <label class="form-label" for="name"></label>
                    <td><input type="text" ng-model="wine.name" id="name"></td>

                    <label class="form-label" for="description"></label>
                    <td><input type="text" ng-model="wine.description" id="description"></td>
                    <label class="form-label" for="country"></label>
                    <td><input type="text" ng-model="wine.country" id="country"></td>
                    <label class="form-label" for="quantity"></label>
                    <td><input type="number" ng-model="wine.quantity" id="quantity"></td>
                    <td>
                        <button type="button" ng-click="ctrl.update(wine)" class="btn btn-outline-info">update</button>
                        <button type="button" ng-click="ctrl.delete(wine.id)" class="btn btn-outline-danger">Remove</button>
                    </td>
                </form>
            </tr>
            </tbody>
        </table>
    </div>
</div>


</body>
</html>
