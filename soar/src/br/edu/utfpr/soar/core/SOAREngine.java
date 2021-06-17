package br.edu.utfpr.soar.core;

import java.util.Collection;
import java.util.Set;

import br.edu.utfpr.ppgca.prs.core.AbstractGoalProcessingEngine;
import br.edu.utfpr.ppgca.prs.entities.Goal;

public class SOAREngine extends AbstractGoalProcessingEngine {

	@Override
	public Collection<Goal> process(Collection<Goal> relevantPlans) {
		Collection<Goal> motivatedGoals = (Set<Goal>) motivatingStage(relevantPlans);
		Collection<Goal> evaluatedGoals = assessmentStage(relevantPlans);
		Collection<Goal> chosenGoals = deliberationStage(relevantPlans);
		Collection<Goal> executiveGoals = checkingStage(relevantPlans);

		executiveGoals.retainAll(motivatedGoals);
		executiveGoals.retainAll(evaluatedGoals);
		executiveGoals.retainAll(chosenGoals);

		return executiveGoals;
	}

}
