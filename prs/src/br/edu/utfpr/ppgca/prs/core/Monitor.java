package br.edu.utfpr.ppgca.prs.core;

import java.util.ArrayList;
import java.util.List;

public class Monitor {

	private final Agent agentRef;

	private List<Log> logs = new ArrayList<Log>();

	public Monitor(Agent agentRef) {
		super();
		this.agentRef = agentRef;
	}

	public List<Log> getLogs() {
		return this.logs;
	}

	public void log() {
		Log log = new Log();
		log.operations = agentRef.beliefBase.getOperations();
		log.activeBeliefs = agentRef.beliefBase.getBeliefs().size();
		log.cycles = agentRef.engine.getCounter();
		log.utilitySum = agentRef.agenda.getUtilitySum();
		log.plansRemaining = agentRef.planLibrary.goals.size();
		log.memoryEfficiency = log.utilitySum / (float) log.activeBeliefs;
		log.cpuEfficiency = log.utilitySum*1_000 / (float) log.cycles;

		logs.add(log);
	}

	public void printLog() {
		System.out.println(this);
	}

	@Override
	public String toString() {
		Log lastLog = logs.get(logs.size() - 1);
		return "Monitor [agentRef=" + agentRef.getDescriptor() + ", lastLog=" + lastLog + "]";
	}

	public class Log {
		Long operations;
		Integer activeBeliefs;
		Long cycles;
		Float utilitySum;
		Integer plansRemaining;
		Float memoryEfficiency;
		Float cpuEfficiency;

		@Override
		public String toString() {
			return "Log [operations=" + operations + ", activeBeliefs="
					+ activeBeliefs + ", cycles=" + cycles + ", utilitySum=" + utilitySum + ", memoryEfficiency="
					+ String.valueOf(memoryEfficiency).replace(".", ",") + ", cpuEfficiency="
					+ String.valueOf(cpuEfficiency).replace(".", ",") + "]";
		}

	}
}
