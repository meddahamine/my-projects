
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<div class="box">
            <div class="box-header">
              <h3 class="box-title">Liste des Admins Entités</h3>
              
              <a style="float: right;" href="newAdminEntite"
              class="btn btn-info">Nouveau Admin Entité</a>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="table" class="table table-bordered table-striped">
              
                <thead>
                <tr>
                  	<th>No</th>
		        	<th>Nom</th>
		        	<th>Adresse</th>
		        	<th>Email</th>
		        	<th>Telephone</th>
		        	<th>Action</th>
                </tr>
                </thead>
                
                <tbody>
                	<c:forEach var="adminEntite" items="${listAdminEntite}" varStatus="status">
	                <tr>
		        		<td>${status.index + 1}</td>
						<td>${adminEntite.name}</td>
						<td>${adminEntite.address}</td>
						<td>${adminEntite.email}</td>
						<td>${adminEntite.telephone}</td>
						
						<td>
							<a class="btn btn-warning" href="editAdminEntite?id=${adminEntite.id}">
								Modifier</a>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a class="btn btn-danger" href="deleteAdminEntite?id=${adminEntite.id}">
								Supprimer</a>
						</td>
							
	        		</tr>
   					</c:forEach>
   				</tbody>
   			</table>
  		</div>
 	</div>