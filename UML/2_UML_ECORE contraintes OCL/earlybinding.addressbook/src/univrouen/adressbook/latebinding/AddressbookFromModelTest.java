package univrouen.adressbook.latebinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.Assert;
import org.junit.Test;

public class AddressbookFromModelTest {
	@Test
	public void implementsAddressbookFromModel() throws IOException {
		 Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
	     Map<String, Object> m = reg.getExtensionToFactoryMap();
	     m.put("ecore", new XMIResourceFactoryImpl());
	     //recuperation des donnée du fichier addressbook.ecore
	     ResourceSet resourceSet = new ResourceSetImpl();
	     URI fileURI = URI.createFileURI("model/addressbook.ecore");
	     Resource resource = resourceSet.createResource(fileURI);

	     resource.load(null);
	     EPackage ePackage = (EPackage) resource.getContents().get(0);
	       
	     // instanciation de la classe AdressBook
	     EClass eAdressBook = (EClass) ePackage.getEClassifier("AdressBook");
	     EReference eContains = (EReference) eAdressBook.getEStructuralFeature("personne");
	     EAttribute eName = (EAttribute) eAdressBook.getEStructuralFeature("name");
	     EObject addressBookInstance = ePackage.getEFactoryInstance().create(eAdressBook);
	     
	     // affection d'un nom du carnet
	     addressBookInstance.eSet(eName, "Mon Carnet");
	        
	     // instanciation de la classe Personne   
	     EClass ePersonneClass1 = (EClass) ePackage.getEClassifier("Personne");
	     EAttribute prenom1 = (EAttribute) ePersonneClass1.getEStructuralFeature("prenom");
	     EAttribute nom1 = (EAttribute) ePersonneClass1.getEStructuralFeature("nom");
	     
	     //personne 1 
	     EObject personneInstance1 = ePackage.getEFactoryInstance().create(ePersonneClass1);
	     personneInstance1.eSet(prenom1, "PRENOM1");
	     personneInstance1.eSet(nom1, "NOM1");
	     
	     //personne 2 
	     EObject personneInstance2 = ePackage.getEFactoryInstance().create(ePersonneClass1);
	     personneInstance2.eSet(prenom1, "PRENOM2");
	     personneInstance2.eSet(nom1, "NOM2");
	     
	     //Ajout des deux persone a Mon carnet
	     List<EObject> List = new ArrayList<EObject>();
	     List.add(personneInstance1);
	     List.add(personneInstance2);
	     addressBookInstance.eSet(eContains, List);
	        
	     // creation de repertoire 
	     resourceSet = new ResourceSetImpl();
	     resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
	     String path = System.getProperty("user.dir")+"/fichier-generer/";
		
	     //creation et save du ficher dans le repertoire créer precedement  
		 URI uri = URI.createURI("file:/" + path+ "AddressBook.xmi");
	     resource = resourceSet.createResource(uri);
	     resource.getContents().add(addressBookInstance);
	     resource.save(null);

         Registry packageRegistry = resourceSet.getPackageRegistry();
	     packageRegistry.put("http://addressbook/1.0", ePackage);
	        
	     resource.load(null);

	     // LES TESTS
	     //recuperation de l'objet adressBook 'carnet'
	     DynamicEObjectImpl adressBookImpl = (DynamicEObjectImpl)(resource.getContents().get(0));
	     EClass adressBook = adressBookImpl.eClass();
	     EAttribute nameAttribute = (EAttribute)(adressBook.getEStructuralFeature("name"));
	     EReference ePersonneClass2 = (EReference)(adressBook.getEStructuralFeature("personne"));
	        
	     //tester l'objet 'adressBook' recuperer
	     Assert.assertEquals("Mon Carnet", adressBookImpl.eGet(nameAttribute));
	        
	     // recupere la liste des personnes
	     EcoreEList listPersonnes = (EcoreEList)adressBookImpl.eGet(ePersonneClass2);
	        
	     // recuperer la premiere personne
	     DynamicEObjectImpl personneImpl1 = (DynamicEObjectImpl)listPersonnes.get(0);
	     EClass personne1 = personneImpl1.eClass();
	     EAttribute nom2 = (EAttribute)personne1.getEStructuralFeature("nom");
	     EAttribute prenom2 = (EAttribute)personne1.getEStructuralFeature("prenom");
	        
	     // tester la premiere personne
	     Assert.assertEquals("NOM1", personneImpl1.eGet(nom2));
	     Assert.assertEquals("PRENOM1", personneImpl1.eGet(prenom2));
	        
	     // recuperer la deuxieme personne
	     DynamicEObjectImpl personneImpl2 = (DynamicEObjectImpl)listPersonnes.get(1);
	     EClass personne = personneImpl2.eClass();
	     EAttribute nom3 = (EAttribute)personne.getEStructuralFeature("nom");
	     EAttribute prenom3 = (EAttribute)personne.getEStructuralFeature("prenom");
	        
	     // tester la deuxieme personne
	     Assert.assertEquals("NOM2", personneImpl2.eGet(nom3));
	     Assert.assertEquals("PRENOM2", personneImpl2.eGet(prenom3));
	        
	}
}
