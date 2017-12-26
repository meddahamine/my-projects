
	<a href="${sessionScope.baseURL}${sessionScope.userConnected.type}/gestionAcheteur"><button style="float:left;">Retour</button></a>
	<div class="box">
		<div class="box-header">
		<h1 align="center">Modifier Acheteur</h1>
		</div>
		<form action="updateAcheteur" method="post">
		<div class="box-body">
		<table id="table" class="table table-bordered table-striped">
			<tr>
				<td>Nom:</td>
				<td>
					<input style="display:none;" name="id" required="required" value="${acheteur.id}"/>
					<input name="name" required="required" value="${acheteur.name}"/>
				</td>
			</tr>
			<tr>
				<td>Adresse:</td>
				<td><input name="address" required="required" value="${acheteur.address}"/></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><input name="email" required="required" value="${acheteur.email}"/></td>
			</tr>
			<tr>
				<td>Telephone:</td>
				<td><input name="telephone" value="${acheteur.telephone}"/></td>
			</tr>
		</table>
		</div>
		<div class="form-group" >
            <div class="col-sm-offset-2 col-sm-9" align="center">
                <button  type="submit" class="btn btn-success">Enregistrer</button>
            </div>
        </div>
		</form>
	</div>