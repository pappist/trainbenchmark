package hu.bme.mit.trainbenchmark.benchmark.iqdyarn.matches;

import hu.bme.mit.incqueryd.engine.rete.dataunits.Tuple;
import hu.bme.mit.trainbenchmark.benchmark.matches.RouteSensorMatch;

public class IQDYarnRouteSensorMatch extends IQDYarnMatch implements RouteSensorMatch {

	public IQDYarnRouteSensorMatch(Tuple tuple) {
		super(tuple);
	}

	@Override
	public Long getRoute() {
		return (Long) tuple.get(3);
	}

	@Override
	public Long getSensor() {
		return (Long) tuple.get(0);
	}

	@Override
	public Long getSwP() {
		return (Long) tuple.get(2);
	}

	@Override
	public Long getSw() {
		return (Long) tuple.get(1);
	}

	@Override
	public Long[] toArray() {
		return new Long[] { getSensor(), getSw(), getSwP(), getRoute()};
	}

}
