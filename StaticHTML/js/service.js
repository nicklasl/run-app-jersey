/**
 * Created with IntelliJ IDEA.
 * User: nicklas
 * Date: 2013-12-16
 * Time: 20:10
 * To change this template use File | Settings | File Templates.
 */
var tracks;

function formatDate(startDate) {
    var date = new Date(startDate);
    return date.toLocaleDateString();
}


function formatStringAsNumber(myNumber, numberOfDecimals) {
    var parsedNumber = parseFloat(myNumber);
    return parseFloat(Math.round(parsedNumber * 100) / 100).toFixed(numberOfDecimals)
}

function getTrackFromId(trackId) {
    for (var i = 0; i < tracks.length; i++) {
        console.log(tracks[i].id);
        if (trackId == tracks[i].id) {
            return tracks[i];
        }
    }
}

function toggleDiv(trackId) {
    var track = getTrackFromId(trackId);
    var color = generateRandomHexColor();
    showLines(track.segments.points, color);
    var point = track.segments.points[0].coordinates;
    setCenter(point.latitude, point.longitude);
    setZoom(12);
    $('#' + trackId + '_div').toggle();
}

function generateRandomHexColor() {
    return '#' + Math.floor(Math.random() * 16777215).toString(16);
}
function getTracks() {
    var trackList = $('#trackList');
    trackList.empty();
    $.getJSON("/api/tracks", function (data) {
        tracks = data.track;
        data.track.forEach(function (track) {
            trackList.append('<span>' +
                '<span style="font-weight:bold; cursor: pointer;" onclick="toggleDiv(' + track.id + ')">' + formatStringAsNumber(track.distance, 2) + ' km run on ' + formatDate(track.startDate) + '</span>' +
                '<div id="' + track.id + '_div" style="display:none;">' +
                '<span class="column">Duration: ' + track.duration + '</span>' +
                '<span class="column">Pace: ' + formatStringAsNumber(track.pace, 2) + ' min/km</span>' +
                '</div>' +
                '</span><br/><hr>')
        });
    });
}
