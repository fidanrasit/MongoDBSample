<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value='tr_TR'/>
<fmt:setBundle basename='com.infonal.resources.App'/>

<html>  
<head>
<title><fmt:message key='project.title'/></title>
</head>  
<body>  
    TEST 12345
    <center>
        <h4>
            <a href="UserPage"><fmt:message key='user.pagetitle'/></a>
        </h4>  
    </center>  
</body>  
</html> 
