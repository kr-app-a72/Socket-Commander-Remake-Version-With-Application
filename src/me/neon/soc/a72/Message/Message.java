package me.neon.soc.a72.Message;

import java.io.DataOutputStream;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Message {
	public static void InputtoServer(String command) {
		Bukkit.getServer().dispatchCommand(
				Bukkit.getServer().getConsoleSender(), command);
	}

	public static void _send_Message(String str) {
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + str);
	}

	public static void __send_Message(String str) {
		Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + str);
	}

	public static void send_Message(DataOutputStream dos, String str) {
		try {
			dos.writeUTF(str);
		} catch (IOException e) {
			_send_Message("메세지를 보내지 못했습니다.\n");
		}
	}
}
