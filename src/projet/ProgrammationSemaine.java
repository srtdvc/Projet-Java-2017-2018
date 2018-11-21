package projet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ProgrammationSemaine {


	private int semaine;


	Map<PieceTheatre,ProgrammationTheatre> maptheatre;
	Map<Film,ProgrammationFilm> mapfilm;

	//hash map parsq l ordre n est pas important
	
	public ProgrammationSemaine(int semaine){
		this.semaine=semaine;
		maptheatre=new HashMap <PieceTheatre,ProgrammationTheatre> ();
		mapfilm=new HashMap <Film,ProgrammationFilm> ();
	}

	
	public void AjoutFilm(Film f,ProgrammationFilm prog){
		if(!mapfilm.containsKey(f)){
			mapfilm.put(f, prog);
		}
		else
			System.out.println("le film existe deja");
	}

	public void AjoutSeance(Film f,SeanceCinema s){
		ProgrammationFilm l=ListeSeanceFilms(f);
		l.ajouterSeance(s);
	}
	public void SupprimerSeanceFilm(Film f,SeanceCinema s){
		ListeSeanceFilms(f).supprimerSeance(s);
	}

	ProgrammationFilm ListeSeanceFilms(Film f){
		return  mapfilm.get(f);
	}

	Iterator <Film> IteratorFilms(){
		Set<Film> set=mapfilm.keySet();
		return set.iterator();
	}

	public Set<Film> EnsembleFilms(){
		Set<Film> EnsFilms=new HashSet<Film>();
		Iterator <Film> itr=IteratorFilms();
		while(itr.hasNext()) {
			Film f = itr.next();
			EnsFilms.add(f);
		}
		return EnsFilms;
	}
	//Rechercher avc date
	public Set <Film> FilmJourDonne(int j,Film f){
		Set<Film> EnsFilmsJour=new HashSet<Film>();
		Iterator <Film> itr=IteratorFilms();
		while(itr.hasNext()) {
			Film f2 = itr.next();
			if(f.equals(f2)){
				Iterator<SeanceCinema> itr2 = new ProgrammationFilm().iterator();
				while(itr2.hasNext()) {
					SeanceCinema s = itr2.next();
					if(s.getJour()==j){
						EnsFilmsJour.add(f);
					}
				}
				return EnsFilmsJour;
			}
		}
		System.out.println("ya pas de film pour le jour "+j);
		return null;
	}

	//Rechercher un film selon l'heure et la date
	public SeanceCinema FilmJourHoraireDonne(int j,Heure h,Film f){
		Iterator <Film> itr=IteratorFilms();
		while(itr.hasNext()) {
			Film f2 = itr.next();
			if(f.equals(f2)){
				Iterator<SeanceCinema> itr2 = mapfilm.get(f).iterator();
				while(itr2.hasNext()) {
					SeanceCinema s = itr2.next();
					if(s.getJour()==j){
						if(s.getHoraire().equals(h))
							return s;}
						else
							System.out.println("y a pas de film pour cet horaire");
					
				}
			}
		}
		System.out.println("le film chercher n est pas disponible");
		return null;
	}

	// pourcentage le taux de remplissage film
	public double TauxRemplissageFilm(Film f){
		int i=0;
		int k=0;
		ProgrammationFilm prog;
		prog=ListeSeanceFilms(f);
		Iterator<SeanceCinema> itr= prog.iterator();
		while(itr.hasNext()){
			SeanceCinema s = itr.next();
			i+=s.tauxRemplissage();
			k++;
		}
		return i/k;
	}

	//titres des films dans la Programmation
	public String toStringEnsTitreFilm(){
		String s="";
		Set<Film> LesFilms = mapfilm.keySet();
		Iterator<Film> itrfilm=LesFilms.iterator();
		while (itrfilm.hasNext()){
			Film unfilm = itrfilm.next();
			s += unfilm.getTitre() + "\n";
		}
		return s;
	}

	//Rechercher un film selon son titre
	public Film ChercherFilm (String nom){
		Iterator <Film> itr=IteratorFilms();
		while(itr.hasNext()) {
			Film f = itr.next();
			if(f.getTitre().equalsIgnoreCase(nom)){
				return f;
			}
		}
		return null;
	}

	//Chiffre d'affaire  film 
	public double ChiffredAffaireFilm(Film f){
		ProgrammationFilm prog=ListeSeanceFilms(f);
		return prog.chiffreAffaire();
	}



	public void AjoutPiece(PieceTheatre f,ProgrammationTheatre prog){
		maptheatre.put(f, prog);
	}

	public void AjoutSeance(PieceTheatre f,SeanceTheatre s){
		ProgrammationTheatre l=ListeSeancePieces(f);
		l.ajouterSeance(s);
	}
	public void SupprimerSeance(PieceTheatre f,SeanceTheatre s){
		ListeSeancePieces(f).supprimerSeance(s);
	}

	ProgrammationTheatre ListeSeancePieces(PieceTheatre f){
		return  maptheatre.get(f);
	}

	Iterator<PieceTheatre> IteratorPieces(){
		Set<PieceTheatre> set=maptheatre.keySet();
		return set.iterator();
	}

	public Set<PieceTheatre> EnsemblePieces(){
		Set<PieceTheatre> EnsPieces=new HashSet<PieceTheatre>();
		Iterator <PieceTheatre> itr=IteratorPieces();
		while(itr.hasNext()) {
			PieceTheatre f = itr.next();
			EnsPieces.add(f);
		}
		return EnsPieces;
	}

	//Rechercher une seance de theatre selon la date
	public SeanceTheatre PieceJourDonne(int j,PieceTheatre pt) {
		Iterator <PieceTheatre> itr=IteratorPieces();
		while(itr.hasNext()) {
			PieceTheatre f2 = itr.next();
			if(pt.equals(f2)){
				Iterator<SeanceTheatre> itr2 = maptheatre.get(pt).iterator();
				while(itr2.hasNext()) {
					SeanceTheatre s = itr2.next();
					
					if(s.getJour()==j){
						return s;
					}
				}
			}
		}
		System.out.println("Aucune piece de theatre n'est prévue ce jour");
		return null;
	}

	//****porcentage piece theatre 
	public double TauxRemplissagePiece(PieceTheatre pt){
		int i=0;
		int k=0;
		ProgrammationTheatre prog;
		prog=ListeSeancePieces(pt);
		Iterator<SeanceTheatre> itr= prog.iterator();
		while(itr.hasNext()){
			SeanceTheatre s = itr.next();
			i+=s.tauxRemplissage();
			k++;
		}
		return i/k;
	}

	// les titres des pieces dans la Programmation
	public String toStringEnsTitrePiece(){
		String s="";
		Set<PieceTheatre> LesPieces = maptheatre.keySet();
		Iterator<PieceTheatre> itrth=LesPieces.iterator();
		while (itrth.hasNext()){
			PieceTheatre unepiece = itrth.next();
			s += unepiece.getTitre() + "\n";
		}
		return s;
	}

	//Rechercher une piece de theatre selon son titre
	public PieceTheatre ChercherPieceTheatre(String nom){
		Iterator <PieceTheatre> itr=IteratorPieces();
		while(itr.hasNext()) {
			PieceTheatre f = itr.next();
			if(f.getTitre().equalsIgnoreCase(nom)){
				return f;
			}
		}
		System.out.println("La piece de theatre recherche n'est pas disponible");
		return null;
	}

	//Chiffre d'affaire  piece de theatre
	public double ChiffredAffairePiece(PieceTheatre pt){
		ProgrammationTheatre prog=ListeSeancePieces(pt);
		return prog.chiffreAffaire();
	}
	public int getSemaine() {
		return semaine;
	}

	public void setSemaine(int semaine) {
		this.semaine = semaine;
	}

//Affiche l'ensemble des spectacles programmé
	public String toString(){
		String s=" ";
		Set<Map.Entry<PieceTheatre,ProgrammationTheatre>> LesProgTheatre = maptheatre.entrySet();
		Set<Entry<Film, ProgrammationFilm>> LesProgFilm = mapfilm.entrySet();
		Iterator<Entry<PieceTheatre, ProgrammationTheatre>> itrtheatre=LesProgTheatre.iterator();
		Iterator<Entry<Film, ProgrammationFilm>> itrfilm=LesProgFilm.iterator();
		while (itrtheatre.hasNext()){
			Entry<PieceTheatre, ProgrammationTheatre>uneProgtheatre = itrtheatre.next();
			PieceTheatre p=uneProgtheatre.getKey();
			ProgrammationTheatre pt = uneProgtheatre.getValue();
			s += p + " " + pt + "\n";
		}
		while (itrfilm.hasNext()){
			Entry<Film, ProgrammationFilm>uneProgfilm = itrfilm.next();
			Film f=uneProgfilm.getKey();
			ProgrammationFilm pf = uneProgfilm.getValue();
			s += f + " " + pf + "\n";
		}
		return s;
	}




	public int hashCode() {
		final int prime = 31;
		int res = 1;
		res = prime * res + ((mapfilm == null) ? 0 : mapfilm.hashCode());
		res = prime * res + ((maptheatre == null) ? 0 : maptheatre.hashCode());
		return res;
	}
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		ProgrammationSemaine other = (ProgrammationSemaine) obj;
		if (mapfilm == null) {
			if (other.mapfilm != null)
				return false;
		} else if (!mapfilm.equals(other.mapfilm))
			return false;
		if (maptheatre == null) {
			if (other.maptheatre != null)
				return false;
		} else if (!maptheatre.equals(other.maptheatre))
			return false;
		return true;
	}
	
}