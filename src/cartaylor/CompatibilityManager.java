package cartaylor;

import java.util.Collection;

/**
 * Deals with the compatibilities between {@link PartType}
 *
 */
public  interface CompatibilityManager extends CompatibilityChecker
{
	/**
	 * Removes a {@link PartType} from the lists of incompatibilities and requirements
	 * @param partType, the object that is removed
	 */
	public void removePartType(PartType partType);
	
	/**
	 * Adds a list of required PartType for a specified PartType
	 * @param partType which has requirements
	 * @param partTypes , a Collection of {@link PartType} required for the first parameter
	 * @throws ConflictingRuleException
	 */
	public void addRequirements(PartType partType, Collection<PartType> partTypes) throws ConflictingRuleException;
	
	/**
	 * Adds a list of incompatible PartType for a specified PartType
	 * @param partType which has incompatibilities
	 * @param partTypes, a Collection of {@link PartType} incompatible with the first parameter
	 * @throws ConflictingRuleException
	 */
	public void addIncompatibilities(PartType partType, Collection<PartType> partTypes) throws ConflictingRuleException;
}

