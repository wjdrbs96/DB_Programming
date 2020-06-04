<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag body-content="scriptless"%>                    
 
<%@ attribute name="name" required="true"%>
<%@ attribute name="color" required="true"%>

<p>
	<table border="1">
		<tr>
			<th>${name}</th>
		</tr>
		<tr>
			<td bgcolor="${color }">
			<jsp:doBody/>	
		</tr>
	</table>
</p>
