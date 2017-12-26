
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<div class="box">
            <div class="box-header">
              <h3 class="box-title">Liste des Admins Contenu</h3>
              
              <a style="float: right;" href="newAdminEntite"
              class="btn btn-info">Nouveau Admin Contenu</a>
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
                	<c:forEach var="adminContenu" items="${listAdminContenu}" varStatus="status">
	                <tr>
		        		<td>${status.index + 1}</td>
						<td>${adminContenu.name}</td>
						<td>${adminContenu.address}</td>
						<td>${adminContenu.email}</td>
						<td>${adminContenu.telephone}</td>
						
						<td>
							<a class="btn btn-warning" href="editAdminContenu?id=${adminContenu.id}">
								Modifier</a>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a class="btn btn-danger" href="deleteAdminContenu?id=${adminContenu.id}">
								Supprimer</a>
						</td>
							
	        		</tr>
   					</c:forEach>
   				</tbody>
   			</table>
  		</div>
 	</div>