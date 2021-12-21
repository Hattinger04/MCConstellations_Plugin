/**
 * @author Simon Greiderer 
 * 
 *  Nothing tested
 */
package io.github.Hattinger04.minecraftconstellations.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Soup implements Listener {

	@EventHandler
	public void onSoupEaten(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
			return;

		if (event.getItem().getType() == Material.MUSHROOM_STEW) {
			player.setHealth(Math.min(player.getHealth() + 7, 20));
			player.getInventory().setItemInMainHand(new ItemStack(Material.BOWL));
		}
	}
}
