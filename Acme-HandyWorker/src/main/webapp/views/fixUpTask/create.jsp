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

	<form:form action="fixUpTask/create.do" modelAttribute="fixUpTask">
		<form:hidden path="id"/>
		<form:hidden path="version"/>
		<form:hidden path="publicationMoment"/>
		<form:hidden path="ticker"/>
		<form:hidden path="applications"/>
		<form:hidden path="phases"/>
		<form:hidden path="complaints"/>
		
		<form:input path="description" placeholder="${description }"/>
		<form:errors cssClass="error" path="description"></form:errors>
		
		<form:input path="address" placeholder="${address }"/>
		<form:errors cssClass="error" path="address"></form:errors>
		
		<form:input path="maxPrice" placeholder="${maxPrice }"/>
		<form:errors cssClass="error" path="maxPrice"></form:errors>
		
		<form:input path="startDate" placeholder="${startDate }"/>
		<form:errors cssClass="error" path="startDate"></form:errors>
		
		<form:input path="endDate" placeholder="${endDate }"/>
		<form:errors cssClass="error" path="endDate"></form:errors>
		
		<form:input path="category.name" placeholder="${category }"/>
		<form:errors cssClass="error" path="category.name"></form:errors>
		
		<form:input path="warranty.title" placeholder="${warranty }"/>
		<form:errors cssClass="error" path="warranty.title"></form:errors>
		
		<input type="button" name="save"
 value="<spring:message code="fixUpTask.save" />"
 onclick="javascript: relativeRedir('fixUpTask/view.do');" />
 
 <input type="button" name="cancel"
 value="<spring:message code="fixUpTask.cance" />"
 onclick="javascript: relativeRedir('fixUpTask/view.do');" />
	</form:form>
</html>