package cartaylor.Categories;

import cartaylor.Category;

public class Engine implements Category{

	public Engine() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return this.getClass().getName();
	}

}
