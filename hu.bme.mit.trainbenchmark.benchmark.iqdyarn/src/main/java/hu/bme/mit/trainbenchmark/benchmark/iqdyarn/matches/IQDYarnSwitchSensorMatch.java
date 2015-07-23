package hu.bme.mit.trainbenchmark.benchmark.iqdyarn.matches;

import hu.bme.mit.incqueryd.engine.rete.dataunits.Tuple;
import hu.bme.mit.trainbenchmark.benchmark.matches.SwitchSensorMatch;

public class IQDYarnSwitchSensorMatch extends IQDYarnMatch implements SwitchSensorMatch {

	public IQDYarnSwitchSensorMatch(final Tuple tuple) {
		super(tuple);
	}

	@Override
	public Long getSw() {
		return (Long) tuple.get(0);
	}

	@Override
	public Long[] toArray() {
		return new Long[] { getSw() };
	}
	
}
