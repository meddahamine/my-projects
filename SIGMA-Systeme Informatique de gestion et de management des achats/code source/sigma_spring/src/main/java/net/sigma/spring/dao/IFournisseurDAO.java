package net.sigma.spring.dao;

import java.util.List;

import net.sigma.spring.model.Files;
import net.sigma.spring.model.Fournisseur;

/**
 * Defines DAO operations for the Fournisseur model.
 * @author www.sigma.net
 *
 */
public interface IFournisseurDAO {
	
	public void save(Fournisseur fournisseur);
	
	public void update(Fournisseur fournisseur);
	
	public void delete(int fournisseurId);
	
	public Fournisseur get(int fournisseurId);
	
	public List<Fournisseur> list();

	public void saveFile(Files file);

	public List<Files> listFiles();

	public void deleteFile(int fileId);

	public Files find(int fileId);
	
	public void evaluer(Fournisseur fournisseur);
}
