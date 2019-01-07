<%@page import="org.springframework.context.i18n.LocaleContextHolder"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="view" tagdir="/WEB-INF/tags"%>

<jstl:set value="<%=LocaleContextHolder.getLocale() %>" var="locale"></jstl:set>

<spring:message code="fixUpTask.description" var="description" />
<spring:message code="fixUpTask.applications.add" var="applicationsAdd" />
<spring:message code="fixUpTask.phases.add" var="phasesAdd" />
<spring:message code="fixUpTask.complaints.add" var="complaintsAdd" />
<spring:message code="fixUpTask.save" var="save" />
<spring:message code="fixUpTask.cancel" var="cancel" />

<spring:message code="general.error" var="error" />

<spring:message code="fixUpTask.aplication.applicationMoment" var="aplication_applicationMoment" />
<spring:message code="fixUpTask.aplication.status" var="aplication_status" />
<spring:message code="fixUpTask.aplication.offeredPrice" var="aplication_offeredPrice" />
<spring:message code="fixUpTask.aplication.comments" var="aplication_comments" />
<spring:message code="fixUpTask.aplication.creditCard" var="aplication_creditCard" />
<spring:message code="fixUpTask.aplication.fixUpTask" var="aplication_fixUpTask" />
<spring:message code="fixUpTask.aplication.handyWorker" var="aplication_handyWorker" />

<spring:message code="fixUpTask.phase.title" var="phase_title" />
<spring:message code="fixUpTask.phase.description" var="phase_description" />
<spring:message code="fixUpTask.phase.startMoment" var="phase_startMoment" />
<spring:message code="fixUpTask.phase.endMoment" var="phase_endMoment" />


<form:form action="fixuptask/customer/edit.do" modelAttribute="fixuptask">
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="ticker" />
	<form:hidden path="publicationMoment" />
	
	<security:authorize access="hasRole('HANDYWORKER')">
		<form:hidden path="description" />
		<form:hidden path="address" />
		<form:hidden path="maxPrice" />
		<form:hidden path="startDate" />
		<form:hidden path="endDate" />
		<form:hidden path="category" />
		<form:hidden path="warranty" />
	</security:authorize>
	
	<security:authorize access="hasRole('CUSTOMER')">
		<div> 
		<form:label path="description">
			<spring:message code="fixUpTask.description" />
		</form:label>
		<br />
		<form:textarea path="description"/>
		<form:errors path="description"/>
	</div>
	
	<div> 
		<form:label path="address">
			<spring:message code="fixUpTask.address" />
		</form:label>
		<form:input path="address" />
		<form:errors path="address"/>
	</div>
	
	<div> 
		<form:label path="maxPrice">
			<spring:message code="fixUpTask.maxPrice" />
		</form:label>
		<form:input type="number" min="0" path="maxPrice" />
		<form:errors path="maxPrice"/>
	</div>
	
	<div> 
		<form:label path="startDate">
			<spring:message code="fixUpTask.startDate" />
		</form:label>
		<form:input path="startDate" />
		<form:errors path="startDate"/>
	</div>
	
	<div> 
		<form:label path="endDate">
			<spring:message code="fixUpTask.endDate" />
		</form:label>
		<form:input path="endDate" />
		<form:errors path="endDate"/>
	</div>
	
	<div> 
		<form:label path="category">
			<spring:message code="fixUpTask.category" />
		</form:label>
		<form:select path="category" itemValue="category.id">
			<jstl:forEach items="${categories}" var="e">
				<form:option value="${e.id}">
					<jstl:if test="${locale == 'en'}">${e.name}</jstl:if>
					<jstl:if test="${locale == 'es'}">${e.espName}</jstl:if>
				</form:option>
			</jstl:forEach>
		</form:select>
		<form:errors path="category"/>
		</div>
		
		<div> 
			<form:label path="warranty">
				<spring:message code="fixUpTask.warranty" />
			</form:label>
			<form:select path="warranty" itemValue="warranty.id">
				<jstl:forEach items="${warranties}" var="e">
					<form:option value="${e.id}">
						${e.title}
					</form:option>
				</jstl:forEach>
			</form:select>
			<form:errors path="warranty"/>
		</div>
	</security:authorize>
	
	<form:hidden path="applications"/>
	<form:hidden path="phases"/>
	
	<security:authorize access="hasRole('HANDYWORKER')">
		<jstl:if test="${canAddApplication}">
			<a id="addAplicationAction" href="javascript:showDialogAprove('view-applications', addAplicacion, hideErrors)">${applicationsAdd}</a>
		</jstl:if>
		
		<br />
		
		<jstl:if test="${canAddPhase}">
			<a id="addPhaseAction" href="javascript:showDialogAprove('view-phases', addPhase, hideErrors)">${phasesAdd}</a>
		</jstl:if>
	</security:authorize>
	
	<security:authorize access="hasRole('CUSTOMER')">
		<table class="ui celled table">
			<thead>
				<tr>
					<th>${aplication_handyWorker}</th>
					<th>${aplication_offeredPrice}</th>
					<th></th>
					<jstl:if test="${not canAddPhase}">
						<th></th>
						<th></th>
					</jstl:if>
				</tr>
			</thead>
			<tbody>
				<jstl:forEach var="e" items="${fixuptask.applications}">
					<tr>
						<td data-label="${aplication_handyWorker}">${e.handyWorker.name} ${e.handyWorker.surname}</td>
						<td data-label="${aplication_offeredPrice}">${e.offeredPrice}</td>
						<td data-label="">${e.status}</td>
						<jstl:if test="${not canAddPhase}">
							<td><a href="javascript:void(0)"><spring:message code="fixUpTask.aplication.accept"/></a></td>
							<td><a href="javascript:void(0)"><spring:message code="fixUpTask.aplication.rejected"/></a></td>
						</jstl:if>
					</tr>
				</jstl:forEach>
			</tbody>
		</table>

		<br />
		<br />
		<input type="submit" name="save" value="<spring:message code="fixUpTask.save" />">
	</security:authorize>
	
</form:form>

<div class="ui modal" id="view-phases" style="display: none">
	<div class="header">${phasesAdd}</div>
	<div class="content">
		<spring:message code="fixUpTask.phase.title" var="phase_title" />
		<spring:message code="fixUpTask.phase.description" var="phase_description" />
		<spring:message code="fixUpTask.phase.startMoment" var="phase_startMoment" />
		<spring:message code="fixUpTask.phase.endMoment" var="phase_endMoment" />
		
			<form action="phase/handyworker/save-async.do" id="phase-save">
				<input type="hidden" name="id" value="0">
				<input type="hidden" name="version" value="0">
				
				<div>
					<label>${phase_title}:</label>
					<input type="text" name="title" value="">
				</div>
				
				<div>
					<label>${phase_description}:</label>
					<input type="text" name="description" value="">
				</div>
				
				<div>
					<label>${phase_startMoment}:</label>
					<input type="text" name="startMoment" value="">
				</div>
				
				<div>
					<label>${phase_endMoment}:</label>
					<input type="text" name="endMoment" value="">
				</div>
			</div>
			
			<h2 style="display: none" id="phases-errors" class="error">${error}</h2>
			<div class="actions">
				<div class="ui approve button">${save}</div>
				<div class="ui cancel button">${cancel}</div>
			</div>
		</form>
</div>

<div class="ui modal" id="view-applications" style="display: none">
	<div class="header">${applicationsAdd}</div>
	<div class="content">
		<form action="application/handyworker/save-async.do" id="application-save">
			<input type="hidden" name="id" value="0">
				<input type="hidden" name="version" value="0">
				<input type="hidden" name="comments" value="">
				
				<input type="hidden" name="fixUpTask" value="${fixuptask.id}">
				<input type="hidden" name="handyWorker" value="${workerId}">
				<input type="hidden" name="status" value="PENDING">
				
				<div>
					<label>${aplication_applicationMoment}:</label>
					<input type="text" name="applicationMoment" value="">
				</div>
				
				<div>
					<label>${aplication_offeredPrice}:</label>
					<input type="number" min="0" name="offeredPrice" value="">
				</div>
			</div>
			
			<h2 style="display: none" id="actions-errors" class="error">${error}</h2>
			<div class="actions">
				<div class="ui approve button">${save}</div>
				<div class="ui cancel button">${cancel}</div>
			</div>
		</form>
</div>

<script>
	function hideErrors() {
		$('.error').hide();
	}
	
	function addPhase() {
		$.ajax({
			type : 'POST',
			url : 'phase/handyworker/save-async.do?q=${fixuptask.id}',
			data : $('#phase-save').serialize(),
			success : function(data) {
				let json = JSON.parse(data);
				
				if(json.errors.length > 0) {
					$('#actions-errors').show();
				} else {
					let appVal = $('[name="phases"]').val();
					
					if(appVal.length > 0) {
						$('[name="phases"]').val(appVal + ',' + json.phase);
					} else {
						$('[name="phases"]').val(json.phase);
					}
					
					//$('#addPhaseAction').remove();
					$('#view-phases').modal('hide');
				}
			}
		});
	}

	function addAplicacion() {
		$.ajax({
			type : 'POST',
			url : 'application/handyworker/save-async.do?q=${fixuptask.id}',
			data : $('#application-save').serialize(),
			success : function(data) {
				let json = JSON.parse(data);
				
				if(json.errors.length > 0) {
					$('#actions-errors').show();
				} else {
					let appVal = $('[name="applications"]').val();
					
					if(appVal.length > 0) {
						$('[name="applications"]').val(appVal + ',' + json.application);
					} else {
						$('[name="applications"]').val(json.application);
					}
					
					$('#addAplicationAction').remove();
					$('#view-applications').modal('hide');
				}
			}
		});
	}
</script>
