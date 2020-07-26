package Tea.LaTeaMaker.Milk.SimpleBackpack;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;


public class DataFactory {

	private static NamespacedKey K = new NamespacedKey(SimpleBackpacks.getPlugin(SimpleBackpacks.class),
			"Backpack-Inventory-Key");

	public static ItemStack SetData(ItemStack Be, Inventory E) {
		ItemStack Aft = Be;
		ItemMeta M = Aft.getItemMeta();
		
		M.getPersistentDataContainer().set(K, PersistentDataType.STRING, toBase64(E));
		
		Aft.setItemMeta(M);

		return Aft;
	}

	public static Inventory GetData(ItemStack Be) {
		if (Be.getItemMeta().getPersistentDataContainer().has(K, PersistentDataType.STRING)) {
			Inventory Inv;
			try {
				Inv = fromBase64(Be.getItemMeta().getPersistentDataContainer().get(K, PersistentDataType.STRING));
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			return Inv;
		} else {
			Inventory Inv = Bukkit.createInventory(null, 36, "Medium Backpack");
			return Inv;
		}

	}
	
	
	
	
	public static String toBase64(Inventory inventory) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
            
            dataOutput.writeInt(inventory.getSize());
            
            for (int i = 0; i < inventory.getSize(); i++) {
                dataOutput.writeObject(inventory.getItem(i));
            }
            
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Exception e) {
            throw new IllegalStateException("Unable To Save Item Stacks.", e);
        }        
    }
	
	public static Inventory fromBase64(String data) throws IOException {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            Inventory inventory = Bukkit.getServer().createInventory(null, dataInput.readInt());
    
            for (int i = 0; i < inventory.getSize(); i++) {
                inventory.setItem(i, (ItemStack) dataInput.readObject());
            }
            dataInput.close();
            return inventory;
        } catch (ClassNotFoundException e) {
            throw new IOException("Unable To Decode Class Type.", e);
        }
    }

}
