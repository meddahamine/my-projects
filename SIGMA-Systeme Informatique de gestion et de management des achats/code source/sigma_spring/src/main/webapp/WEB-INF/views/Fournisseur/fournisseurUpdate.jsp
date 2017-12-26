
	<div align="center">
		<h1>Modifier Profil</h1>
		<form class="form-horizontal" action="updateFournisseur" method="post">
                  <div class="form-group">
                    <label for="inputName" class="col-sm-2 control-label">Nom</label>

                    <div class="col-sm-9">
                    	<input style="display:none;" name="id" value="${fournisseur.id}"/>
						<input style="display:none;" name="idUser" value="${fournisseur.id}"/>
                      <input type="text" class="form-control" name="name" required="required" value="${fournisseur.name}" placeholder="Nom">
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <label for="inputEmail" class="col-sm-2 control-label">Address</label>

                    <div class="col-sm-9">
                      <input type="text" class="form-control" name="address" required="required" value="${fournisseur.address}" placeholder="Address">
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <label for="inputName" class="col-sm-2 control-label">Email</label>

                    <div class="col-sm-9">
                      <input type="email" class="form-control" name="email" required="required" value="${fournisseur.email}" placeholder="Email">
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <label for="inputExperience" class="col-sm-2 control-label">Téléphone</label>

                    <div class="col-sm-9">
                      <input type="tel" class="form-control" name="telephone" value="${fournisseur.telephone}" placeholder="Téléphone">
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <label for="inputEmail" class="col-sm-2 control-label">Numero de Siret</label>

                    <div class="col-sm-9">
                      <input type="number" class="form-control" name="num_siret" value="${fournisseur.num_siret}">
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <label for="inputEmail" class="col-sm-2 control-label">Code APE</label>

                    <div class="col-sm-9">
                      <input type="text" class="form-control" name="code_ape" value="${fournisseur.code_ape}">
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <label for="inputEmail" class="col-sm-2 control-label">Libellé Code APE</label>

                    <div class="col-sm-9">
                      <input type="text" class="form-control" name="libelle_code_ape" value="${fournisseur.libelle_code_ape}">
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <label for="inputEmail" class="col-sm-2 control-label">Raison Social</label>

                    <div class="col-sm-9">
                      <input type="text" class="form-control" name="raison_social" value="${fournisseur.raison_social}">
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <label for="inputEmail" class="col-sm-2 control-label">Nom Socièté</label>

                    <div class="col-sm-9">
                      <input type="text" class="form-control" name="nom_societe" value="${fournisseur.nom_societe}">
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <label for="inputEmail" class="col-sm-2 control-label">Date Création</label>

                    <div class="col-sm-9">
                      <input type="number" class="form-control" name="date_creation" value="${fournisseur.date_creation}">
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <label for="inputEmail" class="col-sm-2 control-label">Type d'Achat</label>

                    <div class="col-sm-9">
                      <input type="text" class="form-control" name="type_achat" value="${fournisseur.type_achat}">
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <label for="inputEmail" class="col-sm-2 control-label">Code Famille</label>

                    <div class="col-sm-9">
                      <input type="number" class="form-control" name="code_famille" value="${fournisseur.code_famille}">
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <label for="inputEmail" class="col-sm-2 control-label">Libellé Famille</label>

                    <div class="col-sm-9">
                      <input type="text" class="form-control" name="libelle_famille" value="${fournisseur.libelle_famille}">
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <label for="inputEmail" class="col-sm-2 control-label">Site Web</label>

                    <div class="col-sm-9">
                      <input type="text" class="form-control" name="site_web" value="${fournisseur.site_web}">
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <label for="inputEmail" class="col-sm-2 control-label">Adresse de la Socièté</label>

                    <div class="col-sm-9">
                      <input type="text" class="form-control" name="adresse_societe" value="${fournisseur.adresse_societe}">
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <label for="inputEmail" class="col-sm-2 control-label">Code Postal</label>

                    <div class="col-sm-9">
                      <input type="number" class="form-control" name="code_postal" value="${fournisseur.code_postal}">
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <label for="inputEmail" class="col-sm-2 control-label">Ville de la Socièté</label>

                    <div class="col-sm-9">
                      <input type="text" class="form-control" name="ville_societe" value="${fournisseur.ville_societe}">
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <label for="inputSkills" class="col-sm-2 control-label">User Name</label>

                    <div class="col-sm-9">
                      <input type="text" class="form-control" name="username" value="${fournisseurUser.userName}" placeholder="User Name">
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <label for="inputSkills" class="col-sm-2 control-label">Mot de Passe</label>

                    <div class="col-sm-9">
                      <input type="password" class="form-control" name="password" value="${fournisseurUser.password}" placeholder="Mot de Passe">
                    </div>
                  </div>

                  <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-9">
                      <button type="submit" class="btn btn-success">Enregistrer</button>
                    </div>
                  </div>
                  
                </form>
	</div>
	