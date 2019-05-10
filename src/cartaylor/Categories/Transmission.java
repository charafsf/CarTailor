package cartaylor.Categories;

import cartaylor.Category;

public class Transmission implements Category{
	
	public Transmission() {
	}

	@Override
	public String getName() {
		return this.getClass().getName();
	}

}
