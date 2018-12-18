<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<form:form action="application/edit.do"
	modelAttribute="application">

	<%-- Parámetros ocultos --%>

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="applicationMoment" />
	<form:hidden path="handyWorker" />
	<form:hidden path="fixUpTask" />
	
	<jstl:if test="${application.id != 0}">
	<form:hidden path="comments" />
	</jstl:if>
	
	<%-- ************** --%>
	<jstl:choose>
	<jstl:when test="${ application.id eq 0 }">
		<p><spring:message code="application.creatingForTask"/><jstl:out value="${ fixUpTask.description }"/></p>
	</jstl:when>
	<jstl:otherwise>
		<p><spring:message code="application.editingForTask"/><jstl:out value="${ fixUpTask.description }"/></p>
	</jstl:otherwise>
	</jstl:choose>
	

	<security:authorize access="hasRole('HANDYWORKER')">
	
	<jstl:if test="${application.id eq 0}">
	<form:label path="comments">
		<spring:message code="application.comments" />:
	</form:label><br/>
	<form:textarea path="comment" />
	<br/><br/>
	</jstl:if>
	
	</security:authorize>
	
	<security:authorize access="hasRole('CUSTOMER')">															
	
	<spring:message code="application.rejected" var="rejected"/>
	<spring:message code="application.pending" var="due"/>
	<spring:message code="application.accepted" var="accepted"/>
	
	<form:label path="status">
		<spring:message code="application.status" />:
	</form:label>
	<form:select id="status" path="status">
		<%-- <form:option value="0" label="----" /> --%>
		<form:option  value="REJECTED" label="${rejected}" />
		<form:option value="PENDING" label="${due}" />
		<form:option value="ACCEPTED" label="${accepted}" />
	</form:select>
	<form:errors cssClass="error" path="status" />
	<br/><br/>
	
	<jstl:if test="${application.id != 0 and application.status eq 'PENDING'}">
	
	<form:hidden path="creditCard.id" />
	<form:hidden path="creditCard.version" />
	
	<form:label path="creditCard.holderName">
		<spring:message code="creditCard.holderName" />:
	</form:label>
	<form:input path="creditCard.holderName" />
	<form:errors cssClass="error" path="creditCard.holderName" />
	<br/>
	
	<form:label path="creditCard.brandName">
		<spring:message code="creditCard.brandName" />:
	</form:label>
	<form:select path="creditCard.brandName">
		<form:options items="defaultCreditCards"/>
	</form:select>
	<form:errors cssClass="error" path="creditCard.brandName" />
	<br/>

	<form:label path="creditCard.number">
		<spring:message code="creditCard.number"/>:
	</form:label>
	<form:input path="creditCard.number" />
	<form:errors cssClass="error" path="creditCard.number"/>
	<br/>

	<form:label path="creditCard.expirationMonth">
		<spring:message code="creditCard.expirationMonth" />:
	</form:label>
	<form:input path="creditCard.expirationMonth" />
	<form:errors cssClass="error" path="creditCard.expirationMonth" />
	<br/>

	<form:label path="creditCard.expirationYear">
		<spring:message code="creditCard.expirationYear" />:
	</form:label>
	<form:input path="creditCard.expirationYear" />
	<form:errors cssClass="error" path="creditCard.expirationYear" />
	<br/>

	<form:label path="creditCard.CVV">
		<spring:message code="creditCard.CVV" />:
	</form:label>
	<form:input path="creditCard.CVV" />
	<form:errors cssClass="error" path="creditCard.CVV" />
	<br/>
	
	</jstl:if>
	
	</security:authorize>
	
	<input type="submit" name="changeStatus"
		value="<spring:message code="application.submit" />" />
		
	<input type="button" name="cancel"
		value="<spring:message code="application.cancel"/>" onclick="javascript:relativeRedir('fixUpTask/list.do');"/>
	
	

</form:form>