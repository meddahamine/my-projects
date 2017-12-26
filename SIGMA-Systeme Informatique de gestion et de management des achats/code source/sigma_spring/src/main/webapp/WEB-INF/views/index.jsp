<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri='http://java.sun.com/jstl/core' prefix='c' %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>${page_title}</title>
        
    <link rel="shortcut icon" href="../resources/img/LogoRouen.gif">

	<jsp:include page="include_top.jsp"/>
  
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

 	<jsp:include page="${userConnected.type}/main_header.jsp"/>
  
	<jsp:include page="${userConnected.type}/main_sidebar.jsp"/>

	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<jsp:include page="${userConnected.type}/${page_name}.jsp"/>
	 </div>
	<!-- /.content-wrapper -->
	  
	<jsp:include page="main_footer.jsp"/>
	
	<jsp:include page="control_sidebar.jsp"/>
  
</div>
<!-- ./wrapper -->

<jsp:include page="include_bottom.jsp"/>

</body>
</html>
