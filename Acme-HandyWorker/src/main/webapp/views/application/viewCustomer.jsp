<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="view" tagdir="/WEB-INF/tags"%>

<spring:message code="application.applicationMoment" var="applicationMoment" />
<spring:message code="application.status" var="status" />
<spring:message code="application.offeredPrice" var="offeredPrice" />
<spring:message code="application.comments" var="comments" />
<spring:message code="application.creditCard" var="creditCard" />
<spring:message code="application.editStatus" var="applicationEditStatus" />

	<display:column property="applicationMoment" title="${applicationMoment}" />
	<display:column property="terms" title="${status}" />
	<display:column property="applicableLaws" titleKey="${offeredPrice}" />
	<display:column property="finalMode" titleKey="${comments}" />
	<display:column property="creditCard.number" titleKey="${creditCard}" />
	<display:column>
		<a href="application/customer/editStatus.do?applicationId=${id}">${applicationEdit}</a>
	</display:column>
	
	<jstl:if test="${status != 'ACCEPTED' || 'REJECTED' }">
	<display:column>
		<a href="application/customer/editComments.do?applicationId=${id}">${applicationEdit}</a>
	</display:column>
	</jstl:if>

</html>