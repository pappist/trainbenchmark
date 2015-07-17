package hu.bme.mit.trainbenchmark.benchmark.iqdyarn.checker;

import hu.bme.mit.trainbenchmark.benchmark.checker.Checker;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.iqdyarn.driver.IQDYarnDriver;
import hu.bme.mit.trainbenchmark.benchmark.iqdyarn.matches.IQDYarnMatch;
import hu.bme.mit.trainbenchmark.constants.Query;

import java.util.Collection;

public class IQDYarnChecker extends Checker<IQDYarnMatch>{

	protected final IQDYarnDriver driver;
	protected final Query query;
	protected final String recipePath;
	
	public IQDYarnChecker(final IQDYarnDriver driver, final BenchmarkConfig bc) {
		super();
		this.driver = driver;
		this.query = bc.getQuery();
		
		recipePath = bc.getWorkspacePath() + "/hu.bme.mit.trainbenchmark.benchmark.iqdyarn/src/main/resources/queries/" + bc.getQuery()
				+ ".rdfiq.recipe";
	}

	@Override
	public Collection<IQDYarnMatch> check() throws Exception {
		return driver.runQuery(query, recipePath);
	}
	
	
	
}
