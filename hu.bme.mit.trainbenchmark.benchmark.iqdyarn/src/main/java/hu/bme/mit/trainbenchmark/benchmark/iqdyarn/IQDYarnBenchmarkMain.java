package hu.bme.mit.trainbenchmark.benchmark.iqdyarn;

import java.io.IOException;

import org.apache.commons.cli.ParseException;

public class IQDYarnBenchmarkMain {

	public static void main(String[] args) throws ParseException, IOException {
		IQDYarnBenchmarkLogic benchmarkLogic = new IQDYarnBenchmarkLogic(args);
		benchmarkLogic.runBenchmark();
	}

}
