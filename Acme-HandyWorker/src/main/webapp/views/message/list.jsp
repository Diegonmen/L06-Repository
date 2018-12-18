<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<h2>
	<jstl:out value="${folder.getName()}" />
</h2>
<b><spring:message code="message.messages" /> :</b>
<br />
<display:table pagesize="10" class="displaytag" name="messages"
	requestURI="message/actor/list.do" id="row2">

	<%-- <display:column>
		<a href="message/display.do?messageId=<jstl:out value="${row2.id}"/>"><spring:message
				code="message.show" /></a>
	</display:column> --%>
	
	<spring:message code="message.sender" var="senderHeader" />
	<display:column title="${senderHeader}">
		<jstl:out value="row2.sender.name"/> <jstl:out value="row2.sender.surname"/>
	</display:column>
	
	<spring:message code="message.priority" var="priorityHeader" />
	
	<display:column property="priority" title="${priorityHeader}"/>
	
	<spring:message code="message.receptionMoment" var="receptionMomentHeader" />
	<spring:message code="master.page.date.format" var="dateFormat" />
	
	<display:column property="moment" title="${receptionMomentHeader}"
		 format="{0,date,${dateFormat}}" />
		 
</display:table>


<%-- <a href="folder/create.do?folderId=${folder.id }"><spring:message
		code="folder.newfolder" /></a> --%>
		
<%-- <jstl:if test="${folder.getSystemFolder() eq false }">

	<input type="submit" name="move"
		value="<spring:message code="folder.move" />" />
	 <input type="button" name="move"
		value="<spring:message code="folder.move" />"
		onclick="javascript: relativeRedir('folder/move.do?folderId=<jstl:out value="${folder.getId()}"/>');" /> 
	<input type="button" name="delete"
		value="<spring:message code="folder.delete" />"
		onclick="javascript: relativeRedir('folder/delete.do?folderId=<jstl:out value="${folder.getId()}"/>');" />

</jstl:if> --%>
