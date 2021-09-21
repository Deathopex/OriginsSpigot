package origins.origins;

import java.util.List;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import origins.Main;
import origins.utils.data;

public class Elytrian extends Origin implements Listener {

	public Elytrian() {
		name = "Elytrian";
		description = Main.config.getStringList("elytrian");
		item = Material.ELYTRA;
		health = 20;
	}

	@EventHandler
	private void onJoin(org.bukkit.event.player.PlayerJoinEvent e) {
		if (data.getOrigin(e.getPlayer().getName()).equals(name)) {
			e.getPlayer().getInventory().setItem(EquipmentSlot.CHEST, elytra());
		}
	}

	@EventHandler
	private void onRespawn(org.bukkit.event.player.PlayerRespawnEvent e) {
		if (data.getOrigin(e.getPlayer().getName()).equals(name)) {
			e.getPlayer().getInventory().setItem(EquipmentSlot.CHEST, elytra());
		}
	}

	@EventHandler
	private void onInteract(org.bukkit.event.player.PlayerInteractEvent e) {
		if (data.getOrigin(e.getPlayer().getName()).equals(name)) {
			if (e.getAction().equals(Action.LEFT_CLICK_AIR) && e.getPlayer().isOnGround()
					&& e.getPlayer().isSneaking()) {
				e.getPlayer().setVelocity(new Vector(0, 1, 0));
			}
		}
	}

	@EventHandler
	private void onAttack(org.bukkit.event.entity.EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player && data.getOrigin(((Player) e.getDamager()).getName()).equals(name)) {
			Player player = (Player) e.getDamager();
			if (player.isGliding()) {
				e.setDamage(e.getDamage() * 2);
			}
		}
	}

	@EventHandler
	private void onFlightDamage(org.bukkit.event.entity.EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			if (data.getOrigin(e.getEntity().getName()).equals(name)) {
				if (e.getCause().equals(DamageCause.FLY_INTO_WALL) || e.getCause().equals(DamageCause.FALL)) {
					e.setDamage(e.getDamage() * 2);
				}

			}
		}
	}

	private ItemStack elytra() {
		ItemStack elytra = new ItemStack(Material.ELYTRA, 1);
		ItemMeta meta = elytra.getItemMeta();
		meta.setDisplayName("Elytrian elytra");
		meta.addEnchant(Enchantment.BINDING_CURSE, 1, true);
		meta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
		meta.setUnbreakable(true);
		elytra.setItemMeta(meta);
		return elytra;
	}

}
