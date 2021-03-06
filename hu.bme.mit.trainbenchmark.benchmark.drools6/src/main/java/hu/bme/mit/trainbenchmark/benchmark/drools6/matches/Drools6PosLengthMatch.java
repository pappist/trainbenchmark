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
package hu.bme.mit.trainbenchmark.benchmark.drools6.matches;

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SEGMENT;
import hu.bme.mit.trainbenchmark.emf.matches.EMFPosLengthMatch;
import hu.bme.mit.trainbenchmark.railway.Segment;

import org.kie.api.runtime.rule.Row;

public class Drools6PosLengthMatch extends EMFPosLengthMatch {

	public Drools6PosLengthMatch(final Row match) {
		super((Segment) match.get(VAR_SEGMENT));
	}

}
