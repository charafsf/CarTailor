package cartaylor;

import java.util.Collection;

/**
 * Defines the methods to check the compatibility between {@link PartType} objects
 *
 */
public interface CompatibilityChecker {
	/**
	 * @param partType
	 * @return A collection of {@link PartType} incompatible with the parameter
	 */
	public Collection<PartType> getIncompatibleParts(PartType partType);
	/**
	 * @param partType
	 * @return  A collection of {@link PartType} required for the parameter
	 */
	public Collection<PartType> getRequiredParts(PartType partType);
}
