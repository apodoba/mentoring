<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

<head>
<title>Cinema Application</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<body>
<span style="background-color: red;">${error}</span>
	<div>
		<table class="simpletablestyle">
			<thead>
				<tr>
					<th>Film</th>
					<th>Time</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${filmSchedules}" var="schedule">
					<form:form action="places" cssClass="button-form">
						<tr>
							<td style="vertical-align: top;"><input type="text"
								id="film" name="film" readonly="readonly"
								value="${schedule.film}"></td>
							<td style="vertical-align: top;"><input
								style="width: 250px;" type="text" id="time" name="time"
								readonly="readonly" value="${schedule.time}"></td>
								<input type="hidden" id="result" name=""result"" value="" />
							<td><input type="submit" value="Show places"></td>
						</tr>
					</form:form>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
