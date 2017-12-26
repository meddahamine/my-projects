
	<div class="box">
		<div class="box-header">
		<h1>Modifier Entité</h1>
		</div>
		<form action="updateEntite" method="post">
		<div class="box-body">
		<table id="table" class="table table-bordered table-striped">
			<tr>
				<td>Nom:</td>
				<td>
					<input style="display:none;" name="id" required="required" value="${entite.id}"/>
					<input name="name" required="required" value="${entite.name}"/>
				</td>
			</tr>
			<tr>
				<td>Adresse:</td>
				<td><input name="address" required="required" value="${entite.address}"/></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><input name="email" required="required" value="${entite.email}"/></td>
			</tr>
			<tr>
				<td>Telephone:</td>
				<td><input name="telephone" value="${entite.telephone}"/></td>
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