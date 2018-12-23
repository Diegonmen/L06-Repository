<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<spring:message code="warranty.title" var="warrantyTitle" />
<spring:message code="warranty.terms" var="warrantyTermse" />
<spring:message code="warranty.applicableLaws" var="warrantyApplicableLaws" />
<spring:message code="warranty.delete" var="warrantyDelete" />
<spring:message code="warranty.edit" var="warrantyedit" />


<display:table pagesize="3" class="displaytag" keepStatus="true" name="list"  requestURI="/warranty/list.do" id="row">
	<display:column value="${row.title}" title="${warrantyTitle}"></display:column>
	<display:column value="${row.terms}" title="${warrantyTermse}"></display:column>
	<display:column value="${row.applicableLaws}" title="${warrantyApplicableLaws}"></display:column>
	<display:column>
		<a href="warrany/edit?q=${row.id}">${warrantyedit}</a>
	</display:column>
	<display:column>
		<a href="warrany/delete?q=${row.id}">${warrantyDelete}</a>
	</display:column>
</display:table>