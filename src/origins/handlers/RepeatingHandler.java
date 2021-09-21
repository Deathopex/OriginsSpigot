package origins.handlers;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import origins.Main;

public class RepeatingHandler implements Listener {

	// checks players states each 40 ticks (2 seconds)

	public int taskID;
	private List<Player> players = new ArrayList<Player>();
	private PlayerStateHandler handler = new PlayerStateHandler();

	public void init() {
		this.registerEvents();
		this.startTask();
	}

	public void startTask() {
		taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
			@Override
			public void run() {
				loopCheck();
			}
		}, 0, 40);
	}

	public void stopTask() {
		Bukkit.getScheduler().cancelTask(taskID);
	}

	private void loopCheck() {
		for (int i = 0; i < players.size(); i++) {
			Player current = players.get(i);
			handler.handle(current);
		}
	}

	private void registerEvents() {
		Bukkit.getServer().getPluginManager().registerEvents(this, Main.plugin);
	}

	@EventHandler
	private void addPlayer(org.bukkit.event.player.PlayerJoinEvent e) {
		players.add(e.getPlayer());
	}

	@EventHandler
	private void removePlayer(org.bukkit.event.player.PlayerQuitEvent e) {
		players.remove(e.getPlayer());
	}
}
