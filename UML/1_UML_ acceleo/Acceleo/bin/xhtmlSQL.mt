<%
metamodel http://www.eclipse.org/emf/2002/Ecore
%>

<%script type="ecore.EPackage" name="xhtmlSQL" file="<%name%>.sql"%>
CREATE DATABASE <%name%>;

<%for (eAllContents("EClass")){%> 
CREATE TABE <%name%>(
	<%for (eAllContents("EAttribute")){%> 
		<%name%> <%eAttributeType.toSQLType.trim()%><%if (following()){%>,<%}%>
	<%}%>
	);
<%}%>

<%script type="ecore.EDataType" name="toSQLType"%>
<%if (name == "EString"){%>
 VARCHAR(255)
<%}else if (name == "EInt") {%>
 Integer
<%}else if (name == "EDate") {%>
 DATETIME
<%}else{%>
 Char(32)
<%}%>