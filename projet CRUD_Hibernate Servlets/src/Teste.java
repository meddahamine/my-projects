import java.util.List;

import dao.AlbumDaoImpl;
import dao.ArtisteDaoImpl;
import dao.TitreMusicalDaoImpl;
import dao.TypeSupportDaoImpl;
import model.Album;
import model.Artiste;
import model.TitreMusical;
import model.TypeSupport;

public class Teste {
	
	public static void main(String[] args) {
		
		
		TypeSupportDaoImpl ts=new TypeSupportDaoImpl();
		List<TypeSupport> wts = ts.list();
		
		TitreMusicalDaoImpl tm=new TitreMusicalDaoImpl();
		List<TitreMusical> wtm = tm.list();
		TitreMusical t = new TitreMusical("x", 1, 1);
		tm.save(t);
		ArtisteDaoImpl ar=new ArtisteDaoImpl();
		List<Artiste> war = ar.list();
		
		AlbumDaoImpl al=new AlbumDaoImpl();
		List<Album> w = al.list();
		
//		for (Album a:w) {
//			System.out.println(a.getNom());
//		}
//		al.findById(5);
//		Album a = al.findById(5); 
//		System.out.println(a.getNom());
//		a.setNom("test Album");
//		al.update(a);
	}
}

