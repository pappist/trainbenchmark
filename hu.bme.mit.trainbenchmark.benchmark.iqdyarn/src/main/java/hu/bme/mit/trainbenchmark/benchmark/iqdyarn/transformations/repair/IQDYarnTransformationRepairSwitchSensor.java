package hu.bme.mit.trainbenchmark.benchmark.iqdyarn.transformations.repair;

import hu.bme.mit.incqueryd.engine.rete.dataunits.ChangeSet;
import hu.bme.mit.incqueryd.engine.rete.dataunits.ChangeType;
import hu.bme.mit.incqueryd.engine.rete.dataunits.Tuple;
import hu.bme.mit.trainbenchmark.benchmark.iqdyarn.driver.IQDYarnDriver;
import hu.bme.mit.trainbenchmark.benchmark.iqdyarn.matches.IQDYarnSwitchSensorMatch;
import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SWITCH;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class IQDYarnTransformationRepairSwitchSensor extends IQDYarnTransformationRepair<IQDYarnSwitchSensorMatch>{

	public IQDYarnTransformationRepairSwitchSensor(final IQDYarnDriver driver) {
		super(driver);
	}

	@Override
	public void rhs(Collection<IQDYarnSwitchSensorMatch> matches) throws Exception {
		
		// XXX
		HashMap<String, ChangeSet> changes = new HashMap<String, ChangeSet>();
		Tuple switchTuple = new Tuple(iqdDriver.getNewVertexId());
		HashSet<Tuple> tupleSet = new HashSet<Tuple>();
		tupleSet.add(switchTuple);
		ChangeSet changeSet = new ChangeSet(tupleSet, ChangeType.POSITIVE);
		changes.put(SWITCH, changeSet);
		iqdDriver.loadChanges(changes);
	}

}
