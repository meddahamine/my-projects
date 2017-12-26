/**
 *
 * $Id$
 */
package addressbook.univrouen.fr.addressbook.validation;


/**
 * A sample validator interface for {@link addressbook.univrouen.fr.addressbook.Adresse}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface AdresseValidator {
	boolean validate();

	boolean validateNumero(int value);

	boolean validateNuméro(int value);
	boolean validateRue(String value);
}
