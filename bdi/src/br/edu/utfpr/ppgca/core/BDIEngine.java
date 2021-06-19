package br.edu.utfpr.ppgca.core;

import java.util.Collection;

import br.edu.utfpr.ppgca.prs.core.AbstractGoalProcessingEngine;
import br.edu.utfpr.ppgca.prs.entities.Goal;

public class BDIEngine extends AbstractGoalProcessingEngine {

	@Override
	public Collection<Goal> process(Collection<Goal> relevantPlans) {
		Collection<Goal> motivatedGoals = motivatingStage(relevantPlans);
		Collection<Goal> evaluatedGoals = assessmentStage(relevantPlans);
		Collection<Goal> chosenGoals = deliberationStage(relevantPlans);
		chosenGoals.retainAll(evaluatedGoals);
		chosenGoals.retainAll(motivatedGoals);

		Collection<Goal> executiveGoals = checkingStage(chosenGoals);
		return executiveGoals;
	}

	@Override
	public String getDescriptor() {
		return "BDI";
	}

}
