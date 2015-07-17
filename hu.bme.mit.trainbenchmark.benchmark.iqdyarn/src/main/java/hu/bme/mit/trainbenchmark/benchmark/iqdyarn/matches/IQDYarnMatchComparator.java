package hu.bme.mit.trainbenchmark.benchmark.iqdyarn.matches;

import hu.bme.mit.trainbenchmark.benchmark.matches.LongComparator;
import hu.bme.mit.trainbenchmark.benchmark.matches.MatchComparator;

public class IQDYarnMatchComparator extends MatchComparator<IQDYarnMatch, Long>{

	protected LongComparator lc = new LongComparator();
	
	@Override
	public int compare(IQDYarnMatch o1, IQDYarnMatch o2) {
		final Long[] m1 = o1.toArray();
		final Long[] m2 = o2.toArray();
		return compareArrays(m1, m2, lc);
	}

}
