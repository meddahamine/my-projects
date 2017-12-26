 
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="../resources/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>${adminConnected.name}</p>
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
		
		<li class="<%if((request.getAttribute("page_name")=="gestionEntite")
        		|| (request.getAttribute("page_name")=="EntiteForm")
        		|| (request.getAttribute("page_name")=="entiteUpdate")){%>active<% }%>">
          <a href="${sessionScope.baseURL}${userConnected.type}/gestionEntite">
            <i class="fa fa-institution"></i> <span>Gestion Des Entités</span>
          </a>
        </li> 
		
		<li class="<%if((request.getAttribute("page_name")=="gestionResponsableAchat")
        		|| (request.getAttribute("page_name")=="responsableAchatForm")){%>active<% }%>">
          <a href="${sessionScope.baseURL}${userConnected.type}/gestionResponsableAchat">
            <i class="fa fa-shopping-cart"></i> <span>Gestion Responsables Achats</span>
          </a>
        </li>

		<li class="<%if((request.getAttribute("page_name")=="gestionAcheteur")
        		|| (request.getAttribute("page_name")=="acheteurForm")){%>active<% }%>">
          <a href="${sessionScope.baseURL}${userConnected.type}/gestionAcheteur">
            <i class="fa fa-users"></i> <span>Gestion Acheteurs</span>
          </a>
        </li>
                
        <li class="<%if((request.getAttribute("page_name")=="gestionFournisseur")
        		|| (request.getAttribute("page_name")=="fournisseurForm")
        		|| (request.getAttribute("page_name")=="fournisseurUpdate")){%>active<% }%>">
          <a href="${sessionScope.baseURL}${userConnected.type}/gestionFournisseur">
            <i class="fa fa-truck"></i> <span>Gestion Fournisseurs</span>
          </a>
        </li>
        
        <li class="<%if((request.getAttribute("page_name")=="gestionArchives")
        		|| (request.getAttribute("page_name")=="listArchives")){%>active<% }%>">
          <a href="${sessionScope.baseURL}${userConnected.type}/gestionArchives">
            <i class="fa fa-location-arrow"></i> <span>Gestion Archives</span>
          </a>
        </li>
        
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>