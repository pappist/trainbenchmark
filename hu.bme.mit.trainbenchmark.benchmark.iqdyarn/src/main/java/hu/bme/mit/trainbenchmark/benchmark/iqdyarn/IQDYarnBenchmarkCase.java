package hu.bme.mit.trainbenchmark.benchmark.iqdyarn;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.iqdyarn.checker.IQDYarnChecker;
import hu.bme.mit.trainbenchmark.benchmark.iqdyarn.driver.IQDYarnDriver;
import hu.bme.mit.trainbenchmark.benchmark.iqdyarn.matches.IQDYarnMatch;
import hu.bme.mit.trainbenchmark.benchmark.iqdyarn.matches.IQDYarnMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.iqdyarn.transformations.IQDYarnTransformation;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RDFBenchmarkConfig;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Comparator;

public class IQDYarnBenchmarkCase extends AbstractBenchmarkCase<IQDYarnMatch, Long>{

	protected IQDYarnDriver iqdDriver;
	protected RDFBenchmarkConfig rbc;
	
	@Override
	protected void init() throws MalformedURLException, URISyntaxException {
		this.rbc = (RDFBenchmarkConfig) bc;

		driver = iqdDriver = new IQDYarnDriver();
		checker = new IQDYarnChecker(iqdDriver, bc);
		transformation = IQDYarnTransformation.newInstance(iqdDriver, bc.getQuery(), bc.getScenario());
	}
	
	@Override
	protected Comparator<?> getMatchComparator() {
		return new IQDYarnMatchComparator();
	}
	
}
