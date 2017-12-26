
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<div class="box">
            <div class="box-header">
              <h3 class="box-title">Liste des Responsables Achats</h3>
              
              <a style="float: right;" href="newResponsableAchat"
              class="btn btn-info">Nouveau Responsable Achat</a>
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
                	<c:forEach var="responsableAchat" items="${listResponsableAchat}" varStatus="status">
	                <tr>
		        		<td>${status.index + 1}</td>
						<td>${responsableAchat.name}</td>
						<td>${responsableAchat.address}</td>
						<td>${responsableAchat.email}</td>
						<td>${responsableAchat.telephone}</td>
						<td>
							<a class="btn btn-warning" href="editResponsableAchat?id=${responsableAchat.id}">
								Modifier</a>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a class="btn btn-danger" href="deleteResponsableAchat?id=${responsableAchat.id}">
								Supprimer</a>
						</td>
							
	        		</tr>
   					</c:forEach>
   				</tbody>
   			</table>
  		</div>
 	</div>
    	