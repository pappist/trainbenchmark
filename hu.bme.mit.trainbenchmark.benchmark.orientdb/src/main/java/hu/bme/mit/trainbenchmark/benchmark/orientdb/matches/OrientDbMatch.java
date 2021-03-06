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

import hu.bme.mit.trainbenchmark.constants.Query;

import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.pipes.util.structures.Row;

public abstract class OrientDbMatch {

	protected Row match;

	public OrientDbMatch(final Row match) {
		this.match = match;
	}

	public abstract Vertex[] toArray();

	public static OrientDbMatch createMatch(final Query query, final Row row) {
		switch (query) {
		case CONNECTEDSEGMENTS:
			return new OrientDbConnectedSegmentsMatch(row);
		case POSLENGTH:
			return new OrientDbPosLengthMatch(row);
		case ROUTESENSOR:
			return new OrientDbRouteSensorMatch(row);
		case SEMAPHORENEIGHBOR:
			return new OrientDbSemaphoreNeighborMatch(row);
		case SWITCHSENSOR:
			return new OrientDbSwitchSensorMatch(row);
		case SWITCHSET:
			return new OrientDbSwitchSetMatch(row);
		default:
			throw new UnsupportedOperationException("Pattern not supported: " + query);
		}
	}

}
