<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Upload and Download files using Spring</title></head>
<body>
<div align="center">
    <h2>Liste des fichiers</h2>
	<table style="border-style:solid;width:700px;">
        <tr>
            <th width="10%">No</th>
            <th width="40%">Filename</th>
            <th width="30%">Notes</th>
            <th width="16%">Type</th>
            <th width="20%">Option</th>
        </tr>
        <c:choose>
            <c:when test="${files != null}">
                <c:forEach var="file" items="${files}" varStatus="counter">
                    <tr>
                        <td>${counter.index + 1}</td>
                        <td>${file.filename}</td>
                        <td>${file.notes}</td>
                        <td>${file.type}</td>
                        <td><div align="center">
                        	<a href="${sessionScope.baseURL}${userConnected.type}/downloadFile?id=${file.id}">Download</a> /
                            <a href="${sessionScope.baseURL}${userConnected.type}/deleteFile?id=${file.id}">Delete</a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </c:when>
        </c:choose>
    </table>
    <h2>Ajouter un nouveau fichier</h2>
    <form action="saveFile" method="post" enctype="multipart/form-data">
        <table style="border-style:solid;width:500px;">
            <tr>
                <td width="35%"><strong>Chargé votre Fichier </strong></td>
                <td width="65%"><input type="file" name="file" /></td>
            </tr>
            <tr>
                <td><strong>Notes</strong></td>
                <td><input type="text" name="notes" width="60" /></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td><input type="submit" name="submit" value="Enregistrer"/></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html> 