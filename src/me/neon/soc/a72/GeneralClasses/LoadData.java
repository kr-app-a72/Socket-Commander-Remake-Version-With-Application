package me.neon.soc.a72.GeneralClasses;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class LoadData {
	public static HashMap<Integer, ArrayList<?>> $GCVC = new HashMap<Integer, ArrayList<?>>();
	public static ArrayList<String> $Sfks = new ArrayList<String>();
	public static ArrayList<String> $Ax15d = new ArrayList<String>();

	public static void M_$sjnSa1() {
		$Sfks.add(Bukkit.getServer().getBukkitVersion());
		Bukkit.broadcastMessage(ChatColor.GREEN + "버전: "
				+ Bukkit.getServer().getBukkitVersion());
		$Sfks.add(Bukkit.getServer().getIp());
		Bukkit.broadcastMessage(ChatColor.GREEN + "IP : "
				+ Bukkit.getServer().getIp().toString());
		$Sfks.add(String.valueOf(Bukkit.getServer().getPort()));
		Bukkit.broadcastMessage(ChatColor.GREEN + "포트: "
				+ String.valueOf(Bukkit.getServer().getPort()));
		$Sfks.add(String.valueOf(Bukkit.getServer().getMaxPlayers()));
		Bukkit.broadcastMessage(ChatColor.GREEN + "최대 유저수 : "
				+ String.valueOf(Bukkit.getServer().getMaxPlayers()));
		$Sfks.add(Bukkit.getServer().getMotd());
		Bukkit.broadcastMessage(ChatColor.GREEN + "Motd : "
				+ Bukkit.getServer().getMotd().toString());
		$GCVC.put(0, $Sfks);
		for (Player $jnjd15P : Bukkit.getServer().getOnlinePlayers()) {
			$Ax15d.add($jnjd15P.getName());
			Bukkit.broadcastMessage($jnjd15P.getName());
		}
		$GCVC.put(1, $Ax15d);
	}
}
