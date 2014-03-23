var trackApp = angular.module('trackApp', []);

trackApp.controller('ViewTrackController', function ($scope, $http) {

    $scope.tracks = [];

    $http.get('/tracks').success(function(response){
        angular.forEach(response, function(each){
            $scope.tracks.push(each);
        })
    });

    $scope.awesome = "Legendary";
});

