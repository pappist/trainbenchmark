package hu.bme.mit.trainbenchmark.benchmark.iqdyarn.transformations;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.iqdyarn.driver.IQDYarnDriver;
import hu.bme.mit.trainbenchmark.benchmark.iqdyarn.transformations.inject.IQDYarnTransformationInjectSwitchSensor;
import hu.bme.mit.trainbenchmark.benchmark.iqdyarn.transformations.repair.IQDYarnTransformationRepairSwitchSensor;
import hu.bme.mit.trainbenchmark.constants.Query;
import hu.bme.mit.trainbenchmark.constants.Scenario;

public abstract class IQDYarnTransformation<M> extends Transformation<M> {

	protected IQDYarnDriver iqdDriver;

	protected IQDYarnTransformation(final IQDYarnDriver driver) {
		this.iqdDriver = driver;
	}

	public static Transformation<?> newInstance(final IQDYarnDriver iqdDriver, 
			final Query query, final Scenario scenario) {
		switch (scenario) {
			case INJECT:
				switch (query) {
					case SWITCHSENSOR:
						return new IQDYarnTransformationInjectSwitchSensor(iqdDriver);
					default:
						throw new UnsupportedOperationException(query + " query is not supported yet!");
				}
			case REPAIR:
				switch (query) {
					case SWITCHSENSOR:
						return new IQDYarnTransformationRepairSwitchSensor(iqdDriver);
					default:
						throw new UnsupportedOperationException(query + " query is not supported yet!");
				}
			default:
				throw new UnsupportedOperationException(scenario + " scenario is not supported yet!");
		}
	}
}
