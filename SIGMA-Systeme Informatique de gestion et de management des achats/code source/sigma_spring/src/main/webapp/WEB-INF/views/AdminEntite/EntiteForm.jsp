
	<div class="box">
		<div class="box-header">
			<h1 align="center">Ajouter Entité</h1>
		</div>
		<form action="saveEntite" method="post">
		<div class="box-body">
		<table id="table" class="table table-bordered table-striped">
			<tr>
				<td>Nom:</td>
				<td><input name="name" placeholder="ex : SIGMA" required="required"/></td>
			</tr>
			<tr>
				<td>Adresse:</td>
				<td><input name="address" placeholder="ex : 45 rue pierre..." required="required"/></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><input name="email" placeholder="ex : xyz@xyz.com" required="required"/></td>
			</tr>
			<tr>
				<td>Telephone:</td>
				<td><input name="telephone" placeholder="ex : 06584.." /></td>
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