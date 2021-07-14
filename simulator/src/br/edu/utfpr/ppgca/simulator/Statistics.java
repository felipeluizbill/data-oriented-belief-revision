package br.edu.utfpr.ppgca.simulator;

import br.edu.utfpr.ppgca.prs.core.Agent;
import br.edu.utfpr.ppgca.prs.core.Monitor.Log;

public class Statistics {

	private static Statistics instance;

	private Statistics() {
	}

	public static Statistics getInstance() {
		if (instance == null) {
			instance = new Statistics();
		}
		return instance;
	}

	public Result processMonitor(Agent agent) {
		Result result = new Result();

		for (Log log : agent.getMonitor().getLogs()) {
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

		public Float getMemoryEfficiency() {
			return (float) getUtilitySumMean() / getActiveBeliefsMean();
		}

		public Float getCpuEfficiency() {
			return (float) getUtilitySumMean() * 100 / (getCyclesMean() + (getOperationsMean() * 10));
		}

		@Override
		public String toString() {
			return String.valueOf(getCyclesMean()).replace(".", ",") + " ; "
					+ String.valueOf(getActiveBeliefsMean()).replace(".", ",") + " ; "
					+ String.valueOf(getOperationsMean()).replace(".", ",") + " ; "
					+ String.valueOf(getUtilitySumMean()).replace(".", ",") + " ; "
					+ String.valueOf(getMemoryEfficiency()).replace(".", ",") + " ; "
					+ String.valueOf(getCpuEfficiency()).replace(".", ",");
		}

	}
}
