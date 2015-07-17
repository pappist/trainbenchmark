package hu.bme.mit.trainbenchmark.benchmark.iqdyarn.transformations.inject;

import hu.bme.mit.trainbenchmark.benchmark.iqdyarn.driver.IQDYarnDriver;
import hu.bme.mit.trainbenchmark.benchmark.iqdyarn.transformations.IQDYarnTransformation;

public abstract class IQDYarnTransformationInject extends IQDYarnTransformation<Long>{

	protected IQDYarnTransformationInject(IQDYarnDriver driver) {
		super(driver);
	}
	
}
