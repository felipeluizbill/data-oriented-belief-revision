package br.edu.utfpr.ppgca.simulator;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import br.edu.utfpr.ppgca.bbgp.core.BBGPEngine;
import br.edu.utfpr.ppgca.bbgp.model.BBGPIntegratedRelevanceModel;
import br.edu.utfpr.ppgca.core.BDIEngine;
import br.edu.utfpr.ppgca.dbr.DBRBeliefBase;
import br.edu.utfpr.ppgca.dbr.Parameters;
import br.edu.utfpr.ppgca.dbr.model.IntegratedRelevanceModel;
import br.edu.utfpr.ppgca.model.BDIIntegratedRelevanceModel;
import br.edu.utfpr.ppgca.prs.core.AbstractGoalProcessingEngine;
import br.edu.utfpr.ppgca.prs.core.Agent;
import br.edu.utfpr.ppgca.prs.entities.Goal;
import br.edu.utfpr.soar.core.SOAREngine;
import br.edu.utfpr.soar.model.SOARIntegratedRelevanceModel;

public class AgentFactory {

	private static Agent buildAgent(AbstractGoalProcessingEngine engine, DBRBeliefBase beliefBase) {
		Agent agent = new Agent();
		agent.setEngine(engine);
		agent.setBeliefBase(beliefBase);
		return agent;
	}

	private static Agent buildSOARAgent(DBRBeliefBase beliefBase) {
		Agent agent = buildAgent(new SOAREngine(), beliefBase);
		return agent;
	}

	private static Agent buildBDIAgent(DBRBeliefBase beliefBase) {
		Agent agent = buildAgent(new BDIEngine(), beliefBase);
		return agent;
	}

	private static Agent buildBBGPAgent(DBRBeliefBase beliefBase) {
		Agent agent = buildAgent(new BBGPEngine(), beliefBase);
		return agent;
	}

	private static DBRBeliefBase buildBeliefBase(Parameters parameters) {
		DBRBeliefBase beliefBase = new DBRBeliefBase();
		beliefBase.setParameters(parameters);
		return beliefBase;
	}

	private static Parameters buildDefaultParameters() {
		return new Parameters(0F, 0F, 0F);
	}

	private static Parameters buildDefaultParameters(IntegratedRelevanceModel relevanceModel) {
		Parameters parameters = buildDefaultParameters();
		parameters.setRelevanceModel(relevanceModel);
		return parameters;
	}

	private static Parameters buildStoringRetrievingParameters(final float STORING_THRESHOLD,
			final float RETRIEVING_THRESHOLD, IntegratedRelevanceModel relevanceModel) {
		Parameters parameters = buildStoringRetrievingParameters(STORING_THRESHOLD, RETRIEVING_THRESHOLD);
		parameters.setRelevanceModel(relevanceModel);
		return parameters;
	}

	private static Parameters buildStoringRetrievingParameters(final float STORING_THRESHOLD,
			final float RETRIEVING_THRESHOLD) {
		return new Parameters(STORING_THRESHOLD, RETRIEVING_THRESHOLD, 0F);
	}

	private static Parameters buildForgettingParameters(final float STORING_THRESHOLD, final float RETRIEVING_THRESHOLD,
			final float OBLIVION_THRESHOLD) {
		return new Parameters(STORING_THRESHOLD, RETRIEVING_THRESHOLD, OBLIVION_THRESHOLD);
	}

	private static Parameters buildForgettingParameters(final float STORING_THRESHOLD, final float RETRIEVING_THRESHOLD,
			final float OBLIVION_THRESHOLD, IntegratedRelevanceModel relevanceModel) {
		Parameters parameters = buildForgettingParameters(STORING_THRESHOLD, RETRIEVING_THRESHOLD, OBLIVION_THRESHOLD);
		parameters.setRelevanceModel(relevanceModel);
		return parameters;
	}

	private static Collection<Agent> buildDefaultAgents() {
		Collection<Agent> agents = new HashSet<>();
		agents.add(buildSOARAgent(buildBeliefBase(buildDefaultParameters())));
		agents.add(buildBDIAgent(buildBeliefBase(buildDefaultParameters())));
		agents.add(buildBBGPAgent(buildBeliefBase(buildDefaultParameters())));
		agents.add(buildSOARAgent(buildBeliefBase(buildDefaultParameters(new SOARIntegratedRelevanceModel()))));
		agents.add(buildBDIAgent(buildBeliefBase(buildDefaultParameters(new BDIIntegratedRelevanceModel()))));
		agents.add(buildBBGPAgent(buildBeliefBase(buildDefaultParameters(new BBGPIntegratedRelevanceModel()))));
		return agents;
	}

	private static Collection<Agent> buildStoringRetrievingAgents(final float STORING_THRESHOLD,
			final float RETRIEVING_THRESHOLD) {
		Collection<Agent> agents = new HashSet<>();
		agents.add(buildSOARAgent(
				buildBeliefBase(buildStoringRetrievingParameters(STORING_THRESHOLD, RETRIEVING_THRESHOLD))));
		agents.add(buildBDIAgent(
				buildBeliefBase(buildStoringRetrievingParameters(STORING_THRESHOLD, RETRIEVING_THRESHOLD))));
		agents.add(buildBBGPAgent(
				buildBeliefBase(buildStoringRetrievingParameters(STORING_THRESHOLD, RETRIEVING_THRESHOLD))));
		agents.add(buildSOARAgent(buildBeliefBase(buildStoringRetrievingParameters(STORING_THRESHOLD,
				RETRIEVING_THRESHOLD, new SOARIntegratedRelevanceModel()))));
		agents.add(buildBDIAgent(buildBeliefBase(buildStoringRetrievingParameters(STORING_THRESHOLD,
				RETRIEVING_THRESHOLD, new BDIIntegratedRelevanceModel()))));
		agents.add(buildBBGPAgent(buildBeliefBase(buildStoringRetrievingParameters(STORING_THRESHOLD,
				RETRIEVING_THRESHOLD, new BBGPIntegratedRelevanceModel()))));
		return agents;
	}

	private static Collection<Agent> buildForgettingAgents(final float STORING_THRESHOLD,
			final float RETRIEVING_THRESHOLD, final float OBLIVION_THRESHOLD) {
		Collection<Agent> agents = new HashSet<>();
		agents.add(buildSOARAgent(buildBeliefBase(
				buildForgettingParameters(STORING_THRESHOLD, RETRIEVING_THRESHOLD, OBLIVION_THRESHOLD))));
		agents.add(buildBDIAgent(buildBeliefBase(
				buildForgettingParameters(STORING_THRESHOLD, RETRIEVING_THRESHOLD, OBLIVION_THRESHOLD))));
		agents.add(buildBBGPAgent(buildBeliefBase(
				buildForgettingParameters(STORING_THRESHOLD, RETRIEVING_THRESHOLD, OBLIVION_THRESHOLD))));

		agents.add(buildSOARAgent(buildBeliefBase(buildForgettingParameters(STORING_THRESHOLD, RETRIEVING_THRESHOLD,
				OBLIVION_THRESHOLD, new SOARIntegratedRelevanceModel()))));
		agents.add(buildBDIAgent(buildBeliefBase(buildForgettingParameters(STORING_THRESHOLD, RETRIEVING_THRESHOLD,
				OBLIVION_THRESHOLD, new BDIIntegratedRelevanceModel()))));
		agents.add(buildBBGPAgent(buildBeliefBase(buildForgettingParameters(STORING_THRESHOLD, RETRIEVING_THRESHOLD,
				OBLIVION_THRESHOLD, new BBGPIntegratedRelevanceModel()))));
		return agents;
	}

	private static Set<Agent> prepareAgents(final float STORING_THRESHOLD, final float RETRIEVING_THRESHOLD,
			final float OBLIVION_THRESHOLD) {
		
		System.out.println(STORING_THRESHOLD + " - " + RETRIEVING_THRESHOLD + " - " + OBLIVION_THRESHOLD);
		
		Set<Agent> agents = new HashSet<>();
		agents.addAll(AgentFactory.buildDefaultAgents());
		agents.addAll(AgentFactory.buildStoringRetrievingAgents(STORING_THRESHOLD, RETRIEVING_THRESHOLD));
		agents.addAll(AgentFactory.buildForgettingAgents(STORING_THRESHOLD, RETRIEVING_THRESHOLD, OBLIVION_THRESHOLD));
		return agents;
	}

	public static Set<Agent> build(Set<Goal> goals) {
		Set<Agent> agents = new HashSet<>();
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				for (int k = 1; k <= 4; k++) {
					agents.addAll(AgentFactory.prepareAgents((i * .2F), (j * .2F), (k * .2F)));
				}
			}
		}
		agents.forEach(a -> a.getPlanLibrary().addGoal(goals));

		return agents;
	}

}
