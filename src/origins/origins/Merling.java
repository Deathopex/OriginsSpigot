package origins.origins;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import origins.utils.data;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import origins.Main;

public class Merling extends Origin implements Listener {

	public Map<Player, Integer> breathe = new HashMap<Player, Integer>();

	public Merling() {
		name = "Merling";
		description = Main.config.getStringList("merling");
		item = Material.COD;
		health = 20;
	}

	@EventHandler
	private void onJoin(org.bukkit.event.player.PlayerJoinEvent e) {
		breathe.put(e.getPlayer(), 6);
		if (data.getOrigin(e.getPlayer().getName()).equals(name)) {
			sheduleEffects(e.getPlayer());
		}
	}

	@EventHandler
	private void onLeave(org.bukkit.event.player.PlayerQuitEvent e) {
		breathe.remove(e.getPlayer());
	}

	@EventHandler
	private void onRespawn(org.bukkit.event.player.PlayerRespawnEvent e) {
		if (data.getOrigin(e.getPlayer().getName()).equals(name)) {
			sheduleEffects(e.getPlayer());
			breathe.replace(e.getPlayer(), 6);
		}
	}

	private void addMerlingEffects(Player player) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 999999999, 255, true));
		player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 999999999, 10, true));
	}

	private void sheduleEffects(Player player) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
			@Override
			public void run() {
				addMerlingEffects(player);
			}
		}, 25);
	}

}
