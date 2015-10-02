
var hangmanStateElement;
var hintElement;
var state = "";
var newLetter = "";
var resultElement = "";

function init() {

	hangmanStateElement = document.getElementById("hangmantext");
	hangmanStateElement.disabled = true;
	hintElement = document.getElementById("hint");
	resultElement = document.getElementById("result");

	var restartButtonElement = document.getElementById("restart");
	restartButtonElement.style.visibility = "hidden";

	getData();
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
    if (state === "RIGHT_LEG") {
        resultElement.innerHTML = "Game Over";
    }
    if (state === "SUCCESS") {
        resultElement.innerHTML = "SUCCESS - CONGRATULATIONS";
    }
}


function tryLetter(letter) {
    document.getElementById(letter).disabled = true;
    newLetter = letter;
    getData();
}


function getData() {
    var url = "hangman?action=tryletter&newletter=" + newLetter;
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
        state = responseXML.getElementsByTagName("state")[0].childNodes[0].nodeValue;
        hangmantext.value = state; 

        if (state === "RIGHT_LEG" || state === "SUCCESS") {
            endOfTheGame();
        } else {
            var nodes = responseXML.getElementsByTagName("letters")[0].childNodes;
            var t_letters = (nodes.length===1) ? nodes[0].nodeValue : "";
            disableTriedLetters(t_letters.split(''));
        }

        hintElement.innerHTML = responseXML.getElementsByTagName("hint")[0].childNodes[0].nodeValue;
     }
}

function playAgain() {
    // reset variables
    hangmanStateElement = "";
    state = "";
    newLetter = "";
    resultElement.innerHTML = "Playing Hang Man ...";

    activateLetters(false) ;
    init();
}

function activateLetters(boolean) {
    var inputs = document.getElementsByClassName("letter");
    for (var i = 0; i < inputs.length; i++) {
        inputs[i].disabled = boolean;
    }
}

function disableTriedLetters(characters) {
	var inputs = document.getElementsByClassName("letter");
	for (var i = 0; i < inputs.length; i++) {
		inputs[i].disabled = false;
	}

	for (var i = 0; i < characters.length; i++) {
		c = document.getElementById(characters[i]);
		c.disabled = true;
	}
}