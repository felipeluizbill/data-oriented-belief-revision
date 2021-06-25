package br.edu.utfpr.ppgca.simulator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import br.edu.utfpr.ppgca.prs.core.Agent;
import br.edu.utfpr.ppgca.prs.core.Monitor;
import br.edu.utfpr.ppgca.prs.core.Monitor.Log;

public class Statistics {

	private Map<String, List<Monitor>> monitorsMap = new HashMap<>();

	public void addResults(Collection<Agent> agents) {
		agents.forEach(a -> {
			if (!monitorsMap.keySet().contains(a.getDescriptor())) {
				monitorsMap.put(a.getDescriptor(), new ArrayList<>());
			}
			monitorsMap.get(a.getDescriptor()).add(a.getMonitor());
		});
	}

	public void run(String scenarioDescriptor) {
		Collection<Result> results = new HashSet<>();

		for (String descriptor : monitorsMap.keySet()) {
			List<Monitor> monitorList = monitorsMap.get(descriptor);
			for (Monitor m : monitorList) {
				Result result = processIndividualMonitor(m);
				results.add(result);
			}
			System.out.print(descriptor);
			processResults(results , scenarioDescriptor);

		}

	}

	private void processResults(Collection<Result> results, String scenarioDescriptor) {
		Result result = new Result();

		for (Result r : results) {
			result.amountOfLogs++;

			result.cyclesSum += r.getCyclesMean();

			result.activeBeliefSum += r.getActiveBeliefsMean();

			result.operationsSum += r.getOperationsMean();

			result.utilitySum += r.getUtilitySumMean();
		}

		System.out.println(result + " ; " + scenarioDescriptor);
	}

	private Result processIndividualMonitor(Monitor monitor) {
		Result result = new Result();

		for (Log log : monitor.getLogs()) {
			result.amountOfLogs++;
			result.cyclesSum += log.cycles;
			result.operationsSum += log.operations;
			result.utilitySum += log.utilitySum;
			result.activeBeliefSum += log.activeBeliefs;
		}

		return result;
	}

	public class Result {
		Integer amountOfLogs = 0;
		Float activeBeliefSum = 0F;
		Float cyclesSum = 0F;
		Float operationsSum = 0F;
		Float utilitySum = 0F;

		public Float getCyclesMean() {
			return (float) (this.cyclesSum / this.amountOfLogs);
		}

		public Float getOperationsMean() {
			return (float) (this.operationsSum / this.amountOfLogs);
		}

		public Float getUtilitySumMean() {
			return this.utilitySum / this.amountOfLogs;
		}

		public Float getActiveBeliefsMean() {
			return (float) (this.activeBeliefSum / this.amountOfLogs);
		}

		@Override
		public String toString() {
			return "; " + String.valueOf(getCyclesMean()).replace(".", ",") + " ; "
					+ String.valueOf(getActiveBeliefsMean()).replace(".", ",") + " ; "
					+ String.valueOf(getOperationsMean()).replace(".", ",") + " ; "
					+ String.valueOf(getUtilitySumMean()).replace(".", ",");
		}

	}
}
