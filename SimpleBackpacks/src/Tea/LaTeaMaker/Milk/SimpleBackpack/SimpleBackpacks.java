package Tea.LaTeaMaker.Milk.SimpleBackpack;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpleBackpacks extends JavaPlugin{
	
	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents((Listener) new UsePack(), this);
		getCommand("GiveMediumBackpack").setExecutor(new BackpackItems());
		
		ShapedRecipe PackRecipe = new ShapedRecipe(new NamespacedKey(this, "SimpleBackPackRecipe"), BackpackItems.getMediumPack()).shape("LIL", "ISI", "LIL").setIngredient('L', Material.LEATHER).
		setIngredient('I', Material.IRON_INGOT).setIngredient('S', Material.STRING);
		getServer().addRecipe(PackRecipe);
	}

}
