'use strict';

angular.module('myApp')
    .controller('WineController', ['$scope', 'WineService', function ($scope, WineService) {
            var self = this;
            self.wine = {};
            self.wines = [];

            self.create = createWine;
            self.update = updateWine
            self.delete = deleteWine;
            self.reset = reset;

            fetchAllWines();

            function fetchAllWines() {
                WineService.fetchAllWines()
                    .then(
                        function (d) {
                            self.wines = d;
                        }
                    );
            }

            function createWine(wine) {
                console.log("createwineController")
                WineService.createWine(wine)
                    .then(
                        reset())
                    .then(
                        fetchAllWines
                    );
            }

            function updateWine(wine) {
                console.log("updatewineController")
                WineService.updateWine(wine)
                    .then(
                        fetchAllWines
                    );
            }

            function deleteWine(id) {
                console.log("deletewine")
                WineService.deleteWine(id)
                    .then(
                        fetchAllWines
                    );
            }

            function reset() {
                console.log("reset");
                self.wine = {name: '', description: '', country: '', quantity: 0};
                $scope.addWineForm.$setPristine(); //reset Form
            }
        }
        ]
    );