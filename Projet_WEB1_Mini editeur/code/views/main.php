<h3>Votre Document</h3>
<?php
function buttons() {
	$btns=array(
	'g','i','s','ligne','ol','lien','image','createsection'
	);
	foreach($btns as $btn){
		if($btn=='createsection')
		{
			echo '<input type="button" id="btn_'.$btn.'" onClick="javascript:titrevide.call(this)"  class="btn-primary" style="width:200px; height:35px;"value="'.ucwords($btn).'"/>';	
		}else{
			
			if($btn=='ol')
			{
				echo '<input type="button" id="btn_'.$btn.'" onClick=""  class="btn-primary" style="width:200px; height:35px;"value="List"/>';
			}else{
					echo '<input type="button" id="btn_'.$btn.'" class="btn-primary" style="width:200px; height:35px;" onClick="formatText(\''.$btn.'\')" value="'.ucwords($btn).'"/>';
			}
			
		}
	}
}
	
	function taille() {
	$btns=array(
	'1','2','3','4','5','6'
	);
	foreach($btns as $btn){
	echo'<option value="'.$btn.'" id="btn_'.$btn.'">'.$btn.'</option>';
		}
	}
	function align() {
	$btns=array(
	'JustifyCenter','JustifyJustify','JustifyLeft','JustifyRight'
	);
	foreach($btns as $btn){
	echo'<option value="'.$btn.'" id="btn_'.$btn.'">'.$btn.'</option>';
		}
	}
?>

<style type="text/css">
input, textarea {padding:5px}
label {display:block}
div {margin-bottom:20px}
textarea {display:none }
</style>
        <link rel="stylesheet" href="resources/css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="resources/css/bootstrap.css" type="text/css">
        <link rel="stylesheet" href="resources/css/style.css" type="text/css">
        <link rel="stylesheet" href="resources/css/menu.css" type="text/css">
        <link href="css/main.css" rel="stylesheet">
		<link href="css/demo.css" rel="stylesheet">
        
        <script src="resources/js/bootstrap.min.js"></script>
        <script src="resources/js/bootstrap.js"></script>
		<script src="editer.js"></script>
		
<script language='javascript'>
function reCat(element){
    var idx=element.selectedIndex;
    var val=element.options[idx].value;
    var content=element.options[idx].innerHTML;
	document.getElementById('trsC').value = content;
 }
</script>
<script language='javascript'>
var b = 0;
function titrevide(){
var str=this.id;
var res=str.substring(6);
var resStart=res.substr(0, 3);
var idtext="text"+res;

if(str=="btn_createsection"){
	var titre = prompt('Saisie Un Titre','section tittle');
	if(titre!=null && titre!=""){
		div(titre,idtext);
		sommaire(this.id,titre);
		}
}
else{
	var titre = prompt('Saisie Un Titre','sous_section tittle');
		if(titre!=null && titre!=""){		
			div2(this.id,titre);
			sommaire(this.id,titre);
			}		
}
}
</script>
<script language='javascript'>
var i = 0;
var j = 0;
var children=0;
var children1=0;
var section = [];
</script>
<script language='javascript'>
function save(){
var x = document.getElementById("abstract").innerHTML;
		document.getElementById('trs').value = x;
		document.getElementById('trsi').value = i;
	var a=	document.getElementById('trsj').value = j;
var z=0;
	while (z <j){
		var x3 = document.getElementById("div_ssection"+z).innerHTML;
		document.getElementById('contenuSS'+z).value = x3;
		var x4 = document.getElementById("text_ssection"+z).value;
		document.getElementById('titreSS'+z).value = x4;
		z++;
	}
var k=0;
	while (k <= i){
		var x1 = document.getElementById("div_section"+k).innerHTML;
		document.getElementById('contenuS'+k).value = x1;
		var x2 = document.getElementById("text_section"+k).value;
		document.getElementById('titreS'+k).value = x2;
		k++;
	}
}
</script>
<script language='javascript'>
function modif(){
var x = document.getElementById("abstractModif").innerHTML;
		document.getElementById('trcModif').value = x;
var i = document.getElementById('indexI').value;
var z=0;
	while (z <i){
		var x3 = document.getElementById("sectionContenu"+z).innerHTML;
		document.getElementById('sectionModif'+z).value = x3;
		z++;
	}
}
</script>
<script language='javascript'>
addAnother = function() {
    var ul = document.getElementById("list");
    var li = document.createElement("li");
    var children = ul.children.length + 1
    li.setAttribute("id", "element"+children)
    li.appendChild(document.createTextNode("Element "+children));
    ul.appendChild(li)
}
</script>
<script language='javascript'>
function titrechange(){
var idtext=this.id;
var res = idtext.substring(4);
var tsm=document.getElementById("atitre"+res).innerHTML;
var txtch=document.getElementById("text"+res).value;
var a=document.getElementById("atitre"+res);
	alert(a);
	//a[0].setAttribute("text", txtch);
	a.innerText=txtch;
}
</script>
<script language='javascript'>
function sommaire(id,titre) {
Element.prototype.appendAfter = function (element) {
		element.parentNode.insertBefore(this, element.nextSibling);
		}, false;
Element.prototype.appendBefor = function (element) {
		element.parentNode.insertBefore(this, element);
		}, false;
var str=id;
var res = str.substring(6);
var resStart=res.substring(0, 3);

if(id=="btn_createsection"){
    var ul=document.createElement("li");
	var a= document.createElement("a");	
		a.setAttribute('href', "#text_section"+children);
		a.textContent = titre;
		a.setAttribute("id", "atitre_section"+children);
		ul.setAttribute("id", "titre_section"+children);
		ul.style.width='20%';
		ul.style.float='left';
		ul.setAttribute('style','text-align:left');
		ul.appendChild(a);
	var list=document.getElementById("list");
		list.appendChild(ul);
		children=children+1;
}
else{
	var a= document.createElement("a");	
		a.setAttribute('href', "#text_ssection"+children1);
		a.textContent = titre;
		a.setAttribute("id", "atitre_ssection"+children1);
	var li = document.createElement("ul");
		li.setAttribute("id", "titre_ssection"+children1);
		li.appendChild(a);
	var list=document.getElementById("titre"+res).id;
		li.appendAfter(document.getElementById(list));
		children1=children1+1;
	}
}
</script>
<script language='javascript'>
//function supprimer ssection & section
function supp_sec(){
var str=this.id;
var ext1 = str.substr(-10,9);
var ext2 = str.substr(-10);
var ext3 = str.substr(-11,10);
var ext4 = str.substr(-11);
var res = str.substring(4);

var indexbtn = section.indexOf("button"+res);
		section.splice(indexbtn,1);
		
var indexbtn1 = section.indexOf("supp"+res);
		section.splice(indexbtn1,1);

var indexdiv = section.indexOf("div"+res);
		section.splice(indexdiv,1);

var indextxt = section.indexOf("text"+res);
		section.splice(indextxt,1);
var indextxt = section.indexOf("note"+res);
		section.splice(indextxt,1);
		
		var element1 = document.getElementById("supp"+res);
element1.outerHTML = "";
delete element1;
var element2 = document.getElementById("button"+res);
element2.outerHTML = "";
delete element2;
var element3 = document.getElementById("div"+res);
element3.outerHTML = "";
delete element3;
var element4 = document.getElementById("text"+res);
element4.outerHTML = "";
delete element4;
var element5 = document.getElementById("note"+res);
element5.outerHTML = "";
delete element5;
}
</script>
<script language='javascript'>
function alerter(){
var i = document.getElementById('indexI').value;
alert(i);
var z=0;
	while (z <i){
		var x3 = document.getElementById("sectionContenu"+z).innerHTML;
		alert(x3);alert(z);
		document.getElementById('sectionModif'+z).value = x3;
		z++;
	}
}
</script>
<script language='javascript'>
function div(titre,idtext){
section.splice(section.length, 0, "supp_section"+i);
section.splice(section.length+1, 0, "text_section"+i);
section.splice(section.length+2, 0, "div_section"+i);
section.splice(section.length+3, 0, "button_section"+i);
var ok = true;
	
if (ok === true) {
	 	var text=document.createElement('input');
		text.input="saisie text";
		text.id="text_section"+i;
		text.placeholder="saisie un titre de votre section";
		text.value=titre;
		text.name="titre";
		text.size="40";
		text.style="font-weight: bold";
		text.setAttribute('onchange','javascript:titrechange.call(this)');

function showButto(){
		button.setAttribute('style','display : block;');
		}

var button= document.createElement('button');
	 	button.type="button";
		button.id="button_section"+i;
		button.value="create";
		button.style.width="20%";
        button.style.height="30px";
        button.style.padding="5px";
		button.setAttribute('onClick', 'javascript:titrevide.call(this)');

var t = document.createTextNode("create_sous_section");       
		button.appendChild(t);                                
		document.body.appendChild(button);
			
var button1= document.createElement('button');
	 	button1.type="button";
		button1.id="supp_section"+i;
		button1.value="create";
		button1.style.width="21%";
        button1.style.height="30px";
        button1.style.padding="5px";
		button1.setAttribute('onClick', 'javascript:supp_sec.call(this)');
		button1.style.display='block';

var t = document.createTextNode("supprimer_cette_section");       
		button1.appendChild(t);                                
		document.body.appendChild(button1);
		
var div = document.createElement('div');
        div.className = 'form-control';
		div.id = "div_section"+i; 
		div.style.width="100%";
        div.style.height="200px";
        div.style.padding="5px";
        div.style.overflow="auto";
		div.setAttribute('contenteditable','true');
		   
var text1 = document.createElement('input');
		text1.type='hidden';
		text1.id="contenuS"+i;
		text1.name="contenuS"+i;

var text2 = document.createElement('input');
		text2.type='hidden';
		text2.id="titreS"+i;
		text2.name="titreS"+i;
		
var text3 = document.createElement('input');
		text3.type='hidden';
		text3.id="id"+i;
		text3.name="id"+i;
		text3.value=text.id;

var br = document.createElement("br");
		document.getElementsByTagName('form')[0].appendChild(br);
		document.getElementsByTagName('form')[0].appendChild(text1);
		document.getElementsByTagName('form')[0].appendChild(text2);
		document.getElementsByTagName('form')[0].appendChild(text3);
		document.getElementsByTagName('form')[0].appendChild(button1);
        document.getElementsByTagName('form')[0].appendChild(text);
        document.getElementsByTagName('form')[0].appendChild(div);
		document.getElementsByTagName('form')[0].appendChild(button);	
		document.getElementsByTagName('form')[0].appendChild(br);
		document.getElementsByTagName('form')[0].appendChild(br);
		document.getElementsByTagName('form')[0].appendChild(br);
    }
	i=i+1;
};
</script>
<script language='javascript'>
function InsertInDiv(){
var str=this.id;
var idtext="text"+str.substring(6);
var cible=document.getElementById(idtext);
		alert(cible);
var titre = prompt('Inserer un lien','http://');
		document.getElementById("abstract").innerHTML=titre;
}
</script>
<script language='javascript'>
function div2(id,titre){
var index = section.indexOf(id);

section.splice(index+1, 0, "supp_ssection"+j);
section.splice(index+2, 0, "text_ssection"+j);
section.splice(index+3, 0, "div_ssection"+j);
section.splice(index+4, 0, "button_ssection"+j);

var ok = true;

    if (ok === true) {
		var text=document.createElement('input');
				text.input="saisie text";
				text.id="text_ssection"+j;
				text.placeholder="saisie un titre de votre sous section";
				text.value=titre;
				text.name="titre";
				text.size="40";
				text.style="font-weight: bold";
				text.setAttribute('onchange','javascript:titrechange.call(this)');
function showButto(){
		button.setAttribute('style','display : block;');
		}

var button= document.createElement('button');
	 	button.type="button";
		button.id="button_ssection"+j;
		button.style.width="20%";
        button.style.height="30px";
        button.style.padding="5px";
		button.setAttribute('onClick', 'javascript:titrevide.call(this)');
		
var t = document.createTextNode("create_sous_section");       
		button.appendChild(t);                                
		document.body.appendChild(button);

var button1= document.createElement('button');
	 	button1.type="button";
		button1.id="supp_ssection"+j;
		button1.style.width="25%";
        button1.style.height="30px";
        button1.style.padding="5px";
		button1.setAttribute('onClick', 'javascript:supp_sec.call(this)');

var t = document.createTextNode("supprime_cette_sous_section");       
		button1.appendChild(t);                                
		document.body.appendChild(button1);
			
var div = document.createElement('div');
        div.className = 'form-control';
		div.id = "div_ssection"+j;  
		div.style.width="95%";
        div.style.height="200px";
        div.style.padding="5px";
        div.style.overflow="auto";
		div.setAttribute('contenteditable','true');
		div.style.float="right"
		   
		Element.prototype.appendAfter = function (element) {
				element.parentNode.insertBefore(this, element.nextSibling);
				}, false;

	var text1 = document.createElement('input');
		text1.type='hidden';
		text1.id="contenuSS"+j;
		text1.name="contenuSS"+j;


	var text2 = document.createElement('input');
		text2.type='hidden';
		text2.id="titreSS"+j;
		text2.name="titreSS"+j;

		
	var text3 = document.createElement('input');
		text3.type='hidden';
		text3.id="idS"+j;
		text3.name="idS"+j;
		var h = i-1;
		text3.value="text_section"+h;
		
	var text4 = document.createElement('input');
		text4.type='hidden';
		text4.id="idSS"+j;
		text4.name="idSS"+j;
		text4.value=div.id;
		
var br = document.createElement("br");
		document.getElementsByTagName('form')[0].appendChild(br);	
		document.getElementsByTagName('form')[0].appendChild(text1);
		document.getElementsByTagName('form')[0].appendChild(text2);		
		document.getElementsByTagName('form')[0].appendChild(text3);
		document.getElementsByTagName('form')[0].appendChild(text4);
		
		button1.appendAfter(document.getElementById(id));
		div.appendAfter(document.getElementById(button1.id));
		div.parentNode.insertBefore( text, div );
		button1.parentNode.insertBefore( text1, button1);
		button.appendAfter(document.getElementById(div.id));
		br.appendAfter(document.getElementById(button1.id));
		div.parentNode.insertBefore( br, div);
    }
	j=j+1;
};
</script>
<script language='javascript'>
function insertbefore(parent, node, referenceNode) {
  parent.insertBefore(node, referenceNode);}
 </script>
<script language='javascript'>
function insertAfter(newElement,targetElement) {
var parent = targetElement.parentNode;
	if(parent.lastchild == targetElement) {
        parent.appendChild(newElement);
    } else {
        parent.insertBefore(newElement, targetElement.nextSibling);
    }
}
</script>
<style type="text/css">
.new-rect {
    background: #990066;
    width: 200%;
    height: 200px;
}
</style>
<script src="editer.js"></script>
<body onLoad ='loadIframe()'>

<form action="../controllers/saveDoc.php" name="blog" id="blog" method="post" >
    <div style="position:fixed; top:0px; margin:auto; z-index:100000; width:50%; background-color:#FF0000">
           <a href="MathJax-website-gh-pages/index.html" id="link" onClick="return doalert()">Link</a>
      <select id="taille" onChange="formatText('taille')">
        <option>- taille -</option>
        
        	<?php echo taille() ?></br>
        </select>
        <select id="couleur" onChange="formatText('couleur')">
        <option>- Couleur -</option>
        <option value="red" style="color:red">Rouge</option>
        <option value="green" style="color:green">Vert</option>
        <option value="blue" style="color:blue">Bleu</option>

        </select>
        <select id="align" onChange="formatText('align')">
        <option>- Align -</option>
        <?php echo align() ?></br>
        
        </select></br>

        <?php echo buttons() ?>
		<input type="button" id="btn_click" onClick="location.href='max.html';"  class="btn-primary" style="width:200px; height:35px;"value="MathML"/>
       
        </div>
    <div>
<script language='javascript'>
window.onload=function(){
	document.getElementById("button").style.display='none';
}
function showButton(){
	document.getElementById("button").style.display='block';
}
</script>
<?php
if($_SESSION['doc']==null){	
?>
<input type="button" id="button" value="New Button" style="display:none"/>
<label> Le contenue du document</label><br/>
   <div>
    	<label>Saisie un Titre</label>
        <input type="text" name="titreDoc" id="titreDoc" size="40" style="font-weight: bold" />
    </div>
<h3>Abstract</h3><br/>
<input type="text" id="userText" value="Change the text" onChange="showButton()" style="display:none;"/>
        <div contenteditable="true" id="abstract" class="form-control" allowtransparency="yes" style=" width: 100%;
            height: 200px;
            padding: 5px;
            overflow: auto; float:right"></div>
        </div>
		<input class="btn-primary btn-lg" type="submit" onClick="save()" name="submit" value="save">
		<p>
	<?php
		}else{
								//echo"<script>alert('Successful operation document');</script>";
								include '../models/RegisterDoc.php';
								$u1 = unserialize($_SESSION['titreD']);
								$u2 = unserialize($_SESSION['abst']);
								$u3 = unserialize($_SESSION['idDoc']);
						?>
						<h3>Titre :</h3></br>
						<INPUT TYPE="hidden" name="idDocModif" id="idDocModif" value="<?php echo $u3;?>"/>
						<input type="text" name="titreDocModif" id="titreDocModif" size="40" style="font-weight: bold" value="<?php echo $u1;?>"/>
						</br>
						<h3>Abstract :</h3></br>
						<INPUT TYPE="hidden" name="trcModif" id="trcModif" />
						<div contenteditable="true" name="abstractModif" id="abstractModif" class="form-control" allowtransparency="yes" style=" width: 100%;
							height: 200px;padding: 5px;overflow: auto; float:right" ><?php echo $u2;?></div>
							</br>
							<h3>Contenu :</h3></br>
								<?php
									$u = unserialize($_SESSION['doc']);
									$req = mysql_query($u);
									$i=0;
									while ($d =mysql_fetch_array($req)){
									?>
									<INPUT TYPE="hidden" name="idSectionModif<?php echo $i;?>" id="idSectionModif<?php echo $i;?>" value="<?php echo $d['id'];?>"/>
									<input type="text" name="sectionTitre<?php echo $i;?>" id="sectionTitre<?php echo $i;?>" size="40" style="font-weight: bold" 
									value="<?php echo $d['titre'];?>"/>
									</br>
									<INPUT TYPE="hidden" name="sectionModif<?php echo $i;?>" id="sectionModif<?php echo $i;?>" />
									<div contenteditable="true" name="sectionContenu<?php echo $i;?>" id="sectionContenu<?php echo $i;?>" class="form-control" allowtransparency="yes" style=" width: 100%;
										height: 200px;padding: 5px;overflow: auto; float:right" ><?php echo $d['contenu'];?></div>
									</br>
									<?php
									$i++;
									}
									?>
									<INPUT TYPE="hidden" name="indexI" id="indexI" value="<?php echo $i?>"/>
									<input class="btn-primary btn-lg" type="submit" name="submit" onClick="modif()" value="Update">
									<?php
									
									$_SESSION['doc']=null;
									$_SESSION['titreD']=null;
									$_SESSION['abst']=null;
									$_SESSION['idDoc']=null;
							}
						?>
					</p>
					
<INPUT TYPE="hidden" name="trs" id="trs" />
<INPUT TYPE="hidden" name="trsi" id="trsi" />
<INPUT TYPE="hidden" name="trsj" id="trsj" />
<INPUT TYPE="hidden" name="trsC" id="trsC" />
   </form>
</body>