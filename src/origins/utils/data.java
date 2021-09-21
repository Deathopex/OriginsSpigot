package origins.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import origins.Main;

public class data {

	public static Boolean hasOrigin(String name) {
		String origin = Main.config.getString(name + ".origin");
		return (!(origin == null));
	}

	public static String getOrigin(String player) {
		String origin = Main.config.getString(player + ".origin");
		if (origin == null) return "Human";
		return origin;
	}

	public static void setOrigin(String name, String origin) {
		Main.config.set(name + ".origin", origin);
		Main.plugin.saveConfig();
	}

}
