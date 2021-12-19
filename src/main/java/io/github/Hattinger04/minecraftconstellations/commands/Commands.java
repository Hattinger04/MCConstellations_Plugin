package io.github.Hattinger04.minecraftconstellations.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.Hattinger04.minecraftconstellations.MinecraftConstellations;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Commands implements CommandExecutor{
	
	private final MinecraftConstellations plugin;
	private final String url = "https://www.google.com";
	
	public Commands(MinecraftConstellations plugin) {
		this.plugin = plugin; // Store the plugin in situations where you need it.
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			final Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("help")) {
				player.sendMessage("Du brauchst Hife?");
			} else if (cmd.getName().equalsIgnoreCase("website")) {
				TextComponent message = new TextComponent(TextComponent.fromLegacyText("Klicken um auf die Website weitergeleitet zu werden!"));
				message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url)); 
				player.spigot().sendMessage(message);
			}
		}
		return false;
	}

}
