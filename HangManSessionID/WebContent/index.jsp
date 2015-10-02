<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hang Man</title>
        <script type="text/javascript" src="hangman.js"></script>
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
    </head>
    <body onload="init()">
        <h1>Play Hang Man</h1>

        <table>
            <tr>
                <th width="200">
<!--                 <img src="/HangManSessionID/imgs/HangingMan.jpg" alt="Hanging Man" width="200"> -->
                </th>
                <th width="200"><textarea id="hangmantext" name="hangmanstate" action="showstate" rows="10" cols="20">
                    </textarea></th>
                <th width="200">
                    <button class="letter" id="a" onclick="tryLetter(id)">A</button>
                    <button class="letter" id="b" onclick="tryLetter(id)">B</button>
                    <button class="letter" id="c" onclick="tryLetter(id)">C</button>
                    <button class="letter" id="d" onclick="tryLetter(id)">D</button>
                    <button class="letter" id="e" onclick="tryLetter(id)">E</button>
                    <br/>
                    <button class="letter" id="f" onclick="tryLetter(id)">F</button>
                    <button class="letter" id="g" onclick="tryLetter(id)">G</button>
                    <button class="letter" id="h" onclick="tryLetter(id)">H</button>
                    <button class="letter" id="i" onclick="tryLetter(id)">I</button>
                    <button class="letter" id="j" onclick="tryLetter(id)">J</button>
                    <br/>
                    <button class="letter" id="k" onclick="tryLetter(id)">K</button>
                    <button class="letter" id="l" onclick="tryLetter(id)">L</button>
                    <button class="letter" id="m" onclick="tryLetter(id)">M</button>
                    <button class="letter" id="n" onclick="tryLetter(id)">N</button>
                    <button class="letter" id="o" onclick="tryLetter(id)">O</button>
                    <br/>
                    <button class="letter" id="p" onclick="tryLetter(id)">P</button>
                    <button class="letter" id="q" onclick="tryLetter(id)">Q</button>
                    <button class="letter" id="r" onclick="tryLetter(id)">R</button>
                    <button class="letter" id="s" onclick="tryLetter(id)">S</button>
                    <button class="letter" id="t" onclick="tryLetter(id)">T</button>
                    <br/>
                    <button class="letter" id="u" onclick="tryLetter(id)">U</button>
                    <button class="letter" id="v" onclick="tryLetter(id)">V</button>
                    <button class="letter" id="w" onclick="tryLetter(id)">W</button>
                    <button class="letter" id="x" onclick="tryLetter(id)">X</button>
                    <button class="letter" id="y" onclick="tryLetter(id)">Y</button>
                    <br/>
                    <button class="letter" id="z" onclick="tryLetter(id)">Z</button>
                    <br/>
                </th> 
            </tr>
        </table>

        <br/>

        <h2 id="result">Playing Hang Man ... Guess which country</h2>
        <br/>
        <h2>Hint</h2>
        <div id="hint"> </div>
        <br/>
        <div id="restart"> <button onclick="playAgain()" >Play Again !</button> </div>

    </body>
</html>
