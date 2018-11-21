package projet;

public class PieceTheatre extends Spectacle {
	private String metteurEnScene;
	private int nbEntractes;

	public PieceTheatre(String metteurEnScene, int nbEntractes,String interpretes, String titre) {
		super(interpretes, titre);
		this.metteurEnScene=metteurEnScene;
		this.nbEntractes=nbEntractes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((metteurEnScene == null) ? 0 : metteurEnScene.hashCode());
		result = prime * result + nbEntractes;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PieceTheatre other = (PieceTheatre) obj;
		if (metteurEnScene == null) {
			if (other.metteurEnScene != null)
				return false;
		} else if (!metteurEnScene.equals(other.metteurEnScene))
			return false;
		if (nbEntractes != other.nbEntractes)
			return false;
		return true;
	}

	public int getNbEntractes() {
		return nbEntractes;
	}

	public void setNbEntractes(int nbEntractes) {
		this.nbEntractes = nbEntractes;
	}

	public String getMetteurEnScene() {
		return metteurEnScene;
	}

	public void setMetteurEnScene(String metteurEnScene) {
		this.metteurEnScene = metteurEnScene;
	}
	public String toString() {
		return super.toString()+"\nMetteur en scene>> :" + metteurEnScene + "\n Nombre d'entractes>>> :" + nbEntractes;
	}

}
