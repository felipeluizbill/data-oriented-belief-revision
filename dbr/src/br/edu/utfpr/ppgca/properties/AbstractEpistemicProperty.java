package br.edu.utfpr.ppgca.properties;

import java.util.Collection;

import br.edu.utfpr.ppgca.dbr.entities.DBRBelief;
import br.edu.utfpr.ppgca.dbr.entities.DBRData;

public abstract class AbstractEpistemicProperty {

	protected Float value = 1F;

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	static void minMaxStandardization(Collection<DBRBelief> beliefs, final float MIN, final float MAX) {
		final float RANGE = MAX - MIN;
		for (DBRBelief belief : beliefs) {
			float z = (belief.getData().getRelevance().getValue() - MIN) / RANGE;
			belief.getData().getRelevance().setValue(z);
		}
	}

	static void minMaxStandardizationData(Collection<DBRData> datum, final float MIN, final float MAX) {
		final float RANGE = MAX - MIN;
		for (DBRData data : datum) {
			float z = (data.getRelevance().getValue() - MIN) / RANGE;
			data.getRelevance().setValue(z);
		}
	}

	/*
	 * default void zScoreStandardization(Collection<Belief> beliefs) { double mean
	 * = mean(beliefs); double deviation = deviation(beliefs, mean); for (Belief
	 * belief : beliefs) { double zScore = (double) (belief.getRelevance() - mean) /
	 * deviation; belief.setRelevance((float) zScore); }
	 * 
	 * }
	 * 
	 * default double deviation(Collection<Belief> beliefs, final double MEAN) {
	 * double squareSum = 0; for (Belief belief : beliefs) { squareSum +=
	 * Math.pow((belief.getRelevance() - MEAN), 2); } double splitN = squareSum /
	 * (double) beliefs.size(); return (double) Math.sqrt(splitN); }
	 * 
	 * default double mean(Collection<Belief> beliefs) { return sum(beliefs) /
	 * (double) beliefs.size(); }
	 * 
	 * default double sum(Collection<Belief> beliefs) { double sum = 0; for (Belief
	 * belief : beliefs) { sum += belief.getRelevance(); } return sum; }
	 */

}
