<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="view" tagdir="/WEB-INF/tags"%>

<spring:message code="fixUpTask.ticker" var="ticker" />
<spring:message code="fixUpTask.publicationMoment" var="publicationMoment" />
<spring:message code="fixUpTask.publicationMoment" var="publicationMoment" />
<spring:message code="fixUpTask.description" var="description" />
<spring:message code="fixUpTask.address" var="address" />
<spring:message code="fixUpTask.maxPrice" var="maxPrice" />
<spring:message code="fixUpTask.startDate" var="startDate" />
<spring:message code="fixUpTask.endDate" var="endDate" />
<spring:message code="fixUpTask.category" var="category" />
<spring:message code="fixUpTask.warranty" var="warranty" />

<display:table name="fixUpTask" id="row"
	requestURI="fixUpTask/customer/list.do" pagesize="3" class="displaytag">

	<security:authorize access="hasRole('CUSTOMER')">
		<display:column>
			<a href="fixUpTask/customer/edit.do?fixUpTaskId=${row.id}"> <spring:message
					code="fixUpTask.edit" />
			</a>
		</display:column>
		<display:column>
			<a href="fixUpTask/customer/list.do?fixUpTaskId=${row.id}"> <spring:message
					code="fixUpTask.delete" />
			</a>
		</display:column>
		<display:column>
			<a href="fixUpTask/customer/application.do"> <spring:message
					code="fixUpTask.application" />
			</a>
		</display:column>
		<display:column property = "category.name" titleKey = "${category.name} }">
		</display:column>
		<display:column property = "warranty.name" titleKey = "${warranty.name} }">
		</display:column>
		<display:column>
			<input type="button" name="viewPhases"
 				value="<spring:message code="fixUpTask.Phases" />"
				 onclick="javascript: relativeRedir('/phase/list.do');" />
		</display:column>
		<display:column>
			<input type="button" name="viewComplaints"
			 	value="<spring:message code="fixUpTask.Complaints" />"
				 onclick="javascript: relativeRedir('/complaint/list.do');" />
		</display:column>
	</security:authorize>
	
	<display:column property="ticker" title="${ticker}" />
	<display:column property="publicationMoment" title="${publicationMoment}" />
	<display:column property="description" titleKey="${description}" />
	<display:column property="address" titleKey="${address}" />
	<display:column property="maxPrice" titleKey="${maxPrice}" />
	<display:column property="startDate" titleKey="${startDate}" />
	<display:column property="endDate" titleKey="${endDate}" />

</display:table>

<!-- <button onclick="window.location.href = '/createFixUpTask.do'">Crear </button> -->
<input type="button" name="createFixUpTask"
 value="<spring:message code="fixUpTask.create" />"
 onclick="javascript: relativeRedir('fixUpTask/create.do');" />
 
