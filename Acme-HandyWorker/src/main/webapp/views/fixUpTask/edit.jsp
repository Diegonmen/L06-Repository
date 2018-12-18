<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="view" tagdir="/WEB-INF/tags"%>
<html>
<spring:message code="fixUpTask.description" var="description" />
<spring:message code="fixUpTask.address" var="address" />
<spring:message code="fixUpTask.maxPrice" var="maxPrice" />
<spring:message code="fixUpTask.startDate" var="startDate" />
<spring:message code="fixUpTask.endDate" var="endDate" />
<spring:message code="fixUpTask.edit" var="editstr" />
<spring:message code="fixUpTask.category" var="category" />

<security:authorize access="hasRole('HANDYWORKER')">
	<a href="createApplication.do?fixUpTaskId=${fixUpTask.id}"><spring:message
			code="fixUpTask.application" /></a>
	<a href="createPhase.do?fixUpTaskId=${fixUpTask.id}"><spring:message
			code="fixUpTask.phases" /></a>
</security:authorize>
<security:authorize access="hasRole('CUSTOMER')">
	<a href="createComplaint.do?fixUpTaskId=${fixUpTask.id}"><spring:message
			code="fixUpTask.complaints" /></a>
	<form:form action="fixUpTask/edit.do" modelAttribute="fixUpTask">
		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="publicationMoment" />
		<form:hidden path="ticker" />
		<form:input path="description" placeholder="${description }" />
		<form:errors cssClass="error" path="description"></form:errors>

		<form:input path="address" placeholder="${address }" />
		<form:errors cssClass="error" path="address"></form:errors>

		<form:input path="maxPrice" placeholder="${maxPrice }" />
		<form:errors cssClass="error" path="maxPrice"></form:errors>

		<form:input path="startDate" placeholder="${startDate }" />
		<form:errors cssClass="error" path="startDate"></form:errors>

		<form:input path="endDate" placeholder="${endDate }" />
		<form:errors cssClass="error" path="endDate"></form:errors>

		<form:input path="warranty.view" placeholder="${warranty }" />
		<form:errors cssClass="error" path="category.name"></form:errors>

		<form:select path="category">
			<jstl:forEach items="categories" var="e">
				<form:option value="e.id">e.name</form:option>
			</jstl:forEach>
		</form:select>

		<form:select path="warranty">
			<jstl:forEach items="warranty" var="e">
				<form:option value="e.id">e.title</form:option>
			</jstl:forEach>
		</form:select>

		<form:input path="application.view" placeholder="${application }" />
		<form:errors cssClass="error" path="category.name"></form:errors>



		<%-- <input type="submit" name="save" value="${editstr }"> --%>
	</form:form>
</security:authorize>
<input type="button" name="save"
	value="<spring:message code="fixUpTask.save" />"
	onclick="javascript: relativeRedir('fixUpTask/view.do');" />
</html>

<input type="button" name="cancel"
	value="<spring:message code="fixUpTask.cancel" />"
	onclick="javascript: relativeRedir('fixUpTask/view.do');" />
</html>
