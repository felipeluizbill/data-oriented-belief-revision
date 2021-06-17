package br.edu.utfpr.ppgca.dbr.model;

import java.util.Collection;

import br.edu.utfpr.ppgca.prs.core.Clock;
import br.edu.utfpr.ppgca.prs.entities.Belief;
import br.edu.utfpr.ppgca.prs.entities.Data;
import br.edu.utfpr.ppgca.prs.entities.Goal;
import br.edu.utfpr.ppgca.prs.entities.GoalStatus;

public abstract class AbstractRelevanceModel {

	public abstract Float compute(Belief belief, Collection<Goal> supportingGoals);

	public abstract Float compute(Data data, Collection<Goal> supportingGoals);

	protected Float getAge(Belief belief) {
		return getAge(belief.getData());
	}

	protected Float getAge(Data data) {
		return Clock.getInstance().getAge(data.getLastActivation());
	}

	protected Float utilitySum(Collection<Goal> supportingGoals) {
		Float relevanceValue = 0f;
		for (Goal goal : supportingGoals) {
			relevanceValue += goal.utility;
		}
		return relevanceValue;
	}

}
