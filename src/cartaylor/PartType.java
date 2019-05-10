package cartaylor;

import java.lang.reflect.InvocationTargetException;

/**
 * A model to instantiate {@link Part}
 *
 */
public interface PartType
{
    /**
     * @return the {@link Category} of the object
     */
    public Category getCategory();
    
    /**
     * @return the name of the part
     */
    public String getName();
	/**
	 * @return {@link Part}, a new instance of this object
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public Part newInstance()throws NoSuchMethodException, SecurityException,InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException ;
}

