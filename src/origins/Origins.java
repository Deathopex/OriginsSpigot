package origins;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import origins.gui.Gui;
import origins.origins.Arachnid;
import origins.origins.Avian;
import origins.origins.Blazeborn;
import origins.origins.Elytrian;
import origins.origins.Enderian;
import origins.origins.Feline;
import origins.origins.Merling;
import origins.origins.IOrigin;
import origins.origins.Phantom;
import origins.origins.Shulk;

public class Origins {

	public List<IOrigin> origins = new ArrayList<IOrigin>();

	public Enderian enderian = new Enderian();
	public Merling merling = new Merling();
	public Phantom phantom = new Phantom();
	public Elytrian elytrian = new Elytrian();
	public Blazeborn blazeborn = new Blazeborn();
	public Avian avian = new Avian();
	public Arachnid arachnid = new Arachnid();
	public Shulk shulk = new Shulk();
	public Feline feline = new Feline();

	public void init() {
		setupOrigins();
		registerEvents();
		initGui();
	}

	public void setupOrigins() {
		for (Field field : this.getClass().getFields()) {
			try {
				Object obj = field.get(this);
				if (obj instanceof IOrigin) {
					IOrigin origin = (IOrigin) obj;
					origins.add(origin);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void registerEvents() {
		for (IOrigin origin : origins) {
			Bukkit.getServer().getPluginManager().registerEvents((Listener) origin, Main.plugin);
		}
	}

	public void initGui() {
		Main.gui.init(origins.size());
		Dictionary a;
		for (IOrigin origin : origins) {
			Gui.addOrigin(origin);
		}
	}

	public List<String> getNames() {
		List<String> list = new ArrayList<String>();
		for (IOrigin origin : origins) {
			list.add(origin.name());
		}
		return list;
	}

	public IOrigin byName(String name) {
		for (IOrigin origin : origins) {
			if (origin.name().equals(name))
				return origin;
		}
		return null;
	}

}
