
	<div align="center">
		<h1>Modifier Profil</h1>
		<form class="form-horizontal" action="updateAdminSigma" method="post">
                  <div class="form-group">
                    <label for="inputName" class="col-sm-2 control-label">Nom</label>

                    <div class="col-sm-9">
                    	<input style="display:none;" name="id" value="${adminSigma.id}"/>
						<input style="display:none;" name="idUser" value="${adminSigma.id}"/>
                      <input type="text" class="form-control" name="name" required="required" value="${adminSigma.name}" placeholder="Nom">
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <label for="inputEmail" class="col-sm-2 control-label">Adresse</label>

                    <div class="col-sm-9">
                      <input type="text" class="form-control" name="address" required="required" value="${adminSigma.address}" placeholder="Address">
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <label for="inputName" class="col-sm-2 control-label">Email</label>

                    <div class="col-sm-9">
                      <input type="email" class="form-control" name="email" required="required" value="${adminSigma.email}" placeholder="Email">
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <label for="inputExperience" class="col-sm-2 control-label">Téléphone</label>

                    <div class="col-sm-9">
                      <input type="tel" class="form-control" name="telephone" value="${adminSigma.telephone}" placeholder="Téléphone">
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <label for="inputSkills" class="col-sm-2 control-label">User Name</label>

                    <div class="col-sm-9">
                      <input type="text" class="form-control" name="username" value="${adminSigmaUser.userName}" placeholder="User Name">
                    </div>
                  </div>
                  
                  <div class="form-group">
                    <label for="inputSkills" class="col-sm-2 control-label">Mot de Passe</label>

                    <div class="col-sm-9">
                      <input type="password" class="form-control" name="password" value="${adminSigmaUser.password}" placeholder="Mot de Passe">
                    </div>
                  </div>

                  <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-9">
                      <button type="submit" class="btn btn-success">Enregistrer</button>
                    </div>
                  </div>
                  
                </form>
	</div>