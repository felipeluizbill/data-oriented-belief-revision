package br.edu.utfpr.ppgca.prs.entities;

public class Event {

	public final Belief belief;

	public final EventType type;

	public Event(Belief belief, EventType type) {
		super();
		this.belief = belief;
		this.type = type;
	}

	@Override
	public String toString() {
		return "Event [belief=" + belief + ", type=" + type + "]";
	}

}
