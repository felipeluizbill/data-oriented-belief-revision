package br.edu.utfpr.ppgca.prs.entities;

public class Belief extends AbstractEpistemicEntity {

	protected Data data;
	protected Float strength = 1F;

	public Belief(Data data) {
		this.data = data;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public Float getStrength() {
		return strength;
	}

	public void setStrength(Float strength) {
		this.strength = strength;
	}

	@Override
	public String toString() {
		return "Belief [data=" + data + ", strength=" + strength + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
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
		Belief other = (Belief) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}

}
