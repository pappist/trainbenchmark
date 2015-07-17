package hu.bme.mit.trainbenchmark.benchmark.iqdyarn.transformations.repair;

import hu.bme.mit.trainbenchmark.benchmark.iqdyarn.driver.IQDYarnDriver;
import hu.bme.mit.trainbenchmark.benchmark.iqdyarn.matches.IQDYarnMatch;
import hu.bme.mit.trainbenchmark.benchmark.iqdyarn.transformations.IQDYarnTransformation;

public abstract class IQDYarnTransformationRepair<M extends IQDYarnMatch> extends IQDYarnTransformation<M>{

	protected IQDYarnTransformationRepair(final IQDYarnDriver driver) {
		super(driver);
	}

}
