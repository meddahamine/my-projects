 
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="../resources/img/logos/${fournisseur.logo}" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>${fournisseur.name}</p>
          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div>
      <!-- search form -->
      <form action="#" method="get" class="sidebar-form">
        <div class="input-group">
          <input type="text" name="q" class="form-control" placeholder="Search...">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </form>
      <!-- /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu">
        <li class="header">NAVIGATION PRINCIPALE</li>

        <li class="<%if(request.getAttribute("page_name")=="accueil"){%>active<% }%>">
          <a href="${sessionScope.baseURL}${userConnected.type}/">
            <i class="fa fa-dashboard"></i> <span>Tableau de bord</span>
          </a>
        </li>
        
        <li class="<%if(request.getAttribute("page_name")=="upload"){%>active<% }%>">
          <a href="${sessionScope.baseURL}${userConnected.type}/uploadFile">
            <i class="fa fa-book"></i> <span>Upload Files</span>
          </a>
        </li>
        
        <li class="<%if(request.getAttribute("page_name")=="fournisseurUpdate"){%>active<% }%>">
          <a href="${sessionScope.baseURL}${userConnected.type}/gestionProfil?id=${sessionScope.userConnected.id}">
            <i class="fa fa-life-saver"></i> <span>Gestion Profil</span>
          </a>
        </li>
        
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>