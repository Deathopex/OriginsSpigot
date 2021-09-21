package origins.origins;

import java.util.List;
import org.bukkit.Material;

public interface IOrigin {

	public String name();

	public List<String> description();

	public Material item();
	
	public double health();

}
