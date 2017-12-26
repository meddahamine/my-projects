package EmploiDuTemps;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
/**
 * classe CaseHoraire.
 */
public class CaseHoraire {
	public LocalTime heureDebut;
	public LocalTime heureFin;
	public String caseHoraireStr;
	
	
	public LocalTime getheureDebut() {
		return heureDebut;
	}
	public LocalTime getheureFin() {
		return heureFin;
	}
	
	public String getChamps(){
		return heureDebut.toString()+" "+ heureFin.toString();
	}
	public String getNom(){
		return caseHoraireStr;
	}
	
	
	public CaseHoraire(LocalTime heureDebut, LocalTime heureFin) {
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
	
	}
	public CaseHoraire() {
		
	}
	public CaseHoraire(String str1) {
		this.caseHoraireStr= str1;
	}
	/**
	 * methode creerCaseHoraire() creer de nouvelle case horaire. 
	 * @param un LocalTime.
	 * @param un LocalTime.
	 * @return une Case Horaire.
	 */
	public static CaseHoraire creerCaseHoraire(LocalTime heureDebut, LocalTime heureFin){
		CaseHoraire caseH;
		String c= heureDebut.toString()+" "+heureFin.toString();
		caseH = new CaseHoraire(c);
		return caseH;
		
	}
	/**
	 * methode afficheCase() affiche les détail de la case horaire. 
	 * @param une Case Horaire.
	 */
	public static void afficheCase(CaseHoraire case2){
		
		System.out.println(case2.getNom());
	}
	
	
	
	
}
