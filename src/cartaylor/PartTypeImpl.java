package cartaylor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Concrete class of {@link PartType}
 *
 */
public class PartTypeImpl implements PartType{
	private String name;
	private Category category;
	private Class <? extends Part> classRef;
	
	public PartTypeImpl(Category category, Class <? extends Part> classRef) {
		this.name = classRef.getName();
		this.category= category;
		this.classRef = classRef;
	}
	
	@Override
	public Part newInstance() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Constructor<? extends Part> constructor;
		constructor = classRef.getConstructor(PartType.class);
		return constructor.newInstance(this);
	}
	
	@Override
	public Category getCategory() {
		return this.category;
	}

	@Override
	public String getName() {
		return this.name;
	}

}
