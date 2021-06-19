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

	public abstract String getDescriptor();

	public void run(final Collection<Goal> relevantPlans) {
		Collection<Goal> executiveGoals = process(relevantPlans);
		agentRef.getAgenda().addIntention(executiveGoals);
	}

	public abstract Collection<Goal> process(final Collection<Goal> relevantPlans);

	protected Collection<Goal> motivatingStage(final Collection<Goal> relevantPlans) {
		Collection<Goal> motivatedGoals = new HashSet<>();
		planLoop: for (Goal p : relevantPlans) {
			Collection<MotivatingRule> rules = getRules(p, MotivatingRule.class);
			if (rules.isEmpty()) {
				p.setStatus(GoalStatus.ACTIVE);
				motivatedGoals.add(p);
				continue planLoop;
			}
			for (MotivatingRule r : rules) {
				for (Belief b : agentRef.getBeliefBase().getBeliefs()) {
					counter++;
					if (r.unify(b) != null) {
						p.setStatus(GoalStatus.ACTIVE);
						motivatedGoals.add(p);
						continue planLoop;
					}
				}
			}
		}
		return motivatedGoals;
	}

	protected Collection<Goal> assessmentStage(final Collection<Goal> motivatedGoals) {
		Collection<Goal> evaluatedGoals = new HashSet<>();
		for (Goal g : motivatedGoals) {
			Collection<ImpossibilityRule> rules = getRules(g, ImpossibilityRule.class);
			if (rules.isEmpty()) {
				g.setStatus(GoalStatus.PURSUABLE);
				evaluatedGoals.add(g);
				continue;
			}
			Collection<ImpossibilityRule> checkdRules = new HashSet<>();
			for (ImpossibilityRule r : rules) {
				for (Belief b : agentRef.getBeliefBase().getBeliefs()) {
					counter++;
					if (r.unify(b) != null) {
						checkdRules.add(r);
					}
				}
			}
			if (rules.size() == checkdRules.size()) {
				g.setStatus(GoalStatus.PURSUABLE);
				evaluatedGoals.add(g);
			}
		}
		return evaluatedGoals;
	}

	protected Collection<Goal> deliberationStage(final Collection<Goal> pursuableGoals) {
		Collection<Goal> deliberatedGoals = new HashSet<>();
		planLoop: for (Goal g : pursuableGoals) {
			Collection<DeliberatingRule> rules = getRules(g, DeliberatingRule.class);
			if (rules.isEmpty()) {
				g.setStatus(GoalStatus.CHOSEN);
				deliberatedGoals.add(g);
				continue planLoop;
			}
			for (DeliberatingRule r : rules) {
				for (Belief b : agentRef.getBeliefBase().getBeliefs()) {
					counter++;
					if (r.unify(b) != null) {
						g.setStatus(GoalStatus.CHOSEN);
						deliberatedGoals.add(g);
						continue planLoop;
					}
				}
			}
		}
		return deliberatedGoals;
	}

	protected Collection<Goal> checkingStage(final Collection<Goal> chosenGoals) {
		Collection<Goal> executiveGoals = new HashSet<>();
		for (Goal g : chosenGoals) {
			Collection<CheckingRule> rules = getRules(g, CheckingRule.class);
			if (rules.isEmpty()) {
				g.setStatus(GoalStatus.EXECUTIVE);
				executiveGoals.add(g);
				continue;
			}
			Collection<CheckingRule> checkedRules = new HashSet<>();
			for (CheckingRule r : rules) {
				for (Belief b : agentRef.getBeliefBase().getBeliefs()) {
					counter++;
					if (r.unify(b) != null) {
						checkedRules.add(r);
					}
				}
			}
			if (rules.size() == checkedRules.size()) {
				g.setStatus(GoalStatus.EXECUTIVE);
				executiveGoals.add(g);
			}
		}
		return executiveGoals;
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
