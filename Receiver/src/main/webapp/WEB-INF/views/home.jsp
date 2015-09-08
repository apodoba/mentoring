<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

<head>
<title>JMS Application</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<c:url value="/static/resources/css/style.css"/>" />
</head>

<body>
	<div id="container">
		<div id="content">
			<h2 style="margin-top: 50px;">Customers</h2>

			<c:choose>
				<c:when test="${customers.size()==0}">
					<em>No users.</em>
				</c:when>
				<c:otherwise>
					<table class="simpletablestyle">
						<thead>
							<tr>
								<th>Name</th>
								<th>Surname</th>
								<th>Count</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${customers}" var="customer">
								<tr>
									<td style="vertical-align: top;">${customer.name}</td>
									<td style="vertical-align: top;">${customer.surname}</td>
									<td style="vertical-align: top;">${customer.count}</td>
							</c:forEach>
						</tbody>
					</table>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>
