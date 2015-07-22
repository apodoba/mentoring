<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

<head>
<title>Hibernate Application</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/static/resources/css/screen.css"/>" />
</head>

<body>
	<div id="container">
		<div id="content">
			<h2 style="margin-top: 50px;">Project</h2>


			<table>
				<tr>
					<td><form:form commandName="newProject" action="/project/add" id="reg">
							<table>
								<tbody>
									<tr>
										<td><form:label path="name">Name:</form:label></td>
										<td><form:input path="name" /></td>
										<td><form:errors class="invalid" path="name" /></td>
									</tr>
									<tr>
										<td><form:label path="description">Description:</form:label></td>
										<td><form:input path="description" /></td>
										<td><form:errors class="invalid" path="description" /></td>
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
					<td><form:form action="/project/editProject" commandName="editProject">
							<table>
								<tbody>
									<tr>
										<td><form:label path="name">Name:</form:label></td>
										<td><form:input path="name" /></td>
										<td><form:errors class="invalid" path="name" /></td>
									</tr>
									<tr>
										<td><form:label path="description">Description:</form:label></td>
										<td><form:input path="description" /></td>
										<td><form:errors class="invalid" path="description" /></td>
									</tr>
									<tr>
										<td><form:hidden path="id" /></td>
									</tr>

								</tbody>
							</table>
							<table>
								<tr>
									<td>
										<input type="submit" value="Edit" class="register" />
									</td>
								</tr>
							</table>
						</form:form></td>
				</tr>
			</table>


			<c:choose>
				<c:when test="${projects.size()==0}">
					<em>No projects.</em>
				</c:when>
				<c:otherwise>


					<table class="simpletablestyle">
						<thead>
							<tr>
								<th>Name</th>
								<th>Description</th>
								<th>Delete</th>
								<th>Edit</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${projects}" var="project">
								<tr>
									<td style="vertical-align: top;">${project.name}</td>
									<td style="vertical-align: top;">${project.description}</td>
									<td><form:form action="/project/delete" cssClass="button-form">
											<input type="hidden" name="id" id="id" value="${project.id}">
											<input type="submit" value="Delete">
										</form:form></td>

									<td><form:form action="/project/edit" cssClass="button-form">
											<input type="hidden" name="id" id="id" value="${project.id}">
											<input type="submit" value="Edit">
										</form:form></td>
							</c:forEach>
						</tbody>
					</table>
				</c:otherwise>
			</c:choose>
		</div>
		
		
		
		<div id="content">
			<h2>Unit</h2>


			<table>
				<tr>
					<td><form:form commandName="newUnit" action="/unit/add" id="reg">
							<table>
								<tbody>
									<tr>
										<td><form:label path="name">Name:</form:label></td>
										<td><form:input path="name" /></td>
										<td><form:errors class="invalid" path="name" /></td>
									</tr>
									<tr>
										<td><form:label path="description">Description:</form:label></td>
										<td><form:input path="description" /></td>
										<td><form:errors class="invalid" path="description" /></td>
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
					<td><form:form action="/unit/editUnit" commandName="editUnit">
							<table>
								<tbody>
									<tr>
										<td><form:label path="name">Name:</form:label></td>
										<td><form:input path="name" /></td>
										<td><form:errors class="invalid" path="name" /></td>
									</tr>
									<tr>
										<td><form:label path="description">Description:</form:label></td>
										<td><form:input path="description" /></td>
										<td><form:errors class="invalid" path="description" /></td>
									</tr>
									<tr>
										<td><form:hidden path="id" /></td>
									</tr>
								</tbody>
							</table>
							<table>
								<tr>
									<td>
										<input type="submit" value="Edit" class="register" />
									</td>
								</tr>
							</table>
						</form:form></td>
				</tr>
			</table>


			<c:choose>
				<c:when test="${units.size()==0}">
					<em>No units</em>
				</c:when>
				<c:otherwise>


					<table class="simpletablestyle">
						<thead>
							<tr>
								<th>Name</th>
								<th>Description</th>
								<th>Delete</th>
								<th>Edit</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${units}" var="unit">
								<tr>
									<td style="vertical-align: top;">${unit.name}</td>
									<td style="vertical-align: top;">${unit.description}</td>
									<td><form:form action="/unit/delete" cssClass="button-form">
											<input type="hidden" name="id" id="id" value="${unit.id}">
											<input type="submit" value="Delete">
										</form:form></td>

									<td><form:form action="/unit/edit" cssClass="button-form">
											<input type="hidden" name="id" id="id" value="${unit.id}">
											<input type="submit" value="Edit">
										</form:form></td>
							</c:forEach>
						</tbody>
					</table>
				</c:otherwise>
			</c:choose>
		</div>
		
		
		
		
		
		<div id="content">
			<h2>Employee</h2>


			<table>
				<tr>
					<td><form:form commandName = "employee" action="/employee/add">
							<table>
								<tbody>
									<tr>
										<td><form:label path="personalInfo.firstName">FirstName:</form:label></td>
										<td><form:input path="personalInfo.firstName" /></td>
										<td><form:errors class="invalid" path="personalInfo.firstName" /></td>
									</tr>
									<tr>
										<td><form:label path="personalInfo.lastName">LastName:</form:label></td>
										<td><form:input path="personalInfo.lastName" /></td>
										<td><form:errors class="invalid" path="personalInfo.lastName" /></td>
									</tr>
									<tr>
										<td><form:label path="personalInfo.gender">Gender:</form:label></td>
										<td><form:input path="personalInfo.gender" /></td>
										<td><form:errors class="invalid" path="personalInfo.gender" /></td>
									</tr>
									<tr>
										<td><form:label path="personalInfo.phone">Phone:</form:label></td>
										<td><form:input path="personalInfo.phone" /></td>
										<td><form:errors class="invalid" path="personalInfo.phone" /></td>
									</tr>
									<tr>
										<td><form:label path="address.city">City:</form:label></td>
										<td><form:input path="address.city" /></td>
										<td><form:errors class="invalid" path="address.city" /></td>
									</tr>
									<tr>
										<td><form:label path="address.street">Street:</form:label></td>
										<td><form:input path="address.street" /></td>
										<td><form:errors class="invalid" path="address.street" /></td>
									</tr>
									<tr>
										<td><form:label path="address.flat">Flat:</form:label></td>
										<td><form:input path="address.flat" /></td>
										<td><form:errors class="invalid" path="address.flat" /></td>
									</tr>
									<tr>
										<td><form:label path="address.house">House:</form:label></td>
										<td><form:input path="address.house" /></td>
										<td><form:errors class="invalid" path="address.house" /></td>
									</tr>
									<tr>
										<td><form:label path="position">Position:</form:label></td>
										<td><form:input path="position" /></td>
										<td><form:errors class="invalid" path="position" /></td>
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
					<td><form:form action="/employee/editEmployee" commandName="editEmployee">
							<table>
								<tbody>
									<tr>
										<td><form:label path="personalInfo.firstName">FirstName:</form:label></td>
										<td><form:input path="personalInfo.firstName" /></td>
										<td><form:errors class="invalid" path="personalInfo.firstName" /></td>
									</tr>
									<tr>
										<td><form:label path="personalInfo.lastName">LastName:</form:label></td>
										<td><form:input path="personalInfo.lastName" /></td>
										<td><form:errors class="invalid" path="personalInfo.lastName" /></td>
									</tr>
									<tr>
										<td><form:label path="personalInfo.gender">Gender:</form:label></td>
										<td><form:input path="personalInfo.gender" /></td>
										<td><form:errors class="invalid" path="personalInfo.gender" /></td>
									</tr>
									<tr>
										<td><form:label path="personalInfo.phone">Phone:</form:label></td>
										<td><form:input path="personalInfo.phone" /></td>
										<td><form:errors class="invalid" path="personalInfo.phone" /></td>
									</tr>
									<tr>
										<td><form:label path="address.city">City:</form:label></td>
										<td><form:input path="address.city" /></td>
										<td><form:errors class="invalid" path="address.city" /></td>
									</tr>
									<tr>
										<td><form:label path="address.street">Street:</form:label></td>
										<td><form:input path="address.street" /></td>
										<td><form:errors class="invalid" path="address.street" /></td>
									</tr>
									<tr>
										<td><form:label path="address.flat">Flat:</form:label></td>
										<td><form:input path="address.flat" /></td>
										<td><form:errors class="invalid" path="address.flat" /></td>
									</tr>
									<tr>
										<td><form:label path="address.house">House:</form:label></td>
										<td><form:input path="address.house" /></td>
										<td><form:errors class="invalid" path="address.house" /></td>
									</tr>
									<tr>
										<td><form:label path="position">Position:</form:label></td>
										<td><form:input path="position" /></td>
										<td><form:errors class="invalid" path="position" /></td>
									</tr>
									<tr>
										<form:hidden path="id" />
									</tr>
								</tbody>
							</table>
							<table>
								<tr>
									<td>
										<input type="submit" value="Edit" class="register" />
									</td>
								</tr>
							</table>
						</form:form></td>
				</tr>
			</table>


			<c:choose>
				<c:when test="${employees.size()==0}">
					<em>No employees.</em>
				</c:when>
				<c:otherwise>


					<table class="simpletablestyle">
						<thead>
							<tr>
								<th>FirstName</th>
								<th>LastName</th>
								<th>Gender</th>
								<th>Phone</th>
								<th>City</th>
								<th>Street</th>
								<th>Flat</th>
								<th>House</th>
								<th>Unit</th>
								<th>Delete</th>
								<th>Edit</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${employees}" var="employee">
								<tr>
									<td style="vertical-align: top;">${employee.personalInfo.firstName}</td>
									<td style="vertical-align: top;">${employee.personalInfo.lastName}</td>
									<td style="vertical-align: top;">${employee.personalInfo.gender}</td>
									<td style="vertical-align: top;">${employee.personalInfo.phone}</td>
									<td style="vertical-align: top;">${employee.address.city}</td>
									<td style="vertical-align: top;">${employee.address.street}</td>
									<td style="vertical-align: top;">${employee.address.flat}</td>
									<td style="vertical-align: top;">${employee.address.house}</td>
									<td style="vertical-align: top;">${employee.unit.name}</td>
									<td><form:form action="/employee/delete" cssClass="button-form">
											<input type="hidden" name="id" id="id" value="${employee.id}">
											<input type="submit" value="Delete">
										</form:form></td>

									<td><form:form action="/employee/edit" cssClass="button-form">
											<input type="hidden" name="id" id="id" value="${employee.id}">
											<input type="submit" value="Edit">
										</form:form></td>
							</c:forEach>
						</tbody>
					</table>
				</c:otherwise>
			</c:choose>
		</div>
		<div id="content">
		<form:form action="/employee/assign/unit" commandName="unitEmployeeForm">
			<table class="simpletablestyle">
				<tr>
					<td>Select Unit</td>
					<td>
						<form:select path="unitId">
							<form:options items="${units}" itemLabel="name" itemValue="id"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td>Select employee</td>
					<td>
						<form:select path="employeeId">
							<form:options items="${employees}" itemLabel="personalInfo.firstName" itemValue="id"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="Add to unit" class="register" style="margin-left: 0px;"/>
					</td>
					<td></td>
				</tr>
			</table>
		</form:form>
		</div>
		<div id="content">
		<form:form action="/employee/assign/project" commandName="projectEmployeeForm">
			<table class="simpletablestyle">
				<tr>
					<td>Select Project</td>
					<td>
						<form:select path="projectId">
							<form:options items="${projects}" itemLabel="name" itemValue="id"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td>Select employee</td>
					<td>
						<form:select path="employeeId">
							<form:options items="${employees}" itemLabel="personalInfo.firstName" itemValue="id"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="Add to project" class="register" style="margin-left: 0px;"/>
					</td>
					<td></td>
				</tr>
			</table>
		</form:form>
		</div>
	</div>
</body>
</html>
