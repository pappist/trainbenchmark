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

package hu.bme.mit.trainbenchmark.benchmark.neo4j.benchmarkcases.xform;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SEGMENT_LENGTH;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.TransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.benchmarkcases.PosLength;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

public class PosLengthXForm extends PosLength implements TransformationBenchmarkCase {

	@Override
	public void modify() {
		long nElemToModify = Util.calcModify(bc, bc.getModificationConstant(), bmr);
		bmr.addModifyParams(nElemToModify);

		final long start = System.nanoTime();
		long startEdit;

		try (Transaction tx = graphDb.beginTx()) {
			// query the model
			final Random random = bmr.getRandom();
			final int size = invalids.size();
			final List<Node> itemsToModify = new ArrayList<>();

			if (size < nElemToModify) {
				nElemToModify = size;
			}

			for (int i = 0; i < nElemToModify; i++) {
				final int rndTarget = random.nextInt(size);
				final Node segment = new ArrayList<>(invalids).get(rndTarget);
				itemsToModify.add(segment);
			}

			// edit
			startEdit = System.nanoTime();

			for (final Node segment : itemsToModify) {
				final Integer segmentLength = (Integer) segment.getProperty(SEGMENT_LENGTH);
				segment.setProperty(SEGMENT_LENGTH, -(segmentLength - 1));
			}

			// finishing Neo4j transaction
			tx.success();
		}

		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

}