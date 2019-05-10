package cartaylor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;


/**
 * Concrete class of {@link CompatibilityManager}
 *
 */
public class CompatibilityManagerImpl implements CompatibilityManager{
	private Map<PartType, Collection<PartType>> incompatibleParts;
	private Map<PartType, Collection<PartType>> requiredParts;
	
	public CompatibilityManagerImpl() {
		this.incompatibleParts = new HashMap<>();
		this.requiredParts = new HashMap<>();
	}

	@Override
	public void removePartType(PartType partType) {
		throw new NotImplementedException();
	}

	@Override
	public void addRequirements(PartType reference, Collection<PartType> requiredParts) throws ConflictingRuleException {
		Objects.requireNonNull(reference);
		Objects.requireNonNull(requiredParts);
		Collection<PartType> inc = getIncompatibleParts(reference);
		for(PartType ptype : requiredParts) {
			if(inc.contains(ptype)) {
				throw new ConflictingRuleException();
			}
			if(getIncompatibleParts(ptype).contains(reference)) {
				throw new ConflictingRuleException();
			}
		}
		this.requiredParts.put(reference, requiredParts);
	}

	@Override
	public void addIncompatibilities(PartType reference, Collection<PartType> incompatibleParts) throws ConflictingRuleException {
		Objects.requireNonNull(reference);
		Objects.requireNonNull(incompatibleParts);
		Collection<PartType> reqs = getRequiredParts(reference);
		for(PartType ptype : incompatibleParts) {
			if(reqs.contains(ptype)) {
				throw new ConflictingRuleException();
			}
			if(getRequiredParts(ptype).contains(reference)) {
				throw new ConflictingRuleException();
			}
		}
		this.incompatibleParts.put(reference, incompatibleParts);
	}

	@Override
	public Collection<PartType> getIncompatibleParts(PartType partType) {
		if(!incompatibleParts.containsKey(partType)) {
			//throw new NullPointerException(partType+ " n'est pas présent dans la liste des incompatibilités du Manager");
			return new ArrayList<PartType>();
		}
		return incompatibleParts.get(partType);
	}

	@Override
	public Collection<PartType> getRequiredParts(PartType partType) {
		if(!requiredParts.containsKey(partType)) {
			//throw new NullPointerException(partType+ " n'est pas présent dans la liste des pièces requises du Manager");
			return new ArrayList<PartType>();
		}
		return requiredParts.get(partType);
	}

}
