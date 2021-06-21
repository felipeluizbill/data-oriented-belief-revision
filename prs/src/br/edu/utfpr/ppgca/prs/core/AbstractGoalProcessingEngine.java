package br.edu.utfpr.ppgca.prs.core;

import java.util.Collection;
import java.util.HashSet;

import br.edu.utfpr.ppgca.prs.entities.Belief;
import br.edu.utfpr.ppgca.prs.entities.Goal;
import br.edu.utfpr.ppgca.prs.entities.GoalStatus;
import br.edu.utfpr.ppgca.prs.knowledge.CheckingRule;
import br.edu.utfpr.ppgca.prs.knowledge.DeliberatingRule;
import br.edu.utfpr.ppgca.prs.knowledge.ImpossibilityRule;
import br.edu.utfpr.ppgca.prs.knowledge.MotivatingRule;
import br.edu.utfpr.ppgca.prs.knowledge.ProceduralRule;

public abstract class AbstractGoalProcessingEngine {

	protected Long counter = 0L;

	protected Agent agentRef;

	public void setAgentRef(final Agent agentRef) {
		this.agentRef = agentRef;
	}

	public String getDescriptor() {
		return this.getClass().getSimpleName();
	}

	public void run(final Collection<Goal> relevantPlans) {
		Collection<Goal> executiveGoals = process(relevantPlans);
		agentRef.getAgenda().addIntention(executiveGoals);
	}

	public abstract Collection<Goal> process(final Collection<Goal> relevantPlans);

	protected Collection<Goal> motivatingStage(final Collection<Goal> relevantPlans) {
		return runStage(relevantPlans, MotivatingRule.class, GoalStatus.ACTIVE, false);
	}

	protected Collection<Goal> assessmentStage(final Collection<Goal> motivatedGoals) {
		return runStage(motivatedGoals, ImpossibilityRule.class, GoalStatus.PURSUABLE, true);
	}

	protected Collection<Goal> deliberationStage(final Collection<Goal> pursuableGoals) {
		return runStage(pursuableGoals, DeliberatingRule.class, GoalStatus.CHOSEN, false);
	}

	protected Collection<Goal> checkingStage(final Collection<Goal> chosenGoals) {
		return runStage(chosenGoals, CheckingRule.class, GoalStatus.EXECUTIVE, true);
	}

	private <T extends ProceduralRule> boolean areRulesEmpty(Collection<T> rules, Collection<Goal> goalsRef, Goal goal,
			GoalStatus status) {
		if (rules.isEmpty()) {
			goal.setStatus(status);
			goalsRef.add(goal);
			return true;
		}
		return false;
	}

	private <T extends ProceduralRule> void positiveFilter(Collection<T> rules, Collection<Goal> goalsRef, Goal goal,
			final GoalStatus STATUS) {
		for (T rule : rules) {
			for (Belief belief : agentRef.beliefBase.getBeliefs()) {
				counter++;
				if (rule.unify(belief) != null) {
					promoteGoal(goal, goalsRef, STATUS);
					return;
				}
			}
		}
	}

	private <T extends ProceduralRule> void negativeFilter(Collection<T> rules, Collection<Goal> goalsRef, Goal goal,
			final GoalStatus STATUS) {
		Collection<T> checkedRules = new HashSet<>();
		for (T rule : rules) {
			for (Belief belief : agentRef.beliefBase.getBeliefs()) {
				counter++;
				if (rule.unify(belief) != null) {
					checkedRules.add(rule);
				}
			}
		}
		if (rules.size() == checkedRules.size()) {
			promoteGoal(goal, goalsRef, STATUS);
		}
	}

	private void promoteGoal(Goal goal, Collection<Goal> nextStageGoalsRef, final GoalStatus STATUS) {
		nextStageGoalsRef.add(goal);
		goal.setStatus(STATUS);
	}

	private <T extends ProceduralRule> Collection<Goal> runStage(final Collection<Goal> originalGoals,
			final Class<T> ruleType, final GoalStatus STATUS, final boolean NEGATIVE_FILTER) {
		Collection<Goal> filteredGoals = new HashSet<>();
		for (Goal g : originalGoals) {
			Collection<? extends ProceduralRule> rules = getRules(g, ruleType);
			if (areRulesEmpty(rules, filteredGoals, g, STATUS)) {
				continue;
			}
			if (NEGATIVE_FILTER) {
				negativeFilter(rules, filteredGoals, g, STATUS);
			}
			positiveFilter(rules, filteredGoals, g, STATUS);
		}
		return filteredGoals;
	}

	protected static <T extends ProceduralRule> Collection<T> getRules(final Goal goal, Class<T> rule) {
		Collection<T> selectedRules = new HashSet<>();
		goal.getRules().forEach(r -> {
			if (rule.isInstance(r)) {
				selectedRules.add((T) r);
			}
		});
		return selectedRules;
	}

	public Long getCounter() {
		return this.counter;
	}

}
