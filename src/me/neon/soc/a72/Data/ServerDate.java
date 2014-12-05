package me.neon.soc.a72.Data;

import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;

public class ServerDate {
	static HashMap<Integer, ArrayList<?>> $GCVC = new HashMap<Integer, ArrayList<?>>();
	static ArrayList<String> $Sfks = new ArrayList<String>();
	static ArrayList<String> $Ax15d = new ArrayList<String>();
	static Server _sd$sdknA12 = Bukkit.getServer();

	public static HashMap<Integer, ArrayList<?>> M_$sjnSa1() {
		$Sfks.add(_sd$sdknA12.getBukkitVersion());
		$Sfks.add(_sd$sdknA12.getIp());
		$Sfks.add(String.valueOf(_sd$sdknA12.getPort()));
		$Sfks.add(String.valueOf(_sd$sdknA12.getMaxPlayers()));
		$Sfks.add(_sd$sdknA12.getMotd());
		$GCVC.put(0, $Sfks);
		for (Player $jnjd15P : _sd$sdknA12.getOnlinePlayers()) {
			$Ax15d.add($jnjd15P.getName());
		}
		$GCVC.put(1, $Ax15d);
		return $GCVC;
	}
}
