<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="view" tagdir="/WEB-INF/tags"%>

<spring:message code="box.name" var="name" />
<spring:message code="box.messages" var="messages" />
<spring:message code="box.parentBox" var="parentBox" />

<display:table>
	<display:column property="name" title="${name}" />
	<display:column property="parentBox" title="${parentBox}" />
	<display:column property="messages" title="${messages}" />
</display:table>
</html>