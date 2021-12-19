package io.github.Hattinger04.minecraftconstellations.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.Hattinger04.minecraftconstellations.MinecraftConstellations;

public class Commands implements CommandExecutor{
	
	private final MinecraftConstellations plugin;

	public Commands(MinecraftConstellations plugin) {
		this.plugin = plugin; // Store the plugin in situations where you need it.
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			final Player p = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("help")) {
				p.sendMessage("Du brauchst Hife?");
			}
		}
		return false;
	}

}
