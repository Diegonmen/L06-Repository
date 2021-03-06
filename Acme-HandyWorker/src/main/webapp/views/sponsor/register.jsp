<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="sponsor/edit.do" modelAttribute="actor">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="suspicious" />
	<form:hidden path="userAccount" />
	<form:hidden path="socialIdentity" />
	<form:hidden path="boxes" />
	<form:hidden path="sponsorships" />
	
	<form:hidden path="userAccount.authorities" />
	
	<div class="form-group"> 
		<form:label path="userAccount.username">
			<spring:message code="sponsor.userAccount.username" />
		</form:label>
		<form:input class="form-control" path="userAccount.username" />
		<form:errors class="text-danger" path="userAccount.username" />
	</div>
	<div class="form-group"> 
		<form:label path="userAccount.password">
			<spring:message code="sponsor.userAccount.password" />
		</form:label>
		<form:password class="form-control" path="userAccount.password" />
		<form:errors class="text-danger" path="userAccount.password" />
	</div>

	<hr />
	
	<div class="form-group"> 
		<form:label path="name">
			<spring:message code="sponsor.name" />
		</form:label>
		<form:input class="form-control" path="name" />
		<form:errors class="text-danger" path="name" />
	</div>
	<div class="form-group"> 
		<form:label path="middleName">
			<spring:message code="sponsor.middleName" />
		</form:label>
		<form:input class="form-control" path="middleName" />
		<form:errors class="text-danger" path="middleName" />
	</div>
	<div class="form-group"> 
		<form:label path="surname">
			<spring:message code="sponsor.surname" />
		</form:label>
		<form:input class="form-control" path="surname" />
		<form:errors class="text-danger" path="surname" />
	</div>
	<div class="form-group"> 
		<form:label path="email">
			<spring:message code="sponsor.email" />
		</form:label>
		<form:input class="form-control" path="email" />
		<form:errors class="text-danger" path="email" />
	</div>
	<div class="form-group"> 
		<form:label path="photo">
			<spring:message code="sponsor.photo" />
		</form:label>
		<form:input class="form-control" path="photo" />
		<form:errors class="text-danger" path="photo" />
	</div>
	<div class="form-group"> 
		<form:label path="phoneNumber">
			<spring:message code="sponsor.phoneNumber" />
		</form:label>
		<form:input class="form-control" path="phoneNumber" />
		<form:errors class="text-danger" path="phoneNumber" />
	</div>
	<div class="form-group"> 
		<form:label path="address">
			<spring:message code="sponsor.address" />
		</form:label>
		<form:input class="form-control" path="address" />
		<form:errors class="text-danger" path="address" />
	</div>
	
	<br/>
	
	<input type="submit" class="ui primary button" name="save"
			value="<spring:message code="sponsor.save" />">
	
	<input type="button" class="ui button" name="cancel"
		value="<spring:message code="sponsor.cancel" />"
		onclick="javascript: relativeRedir('welcome/index.do');">
	
</form:form>