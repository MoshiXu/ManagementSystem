<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>HOME</title>
	</head>
	<body>
		<h2>Hello I did it!</h2>
		
		<p>
		<%-- 
			User: <security:authentication property="principal.username"/>
			<br><br>
			Roles: <security:authentication property="principal.authorities"/> --%>
		</p>
		
	</body>
</html>