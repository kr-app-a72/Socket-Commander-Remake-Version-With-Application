package me.neon.soc.a72.Command;

import java.util.logging.Logger;

import me.neon.soc.a72.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class CommandManager
  implements CommandExecutor
{
  public Logger log = Logger.getLogger("Minecraft");
  private final Main plugin;
  
  public CommandManager(Main instance)
  {
    this.plugin = instance;
  }
  
public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
  {
    if (!label.equalsIgnoreCase("soc")) {
      return false;
    }
    if ((sender instanceof Player))
    {
      Player player = (Player)sender;
      try
      {
        if ((args.length > 0) && (args[0].equalsIgnoreCase("help")))
        {
          player.sendMessage(ChatColor.GREEN + 
            "----------- Socket Commander �÷����� ����  -----------");
          player.sendMessage(ChatColor.BLUE + 
            "/soc add (IP Adress) - �� �����Ǹ� ��� �����ǿ� ����մϴ�.");
          player.sendMessage(ChatColor.GREEN + 
            "--------------------------------------------------");
        }
        if (args[0].equalsIgnoreCase("add")) {
          if (args.length > 2)
          {
            player.sendMessage(ChatColor.RED + 
              "*.*.*.* �� ���·� �Է��� �ּ���");
          }
          else
          {
            this.plugin.configiplist.add(args[1]);
            this.plugin.ip.add(args[1]);
            player.sendMessage(args[1] + " �� ���������� ��ϵǾ����ϴ�.");
          }
        }
      }
      catch (Exception e)
      {
        player.sendMessage("������ ���÷��� /soc help �� �Է��ϼ���");
      }
      return true;
    }
    if (!(sender instanceof Player))
    {
      ConsoleCommandSender player = Bukkit.getServer().getConsoleSender();
      try
      {
        if ((args.length > 0) && (args[0].equalsIgnoreCase("help")))
        {
          sender.sendMessage(ChatColor.GREEN + 
            "----------- Socket Commander �÷����� ����  -----------");
          player.sendMessage(ChatColor.BLUE + 
            "/soc add (IP Adress) - �� �����Ǹ� ��� �����ǿ� ����մϴ�.");
          sender.sendMessage(ChatColor.GREEN + 
            "--------------------------------------------------");
        }
        if (args[0].equalsIgnoreCase("add")) {
          if (args.length > 2)
          {
            player.sendMessage(ChatColor.RED + 
              "*.*.*.* �� ���·� �Է��� �ּ���");
          }
          else
          {
            this.plugin.configiplist.add(args[1]);
            this.plugin.ip.add(args[1]);
            player.sendMessage(args[1] + " �� ���������� ��ϵǾ����ϴ�.");
            player.sendMessage(this.plugin.ip.toString());
          }
        }
      }
      catch (Exception e)
      {
        sender.sendMessage("������ ���÷��� /soc help �� �Է��ϼ���");
      }
      return true;
    }
    return false;
  }
}
