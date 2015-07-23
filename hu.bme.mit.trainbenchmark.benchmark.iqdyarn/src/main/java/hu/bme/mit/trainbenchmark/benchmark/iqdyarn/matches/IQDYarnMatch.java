package hu.bme.mit.trainbenchmark.benchmark.iqdyarn.matches;

import hu.bme.mit.incqueryd.engine.rete.dataunits.Tuple;
import hu.bme.mit.trainbenchmark.constants.Query;

public abstract class IQDYarnMatch {
	
	protected Tuple tuple;
	
	public IQDYarnMatch(Tuple tuple) {
		this.tuple = tuple;
	}
	
	public static IQDYarnMatch createMatch(Query query, Tuple tuple) {
		switch(query) {
			case SWITCHSENSOR:
				return new IQDYarnSwitchSensorMatch(tuple);
			case ROUTESENSOR:
				return new IQDYarnRouteSensorMatch(tuple);
			default:
				throw new UnsupportedOperationException("Currently only " + Query.SWITCHSENSOR + " pattern supported!");
		}
	}
	
	public abstract Long[] toArray();
}
