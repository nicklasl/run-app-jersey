var trackApp = angular.module('trackApp', ['ui.bootstrap', 'leaflet-directive', 'angularFileUpload']);

trackApp.controller('MainController', function ($scope, $http, $modal) {

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

    $scope.addNew = function() {
        $modal.open({
            templateUrl: 'assets/fragments/modal_upload.html',
            controller: uploadController
        });
    }

});

var uploadController = function ($scope, $modalInstance, $upload) {

    $scope.onFileSelect = function($files) {
        console.log("files:", $files);
        //$files: an array of files selected, each file has name, size, and type.
        for (var i = 0; i < $files.length; i++) {
            var file = $files[i];
            $scope.upload = $upload.upload({
                url: 'gpx',
                method: 'PUT',
                data: {
                    gpx: $scope.myModelObj
                },
                file: file // or list of files ($files) for html5 only
            }).progress(function(evt) {
                    console.log('percent: ' + parseInt(100.0 * evt.loaded / evt.total));
                }).success(function(data, status, headers, config) {
                    console.log(data);
                });
        }
    };

    $scope.close = function () {
        $modalInstance.dismiss();
    }

}

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

