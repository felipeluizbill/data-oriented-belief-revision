package br.edu.utfpr.ppgca.dbr.layer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

import br.edu.utfpr.ppgca.dbr.entities.DBRBelief;
import br.edu.utfpr.ppgca.dbr.entities.DBRData;

public class BeliefLayer extends AbstractLayer<DBRBelief> {

	public Collection<DBRBelief> select(Collection<DBRData> data) {
		Collection<DBRBelief> selectedBeliefs = new HashSet<>();
		data.forEach(d -> {
			DBRBelief belief = new DBRBelief(d);
			this.active.add(belief);
			selectedBeliefs.add(belief);
		});
		return selectedBeliefs;
	}

	public Collection<DBRBelief> store(final Float THRESHOLD) {
		Collection<DBRBelief> storedBeliefs = new HashSet<>();

		this.active.forEach(b -> {
			if (b.getData().getRelevance().getValue() < THRESHOLD) {
				if (roulette(b)) {
					storedBeliefs.add(b);
				}
			}
		});
		this.active.removeAll(storedBeliefs);
		this.stored.addAll(storedBeliefs);

		return storedBeliefs;
	}

	public Collection<DBRBelief> retrieve(final Float THRESHOLD) {
		Collection<DBRBelief> retrievedBeliefs = new HashSet<>();
		this.stored.forEach(b -> {
			if (b.getData().getRelevance().getValue() > THRESHOLD) {
				if (roulette(b)) {
					retrievedBeliefs.add(b);
				}
			}
		});
		this.active.addAll(retrievedBeliefs);
		this.stored.removeAll(retrievedBeliefs);

		return retrievedBeliefs;
	}

	public void mapping() {

	}

	public void oblivion(final Float THRESHOLD) {
		Collection<DBRBelief> obliviatedBeliefs = new HashSet<>();
		this.stored.forEach(b -> {
			if (b.getData().getRelevance().getValue() < THRESHOLD) {
				if (roulette(b)) {
					obliviatedBeliefs.add(b);
				}
			}
		});
		this.stored.removeAll(obliviatedBeliefs);
	}

	private boolean roulette(final DBRBelief belief) {
		float random = (float) new Random().nextGaussian();
		if (random > belief.getData().getRelevance().getValue()) {
			return true;
		}
		return false;
	}

}
