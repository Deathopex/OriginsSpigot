package origins.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import origins.Main;

public class origin {

	public static void switchOrigin(String player, String origin) {
		data.setOrigin(player, origin);
		Player plr = Bukkit.getPlayer(player);
		if (plr != null) {
			double health = Main.origins.byName(origin).health();
			plr.setMaxHealth(health);
			for (PotionEffect effect : plr.getActivePotionEffects())
				plr.removePotionEffect(effect.getType());
		}
	}
}
