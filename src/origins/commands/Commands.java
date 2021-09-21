package origins.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import origins.Main;

public class Commands implements CommandExecutor {

	public void init() {
		Bukkit.getPluginCommand("origin").setExecutor(this);
		Bukkit.getPluginCommand("origin").setTabCompleter(new Completer());
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String wtf, String[] args) {
		if (command.getName().equals("origin")) {
			if (!sender.isOp()) {
				sender.sendMessage("You don't have permission to change origins (op required)");
				return true;
			}

			if (args[0].equals("get") && args.length == 2) {
				String msg = "Player " + args[1] + " has origin " + origins.utils.data.getOrigin(args[1]);
				sender.sendMessage(msg);
				return true;
			}

			if (args[0].equals("set") && args.length == 3) {
				origins.utils.origin.switchOrigin(args[1], args[2]);
				String msg = "Player " + args[1] + " origin set to " + args[2];
				sender.sendMessage(msg);
				return true;
			}

			if (args[0].equals("panel") && args.length == 2) {
				Player player = Bukkit.getPlayer(args[1]);
				player.openInventory(Main.gui.originPanel);
				return true;
			}

		}
		return false;
	}

}
