package structural.decorator;

public class BracesDecorator extends Decorator {

	public BracesDecorator(Component component) {
		super(component);
	}

	@Override
	public String operation() {
		String text = component.operation();
		return "{" + text + "}";
	}

}
