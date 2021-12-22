package io.github.Hattinger04.minecraftconstellations.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.Hattinger04.minecraftconstellations.MessageTemplate;
import io.github.Hattinger04.minecraftconstellations.MinecraftConstellations;
import io.github.Hattinger04.minecraftconstellations.constellations.Constellations;
import io.github.Hattinger04.minecraftconstellations.constellations.EConstellations;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Commands implements CommandExecutor {

	private final String url = "https://www.google.com";

	public Commands(MinecraftConstellations plugin) {
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			final Player player = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("help")) {
				MessageTemplate.sendMessageToPlayer("Du brauchst Hife?", player);
			} else if (cmd.getName().equalsIgnoreCase("website")) {
				TextComponent message = new TextComponent(
						TextComponent.fromLegacyText("Klicken um auf die Website weitergeleitet zu werden!"));
				message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
				player.spigot().sendMessage(message);
			} else if (cmd.getName().equalsIgnoreCase("constellation")) {
				// TODO: Permissions müssen noch eingeführt werden!
				if (args.length == 0) {
					MessageTemplate.sendMessageToPlayer("Deine Constellation: ", player, Constellations.getChatColorFromPlayer(player));
				} else if (args.length == 1) {
					EConstellations color = Constellations.getColorFromString(args[0]); 
					if (color == EConstellations.Nothing) {
						MessageTemplate.sendMessageToPlayer("Deine Zuweisung ist fehlgschlagen!", player, ChatColor.RED);
					} else {
						Constellations.addPlayerToConstellation(color, player);
						player.sendMessage("Du bist nun in der Constellation " + color + ".");
					}
				} else if (args.length == 2) {
					try {						
						System.out.println(args[1]);
						EConstellations color = Constellations.addPlayerToConstellation(
								Constellations.getColorFromString(args[0]), Bukkit.getPlayer(args[1]));
						MessageTemplate.sendMessageToPlayer("Du hast Spieler " + args[1] + "in die Constellation " + color + "gesetzt.", player);
						Bukkit.getPlayer(args[1]).sendMessage("Du bist nun in der Constellation " + color + ".");
					} catch (NullPointerException ex) {
						MessageTemplate.sendMessageToPlayer("Deine Zuweisung ist fehlgschlagen!", player, ChatColor.RED);
					}
				}
			}
		}
		return false;
	}

}
