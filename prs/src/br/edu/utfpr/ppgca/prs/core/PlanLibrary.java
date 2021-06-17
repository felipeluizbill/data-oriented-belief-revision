package br.edu.utfpr.ppgca.prs.core;

import java.util.Collection;
import java.util.HashSet;

import br.edu.utfpr.ppgca.prs.entities.Belief;
import br.edu.utfpr.ppgca.prs.entities.Data;
import br.edu.utfpr.ppgca.prs.entities.Event;
import br.edu.utfpr.ppgca.prs.entities.Goal;

public class PlanLibrary {

	Collection<Goal> goals = new HashSet<>();

	private final Agent agentRef;

	public PlanLibrary(Agent agentRef) {
		super();
		this.agentRef = agentRef;
	}

	public void addGoal(final Goal goal) {
		if (this.goals.contains(goal)) {
			return;
		}
		this.goals.add(goal);
	}

	public void removeGoal(final Goal goal) {
		if (this.goals.contains(goal)) {
			this.goals.remove(goal);
		}
	}

	public Collection<Goal> getGoals() {
		return new HashSet<>(this.goals);
	}

	public Collection<Goal> selectRelevantPlans(final Event event) {
		Collection<Goal> relevantPlans = new HashSet<>();
		if (null == event) {
			return relevantPlans;
		}

		this.goals.forEach(g -> {
			if (g.doesItSupport(event.belief)) {
				relevantPlans.add(g);
			}
		});
		return relevantPlans;
	}

	public Collection<Goal> selectSupportingGoals(final Data data) {
		return selectSupportingGoals(new Belief(data));
	}

	public Collection<Goal> selectSupportingGoals(final Belief belief) {
		Collection<Goal> supportingGoals = new HashSet<>();
		this.goals.forEach(g -> {
			if (g.doesItSupport(belief)) {
				supportingGoals.add(g);
			}
		});
		return supportingGoals;
	}
}
