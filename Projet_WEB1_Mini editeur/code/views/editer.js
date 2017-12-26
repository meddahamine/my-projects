// JavaScript Document 
function loadIframe(){
	edit.document.designMode = 'On';	
}

function formatText(btn){
	//var cible = edit.document;

	switch(btn){
	
	case 'g':
		document.execCommand('bold',false,'');
	break;
	
	case 'i':
		document.execCommand('italic',false,'');
	break;
	
	case 's':
		document.execCommand('underline',false,'');
	break;
	
	case 'taille':
	var taille = document.getElementById('taille').options[document.getElementById('taille').selectedIndex].value
		//cible.execCommand('forecolor',false,taille);
		//var taille = prompt('une taille entre 1 et 6','');
		//if(taille>=1 && taille <= 6)
			document.execCommand('FontSize',false,taille);
	break;
	
	case 'couleur':
		var couleur = document.getElementById('couleur').options[document.getElementById('couleur').selectedIndex].value
		document.execCommand('forecolor',false,couleur);
	break;
	case 'align':
		var align = document.getElementById('align').options[document.getElementById('align').selectedIndex].value
		var center = document.getElementById("edit");
		document.execCommand(align,false,center);
		//center();
		//var center = document.getElementById("blog");
		//center=center.style.textAlign = "center";
		//cible.execCommand('JustifyCenter',false,center);
	break;
	case 'ligne':
		document.execCommand('inserthorizontalrule',false,null);
	break;
	
	case 'ol':
		document.execCommand('insertorderedlist',false,	'newOL');
	break;
	
	case 'ul':
		document.execCommand('insertunordredlist',false,'newUL');
	break;
	case 'lien':
		var lien = prompt('Inserer un lien','http://');
		document.execCommand('CreateLink',false,lien);
	break;
	
		

	case 'image':
		var image = prompt('Inserer un lien de l\'image','');
		document.execCommand('insertimage',false,image);
	break;
	}
}
function submitForm(){
	
	var com = document.getElementById('blog');	
	//alert(com);
	com.elements['commentaire'].value = window.frames['frm'].document.body.innerHTML;
	//return false;
	document.getElementById('edit').innerHTML += com;
	//com.submit();
}
function doalert(){
    alert(document.getElementById("link").getAttribute("href"));
    return false;
}

function recup(){
        var x= document.getElementById('blog').innerHTML;
		//var y= document.getElementById('edit').setAttribute('style','visibility:hidden;');
		//alert (x);
		//x.elements['commentaire'].value = document.getElementById('edit').innerHTML;
		//document.getElementById('edit1').innerHTML = x;
		//alert (x);
		//x.elements['commentaire'].value = window.frames['edit'].document.body.innerHTML;
		//alert (x);
		
        //document.getElementById('blog').setAttribute('style','visibility:true;');
        document.getElementById('edit1').innerHTML += x;
		
    }
function showImg() {
    x=document.getElementById("map_img")
    x.style.visibility="visible";
}
//recuperer dans le mathML


function center(){
	//var align = require('align-text');
	//align(text, callback_function_or_integer);
	var com = document.getElementById('blog');	
	var text = com.getText().toString();
	//com.elements['commentaire'].value = window.frames['frm'].document.body.innerHTML;
	//alert ('com.elements['commentaire'].value');
	align(text,centerAlign)
}
function centerAlign(len, longest, line, lines) {
  return {
	  
    character: '\t',
    indent: Math.floor((longest - len) / 2),
    prefix: '~ ',
  }
}