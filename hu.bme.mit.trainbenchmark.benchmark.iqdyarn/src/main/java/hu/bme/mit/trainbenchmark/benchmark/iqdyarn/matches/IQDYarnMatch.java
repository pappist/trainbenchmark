package hu.bme.mit.trainbenchmark.benchmark.iqdyarn.matches;

import hu.bme.mit.incqueryd.engine.rete.dataunits.Tuple;
import hu.bme.mit.trainbenchmark.constants.Query;

import java.util.Collection;

public abstract class IQDYarnMatch {
	
	protected Collection<Tuple> tuples;
	
	public IQDYarnMatch(Collection<Tuple> tuples) {
		this.tuples = tuples;
	}
	
	public static IQDYarnMatch createMatch(Query query, Collection<Tuple> tuples) {
		switch(query) {
			case SWITCHSENSOR:
				return new IQDYarnSwitchSensorMatch(tuples);
			default:
				throw new UnsupportedOperationException("Currently only " + Query.SWITCHSENSOR + " pattern supported!");
		}
	}
	
	public abstract Long[] toArray();
}
