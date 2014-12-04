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

public class CommandManager implements CommandExecutor {
	public Logger log = Logger.getLogger("Minecraft");
	private final Main plugin;

	public CommandManager(Main instance) {
		this.plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (!label.equalsIgnoreCase("soc")) {
			return false;
		}
		if ((sender instanceof Player)) {
			Player player = (Player) sender;
			try {
				if ((args.length > 0) && (args[0].equalsIgnoreCase("help"))) {
					player.sendMessage(ChatColor.GREEN
							+ "----------- Socket Commander 플러그인 도움말  -----------");
					player.sendMessage(ChatColor.BLUE
							+ "/soc add (IP Adress) - 이 아이피를 허용 아이피에 등록합니다.");
					player.sendMessage(ChatColor.BLUE
							+ "/soc del (IP Adress) - 이 아이피를 허용 아이피에서 제거합니다.");
					player.sendMessage(ChatColor.BLUE
							+ "/soc list - 허용 아이피 목록을 봅니다.");
					player.sendMessage(ChatColor.GREEN
							+ "--------------------------------------------------");
				}
				if (args[0].equalsIgnoreCase("add")) {
					if (args.length > 2) {
						player.sendMessage(ChatColor.RED
								+ "*.*.*.* 의 형태로 입력해 주세요");
					} else {
						this.plugin.ip.add(args[1]);
						player.sendMessage(args[1] + " 이 성공적으로 등록되었습니다.");
					}
				}
				if (args[0].equalsIgnoreCase("list")) {
					String[] list = this.plugin.ip.toString().split(",");
					player.sendMessage(ChatColor.YELLOW + "허용 리스트에 추가된 목록");
					for (int i = 0; i < list.length; i++) {
						if (list[i].startsWith("[")) {
							player.sendMessage(ChatColor.YELLOW
									+ String.valueOf(i) + ". "
									+ list[i].replace("[", ""));
						} else if (list[i].endsWith("]")) {
							player.sendMessage(ChatColor.YELLOW
									+ String.valueOf(i) + ". "
									+ list[i].replace("]", ""));
						} else {
							player.sendMessage(ChatColor.YELLOW
									+ String.valueOf(i) + ". " + list[i]);
						}
					}
				}
				if (args[0].equalsIgnoreCase("del")) {
					if (args.length > 2) {
						player.sendMessage(ChatColor.RED
								+ "*.*.*.* 의 형태로 입력해 주세요");
					} else {
						if(this.plugin.ip.contains(args[1])){
							this.plugin.ip.remove(args[1]);
							player.sendMessage(ChatColor.RED
								+ "성공적으로 " + args[1]+ " 을 제거하였습니다.");
						}
					}
				}
			} catch (Exception e) {
				player.sendMessage("도움말을 보시려면 /soc help 를 입력하세요");
			}
			return true;
		}
		if (!(sender instanceof Player)) {
			ConsoleCommandSender player = Bukkit.getServer().getConsoleSender();
			try {
				if ((args.length > 0) && (args[0].equalsIgnoreCase("help"))) {
					player.sendMessage(ChatColor.GREEN
							+ "----------- Socket Commander 플러그인 도움말  -----------");
					player.sendMessage(ChatColor.BLUE
							+ "/soc add (IP Adress) - 이 아이피를 허용 아이피에 등록합니다.");
					player.sendMessage(ChatColor.BLUE
							+ "/soc del (IP Adress) - 이 아이피를 허용 아이피에서 제거합니다.");
					player.sendMessage(ChatColor.BLUE
							+ "/soc list - 허용 아이피 목록을 봅니다.");
					player.sendMessage(ChatColor.GREEN
							+ "--------------------------------------------------");
				}
				if (args[0].equalsIgnoreCase("add")) {
					if (args.length > 2) {
						player.sendMessage(ChatColor.RED
								+ "*.*.*.* 의 형태로 입력해 주세요");
					} else {
						this.plugin.ip.add(args[1]);
						player.sendMessage(ChatColor.AQUA + args[1]
								+ " 이 성공적으로 등록되었습니다.");
					}
				}
				if (args[0].equalsIgnoreCase("list")) {
					String[] list = this.plugin.ip.toString().split(",");
					player.sendMessage(ChatColor.YELLOW + "허용 리스트에 추가된 목록");
					for (int i = 0; i < list.length; i++) {
						if (list[i].startsWith("[")) {
							player.sendMessage(ChatColor.YELLOW
									+ String.valueOf(i) + ". "
									+ list[i].replace("[", ""));
						} else if (list[i].endsWith("]")) {
							player.sendMessage(ChatColor.YELLOW
									+ String.valueOf(i) + ". "
									+ list[i].replace("]", ""));
						} else {
							player.sendMessage(ChatColor.YELLOW
									+ String.valueOf(i) + ". " + list[i]);
						}
					}
				}
				if (args[0].equalsIgnoreCase("del")) {
					if (args.length > 2) {
						player.sendMessage(ChatColor.RED
								+ "*.*.*.* 의 형태로 입력해 주세요");
					} else {
						if(this.plugin.ip.contains(args[1])){
							this.plugin.ip.remove(args[1]);
							player.sendMessage(ChatColor.RED
								+ "성공적으로 " + args[1]+ " 을 제거하였습니다.");
						}
					}
				}
			} catch (Exception e) {
				sender.sendMessage("도움말을 보시려면 /soc help 를 입력하세요");
			}
			return true;
		}
		return false;
	}
}
