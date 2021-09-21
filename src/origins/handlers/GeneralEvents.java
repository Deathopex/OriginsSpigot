package origins.handlers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import origins.Main;
import origins.utils.data;

public class GeneralEvents implements Listener {

	@EventHandler
	private static void healthOnJoin(org.bukkit.event.player.PlayerJoinEvent e) {
		if (data.hasOrigin(e.getPlayer().getName())) {
			String origin = data.getOrigin(e.getPlayer().getName());
			double health = Main.origins.byName(origin).health();
			e.getPlayer().setMaxHealth(health);
		} else {
			e.getPlayer().setMaxHealth(20);
		}
	}

}
