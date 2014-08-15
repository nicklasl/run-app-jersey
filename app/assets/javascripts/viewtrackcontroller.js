var trackApp = angular.module('trackApp', ['ui.bootstrap', 'leaflet-directive']);

trackApp.controller('ViewTrackController', function ($scope, $http, $modal) {

    $scope.tracks = [];
    $scope.predicate = '-date';

    $http.get('/tracks').success(function (response) {
        angular.forEach(response, function (each) {
            $scope.tracks.push(each);
        })
    });

    $scope.showDetails = function (track) {
        $modal.open({
            templateUrl: 'assets/fragments/modal.html',
            controller: modalController,
            resolve: {
                track: function () {
                    return track;
                }
            }
        });
    }

});

var modalController = function ($scope, $modalInstance, track) {

    angular.extend($scope, {
        center: {
            lat: 48,
            lng: 4,
            zoom: 4
        },
        paths: {
            p1: {
                color: '#008000',
                weight: 1
            }
        }
    });

    $scope.showSegment = function (segment) {
        console.log(segment.points[0].coordinates.latitude, segment.points[0].coordinates.longitude)


        var coordinates = _.map(segment.points, function (node) {
            return {lat: node.coordinates.latitude, lng: node.coordinates.longitude};
        });

        $scope.paths = {};
        $scope.paths.p1 = {};
        $scope.paths.p1.latlngs = coordinates;
        $scope.center = {
            lat: coordinates[0].lat,
            lng: coordinates[0].lng,
            zoom: 14
        };
    }

    $scope.showSegment(track.segments[0]);


    $scope.track = track;



    $scope.close = function () {
        $modalInstance.dismiss();
    }


};

