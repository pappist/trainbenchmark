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

package hu.bme.mit.trainbenchmark.benchmark.sesame.benchmarkcases.user;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.TransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.sesame.benchmarkcases.SesameBenchmarkCase;

import java.io.IOException;

public class RouteSensor extends SesameBenchmarkCase implements TransformationBenchmarkCase {

	@Override
	public void modify() throws IOException {
		new hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.user.RouteSensor(bmr, driver).performTransformation();
	}

}