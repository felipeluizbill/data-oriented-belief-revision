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

	public class Log {
		public Long operations;
		public Integer activeBeliefs;
		public Long cycles;
		public Float utilitySum;
		public Integer plansRemaining;

		@Override
		public String toString() {
			return "Log [operations=" + operations + ", activeBeliefs=" + activeBeliefs + ", cycles=" + cycles
					+ ", utilitySum=" + utilitySum + ", plansRemaining=" + plansRemaining + "]";
		}

	}
}
