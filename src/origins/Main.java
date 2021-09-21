package origins;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import net.md_5.bungee.api.ChatColor;
import origins.commands.Commands;
import origins.gui.Gui;
import origins.handlers.GeneralEvents;
import origins.handlers.RepeatingHandler;

public class Main extends JavaPlugin {

	// the objects are initialized on enabled, because either way they are created before the plugin loads which causes nullPointerException
	
	public static Plugin plugin;
	public static FileConfiguration config;
	
	private Commands commands = new Commands();
	public static Gui gui = new Gui();
	public static Origins origins;
	public static RepeatingHandler handler = new RepeatingHandler();

	@Override
	public void onEnable() {
		plugin = this;
		config = this.getConfig();
		this.saveDefaultConfig();
		origins = new Origins();
		commands.init();
		origins.init();
		handler.init();

		Bukkit.getServer().getPluginManager().registerEvents(new GeneralEvents(), this);
		Bukkit.getServer().getPluginManager().registerEvents(gui, this);
		Bukkit.getLogger().info(ChatColor.GREEN + "Origins Spigot enabled.");
	}
}
