<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

<head>
<title>JMS Application</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<c:url value="/static/resources/css/style.css"/>" />
</head>

<body>

<input type="button" onclick="location.href='/JMSSender/send'" value="Send message" >
	<%-- <div id="container">
		<div id="content">
			<h2 style="margin-top: 50px;">Users</h2>


			<table>
				<tr>
					<td><form:form commandName="newUser" action="add" id="reg">
							<table>
								<tbody>
									<tr>
										<td><form:label path="firstName">FirstName:</form:label></td>
										<td><form:input path="firstName" /></td>
									</tr>
									<tr>
										<td><form:label path="lastName">LastName:</form:label></td>
										<td><form:input path="lastName" /></td>
									</tr>
									<tr>
										<td><form:label path="balance">Balance:</form:label></td>
										<td><form:input path="balance" /></td>
									</tr>

								</tbody>
							</table>
							<table>
								<tr>
									<td><input type="submit" value="Add" class="register" />
									</td>
								</tr>
							</table>
						</form:form></td>
					<td><form:form action="editUser"
							commandName="editUser">
							<table>
								<tbody>
									<tr>
										<td><form:label path="firstName">FirstName:</form:label></td>
										<td><form:input path="firstName" /></td>
									</tr>
									<tr>
										<td><form:label path="lastName">LastName:</form:label></td>
										<td><form:input path="lastName" /></td>
									</tr>
									<tr>
										<td><form:label path="balance">Balance:</form:label></td>
										<td><form:input path="balance" /></td>
									</tr>
									<tr>
										<td><form:hidden path="id" /></td>
									</tr>

								</tbody>
							</table>
							<table>
								<tr>
									<td><input type="submit" value="Edit" class="register" />
									</td>
								</tr>
							</table>
						</form:form></td>
				</tr>
			</table>


			<c:choose>
				<c:when test="${users.size()==0}">
					<em>No users.</em>
				</c:when>
				<c:otherwise>


					<table class="simpletablestyle">
						<thead>
							<tr>
								<th>FirstName</th>
								<th>LastName</th>
								<th>Delete</th>
								<th>Edit</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${users}" var="user">
								<tr>
									<td style="vertical-align: top;">${user.firstName}</td>
									<td style="vertical-align: top;">${user.lastName}</td>
									<td style="vertical-align: top;">${user.balance}</td>
									<td><form:form action="delete"
											cssClass="button-form">
											<input type="hidden" name="id" id="id" value="${user.id}">
											<input type="submit" value="Delete">
										</form:form></td>

									<td><form:form action="edit"
											cssClass="button-form">
											<input type="hidden" name="id" id="id" value="${user.id}">
											<input type="submit" value="Edit">
										</form:form></td>
							</c:forEach>
						</tbody>
					</table>
				</c:otherwise>
			</c:choose>
		</div>
	</div> --%>
</body>
</html>
