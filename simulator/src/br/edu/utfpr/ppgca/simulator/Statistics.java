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

	private Map<String, List<Monitor>> monitors = new HashMap<>();

	public void addResults(Collection<Agent> agents) {
		agents.forEach(a -> {
			if (!monitors.keySet().contains(a.getDescriptor())) {
				monitors.put(a.getDescriptor(), new ArrayList<>());
			}
			monitors.get(a.getDescriptor()).add(a.getMonitor());
		});
	}

	public void run() {
		Collection<Result> results = new HashSet<>();
		for (String descriptor : monitors.keySet()) {
			System.out.println(descriptor);
			for (Monitor m : monitors.get(descriptor)) {
				results.add(processIndividualMonitor(m));
			}
			processResults(results);
		}
	}

	private void processResults(Collection<Result> results) {
		Result result = new Result();

		for (Result r : results) {
			result.meanMemoryEfficiency += r.meanMemoryEfficiency;
			result.meanCpuEfficiency += r.meanCpuEfficiency;

			if (r.maxCpuEfficiency > result.maxCpuEfficiency) {
				result.maxCpuEfficiency = r.maxCpuEfficiency;
			}

			if (r.maxMemoryEfficiency > result.maxMemoryEfficiency) {
				result.maxMemoryEfficiency = r.maxMemoryEfficiency;
			}
		}

		result.meanMemoryEfficiency /= (float) results.size();
		result.meanCpuEfficiency /= (float) results.size();

		System.out.println("maxMem = " + result.maxMemoryEfficiency + ", meanMem = " + result.meanMemoryEfficiency);
		System.out.println("maxCpu= " + result.maxCpuEfficiency + ", meanCpu= " + result.meanCpuEfficiency);

	}

	private Result processIndividualMonitor(Monitor monitor) {
		Result result = new Result();

		for (Log log : monitor.getLogs()) {
			result.meanMemoryEfficiency += log.getMemoryEfficiency();
			result.meanCpuEfficiency += log.getCpuEfficiency();

			if (log.getMemoryEfficiency() > result.maxMemoryEfficiency) {
				result.maxMemoryEfficiency = log.getMemoryEfficiency();
			}

			if (log.getCpuEfficiency() > result.maxCpuEfficiency) {
				result.maxCpuEfficiency = log.getCpuEfficiency();
			}
		}

		result.meanMemoryEfficiency /= (float) monitor.getLogs().size();
		result.meanCpuEfficiency /= (float) monitor.getLogs().size();

		return result;
	}

	public class Result {
		public Float maxMemoryEfficiency = Float.MIN_VALUE;
		public Float meanMemoryEfficiency = 0F;

		public Float maxCpuEfficiency = Float.MIN_VALUE;
		public Float meanCpuEfficiency = 0F;

	}
}
