package structural.decorator;

public class ParenthesisDecorator extends Decorator {

	public ParenthesisDecorator(Component component) {
		super(component);
	}

	@Override
	public String operation() {
		String text = component.operation();
		return "{" + text + "}";
	}

}
