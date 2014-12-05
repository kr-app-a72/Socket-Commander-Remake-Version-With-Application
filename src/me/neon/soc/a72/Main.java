package me.neon.soc.a72;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;

import me.neon.soc.a72.Command.CommandManager;
import me.neon.soc.a72.Socket.SocketThread;

import org.bukkit.configuration.file.*;
import org.bukkit.plugin.java.JavaPlugin;

public class Main
  extends JavaPlugin
{
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
public void onEnable()
  {
    FileConfiguration config = getConfig();
    this.ip.add("127.0.0.1");
    this.ip.add("0.0.0.0");
    config.addDefault("��� ���", Boolean.valueOf(false));
    config.addDefault("��� IP", this.ip);
    config.addDefault("��ɾ� �� �˸� ��Ʈ", -1);
    config.addDefault("������ ��Ʈ", -1);
    config.options().copyDefaults(true);
    saveConfig();
    if (config.getInt("��ɾ� �� �˸� ��Ʈ") == -1){
    	port = Integer.valueOf(r.nextInt(65565) + 1).intValue();
    	config.set("��ɾ� �� �˸� ��Ʈ", port);
    } else {
    	port = config.getInt("��ɾ� �� �˸� ��Ʈ");
    }
    if (config.getInt("������ ��Ʈ") == -1){
    	port = port+10;
    	config.set("������ ��Ʈ", port);
    } else {
    	port = config.getInt("������ ��Ʈ");
    }
    this.ip = new ArrayList<String>();
    if (!(this.allowall = config.getBoolean("��� ���"))) {
      this.ip = ((ArrayList<String>)config.get("��� IP"));
    }
    initCommand();
    this.s = new SocketThread(this, port);
 
  }
  
  public void onDisable()
  {
    try
    {
      this.s.socket.close();
    }
    catch (IOException e)
    {
      return;
    }
    FileConfiguration config = getConfig();
    config.addDefault("��� IP", this.ip);
    log.info(this.ip.toString());
    saveConfig();
  }
  

  
  public void initCommand()
  {
    Command = new CommandManager(this);
    getCommand("soc").setExecutor(Command);
  }
}
