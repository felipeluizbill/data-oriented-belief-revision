package br.edu.utfpr.ppgca.prs.knowledge;

import br.edu.utfpr.ppgca.prs.entities.AbstractEpistemicEntity;
import br.edu.utfpr.ppgca.prs.entities.Belief;

public abstract class AbstractRule<T extends AbstractEpistemicEntity> {

	protected final Belief condition;
	private final T consequent;

	public AbstractRule(final Belief condition, final T consequent) {
		this.condition = condition;
		this.consequent = consequent;
	}

	public T unify(final Belief belief) {
		if (!this.condition.getData().getContent().equals(belief.getData().getContent())) {
			return null;
		}
		belief.getData().updateLastActivation();
		return this.consequent;
	}

	public T getConsequent() {
		return this.consequent;
	}

	public Belief getCondition() {
		return this.condition;
	}

}
