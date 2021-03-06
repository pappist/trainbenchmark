/*******************************************************************************
 * Copyright (c) 2010-2015, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.orientdb.matches;

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_ROUTE1;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_ROUTE2;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEMAPHORE;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SENSOR1;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SENSOR2;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_TE1;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_TE2;
import hu.bme.mit.trainbenchmark.benchmark.matches.SemaphoreNeighborMatch;

import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.pipes.util.structures.Row;

public class OrientDbSemaphoreNeighborMatch extends OrientDbMatch implements SemaphoreNeighborMatch{

	public OrientDbSemaphoreNeighborMatch(final Row match) {
		super(match);
	}

	@Override
	public Vertex getSemaphore() {
		return (Vertex) match.getColumn(VAR_SEMAPHORE);
	}

	@Override
	public Vertex getRoute1() {
		return (Vertex) match.getColumn(VAR_ROUTE1);
	}

	@Override
	public Vertex getRoute2() {
		return (Vertex) match.getColumn(VAR_ROUTE2);
	}

	@Override
	public Vertex getSensor1() {
		return (Vertex) match.getColumn(VAR_SENSOR1);
	}

	@Override
	public Vertex getSensor2() {
		return (Vertex) match.getColumn(VAR_SENSOR2);
	}

	@Override
	public Vertex getTe1() {
		return (Vertex) match.getColumn(VAR_TE1);
	}

	@Override
	public Vertex getTe2() {
		return (Vertex) match.getColumn(VAR_TE2);
	}

	@Override
	public Vertex[] toArray() {
		return new Vertex[] { getSemaphore(), getRoute1(), getRoute2(), getSensor1(), getSensor2(), getTe1(), getTe2() };
	}
	
}
