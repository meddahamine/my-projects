/**
 */
package addressbook.univrouen.fr.addressbook;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Adress Book</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link addressbook.univrouen.fr.addressbook.AdressBook#getName <em>Name</em>}</li>
 *   <li>{@link addressbook.univrouen.fr.addressbook.AdressBook#getPersonne <em>Personne</em>}</li>
 * </ul>
 *
 * @see addressbook.univrouen.fr.addressbook.AddressbookPackage#getAdressBook()
 * @model
 * @generated
 */
public interface AdressBook extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see addressbook.univrouen.fr.addressbook.AddressbookPackage#getAdressBook_Name()
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented addressbook.univrouen.fr!AdressBook!name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link addressbook.univrouen.fr.addressbook.AdressBook#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Personne</b></em>' containment reference list.
	 * The list contents are of type {@link addressbook.univrouen.fr.addressbook.Personne}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Personne</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Personne</em>' containment reference list.
	 * @see addressbook.univrouen.fr.addressbook.AddressbookPackage#getAdressBook_Personne()
	 * @model containment="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel get='throw new UnsupportedOperationException();  // FIXME Unimplemented addressbook.univrouen.fr!AdressBook!personne'"
	 * @generated
	 */
	EList<Personne> getPersonne();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='throw new UnsupportedOperationException();  // FIXME Unimplemented addressbook.univrouen.fr!AdressBook!addContact()'"
	 * @generated
	 */
	boolean addContact();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/GenModel body='throw new UnsupportedOperationException();  // FIXME Unimplemented addressbook.univrouen.fr!AdressBook!removeContact()'"
	 * @generated
	 */
	void removeContact();

} // AdressBook
