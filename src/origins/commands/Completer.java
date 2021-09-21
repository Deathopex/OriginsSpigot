package origins.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import origins.Main;

public class Completer implements org.bukkit.command.TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String wtf, String[] args) {
		List<String> tabReturner = new ArrayList<String>();
		if (command.getName().equals("origin")) {
			if (args.length == 1) {
				tabReturner.add("set");
				tabReturner.add("get");
				tabReturner.add("panel");
				return tabReturner;
			}

			if (args[0].equals("set")) {
				if (args.length == 3) {
					return Main.origins.getNames();
				}
			}

			if (args[1].equals("panel")) {

			}
		}

		return null;
	}

}
