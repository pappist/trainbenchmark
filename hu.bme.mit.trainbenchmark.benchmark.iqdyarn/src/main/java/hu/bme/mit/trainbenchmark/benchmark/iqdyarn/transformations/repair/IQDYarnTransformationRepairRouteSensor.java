package hu.bme.mit.trainbenchmark.benchmark.iqdyarn.transformations.repair;

import hu.bme.mit.incqueryd.engine.rete.dataunits.ChangeSet;
import hu.bme.mit.incqueryd.engine.rete.dataunits.ChangeType;
import hu.bme.mit.incqueryd.engine.rete.dataunits.Tuple;
import hu.bme.mit.trainbenchmark.benchmark.iqdyarn.driver.IQDYarnDriver;
import hu.bme.mit.trainbenchmark.benchmark.iqdyarn.matches.IQDYarnRouteSensorMatch;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.ROUTE;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class IQDYarnTransformationRepairRouteSensor extends IQDYarnTransformationRepair<IQDYarnRouteSensorMatch>{

	public IQDYarnTransformationRepairRouteSensor(IQDYarnDriver driver) {
		super(driver);
	}

	@Override
	public void rhs(Collection<IQDYarnRouteSensorMatch> matches) throws Exception {
		HashMap<String, ChangeSet> changes = new HashMap<String, ChangeSet>();
		HashSet<Tuple> tupleSet = new HashSet<Tuple>();
		
		for(final IQDYarnRouteSensorMatch match : matches) {
			Long routeId = match.getRoute();
			Long sensorId = match.getSensor();
			
			Tuple routeDefinition = new Tuple(routeId, sensorId);
			tupleSet.add(routeDefinition);
		}
		
		ChangeSet changeSet = new ChangeSet(tupleSet, ChangeType.POSITIVE);
		changes.put(ROUTE + "_routeDefinition", changeSet);
		iqdDriver.loadChanges(changes);
	}

}
