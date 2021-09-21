package origins.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import origins.Main;
import origins.origins.IOrigin;
import origins.utils.data;
import origins.utils.origin;

public class Gui implements Listener {

	public static Inventory originPanel;

	public static void init(int size) {
		originPanel = Bukkit.getServer().createInventory(null, size, "Select an origin");
	}

	public static void addOrigin(IOrigin origin) {
		ItemStack icon = new ItemStack(origin.item(), 1);
		ItemMeta meta = icon.getItemMeta();
		meta.setLore(origin.description());
		meta.setDisplayName(origin.name());
		icon.setItemMeta(meta);
		originPanel.addItem(icon);
	}

	@EventHandler
	public static void onJoin(org.bukkit.event.player.PlayerLoginEvent e) {
		if (!data.hasOrigin(e.getPlayer().getName())) {
			sheludePanel(e.getPlayer());
		}
	}

	@EventHandler
	public static void onClick(org.bukkit.event.inventory.InventoryClickEvent e) {
		if (e.getView().getTitle().equals("Select an origin")) {
			e.setCancelled(true);
			if (e.getCurrentItem() == null || e.getSlot() > Main.gui.originPanel.getSize())
				return;
			origin.switchOrigin(e.getWhoClicked().getName(), e.getCurrentItem().getItemMeta().getDisplayName());
			e.getView().close();
		}
	}

	static void sheludePanel(Player player) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
			@Override
			public void run() {
				player.openInventory(originPanel);
			}
		}, 40);
	}

}
