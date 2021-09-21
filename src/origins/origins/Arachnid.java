package origins.origins;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.event.Listener;

import origins.Main;

public class Arachnid extends Origin implements Listener{

	public Arachnid() {
		name = "Arachnid";
		description = Main.config.getStringList("arachnid");
		item = Material.COBWEB;
		health = 14;
	}

}
