package br.edu.utfpr.ppgca.dbr;

import java.util.Collection;
import java.util.HashSet;

import br.edu.utfpr.ppgca.dbr.entities.DBRBelief;
import br.edu.utfpr.ppgca.dbr.entities.DBRData;
import br.edu.utfpr.ppgca.dbr.layer.BeliefLayer;
import br.edu.utfpr.ppgca.dbr.layer.DataLayer;
import br.edu.utfpr.ppgca.properties.Credibility;
import br.edu.utfpr.ppgca.properties.Importance;
import br.edu.utfpr.ppgca.properties.Likeability;
import br.edu.utfpr.ppgca.properties.Relevance;
import br.edu.utfpr.ppgca.prs.core.AbstractBeliefBase;
import br.edu.utfpr.ppgca.prs.entities.Belief;
import br.edu.utfpr.ppgca.prs.entities.Data;
import br.edu.utfpr.ppgca.prs.entities.Event;
import br.edu.utfpr.ppgca.prs.entities.EventType;

public class DBRBeliefBase extends AbstractBeliefBase {

	private DataLayer dataLayer = new DataLayer();
	private BeliefLayer beliefLayer = new BeliefLayer();

	private Parameters parameters = new Parameters();

	public void setParameters(Parameters parameters) {
		this.parameters = parameters;
	}

	public void beliefUpdate(final Data DATA) {
		dataLayer.informationUpdate(new DBRData(DATA));
		Credibility.update(dataLayer);
		Relevance.update(dataLayer, agentRef.getPlanLibrary(), parameters.getRelevanceModel());
		dataLayer.focus(parameters.getFocusThreshold());
		Importance.update(dataLayer);
		Likeability.update(dataLayer, agentRef.getPlanLibrary().getGoals());
		Collection<DBRData> selectedData = dataLayer.select(parameters.getSelectThreshold());
		Collection<DBRBelief> selectedBeliefs = beliefLayer.select(selectedData);
		selectedBeliefs.forEach(b -> fireEvent(b, EventType.BELIEF_ADDED));
	}

	public void beliefRevision() {
		if (this.beliefLayer.active.size() < parameters.getMemorySize()) {
			return;
		}

		Relevance.update(beliefLayer, agentRef.getPlanLibrary(), parameters.getRelevanceModel());
		Collection<DBRBelief> storedBeliefs = beliefLayer.store(parameters.getStoreThreshold());
		storedBeliefs.forEach(b -> {
			fireEvent(b, EventType.BELIEF_REMOVED);
			operations++;
		});
		Collection<DBRBelief> retrievedBeliefs = beliefLayer.retrieve(parameters.getRetrieveThreshold());
		retrievedBeliefs.forEach(b -> {
			fireEvent(b, EventType.BELIEF_ADDED);
			operations++;
		});
		beliefLayer.mapping();
		beliefLayer.oblivion(parameters.getOblivionThreshold());
		dataLayer.oblivion(parameters.getOblivionThreshold());
	}

	private void fireEvent(final Belief belief, final EventType type) {
		agentRef.addEvent(new Event(belief, type));
	}

	@Override
	public Collection<Belief> getBeliefs() {
		HashSet<Belief> beliefs = new HashSet<>();
		this.beliefLayer.active.forEach(b -> beliefs.add(b));
		return beliefs;
	}

}
