package projet;

public class Salle {	
	private String nomSalle;
	private int capacite;
	private double tarif;
	private int nbPlacesStandard;
	public Salle(String nomSalle, int capacite, double tarif, 
			int nbPlacesStandard){
		this.nomSalle=nomSalle;
		this.capacite=capacite;
		this.tarif=tarif;
		this.nbPlacesStandard=nbPlacesStandard;
	}
	public String toString() {
		return "Salle : "+nomSalle 
				+ "\n Capacite : " + capacite
				+ "\n Prix standard : " + tarif 
				+ "\n Prix reduit : " + tarif * 0.6
				+ "\n Nombre de places : " + nbPlacesStandard;
	}
	public String getNomSalle() {
		return nomSalle;
	}
	public void setNomSalle(String nomSalle) {
		this.nomSalle = nomSalle;
	}
	public int getCapacite() {
		return capacite;
	}
	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}
	public double getTarif() {
		return tarif;
	}
	public void setTarif(double tarif) {
		this.tarif = tarif;
	}
	public int getNbPlacesStandard() {
		return nbPlacesStandard;
	}
	public void setNbPlacesStandard(int nbPlacesStandard) {
		this.nbPlacesStandard = nbPlacesStandard;
	}

	
}
