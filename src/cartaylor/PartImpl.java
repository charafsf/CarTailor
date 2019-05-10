package cartaylor;

/**
 * Concrete class of {@link Part}
 *
 */
public class PartImpl implements Part{
	private PartType partType;
	
	public PartImpl(PartType pt) {
		this.partType = pt;
	}
	
	@Override
	public PartType getPartType() {
		return this.partType;
	}

}
