package projet;

public class Heure {
	private int minutes;
	private int heures;
	public Heure (int minutes, int heures){
			this.heures = heures;
			this.minutes = minutes;
	}
	public int getMinutes() {
		return minutes;
	}
	public int getHeures() {
		return heures;
	}

	// compareTo()  les horraire 
	public int compareTo(Heure horaire) {
		if(this.heures < horaire.heures){
			return -1;
		}
		else{
			if(this.heures > horaire.heures){
				return 1;
			}
			else{
				if(this.minutes < horaire.minutes){
					return -1;
				}
				else{
					if(this.minutes > horaire.minutes){
						return 1;
					}
				}
			}
		}
		return 0;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + heures;
		result = prime * result + minutes;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Heure other = (Heure) obj;
		if (heures != other.heures)
			return false;
		if (minutes != other.minutes)
			return false;
		return true;
	}
	
	public String toString(){
		return heures+"h"+minutes;
	}
}
