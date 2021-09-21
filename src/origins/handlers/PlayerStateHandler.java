package origins.handlers;

import origins.Main;
import origins.utils.data;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class PlayerStateHandler {
	
	// needs to be rewritten but works

	public void handle(Player player) {
		String origin = data.getOrigin(player.getName());
		if (origin.equals("Enderian")) {
			if (player.getLocation().getBlock().getType().equals(Material.WATER)) {
				player.damage(6);
			}
		}

		if (origin.equals("Merling")) {
			if (!Main.origins.merling.breathe.containsKey(player)) {
				Main.origins.merling.breathe.put(player, 0);
			}
			player.sendActionBar("Breathe: " + Main.origins.merling.breathe.get(player));
			if (player.getLocation().getBlock().getType().equals(Material.WATER)) {
				Main.origins.merling.breathe.replace(player, 6);
				player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 45, 10, true));
				player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 45, 6, true));
			} else {
				if (Main.origins.merling.breathe.get(player) == 0) {
					player.damage(6);
					return;
				}
				Main.origins.merling.breathe.replace(player, Main.origins.merling.breathe.get(player) - 1);
			}
		}

		if (origin.equals("Phantom")) {
			Location loc = player.getLocation();
			long time = loc.getWorld().getTime();
			if (loc.getWorld().getHighestBlockYAt(loc) == loc.getBlockY() - 1
					&& !Main.origins.phantom.phantomstate.get(player) && time > 0 && time < 12300) {
				player.setFireTicks(40);
			}
		}

		if (origin.equals("Elytrian")) {
			Location loc = player.getLocation();
			if (!loc.add(0, 3, 0).getBlock().getType().equals(Material.AIR)) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 45, 2, true));
				player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 45, 2, true));
			}
		}

	}

}
