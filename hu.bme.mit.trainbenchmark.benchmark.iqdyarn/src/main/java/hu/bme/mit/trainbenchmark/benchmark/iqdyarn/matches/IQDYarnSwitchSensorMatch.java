package hu.bme.mit.trainbenchmark.benchmark.iqdyarn.matches;

import hu.bme.mit.incqueryd.engine.rete.dataunits.Tuple;
import hu.bme.mit.trainbenchmark.benchmark.matches.SwitchSensorMatch;

import java.util.Collection;

public class IQDYarnSwitchSensorMatch extends IQDYarnMatch implements SwitchSensorMatch {

	public IQDYarnSwitchSensorMatch(final Collection<Tuple> tuples) {
		super(tuples);
	}

	@Override
	public Long getSw() {
		// SwitchSensor match contains only one tuple
		Tuple switchTuple = tuples.iterator().next();
		return (Long) switchTuple.get(0);
	}

	@Override
	public Long[] toArray() {
		return new Long[] { getSw() };
	}
	
}
