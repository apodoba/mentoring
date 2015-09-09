<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

<head>
<title>Cinema Application</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<body>
<span style="background-color: red;">${result}</span>
	<div>

		<table class="simpletablestyle">
			<thead>
				<tr>
					<th>Hall</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${halls}" var="hall">
					<tr>
						<td style="vertical-align: top;">${hall.key}</td>
						<td style="vertical-align: top;">
							<table class="simpletablestyle">
								<thead>
									<tr>
										<th>Place</th>
										<th>Price</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${hall.value}" var="ticket">
										<td>
										<tr>
											<td style="vertical-align: top;">${ticket.place}</td>
											<td style="vertical-align: top;">${ticket.price}</td>
											<c:choose>
												<c:when test="${ticket.status == 0}">
													<form:form action="book" cssClass="button-form">
														<input type="hidden" id="id" name="id" value="${ticket.id}" />
														<input type="hidden" id="film" name="film" value="${ticket.film}" />
														<input type="hidden" id="time" name="time" value="${ticket.time}" />
														<td><input type="submit" value="Book"></td>
													</form:form>
												</c:when>
												<c:otherwise>
													<c:choose>
														<c:when test="${ticket.status == 1}">
															<form:form action="pay" cssClass="button-form">
																<input type="hidden" id="id" name="id" value="${ticket.id}" />
																<input type="hidden" id="film" name="film" value="${ticket.film}" />
																<input type="hidden" id="time" name="time" value="${ticket.time}" />
																<td><input type="submit" value="Pay"></td>
															</form:form>
														</c:when>
														<c:otherwise>
															<td style="vertical-align: top;">Ticket booked</td>
														</c:otherwise>
													</c:choose>
												</c:otherwise>
											</c:choose>
									</c:forEach>
								</tbody>
							</table>
						</td>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
