package br.edu.utfpr.ppgca.dbr.model;

import java.util.Collection;

import br.edu.utfpr.ppgca.prs.entities.Data;
import br.edu.utfpr.ppgca.prs.entities.Goal;
import br.edu.utfpr.ppgca.prs.entities.GoalStatus;

public class IntegratedRelevanceModel extends AbstractRelevanceModel {

	@Override
	public Float compute(Data data, Collection<Goal> supportingGoals) {
		Float utilitySum = utilitySum(supportingGoals);
		Float age = getAge(data);
		return  utilitySum * age ;
	}

	protected Float utilitySum(Collection<Goal> supportingGoals) {
		Float relevanceValue = 0f;
		for (Goal goal : supportingGoals) {			
			relevanceValue += goal.utility * goalInertia(goal);
		}
		return relevanceValue;
	}

	protected Float goalInertia(Goal goal) {
		return GoalStatus.getValue(goal.getStatus());
	}

}
