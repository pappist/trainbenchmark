package hu.bme.mit.trainbenchmark.benchmark.iqdyarn.checker;

import hu.bme.mit.trainbenchmark.benchmark.checker.Checker;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.iqdyarn.driver.IQDYarnDriver;
import hu.bme.mit.trainbenchmark.benchmark.iqdyarn.matches.IQDYarnMatch;
import hu.bme.mit.trainbenchmark.constants.Query;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;

public class IQDYarnChecker extends Checker<IQDYarnMatch>{

	protected final IQDYarnDriver driver;
	protected final Query query;
	protected final String recipePath;
	
	public IQDYarnChecker(final IQDYarnDriver driver, final BenchmarkConfig bc) throws URISyntaxException {
		super();
		this.driver = driver;
		this.query = bc.getQuery();
		
		URL file = getClass().getClassLoader().getResource("queries/" + bc.getQuery() + ".rdfiq.recipe");
		recipePath = file.toString();
	}

	@Override
	public Collection<IQDYarnMatch> check() throws Exception {
		return driver.runQuery(query, recipePath);
	}
	
	
	
}
