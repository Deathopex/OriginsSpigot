package origins.origins;

import java.util.List;
import origins.utils.data;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import origins.Main;

public class Blazeborn extends Origin implements Listener {

	public Blazeborn() {
		name = "Blazeborn";
		description = Main.config.getStringList("blazeborn");
		item = Material.BLAZE_POWDER;
		health = 20;
	}

	@EventHandler
	private void shootFireballs(org.bukkit.event.player.PlayerInteractEvent e) {
		if (data.getOrigin(e.getPlayer().getName()).equals(name)) {
			if (e.getPlayer().isSneaking() && e.getAction().equals(Action.LEFT_CLICK_AIR)) {
				SmallFireball shot = (SmallFireball) e.getPlayer().getWorld()
						.spawnEntity(e.getPlayer().getEyeLocation(), EntityType.SMALL_FIREBALL);
				shot.setVelocity(e.getPlayer().getEyeLocation().getDirection().multiply(2));
			}
		}
	}

	@EventHandler
	private void closeAttack(org.bukkit.event.entity.EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			if (data.getOrigin(e.getDamager().getName()).equals(name)) {
				if (e.getCause().equals(DamageCause.ENTITY_ATTACK)
						|| e.getCause().equals(DamageCause.ENTITY_SWEEP_ATTACK)) {
					e.getEntity().setFireTicks(100);
				}
			}
		}
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

	private void addBlazeBornEffects(Player player) {
		player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999999, 255, true));
	}

	private void sheduleEffects(Player player) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
			@Override
			public void run() {
				addBlazeBornEffects(player);
			}
		}, 25);
	}
}
