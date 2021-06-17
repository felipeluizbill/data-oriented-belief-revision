package br.edu.utfpr.ppgca.dbr.model;

import java.util.Collection;

import br.edu.utfpr.ppgca.prs.entities.Belief;
import br.edu.utfpr.ppgca.prs.entities.Data;
import br.edu.utfpr.ppgca.prs.entities.Goal;

public class DefaultRelevanceModel extends AbstractRelevanceModel {

	public Float compute(Belief belief, Collection<Goal> supportingGoals) {
		return compute(belief.getData(), supportingGoals);
	}

	public Float compute(Data data, Collection<Goal> supportingGoals) {
		Float utilitySum = utilitySum(supportingGoals);
		Float age = getAge(data);
		return utilitySum * age;
	}
}
