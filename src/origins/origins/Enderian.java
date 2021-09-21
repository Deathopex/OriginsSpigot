package origins.origins;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.ItemStack;

import origins.Main;
import origins.utils.data;

public class Enderian extends Origin implements Listener {

	public Enderian() {
		this.name = "Enderian";
		this.description = Main.config.getStringList("enderian");
		this.item = Material.ENDER_PEARL;
		this.health = 20;
	}

	@EventHandler
	private void onBreak(org.bukkit.event.block.BlockBreakEvent e) {
		if (data.getOrigin(e.getPlayer().getName()).equals(this.name)
				&& e.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
			e.setDropItems(false);
			Item item = (Item) e.getBlock().getLocation().getWorld().spawnEntity(e.getBlock().getLocation(),
					EntityType.DROPPED_ITEM);
			item.setItemStack(new ItemStack(e.getBlock().getType(), 1));
		}
	}

	@EventHandler
	private void onInteract(org.bukkit.event.player.PlayerInteractEvent e) {
		if (data.getOrigin(e.getPlayer().getName()).equals(name)) {
			if (e.getPlayer().isSneaking() && e.getAction().equals(Action.LEFT_CLICK_AIR)) {
				if (data.getOrigin(e.getPlayer().getName()).equals(this.name)) {
					EnderPearl pearl = (EnderPearl) e.getPlayer().getWorld().spawnEntity(e.getPlayer().getEyeLocation(),
							EntityType.ENDER_PEARL);
					pearl.setShooter(e.getPlayer());
					pearl.setVelocity(e.getPlayer().getEyeLocation().getDirection().multiply(2));
				}
			}
		}
	}

	@EventHandler
	private void onTeleport(org.bukkit.event.player.PlayerTeleportEvent e) {
		if (data.getOrigin((e.getPlayer().getName())).equals(this.name)) {
			Player player = e.getPlayer();
			if (e.getCause() == TeleportCause.ENDER_PEARL) {
				e.setCancelled(true);
				player.teleport(e.getTo());
			}
		}
	}
}
