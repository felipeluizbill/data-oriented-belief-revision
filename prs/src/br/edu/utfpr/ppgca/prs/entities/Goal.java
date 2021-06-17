package br.edu.utfpr.ppgca.prs.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import br.edu.utfpr.ppgca.prs.knowledge.ProceduralRule;

public class Goal extends AbstractEpistemicEntity {

	public final String description;
	public Float utility;

	private GoalStatus status = GoalStatus.SLEEPING;

	protected Set<ProceduralRule> rules = new HashSet<ProceduralRule>();

	public Goal(final String description) {
		this.description = description;
	}

	public Goal(final String description, final Float utility) {
		this.description = description;
		this.utility = utility;
	}

	public void addRule(final ProceduralRule proceduralRule) {
		if (this.equals(proceduralRule.getConsequent()) && !this.rules.contains(proceduralRule)) {
			this.rules.add(proceduralRule);
		}
	}

	public void removeRule(final ProceduralRule proceduralRule) {
		if (this.rules.isEmpty() || !this.rules.contains(proceduralRule)) {
			return;
		}
		this.rules.remove(proceduralRule);
	}

	public Collection<ProceduralRule> getRules() {
		return this.rules;
	}

	public boolean doesItSupport(final Belief belief) {
		for (ProceduralRule r : rules) {
			if (r.unify(belief) != null) {
				return true;
			}
		}
		return false;
	}

	public GoalStatus getStatus() {
		return status;
	}

	public void setStatus(GoalStatus status) {
		this.status = status;
	}

	public Float getUtility() {
		return utility;
	}

	public void setUtility(Float utility) {
		this.utility = utility;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
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
		Goal other = (Goal) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}

}
