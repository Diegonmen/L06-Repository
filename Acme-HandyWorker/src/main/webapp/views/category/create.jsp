<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<form:form action="category/administrator/edit.do" modelAttribute="category">

	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<div> 
		<form:label path="name">
			<spring:message code="category.name" />
		</form:label>
		<form:input path="name" />
		<form:errors path="name"/>
	</div>
	
	<div> 
		<form:label path="engName">
			<spring:message code="category.engName" />
		</form:label>
		<form:input path="engName" />
		<form:errors path="engName"/>
	</div>

	<div> 
		<form:label path="parentCategory">
			<spring:message code="category.parentCategory" />
		</form:label>
		<form:input path="parentCategory" />
		<form:errors path="parentCategory"/>
	</div>
	
	<input type="submit" name="save" value="<spring:message code="category.save" />">
 	
	<input type="button" name="cancel" value="<spring:message code="category.cancel" />" onclick="javascript: relativeRedir('category/list.do')">
	
</form:form>