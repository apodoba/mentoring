<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<style type="text/css">
body {
	background-image:
		url('http://cdn3.crunchify.com/wp-content/uploads/2013/03/Crunchify.bg_.300.png');
}
</style>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Created patterns application</title>
</head>
<body>
	<div style="margin: 40px;">
		<span style="font-size: x-large;">Services:</span>
		<table style="margin-top: 20px;">
			<tr>
				<th>Name</th>
				<th></th>
				<th></th>
			</tr>
			<c:forEach items="${services}" var="item">
			<form action="" method='post'>
				<tr>
					<td style="padding: 5px;">
						<input type="text" name="name" value="${item.name}" /> 
						<input type="hidden" name="id" value="${item.id}" /> 
						<input type="hidden" name="object" value="service" />
					</td>
					<td style="padding: 5px;"><input type="submit" name="action" value="Update"></td>
					<td style="padding: 5px;"><input type="submit" name="action" value="Delete"></td>
				</tr>
			</form>
			</c:forEach>
			<form action="" method='post'>
				<tr>
					<td style="padding: 5px;"><input type="text" name="name" value="" /> 
						<input type="hidden" name="id" value="" /> 
						<input type="hidden" name="object" value="service" />
					</td>
					<td style="padding: 5px;"><input type="submit" name="action" value=Add></td>
				</tr>
			</form>
		</table>
	</div>
	
	<div style="margin: 40px;">
		<span style="font-size: x-large;">Users:</span>
		<table style="margin-top: 20px;">
			<tr>
				<th>FirstName</th>
				<th>Lastname</th>
				<th>City</th>
				<th>Street</th>
				<th>Flat</th>
			</tr>
			<c:forEach items="${employees}" var="item">
			<form action="" method='post'>
				<tr>
					<td style="padding: 5px;">
						<input type="text" name="firstName" value="${item.firstName}" /> 
						<input type="hidden" name="id" value="${item.id}" /> 
						<input type="hidden" name="object" value="employee" />
					</td>
					<td style="padding: 5px;">
						<input type="text" name="lastName" value="${item.lastName}" /> 
					</td>
					<td style="padding: 5px;">
						<input type="text" name="city" value="${item.city}" /> 
					</td>
					<td style="padding: 5px;">
						<input type="text" name="street" value="${item.street}" /> 
					</td>
					<td style="padding: 5px;">
						<input type="text" name="flat" value="${item.flat}" /> 
					</td>
					<td style="padding: 5px;"><input type="submit" name="action" value="Update"></td>
					<td style="padding: 5px;"><input type="submit" name="action" value="Delete"></td>
				</tr>
			</form>
			</c:forEach>
			<form action="" method='post'>
				<tr>
					<td style="padding: 5px;">
						<input type="text" name="firstName" value="" /> 
						<input type="hidden" name="id" value="" /> 
						<input type="hidden" name="object" value="employee" />
					</td>
					<td style="padding: 5px;">
						<input type="text" name="lastName" value="" /> 
					</td>
					<td style="padding: 5px;">
						<input type="text" name="city" value="" /> 
					</td>
					<td style="padding: 5px;">
						<input type="text" name="street" value="" /> 
					</td>
					<td style="padding: 5px;">
						<input type="text" name="flat" value="" /> 
					<td style="padding: 5px;"><input type="submit" name="action" value="Add"></td>
				</tr>
			</form>
		</table>
	</div>
</body>
</html>