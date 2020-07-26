package Tea.LaTeaMaker.Milk.SimpleBackpack;

import java.util.HashSet;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class UsePack implements Listener {
	
	public HashSet<UUID> InvMainList = new HashSet<UUID>();
	public HashSet<UUID> InvOffList = new HashSet<UUID>();

	@EventHandler
	public void onRightClick(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(e.getPlayer().getInventory().getItemInMainHand() != null && e.getPlayer().getInventory().getItemInMainHand().hasItemMeta() == true) {
				if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Medium Backpack")) {

					e.setCancelled(true);
					e.getPlayer().openInventory(DataFactory.GetData(e.getPlayer().getInventory().getItemInMainHand()));
					InvMainList.add(e.getPlayer().getUniqueId());
				}
			}
			if(e.getPlayer().getInventory().getItemInOffHand() != null && e.getPlayer().getInventory().getItemInOffHand().hasItemMeta() == true) {
				if (e.getPlayer().getInventory().getItemInOffHand().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Medium Backpack")) {

					e.setCancelled(true);
					e.getPlayer().openInventory(DataFactory.GetData(e.getPlayer().getInventory().getItemInOffHand()));
					InvOffList.add(e.getPlayer().getUniqueId());
				}
			}
			
		}
	}
	
	@EventHandler
	public void OnInvClose(InventoryCloseEvent e) {
		if(InvMainList.contains(e.getPlayer().getUniqueId())) {
			e.getPlayer().getInventory().setItemInMainHand(DataFactory.SetData(e.getPlayer().getInventory().getItemInMainHand(), e.getInventory()));
			InvMainList.remove(e.getPlayer().getUniqueId());
		}else if(InvOffList.contains(e.getPlayer().getUniqueId())){
			e.getPlayer().getInventory().setItemInOffHand(DataFactory.SetData(e.getPlayer().getInventory().getItemInOffHand(), e.getInventory()));
			InvOffList.remove(e.getPlayer().getUniqueId());
		}
		
	}

	@EventHandler
	public void AnvilName(InventoryClickEvent e) {
		Inventory Inv = e.getInventory();

		if (Inv instanceof AnvilInventory) {
			if(e.getAction() == InventoryAction.PLACE_ALL || e.getAction() == InventoryAction.PLACE_ONE || e.getAction() == InventoryAction.PLACE_SOME) {
				if(e.getCursor().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Medium Backpack")) {
					e.setCancelled(true);
				}
			}
		}
	}

	@EventHandler
	public void Stacking(InventoryClickEvent e) {
		if(e.getAction() == InventoryAction.PLACE_ALL || e.getAction() == InventoryAction.PLACE_ONE || e.getAction() == InventoryAction.PLACE_SOME) {
			if(e.getCursor().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Medium Backpack")) {
				e.setCancelled(true);
			}
		}
	}
	
	
	@EventHandler
	public void Pickup(EntityPickupItemEvent e) {
		
		if(e.getEntityType() == EntityType.PLAYER) {
			if(e.getItem().getItemStack().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Medium Backpack")) {
				e.setCancelled(true);
				for(int i = 0; i <= 35; i++){
					if(((Player) e.getEntity()).getInventory().getItem(i) == null) {
						((Player) e.getEntity()).getInventory().setItem(i, e.getItem().getItemStack());
					}
					
				}
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
