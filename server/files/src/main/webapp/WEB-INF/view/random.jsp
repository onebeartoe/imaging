<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<body>
    <div>
        <div>
            <h1>Another page</h1>
            <h2>Hello ${message}</h2>
             
            Click on this <strong><a href="/">link</a></strong> to visit previous page.
        </div>
    </div>
            
    <h3>Random Image</h3>

    <img id="randomImage" 
         src="http://someimage-server:8080/${randomImageUrl}"/>

    <br/>
    
    Current Port: ${currentPort}
    

    <%= 44 * 3 %>            
</body>
</html>