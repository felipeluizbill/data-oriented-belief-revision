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
		log.clockCounter = Clock.getInstance().getCounter();
		log.operations = agentRef.beliefBase.getOperations();
		log.activeBeliefs = agentRef.beliefBase.getBeliefs().size();
		log.cycles = agentRef.engine.getCounter();
		log.utilitySum = agentRef.agenda.getUtilitySum();
		log.plansRemaining = agentRef.planLibrary.goals.size();
		System.out.println(log);
		logs.add(log);
	}

	public class Log {
		Integer clockCounter;
		Long operations;
		Integer activeBeliefs;
		Long cycles;
		Float utilitySum;
		Integer plansRemaining;

		@Override
		public String toString() {
			return "Log [clockCounter=" + clockCounter + ", operations=" + operations + ", activeBeliefs="
					+ activeBeliefs + ", cycles=" + cycles + ", utilitySum=" + utilitySum + ", plansRemaining="
					+ plansRemaining + "]";
		}

	}
}
