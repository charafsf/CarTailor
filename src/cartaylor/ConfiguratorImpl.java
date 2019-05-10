package cartaylor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;


/**
 *
 *
 */
public class ConfiguratorImpl implements Configurator{
	//configuration
	private Collection<Part> configuration;
	//configurator
	private Collection<Category> categories;
	private Collection<PartType> partTypes;
	//compatibilityManager
	private CompatibilityManagerImpl compatibilityManager;
	private Collection<Observer> observers;
	
	public ConfiguratorImpl() {
		this.configuration = new ArrayList<Part>();
		this.categories = new ArrayList<Category>();
		this.partTypes = new ArrayList<PartType>();
		this.observers = new ArrayList<Observer>();
		this.compatibilityManager = new CompatibilityManagerImpl();
	}
	
	
	/**
	 * @return a collection of the {@link Part} of the current configuration
	 */
	public Collection<Part> getConfigurationList(){
		return this.configuration;
	}
	
	
	/**
	 * @return a collection of created {@link PartType}
	 */
	public Collection<PartType> getPartTypes(){
		return this.partTypes;
	}
	
	public CompatibilityManagerImpl getCompatibilityManager() {
		return this.compatibilityManager;
	}

	@Override
	public Part getSelectionForCategory(Category parameter) {
		for(Part p : configuration) {
			if(p.getPartType().getCategory()==parameter) {
				return p;
			}
		}
		return null;
	}

	@Override
	public boolean isComplete() {
		boolean complete = true;
		boolean found = false;
		for(Category c : categories) {
			for(Part p : configuration) {
				if(p.getPartType().getCategory()==c) {
					found = true;
				}
			}
			if(!found) {
				complete = false;
			}
			found = false;
		}
		return complete;
	}

	@Override
	public boolean isValid() {
		boolean valid = true;
		for(Part p : configuration) {
			Collection<PartType> incompatiblities= compatibilityManager.getIncompatibleParts(p.getPartType());
			Collection<PartType> requirements= compatibilityManager.getRequiredParts(p.getPartType());
			//check that there are no incompatible parts in the configuration
			for(Part p1 : configuration) {
				if(p1!=p && (incompatiblities.contains(p1.getPartType()))){
					valid = false;
				}
			}
			//check that we don't miss required parts in the configuration
			for(PartType p1 : requirements) {
				boolean b =false;
				for(Part p2 : configuration) {
					if(p1==p2.getPartType()) {
						b = true;
					}
				}
				if(!b) {
					valid = false;
				}
			}
		}
		return valid;
	}

	@Override
	public void selectPart(PartType p) {
		//choose a part for the configuration (remove part of same category if exists)
		Category cat = p.getCategory();
		Part changed=null;
		for (Part part:configuration) {
			if(part.getPartType().getCategory()==cat) {
				changed = part;
			}
		}
		try {
			configuration.add(p.newInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(changed != null) {
				configuration.remove(changed);
			}
		}
	}


	@Override
	public Configuration getConfiguration() {
		throw new NotImplementedException();
	}
	
	@Override
	public Collection<Category> getCategories() {
		return categories;
	}

	@Override
	public Collection<PartType> getVariantsForCategory(Category c) {
		ArrayList<PartType> variants = new ArrayList<>();
		for(PartType p : partTypes) {
			if(p.getCategory()==c) {
				variants.add(p);
			}
		}
		return variants;
	}


	@Override
	public void register(Class<? extends Observer> obs) {
		//what is this supposed to do???
		// TODO Auto-generated method stub
	}


	@Override
	public Object getValue() {
		return this;
	}

	@Override
	public boolean isRegistered(Observer o) {
		boolean registered = false;
		for(Observer ob:observers) {
			if(ob==o) {
				registered = true;
			}
		}
		return registered;
	}

	@Override
	public void register(Observer obs) {
		Objects.requireNonNull(obs);
		if(!observers.contains(obs)) {
			observers.add(obs);
		}		
	}

	@Override
	public void unregister(Observer obs) {
		Objects.requireNonNull(obs);
		if(observers.contains(obs)) {
			observers.remove(obs);
		}
	}

	@Override
	public void sendNotification() {
		for(Observer o:observers) {
			o.update(this);
		}
	}

	@Override
	public void update(Observable observable) {
		//to verify
		if(observable.getClass()==compatibilityManager.getClass()) {
			compatibilityManager = (CompatibilityManagerImpl) observable;
		}
	}
}
