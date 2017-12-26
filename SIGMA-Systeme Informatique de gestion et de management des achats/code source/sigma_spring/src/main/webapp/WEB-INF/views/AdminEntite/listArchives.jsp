
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<div class="box">
            <div class="box-header">
              <h3 class="box-title">Liste des Archives</h3>
              
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="table" class="table table-bordered table-striped">
              
                <thead>
                <tr>
                  	<th>No</th>
		        	<th>Nom</th>
		        	<th>Address</th>
		        	<th>Email</th>
		        	<th>Telephone</th>
	        		<th>Type</th>
		        	<th>Action</th>
                </tr>
                </thead>
                
                <tbody>
                	<c:forEach var="archive" items="${listArchives}" varStatus="status">
	                <tr>
		        		<td>${status.index + 1}</td>
						<td>${archive.name}</td>
						<td>${archive.address}</td>
						<td>${archive.email}</td>
						<td>${archive.telephone}</td>
						<td>${archive.type}</td>
						<td>
							<a class="btn btn-warning" href="${sessionScope.baseURL}${userConnected.type}/recoverArchive?id=${archive.id}">
								Modifier</a>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a class="btn btn-danger" href="${sessionScope.baseURL}${userConnected.type}/deleteArchive?id=${archive.id}">
								Supprimer</a>
						</td>
							
	        		</tr>
   					</c:forEach>
   				</tbody>
   			</table>
  		</div>
 	</div>
 	