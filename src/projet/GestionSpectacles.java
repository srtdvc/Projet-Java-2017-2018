package projet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionSpectacles {
	
	static EnsemblesSallesTheatre sallesth = new EnsemblesSallesTheatre();
	static EnsemblesSalles salles = new EnsemblesSalles();	
	static List<ProgrammationSemaine> lesProgrammations=new ArrayList<ProgrammationSemaine>();
	static Scanner sc=new Scanner(System.in);
	
	static String nomSalle;
	static int capacite;
	static double tarif;
	static int nbPlacesStandard;
	static double prixFauteuil;
	static int nbFauteuils;
	


	
	
	//chargement des salle de theatre depuis le fichier salleCinema.scv
	public static void ChargementSalleCinema() throws NumberFormatException, IOException{
		File file = new File("SallesCinema.csv");
		BufferedReader bufferedReader = null;
		

			FileReader fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			
			String tabmot[] ;
		    String line;
		    Salle  salle;
		    boolean bool=false;
		    while((line=bufferedReader.readLine()) !=null ){
		    	
		    	System.out.println(line);
		    	if (bool){
		    		
		    		tabmot=line.split(";");
			    	 nomSalle=tabmot[0];
			    	 capacite=Integer.parseInt(tabmot[1]);
			    	 tarif=Double.parseDouble(tabmot[2].replaceAll(",","."));
			    	 nbPlacesStandard=capacite;
			    	
			    	
			 		salle=new Salle(nomSalle, capacite, tarif, nbPlacesStandard);
			 		
			 		System.out.println("Ces parametres vous conviennent-ils? \n Oui : o - Non : n");
			 		String s2=sc.nextLine();
			 		if(s2.equalsIgnoreCase("o")){
			 			salles.ajouterSalle(salle);
			 		}
		    	}
		    	bool=true;
		    
		
		    }
		
		bufferedReader.close();
		
		
	}
	
	
	

	//chargement des salle de theatre depuis le fichier salleTheatre.scv
	public static void ChargementSalleTheatre() throws Exception{
		File file = new File("SallesTheatre.csv");
		BufferedReader bufferedReader = null;
		

			FileReader fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			
			String tabmot[] ;
		    String line;
		    SalleTheatre  salletheatre;
		    boolean bool=false;
		    while((line=bufferedReader.readLine()) !=null ){
		    	
		    	System.out.println(line);
		    	if (bool){
		    		
		    		tabmot=line.split(";");
			    	 nomSalle=tabmot[0];
			    	 capacite=Integer.parseInt(tabmot[1]);
			    	 tarif=Double.parseDouble(tabmot[2].replaceAll(",","."));
			    	 nbFauteuils=Integer.parseInt(tabmot[3]);
			    	 prixFauteuil=Double.parseDouble(tabmot[4].replaceAll(",","."));
			    	 nbPlacesStandard=capacite-nbFauteuils;
			    	
			    	salletheatre=new SalleTheatre(nbFauteuils, prixFauteuil, nomSalle, capacite, tarif, nbPlacesStandard);
			    	System.out.println("Ces parametres vous conviennent-ils? \nOui : O - Non : N");
					String s1=sc.nextLine();
					if(s1.equalsIgnoreCase("o")){
						sallesth.ajouterSalleTheatre(salletheatre);
					}
		    	}
		    	bool=true;
		    
		
		    }
		
		bufferedReader.close();
		
	}
	public static void main(String[]args){
		try {
			ChargementSalle();
			Programmations();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	

		
public static void ChargementSalle()throws Exception{
	boolean bool=true;
	while (bool){			

System.out.println("(S)==>charger les salles de cinema ");
System.out.println("(T)==>charger les salles de theatre ");
System.out.println("(P)==>passer au programations");
String rep=sc.nextLine();
			if(rep.equalsIgnoreCase("S")){
				ChargementSalleCinema();
				}else if(rep.equalsIgnoreCase("T")){
         		ChargementSalleTheatre();

				}else if(rep.equalsIgnoreCase("P")){
					break;
				}
				else{
					System.out.println("Choix incorrect");
				}
			}
		}
	

		
	

	//Propose des actions sur les programamtions de la semaine
	
	public static void Programmations() throws Exception{
		while(true){
			System.out.println("#################< faites un choix >##################### ");
			System.out.println("- >>>Tapez c pour creer la programmation d'une semaine au choix \n" +
" >>>Tapez M pour modifier une programation \n" +
" >>>Tapez V pour vendre des places .\n" +
" >>>Tapez I pour avoir des informations sur les ventes\n" +
" >>>Tapez Q  menu\n");
			String choix=sc.nextLine();
			if(choix.equalsIgnoreCase("q")){ 
				ChargementSalle();
			}

			if(choix.equalsIgnoreCase("c")){ 
				//Creer une prog pour la semaine
				CreationProgSemaine();
			}

			if(choix.equalsIgnoreCase("m")){ 
				//Modifier une programmation
				ModificationProgSemaine();
			}

			if(choix.equalsIgnoreCase("i")){ 
				//Informations sur les seances et les spectacles
				InfosSpectacles();
			}

			if(choix.equalsIgnoreCase("v")){ //Vente de places
				VendreProgSemaine();
			}
		}
	}


	//Fournit des informations sur les seances et les spectacles
	//Appelée par ActionsSurProgramamtions
	public static void InfosSpectacles() {
		ProgrammationSemaine prog;
		while(true){
			
	System.out.println(">Pour quelle semaine voulez vous des informations?");
	int sem=sc.nextInt();
	if(sem>0 && sem<=lesProgrammations.size()){
		if(lesProgrammations.get(sem)!=null){//Vérifie si une programmation est affectée 
			prog=lesProgrammations.get(sem);
			break;
		}else{
			System.out.println("Semaine incorrect");
		}
	}else{
		System.out.println("Semaine incorrect");
	}
}
		while(true){
			System.out.println("Taper 1 >>>Info sur une seance de cinema "
					+"\nTaper 2 >>>Info sur une seance de theatre "
					+"\nTaper 3 >>>Info sur un film "
					+"\nTaper 4 >>>Info sur une piece de theatre");
			int choix=sc.nextInt();
			sc.nextLine();
			if(choix != 1 &&choix != 2 &&choix != 3 &&choix != 4 ){
				continue;
			}else{
				switch(choix){
				case 1:
					InfosSeanceFilm(prog);
					break;
				case 2:
					InfosSeanceTheatre(prog);
					break;
				case 3:
					InfosFilm(prog);
					break;
				case 4:
					InfosPiece(prog);
					break;
				}
				break;
			}
		}
	}

	//Fournit des informations sur les films
	//Appelée par InfosProgSemaine
	public static void InfosFilm(ProgrammationSemaine prog){
		System.out.println(prog.toStringEnsTitreFilm());
		Film film=null;
		while(film==null){
			System.out.println(">Titre du film?");
			String titref=sc.nextLine();
			film=prog.ChercherFilm(titref);
		}
		System.out.println(">Taux de remplissage : "+prog.TauxRemplissageFilm(film)+"%"
				+"\nChiffre d'affaire : "+prog.ChiffredAffaireFilm(film)+"é");
	}

	//Fournit des informations sur les pieces de theatre
	//Appelée par InfosProgSemaine
	public static void InfosPiece(ProgrammationSemaine prog){
		System.out.println(prog.toStringEnsTitrePiece());
		PieceTheatre piece=null;
		while(piece==null){
			System.out.println(">Titre de la piece?");
			String titrep=sc.nextLine();
			piece=prog.ChercherPieceTheatre(titrep);
		}
		System.out.println("Taux de remplissage : "+prog.TauxRemplissagePiece(piece)+"%"
				+"\nChiffre d'affaire : "+prog.ChiffredAffairePiece(piece)+"é");
	}

	//Fournit des informations sur les seances de film
	//Appelée par InfosProgSemaine
	public static void InfosSeanceFilm(ProgrammationSemaine prog){
		System.out.println(prog.toStringEnsTitreFilm());
		Film film=null;
		while(film==null){
			System.out.println(">Titre du film?");
			String titref=sc.nextLine();
			film=prog.ChercherFilm(titref);
		}
		System.out.println(prog.mapfilm.get(film).toStringEnsHoraireFilm());
		SeanceCinema seance=null;
		while(seance==null){
			System.out.println(">Jour de la seance?");
			int jourf=sc.nextInt();
			sc.nextLine();
			Heure horaire;
			while(true){
				System.out.println(">Horaire de la seance? (EX : 17h50)");
				String str = sc.nextLine();
				String[] strsplite; 
				strsplite = str.split("h");
				if(Integer.parseInt(strsplite[0])<25 && Integer.parseInt(strsplite[0])>=0 
						&& Integer.parseInt(strsplite[1])>=0 && Integer.parseInt(strsplite[1]) <60){	// 00h00 < Horaire < 23h59
					horaire=new Heure(Integer.parseInt(strsplite[1]),Integer.parseInt(strsplite[0]));
					break;
				}else{
					System.out.println("Horaire incorrect");
					continue;
				}
			}
			seance=prog.FilmJourHoraireDonne(jourf,horaire,film);
		}
		System.out.println(">Taux de remplissage :"+seance.tauxRemplissage()+"%"
				+"\nChiffre d'affaire pour l'ensemble des seances : "+prog.ChiffredAffaireFilm(film)+"é");
	}

	//Fournit des informations sur les seances de theatre
	//Appelée par InfosProgSemaine
	public static void InfosSeanceTheatre(ProgrammationSemaine prog){
		System.out.println(prog.toStringEnsTitrePiece());
		PieceTheatre piece=null;
		while(piece==null){
			System.out.println("Titre de la piece?");
			String titrep=sc.nextLine();
			piece=prog.ChercherPieceTheatre(titrep);
		}
		System.out.println(prog.maptheatre.get(piece).toStringEnsHorairePiece());
		SeanceTheatre seancet=null;
		while(seancet==null){
			System.out.println("Jour de la seance?");
			int jourt=sc.nextInt();
			sc.nextLine();
			seancet=prog.PieceJourDonne(jourt,piece);
		}
		System.out.println("Taux de remplissage :"+seancet.tauxRemplissage()+"%"
				+"\nChiffre d'affaire pour l'ensemble des seances : "+prog.ChiffredAffairePiece(piece)+"é");
	}




	//Vente de places
	//Appelée par Programmations
	public static void VendreProgSemaine() {
		System.out.println("Tapez f>>> pour vendre des places de cinema"
				+"\nTapez t>>> pour vendre des places de theatre"
				+"\nTapez q>>> pour quitter");
		String type=sc.nextLine();
		ProgrammationSemaine prog;
		while(true){
			
			System.out.println("Pour quelle semaine voulez vous vendre des places?");
			int sem=sc.nextInt();
			if(sem>0 && sem<=lesProgrammations.size()){
				if(lesProgrammations.get(sem)!=null){//Vérifie si une programmation est affectée a cet index
					prog=lesProgrammations.get(sem);
					break;
				}else{
					System.out.println("Semaine incorrect !");
				}
			}else{
				System.out.println("Semaine incorrect !");
			}
		}
		
			
			
			if(type.equalsIgnoreCase("f")){
				VendreFilm(prog);
			}
			if (type.equalsIgnoreCase("t")){
				VendreTheatre(prog);
			}
		}
	

	 //Vente de places de cinema
	//Appelée par VendreProgSemaine
	 static Film film=null;
	public static void VendreFilm(ProgrammationSemaine prog){
		

		System.out.println(prog.toStringEnsTitreFilm());
	
		
		
		while(film==null ){
			
			System.out.println(">Titre du film ?");
			sc.nextLine();
			String titref=sc.nextLine();
			film=prog.ChercherFilm(titref);
		}
		System.out.println(prog.mapfilm.get(film).toStringEnsHoraireFilm());
		SeanceCinema seance=null;
		while(seance==null){
			System.out.println(">Jour de la seance?");
			int jourf=sc.nextInt();
			sc.nextLine();
			Heure horaire;
			while(true){
				System.out.println(">Horaire de la seance? (EX : 17h50)");
				String str = sc.nextLine();
				String[] strsplite; 
				strsplite = str.split("h");

				if(Integer.parseInt(strsplite[0])<25 && Integer.parseInt(strsplite[0])>=0 
						&& Integer.parseInt(strsplite[1])>=0 && Integer.parseInt(strsplite[1]) <60){	// 00h00 < Horaire < 23h59
					horaire=new Heure(Integer.parseInt(strsplite[1]),Integer.parseInt(strsplite[0]));
					break;
				}else{
					System.out.println("Horaire incorrect !");
					continue;
				}
			}
			seance=prog.FilmJourHoraireDonne(jourf,horaire,film);
		}
		System.out.println("******Nombre de places disponibles==> "+seance.nbPlacesDispo());
		while (true){
			System.out.println("T>arif reduit? Oui : o  Non : n");
			String tnr=sc.nextLine();
			System.out.println(">Combien de places voulez vous vendre?");
			int nbplaces=sc.nextInt();
			sc.nextLine();
			if(nbplaces>0 && seance.nbPlacesDispo()>=nbplaces){
				if(tnr.equalsIgnoreCase("o")){
					seance.vendrePlacesTR(nbplaces);
					System.out.println(">place vendu !");
					System.out.println(">Nombre de places disponibles==> "+seance.nbPlacesDispo());
				}
				if(tnr.equalsIgnoreCase("n")){
					seance.vendrePlacesTN(nbplaces);
				System.out.println(">place(s) vendu !");
				System.out.println(">Nombre de places disponibles==> "+seance.nbPlacesDispo());}
				
			}else {
				System.out.println(">impossible d effectuer la reservation !");
			}
			System.out.println(">voulez vous vendre d autre place o/n ?");
			String rep=sc.nextLine();
			if(rep.equalsIgnoreCase("n")){
				break;
				
			}
				
		}
		
	}

	//Vente de places de theatre
	//Appelée par VendreProgSemaine
	public static void VendreTheatre(ProgrammationSemaine prog){
		System.out.println(prog.toStringEnsTitrePiece());
		PieceTheatre piece=null;
		while(piece==null){
			System.out.println(">Titre de la piece?");
			
			String titrep=sc.nextLine();
			sc.nextLine();
			piece=prog.ChercherPieceTheatre(titrep);
		}
		System.out.println(prog.maptheatre.get(piece).toStringEnsHorairePiece());
		SeanceTheatre seance=null;
			while(seance==null){
				System.out.println(">Jour de la seance?");
				int jourt=sc.nextInt();
				sc.nextLine();
				seance=prog.PieceJourDonne(jourt,piece);
				System.out.println(seance.toString());
			}
			System.out.println(">Nombre de places disponibles :"+seance.nbPlacesDispo());
			while (true){
				System.out.println(">Places avec fauteuils? Oui : o  Non : n");
				String tnr=sc.nextLine();
				System.out.println(">Combien de places voulez vous vendre?");
				int nbplaces=sc.nextInt();
				sc.nextLine();
				if(nbplaces>0 && seance.nbPlacesDispo()>=nbplaces){
					if(tnr.equalsIgnoreCase("o")){
						seance.vendrePlacesFauteuil(nbplaces);
						System.out.println(">Nombre de places disponibles :"+seance.nbPlacesDispo());}
					if(tnr.equalsIgnoreCase("n")){
						seance.vendrePlacesTN(nbplaces);
					System.out.println(">Nombre de places disponibles :"+seance.nbPlacesDispo());
					}
					
				}else{
					System.out.println(">impossible d effectuer la vente !");
				}
				System.out.println(">voulez vous vendre d autre place o/n ?");
				String rep=sc.nextLine();
				if(rep.equalsIgnoreCase("n")){
					break;
					
				}
				
			}
		}
	
	
	
	
		//Modifications des programmations (Suppression et Ajout de seances)
		//Appelée par Programmations
		public static void ModificationProgSemaine() {
			ProgrammationSemaine modprog;
			while(true){
				//Evite l'erreur IndexOutOfBoundsException
				System.out.println("Quelle semaine voulez vous modifier?");
				int sem=sc.nextInt();
				if(sem>0 && sem<=lesProgrammations.size()){
					if(lesProgrammations.get(sem)!=null){//Vérifie si une programmation est affectée a cet index
						modprog=lesProgrammations.get(sem);
						break;
					}else{
						System.out.println("Semaine incorrect");
					}
				}else{
					System.out.println("Semaine incorrect");
				}
			}
			System.out.println(modprog);//Appelle la toString() de ProgrammationSemaine
			while(true){
				System.out.println("Taper 1>>> Ajouter des seances a un film "+
						"\nTaper 2>>>Supprimer des seances d'un film : 2"+
						"\nTaper 3>>>Ajouter des seances a une piece de theatre"+
						"\nTaper 4>>>Supprimer des seances d'une piece de theatre");
				int choix = sc.nextInt();
				sc.nextLine();
				if (choix==1 || choix==2 || choix==3 || choix==4){
					switch (choix) {
					case 1 :
						AjoutSeanceFilm(modprog);
						break;
	
					case 2 :
						SupprSeanceFilm(modprog);
						break;
	
					case 3 :
						AjoutSeanceTheatre(modprog);
						break;
	
					case 4 :
						SupprSeanceTheatre(modprog);
						break;
				}
			}else{
				System.out.println("\nChoix incorrect\n");
				continue;
			}
			break;
		}
	}

	//Permet d'ajouter une seance de film
	//Appelée par ModificationProgSemaine
	public static void AjoutSeanceFilm(ProgrammationSemaine modprog) {
		Film film=null;
		while(film==null){
			System.out.println(">Quel est le titre du film auquel vous voulez ajouter une seance?");
			String titref=sc.nextLine();
			film=modprog.ChercherFilm(titref);
		}
		System.out.println("#####<|AJOUT DE SEANCES|>#####");
		SeanceCinema seance;
		while(true){
			while(true){
				Salle salle=null;
				while(salle==null){
					System.out.println(">Nom de la salle?");
					String strsalle=sc.nextLine();
					salle=salles.RechercherSalle(strsalle);
				}
				System.out.println(">Jour? (de 1 é 7)");
				int jour = sc.nextInt();
				sc.nextLine();
				Heure horaire;
				while(true){
					System.out.println(">Horaire du film? (EX: 17h45)");//On split la durée pour creer un objet de type Heure
					String str1 = sc.nextLine();
					String[] str1splite; 
					str1splite = str1.split("h");

					if(Integer.parseInt(str1splite[0])<25 && Integer.parseInt(str1splite[0])>=0 
							&& Integer.parseInt(str1splite[1])>=0 && Integer.parseInt(str1splite[1]) <60){	// 00h00 < Horaire < 23h59
						horaire=new Heure(Integer.parseInt(str1splite[1]),Integer.parseInt(str1splite[0]));
						break;
					}else{
						System.out.println(">Horaire incorrect !");
						continue;
					}
				}
				if(jour<=7 && jour>0 && salle!=null){
				
					seance=new SeanceCinema(jour, 0, horaire, salle, 0);
					break;
				}
				else
					System.out.println(">Vérifiez les parametres");
			}
			System.out.println(film);
			System.out.println("\n"+seance);
			System.out.println(">Ces parametres vous conviennent-ils? \n Oui : o - Non : n");
			String verif=sc.nextLine();
			if(verif.equalsIgnoreCase("o")){
				modprog.AjoutSeance(film, seance);
			}
			System.out.println(">Voulez-vous ajouter d'autres seances? \n Oui : o - Non : n ");
			String aj=sc.nextLine();
			if(aj.equalsIgnoreCase("n")){
				break;
			}
		}
	}

	//Permet de supprimer une seance de film
	//Appelee par ModificationProgSemaine
	public static void SupprSeanceFilm(ProgrammationSemaine modprog) {

		Film film=null;
		while(film==null){
			System.out.println(">Quel est le titre de la piece pour laquelle vous voulez supprimer une seance?");
			String titref=sc.nextLine();
			film=modprog.ChercherFilm(titref);
		}
		SeanceCinema seancef = null;
		while(seancef==null){
			System.out.println(modprog.mapfilm.get(film));
			System.out.println(">Jour de la seance é supprimer?");
			int jour=sc.nextInt();
			Heure horaire;

			while(true){
				System.out.println(">Horaire de la seance é supprimer? (EX : 17h50)");
				String str = sc.nextLine();
				String[] strsplite; 
				strsplite = str.split("h");
				if(Integer.parseInt(strsplite[0])<25 && Integer.parseInt(strsplite[0])>=0 
						&& Integer.parseInt(strsplite[1])>=0 && Integer.parseInt(strsplite[1]) <60){	// 00h00 < Horaire < 23h59
					horaire=new Heure(Integer.parseInt(strsplite[1]),Integer.parseInt(strsplite[0]));
					break;
				}else{
					System.out.println("Horaire incorrect");
					continue;
				}
			}
			seancef=modprog.FilmJourHoraireDonne(jour,horaire,film);	//ProgSem --> Map<Film,Progf> --> Progf --> Seancef 
		}
		modprog.SupprimerSeanceFilm(film, seancef);
	}

	//Permet d'ajouter une seance de theatre
	//Appele par ModificationProgSemaine
	public static void AjoutSeanceTheatre(ProgrammationSemaine modprog) {

		PieceTheatre piece=null;
		while(piece==null){
			System.out.println(">Quel est le titre de la piece pour laquelle vous voulez ajouter une seance?");
			String titrep=sc.nextLine();
			piece=modprog.ChercherPieceTheatre(titrep);
		}
		System.out.println("AJOUT DE SEANCES");
		SeanceTheatre seancetheatre;
		while(true){
			while(true){
				SalleTheatre salletheatre = null;
				while(salletheatre==null){
					System.out.println("Nom de la salle?");
					String strsalletheatre=sc.nextLine();
					salletheatre=sallesth.RechercherSalleTheatre(strsalletheatre);
				}
				System.out.println(">Jour? (de 1 a 7)");
				int jourtheatre = sc.nextInt();
				sc.nextLine();
				Heure horairepiece;
				while(true){
					System.out.println(">Horaire de la seance?(EX: 17h30)");//On split la dure pour creer un objet de type Heure
					String str3 = sc.nextLine();
					String[] str3splite; 
					str3splite = str3.split("h");

					if(Integer.parseInt(str3splite[0])<25 && Integer.parseInt(str3splite[0])>=0 
							&& Integer.parseInt(str3splite[1])>=0 && Integer.parseInt(str3splite[1]) <60){	// 00h00 <= Horaire <= 23h59
						horairepiece=new Heure(Integer.parseInt(str3splite[1]),Integer.parseInt(str3splite[0]));
						break;
					}else{
						System.out.println("Horaire incorrect");
						continue;
					}
				}
				if(jourtheatre<=7 && jourtheatre>=1){ // 0 < Jour < 8
					seancetheatre=new SeanceTheatre(jourtheatre, 0, horairepiece, salletheatre, 0);
					break;
				}
				else
					System.out.println("Vérifiez les parametres !");
			}
			System.out.println("\n"+seancetheatre);
			System.out.println("Ces parametres vous conviennent-ils? \n Oui : o - Non : n");
			String veriftheatre=sc.nextLine();
			if(veriftheatre.equalsIgnoreCase("o")){
				modprog.AjoutSeance(piece, seancetheatre);
			}
			System.out.println("Voulez-vous ajouter d'autres seances? \n Oui : o - Non : n ");
			String aj=sc.nextLine();
			if(aj.equalsIgnoreCase("n")){
				break;
			}
		}
	}

	//Permet de supprimer une seance de theatre
	//Appelée par ModificationProgSemaine
	public static void SupprSeanceTheatre(ProgrammationSemaine modprog) {
		PieceTheatre piece=null;
		while(piece==null){
			System.out.println(">Quel est le titre de la piece pour laquelle vous voulez supprimer une seance?");
			String titrep=sc.nextLine();
			piece=modprog.ChercherPieceTheatre(titrep);
		}
		SeanceTheatre seanceth = null;
		while(seanceth==null){
			System.out.println(">Jour de la seance a supprimer?");
			int jour=sc.nextInt();
			seanceth=modprog.PieceJourDonne(jour,piece);//ProgSem --> Map<Piece,Progth> --> Progth --> Seanceth 
		}
		modprog.SupprimerSeance(piece, seanceth);
	}




	//Creer une programmation de la semaine.
	
	public static void CreationProgSemaine() {
		int semaine;
		while (true){
			System.out.println(">Pour quelle semaine voulez-vous creer une programmation?");
			int semainetest=sc.nextInt();
			if(semainetest>0){
				semaine=semainetest;
				break;
			}
			else System.out.println("Numero de semaine incorrect");
		}

		ProgrammationSemaine ProgSemaine=new ProgrammationSemaine(semaine);
		sc.nextLine();
		while (true){//Creation film
			System.out.println("- Tapez f >>> pour creer un film");
			System.out.println("- Tapez t >>> pour creer une piece de theatre");
			System.out.println("- Tapez q >>> pour quitter");
			String type=sc.nextLine();
			if (type.equalsIgnoreCase("q")){
				break;
			}
			if (type.equalsIgnoreCase("f")){
				CreationProgSemaineFilm(ProgSemaine);
			}
			if (type.equalsIgnoreCase("t")){
				CreationProgSemaineTheatre(ProgSemaine);
			}
		}
	}

	//Creer une programmation de la semaine pour le theatre.
	//Appele par CreationProgSemaine
	public static void CreationProgSemaineTheatre(ProgrammationSemaine ProgSemaine) {
		if(ProgSemaine.getSemaine()<=lesProgrammations.size()){
			if(lesProgrammations.get(ProgSemaine.getSemaine())!=null){//Verifie si une programmation est deja affectée a cet index
				System.out.println("Une programmation a deja été cree pour cette semaine !");
				return;
			}
		}
		ProgrammationTheatre progtheatre=new ProgrammationTheatre();
		System.out.println("#####<[CREATION DE LA PIECE]>#####");
		System.out.println(">Metteur en scéne?");
		String metteurensc = sc.nextLine();
		int nbentractes;
		while (true){
			System.out.println(">Nombre d'entractes?");
			int nbentractestest=sc.nextInt();
			if(nbentractestest>0){
				nbentractes=nbentractestest;
				break;
			}
			else System.out.println(">Nombre d'entractes incorrect");
		}
		sc.nextLine();
		System.out.println(">Interpretes?");
		String comediens=sc.nextLine();
		System.out.println(">Titre de la piece?");
		String titrepiece=sc.nextLine();
		PieceTheatre piece=new PieceTheatre(metteurensc,nbentractes,comediens,titrepiece);
		System.out.println(piece);
		System.out.println("#####<[AJOUT DE SEANCES]>#####");
		SeanceTheatre seancetheatre;
		while(true){
			while(true){
				SalleTheatre salletheatre = null;
				while(salletheatre==null){
					System.out.println(">Nom de la salle?");
					String strsalletheatre=sc.nextLine();
					salletheatre=sallesth.RechercherSalleTheatre(strsalletheatre);
				}
				System.out.println(">Jour? (de 1 a 7)");
				int jourtheatre = sc.nextInt();
				sc.nextLine();
				Heure horairepiece;
				while(true){
					System.out.println(">Horaire de la seance?(EX: 17h30)");//On split la durée pour creer un objet de type Heure
					String str3 = sc.nextLine();
					String[] str3splite; 
					str3splite = str3.split("h");
					if(Integer.parseInt(str3splite[0])<25 && Integer.parseInt(str3splite[0])>=0 
							&& Integer.parseInt(str3splite[1])>=0 && Integer.parseInt(str3splite[1]) <60){	// 00h00 < Horaire < 23h59
						horairepiece=new Heure(Integer.parseInt(str3splite[1]),Integer.parseInt(str3splite[0]));
						break;
					}else{
						System.out.println("Horaire incorrect");
						continue;
					}
				}
				if(jourtheatre<=7 && jourtheatre>=1 && salletheatre!=null){
					seancetheatre=new SeanceTheatre(jourtheatre, 0, horairepiece, salletheatre, 0);
					break;
				}
				else
					System.out.println(">Vérifiez les parametres");
			}
			System.out.println(piece);
			System.out.println("\n"+seancetheatre);
			System.out.println(">Ces parametres vous conviennent-ils? \n Oui : o - Non : n");
			String veriftheatre=sc.nextLine();
			if(veriftheatre.equalsIgnoreCase("o")){
				progtheatre.ajouterSeance(seancetheatre);
			}
			System.out.println(">Voulez-vous ajouter d'autres seances? \n Oui : o - Non : n ");
			String aj=sc.nextLine();
			if(aj.equalsIgnoreCase("n")){
				break;
			}
		}
		ProgSemaine.AjoutPiece(piece, progtheatre);
		
		if(lesProgrammations.size()<ProgSemaine.getSemaine()){ 
			for(int i = lesProgrammations.size();i<=ProgSemaine.getSemaine();i++){
				lesProgrammations.add(null);
			}
			lesProgrammations.add(ProgSemaine.getSemaine(),ProgSemaine);
		}
		else{
			if(lesProgrammations.get(ProgSemaine.getSemaine())==null){
				lesProgrammations.set(ProgSemaine.getSemaine(),ProgSemaine);
			}
		}
	}

	//Creer une programmation de la semaine pour le cinema.
	//Appel par CreationProgSemaine
	public static void CreationProgSemaineFilm(ProgrammationSemaine ProgSemaine) {
		if(ProgSemaine.getSemaine()<=lesProgrammations.size()){
			if(lesProgrammations.get(ProgSemaine.getSemaine())!=null){
				System.out.println("Une programmation a deja été créée pour cette semaine");
				return;
			}
		}
		ProgrammationFilm progfilm=new ProgrammationFilm();
		System.out.println("#######<[CREATION DU FILM]>########");
		System.out.println("Realisateur?");
		String real = sc.nextLine();
		Heure duree;
		while(true){
			System.out.println(">Durée du film? (EX: 1h40)"); 
			String str = sc.nextLine();
			String[] strsplite; 
			strsplite = str.split("h");
			if(Integer.parseInt(strsplite[0])<25 && Integer.parseInt(strsplite[0])>=0 
					&& Integer.parseInt(strsplite[1])>=0 && Integer.parseInt(strsplite[1]) <60){	
				duree=new Heure(Integer.parseInt(strsplite[1]),Integer.parseInt(strsplite[0]));
				break;
			}else{
				System.out.println("Horaire incorrect");
				continue;
			}
		}
		System.out.println(">Interpretes?");
		String interpretes=sc.nextLine();
		System.out.println(">Titre du film?");
		String titre=sc.nextLine();
		Film film=new Film(real,duree,interpretes,titre);
		System.out.println(film);
		System.out.println("####<[AJOUT DE SEANCES]>####");
		SeanceCinema seance;
		while(true){
			while(true){
				Salle salle=null;
				while(salle==null){
					System.out.println(">Nom de la salle?");
					String strsalle=sc.nextLine();
					salle=salles.RechercherSalle(strsalle);
				}
				System.out.println(">Jour ? (1-7)");
				int jour = sc.nextInt();
				sc.nextLine();
				Heure horaire;
				while(true){
					System.out.println(">heure du film? (EX: 2h45)");//On split la durée pour creer un objet de type Heure
					String str1 = sc.nextLine();
					String[] str1splite; 
					str1splite = str1.split("h");
					if(Integer.parseInt(str1splite[0])<25 && Integer.parseInt(str1splite[0])>=0 
							&& Integer.parseInt(str1splite[1])>=0 && Integer.parseInt(str1splite[1]) <60){	// 00h00 < Horaire < 23h59
						horaire=new Heure(Integer.parseInt(str1splite[1]),Integer.parseInt(str1splite[0]));
						break;
					}else{
						System.out.println("Horaire incorrect");
						continue;
					}
				}
				if(jour<=7 && jour>0 && salle!=null){
					seance=new SeanceCinema(jour, 0, horaire, salle, 0);
					break;
				}
				else
					System.out.println(">Vérifiez les parametres !");
			}
			System.out.println(film);
			System.out.println("\n"+seance);
			System.out.println(">Ces parametres vous conviennent-ils? \n Oui : o - Non : n");
			String verif=sc.nextLine();
			if(verif.equalsIgnoreCase("o")){
				progfilm.ajouterSeance(seance);
			}
			System.out.println(">Voulez-vous ajouter d'autres seances? \n Oui : o - Non : n ");
			String aj=sc.nextLine();
			if(aj.equalsIgnoreCase("n")){
				break;
			}
		}
		ProgSemaine.AjoutFilm(film, progfilm);
	
		if(lesProgrammations.size()<ProgSemaine.getSemaine()){
			for(int i = lesProgrammations.size();i<=ProgSemaine.getSemaine();i++){
				lesProgrammations.add(null);
			}
			lesProgrammations.add(ProgSemaine.getSemaine(),ProgSemaine);
		}
		else{
			if(lesProgrammations.get(ProgSemaine.getSemaine())==null){
				lesProgrammations.set(ProgSemaine.getSemaine(),ProgSemaine);
			}
		}
	}

}