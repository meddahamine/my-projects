
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

		<div class="box">
            <div class="box-header">
              <h3 class="box-title">Liste des Fournisseurs</h3>
              
              <a style="float: right;" href="${sessionScope.baseURL}${userConnected.type}/newFournisseur"
              class="btn btn-info">Nouveau Fournisseur</a>
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
		        	<th>Évaluation</th>
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
							<form class="form-horizontal" action="evaluateFournisseur" method="post">
			                  
			                  
			                    <div class="col-sm-12">
			                    	<input style="display:none;" name="id" value="${fournisseur.id}"/>
			                      	<input type="number" class="form-control" name="score" required="required" 
			                      		value="${fournisseur.score}" min="1" max="10">
			                    </div>
			                    
			                    <div class="col-sm-offset-2 col-sm-12">
			                      <button type="submit" class="btn btn-success" style="margin-top: 10px;">
			                      	Évaluer</button>
			                    </div>
			                  
			                  
			                </form>
						</td>
							
	        		</tr>
   					</c:forEach>
   				</tbody>
   			</table>
  		</div>
 	</div>