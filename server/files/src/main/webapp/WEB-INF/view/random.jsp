<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html lang="en">

<head>
    <!-- refresh the page every 3 minutes -->
    <meta http-equiv="Refresh" content="180"> 

    <link rel="stylesheet" type="text/css" href="/layout.css" />
</head>
    
<body>
    <div>

    <img id="randomImage"
         class="mainImage"         
         width="50%"
         height="50%"
         src="${randomImageUrl}"/>
    </div>

    <br/>
    
    Current Port: ${currentPort}

    <%= 44 * 3 %>                
    
    <div>
        <div>
            <h1>Another page</h1>

            <h2>Hello ${message}</h2>
             
            Click on this <strong><a href="/">link</a></strong> to visit previous page.
        </div>
    </div>
            
    <h3>Random Image for You</h3>
</body>
</html>
