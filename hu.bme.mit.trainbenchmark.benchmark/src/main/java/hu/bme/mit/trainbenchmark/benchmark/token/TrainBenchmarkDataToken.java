/*******************************************************************************
 * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/

package hu.bme.mit.trainbenchmark.benchmark.token;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.AbstractBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import eu.mondo.sam.core.DataToken;

public class TrainBenchmarkDataToken implements DataToken {

	private AbstractBenchmarkCase<?, ?> benchmarkCase;

	private BenchmarkConfig config;

	@Override
	public void init() {
	}

	@Override
	public void destroy() {
	}

	public AbstractBenchmarkCase<?, ?> getBenchmarkCase() {
		return benchmarkCase;
	}

	public void setBenchmarkCase(AbstractBenchmarkCase<?, ?> benchmarkCase) {
		this.benchmarkCase = benchmarkCase;
	}

	public BenchmarkConfig getConfig() {
		return config;
	}

	public void setConfig(BenchmarkConfig config) {
		this.config = config;
	}

}
