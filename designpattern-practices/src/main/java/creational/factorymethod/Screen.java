package creational.factorymethod;

// Creator
public abstract class Screen {
	// factoryMethod
	public abstract Drawable getShape(int shapeNo);
	
	// operation
	public void display(int shapeNo) {
		Drawable drawable = getShape(shapeNo);

		if(drawable == null) {
			System.out.println("도형이 없습니다.");
		}
		
		drawable.draw();
	}
}