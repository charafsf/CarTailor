package cartaylor;

import java.util.Collection;

public  interface Configurator extends Configuration
{
	public Configuration getConfiguration() ;
	public Collection<Category> getCategories();
	public Collection<PartType> getVariantsForCategory(Category c);
	//public Collection<PartType> getIncompatibleParts(PartType pt);
}

