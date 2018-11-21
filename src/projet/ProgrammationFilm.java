package projet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
public class ProgrammationFilm {
	private List <SeanceCinema> l;
	
	public ProgrammationFilm(){
		l=new ArrayList<SeanceCinema>();
	}

	void ajouterSeance(int index, SeanceCinema seance){
		l.add(index,seance);
	}
	void ajouterSeance(SeanceCinema seance){
		l.add(seance);
	}


	public void supprimerSeance(SeanceCinema seance){
		l.remove(seance);
	}

	//Chercher seance avec horrairre et jour
	public SeanceCinema chercher (int jour, Heure horaire){
		Iterator<SeanceCinema> it = l.iterator();
		while(it.hasNext()) {
			SeanceCinema seance = it.next();
			if(seance.getJour()==jour && seance.getHoraire()==horaire){
					return seance;
			}
		}
		System.out.println("Aucune seance ne correspond a ces horaires");
		return null;
	}

	// taux de remplissage en pourcentage pour la seance
	public double tauxRemplissage(int jour,	Heure h){
		return chercher(jour,h).tauxRemplissage();
	}

	// chiffre d'affaire du film en fonction du nombre total
	//de places vendues pour toutes les seances
	public double chiffreAffaire(){
		Iterator<SeanceCinema> it = l.iterator();
		double i=0;
		while(it.hasNext()) {
			SeanceCinema seance = it.next();
			i += ((seance.getNbPlacesVenduesTN()+0.6*seance.getNbPlacesVenduesTR()) * seance.getSalle().getTarif());
		}
		return i;
	}
	public void tripardate (){
		Collections.sort(l, new Comparator<SeanceCinema>() {
			public int compare(SeanceCinema s1, SeanceCinema s2) {
				return s1.compareTo(s2);
			}
		});
	}
	
	public Iterator<SeanceCinema> iterator() {
		Iterator<SeanceCinema> it=l.iterator();
		return it;
	}

	//Tri de la liste par jour horaire
	

	public String toStringEnsHoraireFilm(){
		Iterator<SeanceCinema> it = l.iterator();
		String s="";
		while(it.hasNext()) {
			SeanceCinema seance = it.next();
			s+="Jour :"+seance.getJour()+"\nHoraire :"+seance.getHoraire() + "\n";
		}
		return s;
	}
	
	public String toString(){
		Iterator<SeanceCinema> it = l.iterator();
		String s="";
		while(it.hasNext()) {
			SeanceCinema seance = it.next();
			s+=seance.toString() + "\n"+ "\n";
		}
		return s;
	}

}