package hu.bme.mit.trainbenchmark.benchmark.iqdyarn;

import org.apache.commons.cli.ParseException;

import hu.bme.mit.trainbenchmark.benchmark.rdf.RDFBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.scenarios.AbstractBenchmarkLogic;

public class IQDYarnBenchmarkLogic extends AbstractBenchmarkLogic {
	
	protected RDFBenchmarkConfig rbc;
	
	public IQDYarnBenchmarkLogic(final String[] args) throws ParseException {
		bc = rbc = new RDFBenchmarkConfig(args, "IQDYarn");
	}
	
	public IQDYarnBenchmarkLogic(final RDFBenchmarkConfig rbc) {
		super(rbc);
		this.rbc = rbc;
	}
	
}
