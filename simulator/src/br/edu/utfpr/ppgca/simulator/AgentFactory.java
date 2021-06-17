package br.edu.utfpr.ppgca.simulator;

import java.util.Collection;
import java.util.HashSet;

import br.edu.utfpr.ppgca.bbgp.core.BBGPEngine;
import br.edu.utfpr.ppgca.core.BDIEngine;
import br.edu.utfpr.ppgca.dbr.DBRBeliefBase;
import br.edu.utfpr.ppgca.dbr.Parameters;
import br.edu.utfpr.ppgca.dbr.model.DefaultRelevanceModel;
import br.edu.utfpr.ppgca.prs.core.AbstractGoalProcessingEngine;
import br.edu.utfpr.ppgca.prs.core.Agent;
import br.edu.utfpr.soar.core.SOAREngine;

public class AgentFactory {

	private static Agent buildAgent(AbstractGoalProcessingEngine engine, Parameters parameters) {
		Agent agent = new Agent();
		DBRBeliefBase beliefBase = new DBRBeliefBase();
		beliefBase.setParameters(parameters);
		agent.setBeliefBase(beliefBase);
		agent.setEngine(engine);
		return agent;
	}

	private static Parameters getParameters(final float STORING_THRESHOLD, final float RETRIEVING_THRESHOLD,
			final float OBLIVION_THRESHOLD, final Integer MEMORY_SIZE) {
		Parameters parameters = new Parameters();
		parameters.setStoreThreshold(STORING_THRESHOLD);
		parameters.setRetrieveThreshold(RETRIEVING_THRESHOLD);
		parameters.setOblivionThreshold(OBLIVION_THRESHOLD);
		parameters.setMemorySize(MEMORY_SIZE);
		parameters.setRelevanceModel(new DefaultRelevanceModel());
		return parameters;
	}

	private static Parameters getDefaultParameters(final float STORING_THRESHOLD, final float RETRIEVING_THRESHOLD,
			final float OBLIVION_THRESHOLD, final Integer MEMORY_SIZE) {
		return getParameters(0, 0, 0, Integer.MAX_VALUE);
	}

	private static Parameters getStoringRetrievingParameters(final float STORING_THRESHOLD,
			final float RETRIEVING_THRESHOLD, final float OBLIVION_THRESHOLD, final Integer MEMORY_SIZE) {
		return getParameters(STORING_THRESHOLD, RETRIEVING_THRESHOLD, 0, MEMORY_SIZE);
	}

	private static Parameters getForgettingParameters(final float STORING_THRESHOLD, final float RETRIEVING_THRESHOLD,
			final float OBLIVION_THRESHOLD, final Integer MEMORY_SIZE) {
		return getParameters(STORING_THRESHOLD, RETRIEVING_THRESHOLD, OBLIVION_THRESHOLD, MEMORY_SIZE);
	}

	public static Collection<Agent> buildBDIAgents(final float STORING_THRESHOLD, final float RETRIEVING_THRESHOLD,
			final float OBLIVION_THRESHOLD, final Integer MEMORY_SIZE) {
		Collection<Agent> agents = new HashSet<>();
		agents.add(buildAgent(new BDIEngine(),
				getDefaultParameters(STORING_THRESHOLD, RETRIEVING_THRESHOLD, OBLIVION_THRESHOLD, MEMORY_SIZE)));
		agents.add(buildAgent(new BDIEngine(), getStoringRetrievingParameters(STORING_THRESHOLD, RETRIEVING_THRESHOLD,
				OBLIVION_THRESHOLD, MEMORY_SIZE)));
		agents.add(buildAgent(new BDIEngine(),
				getForgettingParameters(STORING_THRESHOLD, RETRIEVING_THRESHOLD, OBLIVION_THRESHOLD, MEMORY_SIZE)));
		return agents;
	}

	public static Collection<Agent> buildBBGPAgents(final float STORING_THRESHOLD, final float RETRIEVING_THRESHOLD,
			final float OBLIVION_THRESHOLD, final Integer MEMORY_SIZE) {
		Collection<Agent> agents = new HashSet<>();
		agents.add(buildAgent(new BBGPEngine(),
				getDefaultParameters(STORING_THRESHOLD, RETRIEVING_THRESHOLD, OBLIVION_THRESHOLD, MEMORY_SIZE)));
		agents.add(buildAgent(new BBGPEngine(), getStoringRetrievingParameters(STORING_THRESHOLD, RETRIEVING_THRESHOLD,
				OBLIVION_THRESHOLD, MEMORY_SIZE)));
		agents.add(buildAgent(new BBGPEngine(),
				getForgettingParameters(STORING_THRESHOLD, RETRIEVING_THRESHOLD, OBLIVION_THRESHOLD, MEMORY_SIZE)));
		return agents;
	}

	public static Collection<Agent> buildSOARAgents(final float STORING_THRESHOLD, final float RETRIEVING_THRESHOLD,
			final float OBLIVION_THRESHOLD, final Integer MEMORY_SIZE) {
		Collection<Agent> agents = new HashSet<>();
		agents.add(buildAgent(new SOAREngine(),
				getDefaultParameters(STORING_THRESHOLD, RETRIEVING_THRESHOLD, OBLIVION_THRESHOLD, MEMORY_SIZE)));
		agents.add(buildAgent(new SOAREngine(), getStoringRetrievingParameters(STORING_THRESHOLD, RETRIEVING_THRESHOLD,
				OBLIVION_THRESHOLD, MEMORY_SIZE)));
		agents.add(buildAgent(new SOAREngine(),
				getForgettingParameters(STORING_THRESHOLD, RETRIEVING_THRESHOLD, OBLIVION_THRESHOLD, MEMORY_SIZE)));
		return agents;
	}

}
