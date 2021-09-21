package origins.origins;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.Listener;

import origins.Main;

public class Shulk extends Origin implements Listener {

	public Shulk() {
		name = "Shulk";
		description = Main.config.getStringList("shulk");
		item = Material.SHULKER_SHELL;
		health = 20;
	}

}
