
function getData() {
    var url = "monitor?action=displaygames";
    req = initRequest();
    req.open("GET", url, true);
    req.onreadystatechange = callback;
    req.send(null);
}

function initRequest() {
    if (window.XMLHttpRequest) {
        if (navigator.userAgent.indexOf('MSIE') != -1) {
            isIE = true;
        }
        return new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        isIE = true;
        return new ActiveXObject("Microsoft.XMLHTTP");
    }
}

function callback() {
    if (req.readyState === 4) {
        if (req.status === 200) {
            parseMessages(req.responseXML);
        }
    }
}

function parseMessages(responseXML) {
    if (responseXML === null) {    // no matches returned
        return false;
    } else {
        monitorStateElement = document.getElementById("monitorstate");
        var gamesDescriptor = "";
        var games = responseXML.getElementsByTagName("games")[0];

        var gamesCounter = games.childNodes.length;
        if (gamesCounter > 0) {
            for (loop = 0; loop < games.childNodes.length; loop++) {
                var game = games.childNodes[loop];
                gamesDescriptor += "<div  class=\"gamestate\" >" + game.innerHTML + "</div></br>"
            }
        }
        monitorStateElement.innerHTML = decodeSpecialCharacters(gamesDescriptor);

        gamesCounterElement = document.getElementById("gamescounter");
        gamesCounterElement.innerHTML = gamesCounter;
    }
}

function decodeSpecialCharacters(s) {
    return s.replace(/&gt;/g, '>').replace(/&lt;/g, '<');
}