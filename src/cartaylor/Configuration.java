package cartaylor;




/**
 * Stores the different {@link Part} for a specific configuration
 * 
 */
public  interface Configuration extends Observable, Observer
{	
	/**
	 * @param Takes a {@link Category}
	 * @return the {@link Part} with the same category as passed in parameter
	 */
	public Part getSelectionForCategory(Category category) ;
	
	/**
	 * Checks if the configuration is complete, there is one {@link Part} for each existing {@link Category}
	 * @return true if the configuration is complete, false if not
	 */
	public boolean isComplete() ;
	
	/**
	 * Checks if the configuration is valid, there are not 2 incompatible {@link Part} and it contains all the required {@link Part} for each one of them
	 * @return true if the configuration is valid, false if not
	 */
	public boolean isValid() ;

	/**
	 * Adds a {@link Part} to our configuration
	 * @param parameter of type {@link PartType}
	 */
	public void selectPart(PartType parameter) ;
	/**
	 * Not implemented yet
	 */
	public void register(Class <? extends Observer> obs);

}

