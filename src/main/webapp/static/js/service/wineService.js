'use strict';
angular.module('myApp').factory('WineService', ['$http', '$q', function ($http, $q) {

    const REST_SERVICE_URI = "http://localhost:8080/wines/"

    var factory = {
        fetchAllWines: fetchAllWines,
        createWine: createWine,
        updateWine: updateWine,
        deleteWine: deleteWine
    };

    return factory;

    function fetchAllWines() {
        //  set up a defer object to capture your message/data
        console.log("fetchallwine")
        var deferred = $q.defer();
        //  call the backend api   @Getmapping("/")  (full path is http://localhost:8080/WineDragon_war/)
        $http.get(REST_SERVICE_URI)
            .then(
                function (response) {
                    //if it called successfully and backend responded. The data will stored into "defer"
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    // in another hand if it fail. a message of "not success" will be stored into "defer"
                    console.error('Error while fetching Wines');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function createWine(wine) {
        console.log("createWineServices")
        var deferred = $q.defer();
        //  call the backend api    @PostMapping("/") with "Wine" object, backend pickup the "Wine" object by @RequestBody
        $http.post(REST_SERVICE_URI, wine)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while creating Wine');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }


    function updateWine(wine) {
        console.log("updatewineService")
        var deferred = $q.defer();
        //  call the backend api    @PutMapping("/") with "Wine" object, backend pickup the "Wine" object by @RequestBody
        $http.put(REST_SERVICE_URI, wine)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while updating Wine');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function deleteWine(id) {
        var deferred = $q.defer();
        //  instead of removing the wine from the database, it will update the wine(by id), and set its attribute "deleted"
        //  from deleted = false to deleted = true. but it will still uses @DeleteMapping
        //  call the backend api    @DeletedMapping("/{id}")
        $http.delete(REST_SERVICE_URI + id)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while deleting Wine');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }
}]);
