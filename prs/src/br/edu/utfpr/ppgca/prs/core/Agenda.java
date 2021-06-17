package br.edu.utfpr.ppgca.prs.core;

import java.util.Collection;
import java.util.HashSet;

import br.edu.utfpr.ppgca.prs.entities.Goal;

public class Agenda {

	private Collection<Goal> intentions = new HashSet<>();
	private Collection<Goal> suspendedIntentions = new HashSet<>();

	private final Agent agentRef;

	private float utilitySum = 0F;

	public Agenda(final Agent agentRef) {
		this.agentRef = agentRef;
	}

	public void addIntention(final Collection<Goal> goals) {
		goals.forEach(g -> addIntention(g));
	}

	public void addIntention(final Goal goal) {
		intentions.add(goal);
	}

	public Goal selectIntention() {
		if (intentions.iterator().hasNext()) {
			Goal selectedIntention = intentions.iterator().next();
			removeIntention(selectedIntention);
			utilitySum += selectedIntention.utility;
			return selectedIntention;
		}
		return null;
	}

	public void updateIntention(final Goal goal) {

	}

	public void removeIntention(final Goal goal) {
		intentions.remove(goal);
	}

	public void suspendIntention(final Goal goal) {
		if (intentions.contains(goal)) {
			intentions.remove(goal);
			suspendedIntentions.add(goal);
		}
	}

	public void restartIntention(final Goal goal) {
		if (suspendedIntentions.contains(goal)) {
			suspendedIntentions.remove(goal);
			intentions.add(goal);
		}
	}

	public float getUtilitySum() {
		return this.utilitySum;
	}

}
