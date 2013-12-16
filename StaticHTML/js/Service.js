/**
 * Created with IntelliJ IDEA.
 * User: nicklas
 * Date: 2013-12-16
 * Time: 20:10
 * To change this template use File | Settings | File Templates.
 */
function formatDate(startDate) {
    var date = new Date(startDate);
    return date.toLocaleDateString();
}


function formatStringAsNumber(myNumber, numberOfDecimals) {
    var parsedNumber = parseFloat(myNumber);
    return parseFloat(Math.round(parsedNumber * 100) / 100).toFixed(numberOfDecimals)
}

function toggleDiv(id) {
    $('#' + id).toggle();
}

function getTracks() {
    var trackList = $('#trackList');
    $.getJSON("/api/tracks", function (data) {
        data.track.forEach(function (track) {
            console.log(track.id);
            trackList.append('<span>' +
                '<span style="font-weight:bold" onclick="toggleDiv(\'' + track.id + '_div\')">' + formatStringAsNumber(track.distance, 2) + ' km run on ' + formatDate(track.startDate) + '</span>' +
                '<div id="' + track.id + '_div" style="display:none;">' +
                '<span class="column">Duration: ' + track.duration + '</span>' +
                '<span class="column">Pace: ' + formatStringAsNumber(track.pace,2) + ' min/km</span>' +
                '</div>' +
                '</span><br/><hr>')
        });
    });

}