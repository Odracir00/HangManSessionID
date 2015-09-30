<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hang Man Monitor</title>
        <script type="text/javascript" src="monitor.js"></script>
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
    </head>
    <body onload="getData()">
        <h1>Hang Man Monitor</h1>
        <br/>
        <h2>Games Being played: <span id="gamescounter"> </span> </h2>
        <br/>

        <div id="monitorstate"> </div>
        <br/>

        <br/>
        <div id="refresh"> <button onclick="getData()" >Refesh Data</button> </div>


    </body>
</html>
