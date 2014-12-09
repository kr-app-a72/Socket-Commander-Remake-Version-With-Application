package me.neon.soc.a72;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Logger;

import me.neon.soc.a72.Command.CommandManager;
import me.neon.soc.a72.Socket.SocketThread;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public ArrayList<String> ip = new ArrayList<String>();
	public static HashMap<String, Boolean> pwal = new HashMap<String, Boolean>();
	public static Random r = new Random();
	public static StringBuilder b = new StringBuilder();
	public static Logger log = Logger.getLogger("Minecraft");
	public boolean allowall;
	private SocketThread s;
	public static String Load = "";
	public static boolean version = true;
	public static boolean fail = false;
	public static String VERSION = "2";
	public static boolean stop = false;
	private static CommandManager Command;
	public int port = 0;
	public static int Connection_Count = 0;

	@SuppressWarnings("unchecked")
	public void onEnable() {
		FileConfiguration config = getConfig();
		this.ip.add("127.0.0.1");
		this.ip.add("0.0.0.0");
		config.addDefault("전체 허용",Boolean.valueOf(false));
		config.addDefault("허용 IP", this.ip);
		config.addDefault("포트", -1);
		config.options().copyDefaults(true);
		saveConfig();
		if (config.getInt("포트") == -1) {
			port = Integer.valueOf(r.nextInt(65565) + 1).intValue();
			config.set("포트", port);
		} else {
			port = config.getInt("포트");
		}
		this.ip = new ArrayList<String>();
		if (!(this.allowall = config.getBoolean("전체 허용")))" {
			this.ip = ((ArrayList<String>) config.get("허용 IP"));
		}
		
		initCommand();
		this.s = new SocketThread(this, port);
	}

	public void onDisable() {
		try {
			this.s.socket.close();
		} catch (IOException e) {
			return;
		}
		FileConfiguration config = getConfig();
		config.addDefault("허용 IP", this.ip);
		log.info(this.ip.toString());
		saveConfig();
	}

	public void initCommand() {
		Command = new CommandManager(this);
		getCommand("soc").setExecutor(Command);
	}

	
}
