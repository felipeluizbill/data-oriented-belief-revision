package br.edu.utfpr.ppgca.bbgp.core;

import java.util.Collection;

import br.edu.utfpr.ppgca.prs.core.AbstractGoalProcessingEngine;
import br.edu.utfpr.ppgca.prs.entities.Goal;

public class BBGPEngine extends AbstractGoalProcessingEngine {

	@Override
	public Collection<Goal> process(Collection<Goal> relevantPlans) {
		Collection<Goal> motivatedGoals = motivatingStage(relevantPlans);
		Collection<Goal> evaluatedGoals = assessmentStage(motivatedGoals);
		Collection<Goal> chosenGoals = deliberationStage(evaluatedGoals);
		Collection<Goal> executiveGoals = checkingStage(chosenGoals);
		return executiveGoals;
	}
}
