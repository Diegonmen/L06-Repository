<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<spring:message code="application.applicationMoment" var="applicationMoment" />
<spring:message code="application.status" var="applicationStatus" />
<spring:message code="application.offeredPrice" var="applicationOfferedPrice" />
<spring:message code="application.comments" var="applicationOfferedPrice" />
<spring:message code="application.creditCard" var="applicationCreditCard" />
<spring:message code="application.fixUpTask" var="applicationFixUpTask" />
<spring:message code="application.handyWorker" var="applicationHandyWorker" />
<spring:message code="application.view" var="applicationView" />


<display:table pagesize="3" class="displaytag" keepStatus="true"
	name="list" requestURI="/application/list.do" id="row">
	
	<display:column value="${row.applicationMoment}" title="${applicationApplicationMoment}"></display:column>
	<display:column value="${row.status}" title="${applicationStatus}"></display:column>
	<display:column value="${row.offeredPrice}" title="${applicationOfferedPrice}"></display:column>
	<display:column value="${row.comments}" title="${applicationComments}"></display:column>
	<display:column value="${row.creditCard.number}" title="${applicationCreditCard}"></display:column>
	<display:column value="${row.fixUpTask.description}" title="${applicationFixUpTask}"></display:column>
	<display:column value="${row.handyWorker.userAccount.username}" title="${applicationHandyWorker}"></display:column>
	
	<security:authorize access="hasRole('CUSTOMER')">
	<display:column>
			<a href="application/customer/view.do?applicationId=${row.id}">${applicationView}</a>
	</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('HANDYWORKER')">
	<display:column>
			<a href="application/handyWorker/view.do?applicationId=${row.id}">${applicationView}</a>
	</display:column>
	</security:authorize>
		
	</display:table>
	
	<security:authorize access="hasRole('HANDYWORKER')">
	<input type="button" class="ui button" name="create"
		value="<spring:message code="application.create" />"
		onclick="javascript: relativeRedir('application/handyWorker/create.do');">
	</security:authorize>
	