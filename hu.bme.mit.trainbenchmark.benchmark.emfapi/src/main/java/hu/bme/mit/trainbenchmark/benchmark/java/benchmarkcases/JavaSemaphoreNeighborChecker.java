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

package hu.bme.mit.trainbenchmark.benchmark.java.benchmarkcases;

import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.emf.matches.EMFSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Semaphore;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.TrackElement;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

public class JavaSemaphoreNeighborChecker extends JavaChecker<EMFSemaphoreNeighborMatch> {

	public JavaSemaphoreNeighborChecker(final EMFDriver emfDriver) {
		super(emfDriver);
	}

	@Override
	public Collection<EMFSemaphoreNeighborMatch> check() {
		matches = new ArrayList<>();
		final TreeIterator<EObject> contents = emfDriver.getContainer().eAllContents();
		while (contents.hasNext()) {
			final EObject eObject = contents.next();

			if (eObject instanceof Route) {
				final Route route1 = (Route) eObject;
				checkRoute(route1);
			}
		}

		return matches;
	}

	private void checkRoute(final Route route1) {
		final Semaphore semaphore = route1.getExit();
		if (semaphore == null) {
			return;
		}
		for (final Sensor sensor1 : route1.getDefinedBy()) {
			for (final TrackElement te1 : sensor1.getElements()) {
				for (final TrackElement te2 : te1.getConnectsTo()) {
					final Sensor sensor2 = te2.getSensor();

					if (sensor2 == null) {
						continue;
					}

					// reverse navigation on the (sensor2)<-[definedBy]-(route2) edge
					final EObject container = sensor2.eContainer();
					if (!(container instanceof Route)) {
						continue;
					}

					final Route route2 = (Route) container;

					// route1 != route2
					if (route1.equals(route2)) {
						continue;
					}

					// (route2)-[entry]->(semaphore) NAC
					if (!semaphore.equals(route2.getEntry())) {
						matches.add(new EMFSemaphoreNeighborMatch(semaphore, route1, route2, sensor1, sensor2, te1, te2));
						return;
					}
				}
			}
		}

		return;
	}

}
