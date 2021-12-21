/**
 * @author Simon Greiderer
 * 
 * Not tested!
 */
package io.github.Hattinger04.minecraftconstellations.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Container;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import io.github.Hattinger04.minecraftconstellations.constellations.Constellations;

public class PlayerDeath implements Listener {

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {

		Player killed = event.getEntity();
		Player killer = null;
		event.getDrops().clear();
		setDeathChest(killed);
		if (killed.getKiller() instanceof Player) {
			killer = event.getEntity().getKiller();
			Constellations.addStatsToColor(Constellations.getColorFromPlayer(killer), 1);
			Constellations.addStatsToColor(Constellations.getColorFromPlayer(killed), -1);
			return;
		}
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			if (Constellations.getColorFromPlayer(p) != Constellations.getColorFromPlayer(killed)) {
				p.sendMessage(event.getDeathMessage());
			}
		}
		event.setDeathMessage(null);
	}

	/**
	 * No idea if this could possible work
	 * 
	 * @param killed
	 * @return
	 */
	public boolean setDeathChest(Player killed) {
		Location location = killed.getLocation();
		Location location2 = killed.getLocation().add(1, 0, 0);

		Block block = location.getBlock();
		Block block2 = location2.getBlock();

		location.getBlock().setType(Material.CHEST);
		location2.getBlock().setType(Material.CHEST);

		BlockState state = block.getState();
		BlockState state2 = block2.getState();

		Container cont = (Container) state;
		Container cont2 = (Container) state2;

		Inventory inv = cont.getInventory();
		Inventory inv2 = cont2.getInventory();

		ItemStack[] playerInv = killed.getInventory().getContents();

		for (int i = 0; i < 27; i++) {
			if (playerInv[i] != null) {
				inv.addItem(playerInv[i]);
			} 
		}
		for (int i = 27; i < playerInv.length; i++) {
			if (playerInv[i] != null) {
				inv2.addItem(playerInv[i]);
			}
		}
		return true;
	}
}
