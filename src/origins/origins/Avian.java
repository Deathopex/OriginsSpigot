package origins.origins;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import origins.Main;
import origins.utils.data;

public class Avian extends Origin implements Listener {

	public Avian() {
		name = "Avian";
		description = Main.config.getStringList("avian");
		item = Material.FEATHER;
		health = 20;
	}

	@EventHandler
	private void onJoin(org.bukkit.event.player.PlayerJoinEvent e) {
		if (data.getOrigin(e.getPlayer().getName()).equals(name)) {
			sheduleEffects(e.getPlayer());
		}
	}

	@EventHandler
	private void onRespawn(org.bukkit.event.player.PlayerRespawnEvent e) {
		if (data.getOrigin(e.getPlayer().getName()).equals(name)) {
			sheduleEffects(e.getPlayer());
		}
	}

	private void addAvianEffects(Player player) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 999999999, 255, true));
	}

	private void sheduleEffects(Player player) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
			@Override
			public void run() {
				addAvianEffects(player);
			}
		}, 25);
	}

}
