
	<a href="${sessionScope.baseURL}${sessionScope.userConnected.type}/gestionFournisseur"><button style="float:left;">Retour</button></a>
	<div class="box">
		<div class="box-header">
		<h1 align="center">Ajouter Fournisseur</h1>
		</div>
		<form action="saveFournisseur" method="post">
		<div class="box-body">
		<table id="table" class="table table-bordered table-striped">
			<tr>
				<td>Nom:</td>
				<td><input name="name" placeholder="ex : MARTIN" required="required"/></td>
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
				<td><input name="telephone" placeholder="ex : 06584.." onFocus="javascript:this.value=''"/></td>
			</tr>
		</table>
		</div>
		<div class="box-body">
		<table id="table" class="table table-bordered table-striped">
			<tr>
             	<td>Nom d'utilisateur:</td>
              	<td><input type="text" name="userName" placeholder="saisissez un login" required="required"/></td>
          	</tr>
          	 <tr>
        		<td>Mot de passe:</td>
        	  	<td><input type="password" name="password" value="" required="required"/></td>
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