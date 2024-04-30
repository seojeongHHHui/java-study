package structural.decorator;

public class ConcretComponent extends Component {
	private String text;
	
	public ConcretComponent(String text) {
		this.text = text;
	}

	@Override
	public String operation() {
		return text;
	}

}
