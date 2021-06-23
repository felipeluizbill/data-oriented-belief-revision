package br.edu.utfpr.ppgca.dbr.model;

import java.util.Collection;

import br.edu.utfpr.ppgca.prs.core.Clock;
import br.edu.utfpr.ppgca.prs.entities.Belief;
import br.edu.utfpr.ppgca.prs.entities.Data;
import br.edu.utfpr.ppgca.prs.entities.Goal;

public abstract class AbstractRelevanceModel {

	public Float compute(Belief belief, Collection<Goal> supportingGoals) {
		return compute(belief.getData(), supportingGoals);
	}

	public abstract Float compute(Data data, Collection<Goal> supportingGoals);

	protected Float getAge(Belief belief) {
		return getAge(belief.getData());
	}

	protected Float getAge(Data data) {
		Float age = Clock.getInstance().getAge(data);
		return age <= 0 ? 0.01F : age;
	}

	protected Float utilitySum(Collection<Goal> supportingGoals) {
		Float relevanceValue = 0f;
		for (Goal goal : supportingGoals) {
			relevanceValue += goal.utility;
		}
		return relevanceValue;
	}

	public String getDescriptor() {
		return this.getClass().getSimpleName();
	}

}
