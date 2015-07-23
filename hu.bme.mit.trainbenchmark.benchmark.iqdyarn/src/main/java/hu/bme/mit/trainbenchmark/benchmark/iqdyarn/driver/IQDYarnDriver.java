package hu.bme.mit.trainbenchmark.benchmark.iqdyarn.driver;

import hu.bme.mit.incqueryd.coordinator.client.IQDYarnClient;
import hu.bme.mit.incqueryd.engine.rete.dataunits.ChangeSet;
import hu.bme.mit.incqueryd.engine.rete.dataunits.Tuple;
import hu.bme.mit.trainbenchmark.benchmark.iqdyarn.matches.IQDYarnMatch;
import hu.bme.mit.trainbenchmark.benchmark.matches.LongComparator;
import hu.bme.mit.trainbenchmark.benchmark.rdf.RDFDatabaseDriver;
import hu.bme.mit.trainbenchmark.constants.Query;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.incquery.runtime.rete.recipes.RecipesPackage;
import org.eclipse.incquery.runtime.rete.recipes.ReteRecipe;

public class IQDYarnDriver extends RDFDatabaseDriver<Long> {

	private IQDYarnClient iqdClient;
	
	private HashMap<Query, ReteRecipe> recipes;
	
	public IQDYarnDriver() {
		iqdClient = new IQDYarnClient();
		iqdClient.connect();
		iqdClient.startActorSystems();
		iqdClient.startCoordinator();
		URL metamodelURL = getClass().getClassLoader().getResource("metamodel/railway.rdf");
		iqdClient.loadMetamodel(metamodelURL);
		recipes = new HashMap<Query, ReteRecipe>();
	}
	
	@Override
	public void destroy() throws Exception {
		System.out.println("Dispose driver...");
		if(iqdClient != null)
			iqdClient.dispose();
	}

	@Override
	protected boolean ask(String askQuery) throws Exception {
		throw new UnsupportedOperationException("Not implemented in IQDYarnDriver");
	}

	@Override
	public void read(String modelPathWithoutExtension) throws Exception {
		File model = new File(modelPathWithoutExtension + getExtension());
		iqdClient.loadInitialData(model.toURI().toURL());
	}

	@Override
	public List<Long> collectVertices(String type) throws Exception {
		throw new UnsupportedOperationException("Not implemented in IQDYarnDriver");
	}

	@Override
	public Comparator<Long> getElementComparator() {
		return new LongComparator();
	}
	
	@Override
	public Long getNewVertexId() throws Exception {
		long range = 10000000L;
		Random r = new Random();
		return (long)(r.nextLong()*range);
	}
	
	@Override
	public Collection<IQDYarnMatch> runQuery(Query query, String recipeURL) throws Exception {
		if(recipes.get(query) == null)
			loadRecipe(query, recipeURL);
		Collection<Tuple> tuples = iqdClient.checkQuery(recipes.get(query), getPatternName(query));
		ArrayList<IQDYarnMatch> matches = new ArrayList<IQDYarnMatch>();

		for(Tuple tuple : tuples) {
			matches.add(IQDYarnMatch.createMatch(query, tuple));
		}
		return matches;
	}
	
	public void loadChanges(Map<String, ChangeSet> changes) {
		iqdClient.loadChanges(changes);
	}

	
	// utility
	
	private void loadRecipe(Query query, String recipeURL) throws IOException {
		String extension = FilenameUtils.getExtension(recipeURL);
		RecipesPackage.eINSTANCE.eClass();
	    ResourceSet resourceSet = new ResourceSetImpl();
	    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(extension, new XMIResourceFactoryImpl());
	    Resource resource = resourceSet.createResource(URI.createURI(recipeURL));
	    resource.load(new HashMap<Object, Object>());
	    recipes.put(query, (ReteRecipe)resource.getContents().get(0));
	}
	
	private String getPatternName(final Query query) {
		return WordUtils.uncapitalize(query.toString()); 
	}
	
}
