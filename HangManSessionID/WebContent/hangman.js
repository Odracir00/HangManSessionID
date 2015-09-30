
var hangmanStateElement;
var hintElement;
var state = "";
var stateDescription = "";
var hint = "";
var id = "";
var key = "";
var letters = "";
var newLetter = "";
var resultElement = "";
var restart = false;

function init() {

    hangmanStateElement = document.getElementById("hangmantext");
    hangmanStateElement.disabled = true;
    hintElement = document.getElementById("hint");
    resultElement = document.getElementById("result");

    var restartButtonElement = document.getElementById("restart");
    restartButtonElement.style.visibility = "hidden";

    cookieValue = getCookieValue("hangman");

    if (cookieValue === null || restart) {
        cookieValue = createCookieValue();
        setCookie("hangman", cookieValue);
        getData();
    } else {    // get state from cookie
        getState(cookieValue);
        loadState();
    }
}

function loadState() {
    hangmanStateElement.value = stateDescription;
    hangmanStateElement.disabled = true;

    var hintArray = hint.split('');
    var hintHtml = "";
    for (var i = 0; i < hintArray.length; i++) {
        hintHtml += hintArray[i];
    }

    hintElement.innerHTML = hintHtml;

    var lettersArray = letters.split('');
    for (var i = 0; i < lettersArray.length; i++) {
        var letterElement = document.getElementById(lettersArray[i]);
        letterElement.disabled = true;
    }

    if (state === "RIGHT_LEG" || state === "SUCCESS") {
        var restartButtonElement = document.getElementById("restart");
        restartButtonElement.style.visibility = "visible";
        activateLetters(true);
        updateEndGameMessage();
    }
}


function endOfTheGame() {

    updateEndGameMessage();

    var inputs = document.getElementsByClassName("letter");
    for (var i = 0; i < inputs.length; i++) {
        inputs[i].disabled = true;
    }

    var restartButtonElement = document.getElementById("restart");
    restartButtonElement.style.visibility = "visible";
}

function updateEndGameMessage() {
    if (state === "RIGHT_LEG") {  // duplicação
        resultElement.innerHTML = "Game Over";
    }
    if (state === "SUCCESS") {
        resultElement.innerHTML = "SUCCESS - CONGRATULATIONS";
    }
}
    
function getCookieValue(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) === ' ')
            c = c.substring(1);
        if (c.indexOf(name) === 0)
            return c.substring(name.length, c.length);
    }
    return null;
}

function getState(cValue) {

    var startIndex;
    var endIndex;
    var aux = cValue;
    startIndex = cValue.indexOf("ID=");
    endIndex = cValue.indexOf(":");
    id = aux.substring(startIndex + 3, endIndex);

    aux = aux.substring(endIndex + 1);
    
    startIndex = aux.indexOf("KEY=");
    endIndex = aux.indexOf(":");
    key = aux.substring(startIndex + 4, endIndex);

    aux = aux.substring(endIndex + 1);

    startIndex = aux.indexOf("STATE=");
    endIndex = aux.indexOf(":");
    state = aux.substring(startIndex + 6, endIndex);

    aux = aux.substring(endIndex + 1);

    startIndex = aux.indexOf("STATE_DESCRIPTION=");
    endIndex = aux.indexOf(":");
    stateDescription = aux.substring(startIndex + 18, endIndex);

    aux = aux.substring(endIndex + 1);

    startIndex = aux.indexOf("HINT=");
    endIndex = aux.indexOf(":");
    hint = aux.substring(startIndex + 5, endIndex);

    startIndex = aux.indexOf("LETTERS=");
    endIndex = aux.indexOf(";");
    letters = aux.substring(startIndex + 8);
}

function createCookieValue() {
    var newCookieValue = "ID=" + id + ":KEY=" + key + ":STATE=" + state + ":STATE_DESCRIPTION=" + stateDescription + ":HINT=" + hint + ":LETTERS=" + letters;
    return newCookieValue;
}

function setCookie(cname, cValue) {
    var d = new Date();
    d.setTime(d.getTime() + (365 * 24 * 60 * 60 * 1000)); // expires in one year
    var expDate = "" + d.toUTCString();
    document.cookie = cname + "=" + cValue + "; expires=" + expDate + ";path=/";
}

function tryLetter(letter) {
    document.getElementById(letter).disabled = true;
    letters = letters + letter;
    newLetter = letter;
    getData();
}


function getData() {
    var url = "hangman?action=tryletter&id=" + id + "&key=" + key + "&state=" + state +
            "&hint=" + hint + "&newletter=" + newLetter + "&triedletters=" + letters;
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

function updateStateDescription(newState) {
    if (newState === state) {
        // do nothing
    } else {
        stateDescription = stateDescription + " " + newState + "   ";
    }
}

function parseMessages(responseXML) {
    if (responseXML === null) {    // no matches returned
        return false;
    } else {
        id = responseXML.getElementsByTagName("id")[0].childNodes[0].nodeValue;
        key = responseXML.getElementsByTagName("key")[0].childNodes[0].nodeValue;
        var newState = responseXML.getElementsByTagName("state")[0].childNodes[0].nodeValue;
        updateStateDescription(newState);
        hangmantext.value = stateDescription;
        
        state = newState;

        if (state === "RIGHT_LEG" || state === "SUCCESS") {
            endOfTheGame();
        }

        hint = responseXML.getElementsByTagName("hint")[0].childNodes[0].nodeValue;

        // update cookie
        cookieValue = createCookieValue();
        setCookie("hangman", cookieValue);

        hintElement.innerHTML = hint;
    }
}

function playAgain() {
    // reset variables
    hangmanStateElement = "";
    state = "";
    stateDescription = "";
    hint = "";
    id = "";
    key = "";
    letters = "";
    newLetter = "";
    resultElement.innerHTML = "Playing Hang Man ...";

    // reset cookie
    var cookieValue = createCookieValue();
    setCookie("hangman", cookieValue);

    activateLetters(false) ;
    restart = true;
    init();
}

function activateLetters(boolean) {
    var inputs = document.getElementsByClassName("letter");
    for (var i = 0; i < inputs.length; i++) {
        inputs[i].disabled = boolean;
    }
}