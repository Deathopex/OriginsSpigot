package origins.origins;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import origins.utils.data;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import origins.Main;

public class Phantom extends Origin implements Listener {

	public Map<Player, Boolean> phantomstate = new HashMap<Player, Boolean>();

	public Phantom() {
		name = "Phantom";
		description = Main.config.getStringList("phantom");
		item = Material.PHANTOM_MEMBRANE;
		health = 6;
	}

	@EventHandler
	private void onJoin(org.bukkit.event.player.PlayerJoinEvent e) {
		phantomstate.put(e.getPlayer(), false);
	}

	@EventHandler
	private void onLeave(org.bukkit.event.player.PlayerQuitEvent e) {
		phantomstate.remove(e.getPlayer());
	}

	@EventHandler
	private void onDie(org.bukkit.event.entity.EntityDeathEvent e) {
		if (e.getEntity() instanceof Player) {
			phantomstate.put((Player) e.getEntity(), false);
		}
	}

	@EventHandler
	private void onInteract(org.bukkit.event.player.PlayerInteractEvent e) {
		if (data.getOrigin(e.getPlayer().getName()).equals(name)) {
			if (e.getAction().equals(Action.LEFT_CLICK_AIR) && e.getPlayer().isSneaking()) {
				togglePhantomState(e.getPlayer());
			}
		}
	}

	private void togglePhantomState(Player player) {
		phantomstate.put(player, !phantomstate.get(player));
		player.sendActionBar(msg(phantomstate.get(player)));
		if (phantomstate.get(player)) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 99999999, 1, true));
			player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 99999999, 1, true));
		} else {
			player.removePotionEffect(PotionEffectType.INVISIBILITY);
			player.removePotionEffect(PotionEffectType.HUNGER);
		}
	}

	private String msg(Boolean state) {
		if (state) {
			return "Phantom state enabled";
		} else
			return "Phantom state disabled";
	}
}
