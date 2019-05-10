package cartaylor;

/**
 * Thrown when there are incompatible {@link Part} or missing {@link Part} in a configuration
 *
 */
public class ConflictingRuleException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ConflictingRuleException() {
		super();
	}
	public ConflictingRuleException(String message) {
		super(message);
	}
}
