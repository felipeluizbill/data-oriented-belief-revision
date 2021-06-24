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
		public Long operations;
		public Integer activeBeliefs;
		public Long cycles;
		public Float utilitySum;
		public Integer plansRemaining;

		public Float getCpuEfficiency() {
			float cpuEfficiency = utilitySum * 1_000 / (float) cycles;
			return Float.isNaN(cpuEfficiency) ? 0 : cpuEfficiency;
		}

		public Float getMemoryEfficiency() {
			float memoryEfficiency = utilitySum / (float) activeBeliefs;
			return Float.isNaN(memoryEfficiency) ? 0 : memoryEfficiency;
		}

	}
}
