package origins.origins;

import java.util.List;

import org.bukkit.Material;

public class Origin implements IOrigin {
	protected String name;
	protected List<String> description;
	protected Material item;
	protected double health;

	@Override
	public String name() {
		return this.name;
	}

	@Override
	public List<String> description() {
		return this.description;
	}

	@Override
	public Material item() {
		return this.item;
	}

	@Override
	public double health() {
		return this.health;
	}

}
