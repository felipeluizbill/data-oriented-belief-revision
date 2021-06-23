package br.edu.utfpr.ppgca.prs.core;

import java.util.Collection;

import br.edu.utfpr.ppgca.prs.entities.Belief;
import br.edu.utfpr.ppgca.prs.entities.Data;

public abstract class AbstractBeliefBase {

	protected Long operations = 0L;

	protected Agent agentRef;

	protected Integer memorySize;

	public void setAgentRef(final Agent agentRef) {
		this.agentRef = agentRef;
	}

	public abstract void beliefUpdate(Data data);

	public abstract void beliefRevision();

	public abstract Collection<Belief> getBeliefs();

	public abstract String getDescriptor();

	public Long getOperations() {
		return this.operations;
	}

	public void setMemorySize(final Integer MEMORY_SIZE) {
		this.memorySize = MEMORY_SIZE;
	}

}
