package me.micrjonas.grandtheftdiamond.item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.micrjonas.grandtheftdiamond.util.Nameable;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Kit implements Nameable {
	
	private String name;
	private List<ItemStack> items;
	private int delay;
	
	public Kit(String name) {
		items = new ArrayList<>();
	}
	
	public Kit(String name, int delay) {
		this(name);
		this.delay = delay;
	}
	
	public Kit(String name, ItemStack... items) {
		this(name);
		for (ItemStack item : items) {
			this.items.add(item.clone());
		}
	}
	
	public Kit(String name, List<ItemStack> items) {
		this(name);
		for (ItemStack item : items) {
			this.items.add(item.clone());
		}
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public int getDelay() {
		return delay;
	}
	
	public void setDelay(int delay) {
		if (delay < -1) {
			throw new IllegalArgumentException("Delay is not allowed to be < -1");
		}
	}
	
	public List<ItemStack> getItems() {
		return Collections.unmodifiableList(items);
	}
	
	public void addItem(ItemStack item) {
		if (item != null) {
			items.add(item.clone());
		}
	}
	
	public void removeItemsOfType(Material type) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getType() == type) {
				items.remove(i);
				i--;
			}
		}
	}
	
	public void removeEqualItems(ItemStack item, boolean ignoreAmount) {
		if (!ignoreAmount) {
			for (int i = 0; i < items.size(); i++) {
				if (items.get(i).equals(item)) {
					items.remove(i);
					i--;
				}
			}
		}
		else {
			for (int i = 0; i < items.size(); i++) {
				ItemStack itemToCheck = items.get(i);
				boolean remove = true;
				if (item.hasItemMeta() != itemToCheck.hasItemMeta()) {
					remove = false;
				}
				else if (item.hasItemMeta() != itemToCheck.hasItemMeta()) {
					remove = false;
				}
				else if (item.hasItemMeta() && !item.getItemMeta().equals(itemToCheck.getItemMeta())) {
					remove = false;
				}
				if (remove) {
					items.remove(i);
					i--;
				}
			}
		}
	}

	public void giveToPlayer(Player p) {
		for (ItemStack item : items) {
			p.getInventory().addItem(item);
		}
	}
	
}
