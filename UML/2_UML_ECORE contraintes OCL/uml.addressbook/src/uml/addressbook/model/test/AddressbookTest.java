package uml.addressbook.model.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.Assert;
import org.junit.Test;
import addressbook.univrouen.fr.addressbook.AddressbookFactory;
import addressbook.univrouen.fr.addressbook.AdressBook;
import addressbook.univrouen.fr.addressbook.Adresse;
import addressbook.univrouen.fr.addressbook.Personne;

public class AddressbookTest {

	@Test
	public void createAddressbookTest() throws IOException{
		AdressBook carnet = AddressbookFactory.eINSTANCE.createAdressBook();
		carnet.setName("MonCarnet");
		
		// Création d'une adresse
		Adresse adr1 = AddressbookFactory.eINSTANCE.createAdresse();
		adr1.setNumero(7);
		adr1.setRue("Rue Pierre Guignois");
		
		Adresse adr2 = AddressbookFactory.eINSTANCE.createAdresse();
		adr2.setNumero(8);
		adr2.setRue("Boulvard Pierre Guignois");
		
		Adresse adr3 = AddressbookFactory.eINSTANCE.createAdresse();
		adr3.setNumero(9);
		adr3.setRue("Place Pierre Guignois");
		
		// Création d'une personne
		Personne per1 = AddressbookFactory.eINSTANCE.createPersonne();
		per1.setNom("PER");
		per1.setPrenom("SONNE1");
		per1.setAdresse(adr1);
		per1.setAge(25);
		
		Personne per2 = AddressbookFactory.eINSTANCE.createPersonne();
		per2.setNom("PER2");
		per2.setPrenom("SONNE2");
		per2.setAdresse(adr2);
		per2.setAge(20);
		
		Personne per3 = AddressbookFactory.eINSTANCE.createPersonne();
		per3.setNom("PER3");
		per3.setPrenom("SONNE3");
		per3.setAdresse(adr3);
		per3.setAge(30);
		
		
		// Ajout personne / adresse dans carnet
		carnet.getPersonne().add(per1);
		carnet.getPersonne().add(per2);
		carnet.getPersonne().add(per3);
		
		// Affichage du contenu de carnet - affichez les valeurs suivantes :
		assertEquals(carnet.getName(), "MonCarnet");
		assertEquals(carnet.getPersonne().size(), 3);
		
		//tests sur les personne
		assertEquals(carnet.getPersonne().get(0).display(), per1.display());
		assertEquals(carnet.getPersonne().get(0).getAdresse(), adr1);

		assertEquals(carnet.getPersonne().get(1).display(), per2.display());
		assertEquals(carnet.getPersonne().get(1).getAdresse(), adr2);

		assertEquals(carnet.getPersonne().get(2).display(), per3.display());
		assertEquals(carnet.getPersonne().get(2).getAdresse(), adr3);
		
		carnet.getName();
		carnet.getPersonne().size();
		carnet.getPersonne().get(0).display();
		carnet.getPersonne().get(0).getAdresse();
		
		//affiche le resultat sur console 
		System.out.println(carnet.getName());
		System.out.println(carnet.getPersonne().size());
		System.out.println(carnet.getPersonne().get(0).display());
		System.out.println(carnet.getPersonne().get(0).getAdresse());
		
		//Sauvegarde du carnet d'adresse dans un fichier XML qui va etre creé dans le dossier "fichier-xml-generer"
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("addressbook", new XMIResourceFactoryImpl());
	
		String path = System.getProperty("user.dir")+"/fichier-xml-generer/";
		
		URI uri = URI.createURI("file:/" + path+ "Carnet.addressbook");
		Resource resource = resourceSet.createResource(uri);
		resource.getContents().add(carnet);
		try {
            resource.save(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		//test creation de fichier
        Assert.assertTrue(new File(path + "Carnet.addressbook").exists());
        
        //Restauration du carnet d'adresse a partie du fichier xml generer avant qui se trouve dans resource
        AdressBook carnet2 = (AdressBook) resource.getContents().get(0);
        Assert.assertEquals(carnet.getName() , carnet2.getName());
        System.out.println("le nom du carnet restaurer = "+carnet2.getName());
	}
	
}
