var map;
var markersArray = [];
function initialize() {
    console.log("init map is running");
    var mapOptions = {
        zoom: 8,
        center: new google.maps.LatLng(59.20, 18.03)
    };
    map = new google.maps.Map(document.getElementById('map-canvas'),
        mapOptions);
}

function clearMapData() {
    for (var i = 0; i < markersArray.length; i++) {
        markersArray[i].setMap(null);
    }
    markersArray.length = 0;
}

function showLines(array, hexColor) {
    var coordinates = $.map(array, function (node) {
        return new google.maps.LatLng(node.coordinates.latitude, node.coordinates.longitude);
    });
    var path = new google.maps.Polyline({
        path: coordinates,
        geodesic: true,
        strokeColor: hexColor,
        strokeOpacity: 1.0,
        strokeWeight: 2
    });
    markersArray.push(path);
    path.setMap(map);
}

function setCenter(lat, lng) {
    var location = new google.maps.LatLng(lat, lng);
    // using global variable:
    map.panTo(location);
}

function setZoom(zoomFactor) {
    map.setZoom(zoomFactor);
}

google.maps.event.addDomListener(window, 'load', initialize);