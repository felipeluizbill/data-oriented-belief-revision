package br.edu.utfpr.ppgca.dbr.layer;

import java.util.Collection;
import java.util.HashSet;

import br.edu.utfpr.ppgca.prs.entities.AbstractEpistemicEntity;

public abstract class AbstractLayer<T extends AbstractEpistemicEntity> {

	public Collection<T> active = new HashSet<>();
	public Collection<T> stored = new HashSet<>();

	public Collection<T> getAll() {
		Collection<T> all = new HashSet<>();
		all.addAll(active);
		all.addAll(stored);
		return all;
	}
}
