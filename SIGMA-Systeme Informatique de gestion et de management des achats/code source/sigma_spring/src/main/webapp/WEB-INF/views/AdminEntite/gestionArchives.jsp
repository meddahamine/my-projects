
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

		<div class="box">
            <div class="box-header">
              <h3 class="box-title">Liste Des Utilisateurs</h3>
              
              <a style="float: right;" href="${sessionScope.baseURL}${userConnected.type}/listArchives"
              class="btn btn-info">Liste des Archives</a>
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
		        	<th>Action</th>
                </tr>
                </thead>
                
                <tbody>
                	<c:forEach var="fournisseur" items="${listFournisseur}" varStatus="status">
	                <tr>
		        		<td>${status.index + 1}</td>
						<td>${fournisseur.name}</td>
						<td>${fournisseur.address}</td>
						<td>${fournisseur.email}</td>
						<td>${fournisseur.telephone}</td>
						<td>
							<a class="btn btn-success" href="${sessionScope.baseURL}${userConnected.type}/archiver?id=${fournisseur.id}">
								Archiver</a>
						</td>
							
	        		</tr>
   					</c:forEach>
   				</tbody>
   			</table>
  		</div>
 	</div>
 	