
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<div class="box">
            <div class="box-header">
              <h3 class="box-title">Liste des Acheteurs</h3>
              
              <a style="float: right;" href="newAcheteur"
              class="btn btn-info">Nouveau Acheteur</a>
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
                	<c:forEach var="acheteur" items="${listAcheteur}" varStatus="status">
	                <tr>
		        		<td>${status.index + 1}</td>
						<td>${acheteur.name}</td>
						<td>${acheteur.address}</td>
						<td>${acheteur.email}</td>
						<td>${acheteur.telephone}</td>
						<td>
							<a class="btn btn-warning" href="editAcheteur?id=${acheteur.id}">
								Modifier</a>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a class="btn btn-danger" href="deleteAcheteur?id=${acheteur.id}">
								Supprimer</a>
						</td>
							
	        		</tr>
   					</c:forEach>
   				</tbody>
   			</table>
  		</div>
 	</div>