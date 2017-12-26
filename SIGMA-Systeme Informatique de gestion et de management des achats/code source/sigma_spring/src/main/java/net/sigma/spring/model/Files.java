package net.sigma.spring.model;

public class Files {
    private int id;
    private String filename;
    private String notes;
    private String type;
    private byte[] file;
    private int id_fournisseur;
    
    
	public Files() {
		super();
	}
	public Files(int id, String filename, String notes, String type, byte[] file, int id_fournisseur) {
		super();
		this.id = id;
		this.filename = filename;
		this.notes = notes;
		this.type = type;
		this.file = file;
		this.id_fournisseur = id_fournisseur;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	public int getId_fournisseur() {
		return id_fournisseur;
	}
	public void setId_fournisseur(int id_fournisseur) {
		this.id_fournisseur = id_fournisseur;
	}
}