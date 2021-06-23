package br.edu.utfpr.ppgca.prs.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import br.edu.utfpr.ppgca.prs.entities.Data;
import br.edu.utfpr.ppgca.prs.entities.Event;
import br.edu.utfpr.ppgca.prs.entities.Goal;
import br.edu.utfpr.ppgca.prs.entities.Message;

public class Agent {

	Agenda agenda = new Agenda(this);
	AbstractBeliefBase beliefBase;
	PlanLibrary planLibrary = new PlanLibrary();
	Monitor monitor = new Monitor(this);
	AbstractGoalProcessingEngine engine;

	List<Event> events = new ArrayList<Event>();

	public String getDescriptor() {
		return beliefBase.getDescriptor().concat(" ; ").concat(engine.getDescriptor());
	}

	public void setBeliefBase(AbstractBeliefBase beliefBase) {
		beliefBase.setAgentRef(this);
		this.beliefBase = beliefBase;
	}

	public void setEngine(AbstractGoalProcessingEngine engine) {
		engine.setAgentRef(this);
		this.engine = engine;
	}

	public AbstractBeliefBase getBeliefBase() {
		return this.beliefBase;
	}

	public PlanLibrary getPlanLibrary() {
		return this.planLibrary;
	}

	public Agenda getAgenda() {
		return this.agenda;
	}

	public Monitor getMonitor() {
		return this.monitor;
	}

	public void perceive(final List<Data> perceptions) {
		perceptions.forEach(p -> perceive(p));
	}

	public void perceive(final Data perception) {
		this.beliefBase.beliefUpdate(perception);
		this.beliefBase.beliefRevision();
		this.engine.checkContext(unifyEvent(selectEvent()));
		executeIntention(selectIntention());
		monitor.log();
	}

	public void checkMail(Collection<Message> messages) {
		Collection<Message> selectedMessages = selectSoccialyAcceptedMessages(messages);
		selectedMessages.forEach(m -> checkMail(m));
	}

	public void checkMail(Message message) {
		this.beliefBase.beliefUpdate(message);
		this.beliefBase.beliefRevision();
	}

	private Collection<Message> selectSoccialyAcceptedMessages(Collection<Message> messages) {
		Collection<Message> selectedMessages = new HashSet<>();
		selectedMessages.addAll(messages);
		return selectedMessages;
	}

	public void addEvent(final Event event) {
		this.events.add(event);
	}

	private Event selectEvent() {
		if (events.iterator().hasNext()) {
			Event nextEvent = events.iterator().next();
			events.remove(nextEvent);
			return nextEvent;
		}
		return null;
	}

	private Collection<Goal> unifyEvent(final Event event) {
		return planLibrary.selectRelevantPlans(event);
	}

	private Goal selectIntention() {
		return agenda.selectIntention();
	}

	private void executeIntention(final Goal intention) {
		if (intention == null) {
			return;
		}

		planLibrary.removeGoal(intention);
		act();
		sendMessage(null);
	}

	private void sendMessage(final Message message) {

	}

	private void act() {

	}

}
