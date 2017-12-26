package univrouen.adressbook.latebinding;

import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.Test;

public class LateBindingTest {
	@Test(expected = NullPointerException.class)
	public void queryAddressbookStructureWithoutCode() {
	   Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
	   Map<String, Object> m = reg.getExtensionToFactoryMap();
	   m.put("ecore", new XMIResourceFactoryImpl());
	   ResourceSet resourceSet = new ResourceSetImpl();
	   URI fileURI = URI.createFileURI("model/addressbook.ecore");
	   Resource resource = resourceSet.getResource(fileURI, true);
	   EPackage ePackage = (EPackage) resource.getContents().get(0);
		//recuperer les attributs de la classe AddressbookPackage qui sont de type EClass et les mettre dans la Liste 'eClass'
		EList<EClassifier> eClass  = ePackage.getEClassifiers();
			for (EClassifier eClassifier : eClass) {
			    //afficher le nom de la classe
			    System.out.println(eClassifier.getName());
			    
			    if (eClassifier instanceof EClass) {
					EClass eClass1 = (EClass) eClassifier;
					//recuperer tous les attributs de la classe 'eClass1' et les mettre dans la Liste 'eAttributeList'
					EList<EAttribute> eAttributeList = eClass1.getEAttributes();
					//afficher tosu les attributs et leurs types.
					System.out.print("       ");
					for (EAttribute eAttribute : eAttributeList) {
						System.out.print(eAttribute.getName()+"("+eAttribute.getEAttributeType().getName()+")");
						System.out.print(",  ");
					}
					System.out.print("\n");
					//Question III.2 - Affichage du contenu
					//I.Les Reference
					if (!eClass1.getEAttributes().isEmpty() && !eClass1.getEReferences().isEmpty()) {
	                    System.out.print("       Références : ");
	    				//recuperer toutes les references de la classe 'eClass1' et les mettre dans la Liste 'eReferenceList'
	                    EList<EReference> eReferenceList = eClass1.getEReferences();
	                    //afficher la liste des references
	                    for (EReference eReference : eReferenceList) {
	                        System.out.println(eReference.getName() + "("
	                            + eReference.getEReferenceType().getName() + "["
	                            + eReference.getLowerBound() + ".."
	                            + eReference.getUpperBound() + "])");
	                    }
					}
					
					//I.Les Operations
					if (!eClass1.getEOperations().isEmpty()) {
	                    System.out.print("       Opérations : ");
	    				//recuperer toutes les references de la classe 'eClass1' et les mettre dans la Liste 'eOperationList'
	                    EList<EOperation> eOperationList = eClass1.getEOperations();
	                    //afficher la liste des operations
	                    for (EOperation eOperation : eOperationList) {
	                    	System.out.print(eOperation.getEType().getName()
	                                + " " + eOperation.getName());
	                    	System.out.print(",  ");
	                    }
	                    System.out.print("\n");
					}
			   }
			}
	}
}
