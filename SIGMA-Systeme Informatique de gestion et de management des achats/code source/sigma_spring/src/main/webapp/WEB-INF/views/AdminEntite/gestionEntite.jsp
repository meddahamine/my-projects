
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

		<div class="box">
            <div class="box-header">
              <h3 class="box-title">Liste des Entités</h3>
              
              <a style="float: right;" href="${sessionScope.baseURL}${userConnected.type}/newEntite"
              class="btn btn-info">Nouvelle Entité</a>
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
                	<c:forEach var="entite" items="${listEntite}" varStatus="status">
	                <tr>
		        		<td>${status.index + 1}</td>
						<td>${entite.name}</td>
						<td>${entite.address}</td>
						<td>${entite.email}</td>
						<td>${entite.telephone}</td>
						<td>
							<a class="btn btn-warning" href="${sessionScope.baseURL}${userConnected.type}/editEntite?id=${entite.id}">
								Modifier</a>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a class="btn btn-danger" href="${sessionScope.baseURL}${userConnected.type}/deleteEntite?id=${entite.id}">
								Supprimer</a>
						</td>
							
	        		</tr>
   					</c:forEach>
   				</tbody>
   			</table>
  		</div>
 	</div>