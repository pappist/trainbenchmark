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

package hu.bme.mit.trainbenchmark.benchmark.drools.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.BenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.drools.ResultListener;
import hu.bme.mit.trainbenchmark.benchmark.util.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.emf.FileBroker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message.Level;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.rule.LiveQuery;

import Concept.ConceptPackage;
import Concept.IndividualContainer;

public abstract class Drools6BenchmarkCase<T> implements BenchmarkCase {

	protected String fileName;
	protected BenchmarkResult bmr;
	protected Resource resource;
	protected IndividualContainer pack;
	protected LiveQuery query;
	protected BenchmarkConfig bc;
	protected KieSession ksession;
	protected ResultListener<T> listener;
	protected List<T> invalids;

	@Override
	public String getTool() {
		return "Drools6";
	}

	protected void readKnowledgeBase() throws Exception {
		KieServices kieServices = KieServices.Factory.get();

		KieFileSystem kfs = kieServices.newKieFileSystem();
		String queryFile = bc.getWorkspacePath() + "/hu.bme.mit.trainbenchmark.benchmark.drools6/src/main/resources/queries/" + getName()
				+ ".drl";
		File file = new File(queryFile);
		if (!file.exists()) {
			throw new IOException("Query file not found: " + queryFile);
		}
		kfs.write("src/main/resources/KBase1/oneQuery.drl", kieServices.getResources().newFileSystemResource(queryFile));

		KieBuilder kieBuilder = kieServices.newKieBuilder(kfs);
		kieBuilder.buildAll();
		if (kieBuilder.getResults().hasMessages(Level.ERROR)) {
			throw new RuntimeException("Build Errors:\n" + kieBuilder.getResults().toString());
		}

		KieContainer kContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
		ksession = kContainer.newKieSession();

	}

	@Override
	public void init(BenchmarkConfig bc) throws IOException {
		bmr = new BenchmarkResult(getTool(), getName());
		bmr.setBenchmarkConfig(bc);

		this.bc = bc;
		if (bc.isBenchmarkMode()) {
			Util.freeCache(bc);
		}
	}

	@Override
	public void load() throws FileNotFoundException, IOException {
		bmr.startStopper();
		ResourceSet resourceSet = new ResourceSetImpl();
		ConceptPackage.eINSTANCE.eClass();
		URI resourceURI = FileBroker.getEMFUri(bc.getBenchmarkArtifact());
		resource = resourceSet.getResource(resourceURI, true);

		// change Drools knowledge base based on EMF notifications
		try {
			query = null;

			readKnowledgeBase();

			EObject eObject = null;
			for (TreeIterator<EObject> tIterator = resource.getAllContents(); tIterator.hasNext();) {
				eObject = tIterator.next();
				ksession.insert(eObject);
			}

			EContentAdapter adapter = new EContentAdapter() {
				public void notifyChanged(Notification notification) {
					super.notifyChanged(notification);
					EObject notifier = (EObject) notification.getNotifier();
					FactHandle notifierFH = ksession.getFactHandle(notifier);
					int event = notification.getEventType();

					switch (event) {
					case Notification.REMOVING_ADAPTER:
						break;
					case Notification.MOVE:
						break; // currently no support for ordering
					case Notification.ADD:
					case Notification.ADD_MANY:
					case Notification.REMOVE:
					case Notification.REMOVE_MANY:
					case Notification.RESOLVE:
					case Notification.UNSET:
					case Notification.SET:
						ksession.update(notifierFH, notifier);
						break;
					}
				}

				@Override
				protected void addAdapter(Notifier notifier) {
					super.addAdapter(notifier);

					ksession.insert(notifier);
				}

				@Override
				protected void removeAdapter(Notifier notifier) {
					super.removeAdapter(notifier);

					FactHandle changedFH = ksession.getFactHandle(notifier);
					ksession.delete(changedFH);
				}
			};
			resource.eAdapters().add(adapter);
		} catch (Exception e) {
			throw new IOException(e);
		}

		if (resource.getContents().size() > 0 && resource.getContents().get(0) instanceof IndividualContainer) {
			pack = (IndividualContainer) resource.getContents().get(0);
		}

		bmr.setReadTime();
	}

	@Override
	public void check() {
		bmr.startStopper();
		bmr.addInvalid(doCCheck());
		bmr.addCheckTime();
	}

	protected abstract int doCCheck();

	@Override
	public void measureMemory() {
		Util.runGC();
		bmr.addMemoryBytes(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
	}

	@Override
	public void destroy() {
		query.close();
	}

	@Override
	public BenchmarkResult getBenchmarkResult() {
		return bmr;
	}

	@Override
	public int getResultSize() {
		return invalids.size();
	}

}