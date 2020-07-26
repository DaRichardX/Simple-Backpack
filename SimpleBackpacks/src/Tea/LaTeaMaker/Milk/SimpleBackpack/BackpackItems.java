package Tea.LaTeaMaker.Milk.SimpleBackpack;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BackpackItems implements CommandExecutor{
	
	public static ItemStack getMediumPack() {
		
		ItemStack PackItem = SkullUtil.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGRjYzZlYjQwZjNiYWRhNDFlNDMzOTg4OGQ2ZDIwNzQzNzU5OGJkYmQxNzVjMmU3MzExOTFkNWE5YTQyZDNjOCJ9fX0=");
		ItemMeta M = PackItem.getItemMeta();
		
		M.setDisplayName(ChatColor.BLUE + "Medium Backpack");
		M.addItemFlags(ItemFlag.values());
		
		PackItem.setItemMeta(M);
		return PackItem;
	}
	

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg0 instanceof Player) {
			((Player) arg0).getInventory().addItem(getMediumPack());
			arg0.sendMessage(ChatColor.GREEN + "You Recived: " + ChatColor.WHITE + "1x" + ChatColor.BLUE + " Medium Backpack");
		}else {
			arg0.sendMessage(ChatColor.RED + "You Gotta Be A Player To Do This!");
		}
		return false;
	}

}
