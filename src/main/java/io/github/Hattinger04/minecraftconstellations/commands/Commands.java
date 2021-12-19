package io.github.Hattinger04.minecraftconstellations.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.Hattinger04.minecraftconstellations.MinecraftConstellations;
import io.github.Hattinger04.minecraftconstellations.constellations.Constellations;
import io.github.Hattinger04.minecraftconstellations.constellations.EConstellations;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Commands implements CommandExecutor {

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
				TextComponent message = new TextComponent(
						TextComponent.fromLegacyText("Klicken um auf die Website weitergeleitet zu werden!"));
				message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
				player.spigot().sendMessage(message);
			} else if (cmd.getName().equalsIgnoreCase("constellation")) {
				// TODO: Permissions müssen noch eingeführt werden!
				if (args.length == 0) {
					player.sendMessage("Deine Constellation: " + Constellations.getColorFromPlayer(player).toString());
				} else if (args.length == 1) {
					EConstellations color = Constellations
							.addPlayerToConstellation(Constellations.getColorFromString(args[0]), player);
					if (color == EConstellations.Nothing) {
						player.sendMessage(ChatColor.RED + "Deine Zuweisung ist fehlgschlagen!" + ChatColor.RESET);
					} else {
						player.sendMessage("Du bist nun in der Constellation " + color + ".");
					}
				} else if (args.length == 2) {
					try {
						EConstellations color = Constellations.addPlayerToConstellation(
								Constellations.getColorFromString(args[0]), Bukkit.getPlayer(args[1]));
						player.sendMessage("Du hast Spieler " + args[1] + "in die Constellation " + color + "gesetzt.");
						Bukkit.getPlayer(args[1]).sendMessage("Du bist nun in der Constellation " + color + ".");
					} catch (NullPointerException ex) {
						player.sendMessage(ChatColor.RED + "Deine Zuweisung ist fehlgschlagen!" + ChatColor.RESET);
					}
				}
			}
		}
		return false;
	}

}
