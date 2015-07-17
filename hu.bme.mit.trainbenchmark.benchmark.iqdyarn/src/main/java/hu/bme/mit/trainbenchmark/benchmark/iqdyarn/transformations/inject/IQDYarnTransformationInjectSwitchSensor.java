package hu.bme.mit.trainbenchmark.benchmark.iqdyarn.transformations.inject;

import hu.bme.mit.trainbenchmark.benchmark.iqdyarn.driver.IQDYarnDriver;

import java.util.Collection;

public class IQDYarnTransformationInjectSwitchSensor extends IQDYarnTransformationInject {

	public IQDYarnTransformationInjectSwitchSensor(final IQDYarnDriver driver) {
		super(driver);
	}

	@Override
	public void rhs(Collection<Long> switches) throws Exception {
		
	}

}
