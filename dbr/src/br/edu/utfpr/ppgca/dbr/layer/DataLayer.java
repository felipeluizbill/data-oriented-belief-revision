package br.edu.utfpr.ppgca.dbr.layer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import br.edu.utfpr.ppgca.dbr.entities.DBRData;

public class DataLayer extends AbstractLayer<DBRData> {

	public void informationUpdate(final DBRData data) {
		this.stored.add(data);
	}

	public void focus(final Float THRESHOLD) {
		Set<DBRData> focusedData = new HashSet<>();
		this.stored.stream().forEach(d -> {
			if (d.getRelevance().getValue() > THRESHOLD) {
				focusedData.add(d);
			}
		});
		this.active.addAll(focusedData);
		this.stored.removeAll(focusedData);
	}

	public Collection<DBRData> select(final Float THRESHOLD) {
		Collection<DBRData> selectedData = new HashSet<>();
		this.active.stream().forEach(d -> {
			if (d.getCredibility().getValue() * d.getImportance().getValue()
					* d.getLikeability().getValue() > THRESHOLD) {
				selectedData.add(d);
			}
		});
		this.active.removeAll(selectedData);

		return selectedData;
	}

	public void oblivion(final Float THRESHOLD) {
		Set<DBRData> obliviatedData = new HashSet<>();

		this.stored.stream().forEach(d -> {
			if (d.getRelevance().getValue() < THRESHOLD) {
				obliviatedData.add(d);
			}
		});

		stored.removeAll(obliviatedData);
	}
}
