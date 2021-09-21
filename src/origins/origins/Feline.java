package origins.origins;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.Listener;

import origins.Main;

public class Feline extends Origin implements Listener {

	public Feline() {
		name = "Feline";
		description = Main.config.getStringList("feline");
		item = Material.ORANGE_WOOL;
		health = 18;
	}

}
