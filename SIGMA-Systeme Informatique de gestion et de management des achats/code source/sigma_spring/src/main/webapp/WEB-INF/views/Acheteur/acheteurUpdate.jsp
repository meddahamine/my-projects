
	<div align="center">
		<h1>Modifier Profil</h1>
		<form class="form-horizontal" action="updateAcheteur" method="post">
                  <div class="form-group">
                    <label for="inputName" class="col-sm-2 control-label">Nom</label>

                    <div class="col-sm-9">
                    	<input style="display:none;" name="id" value="${acheteur.id}"/>
						<input style="display:none;" name="idUser" value="${acheteur.id}"/>
                      <input type="text" class="form-control" name="name" required="required" value="${acheteur.name}" placeholder="Nom">
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <label for="inputEmail" class="col-sm-2 control-label">Address</label>

                    <div class="col-sm-9">
                      <input type="text" class="form-control" name="address" required="required" value="${acheteur.address}" placeholder="Address">
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <label for="inputName" class="col-sm-2 control-label">Email</label>

                    <div class="col-sm-9">
                      <input type="email" class="form-control" name="email" required="required" value="${acheteur.email}" placeholder="Email">
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <label for="inputExperience" class="col-sm-2 control-label">Téléphone</label>

                    <div class="col-sm-9">
                      <input type="tel" class="form-control" name="telephone" value="${acheteur.telephone}" placeholder="Téléphone">
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <label for="inputSkills" class="col-sm-2 control-label">User Name</label>

                    <div class="col-sm-9">
                      <input type="text" class="form-control" name="username" value="${acheteurUser.userName}" placeholder="User Name">
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <label for="inputSkills" class="col-sm-2 control-label">Mot de Passe</label>

                    <div class="col-sm-9">
                      <input type="password" class="form-control" name="password" value="${acheteurUser.password}" placeholder="Mot de Passe">
                    </div>
                  </div>

                  <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-9">
                      <button type="submit" class="btn btn-success">Enregistrer</button>
                    </div>
                  </div>
                  
                </form>
	</div>