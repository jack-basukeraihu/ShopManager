package org.bitbucket.jack_basukeraihu.ShopManager;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class PlayerListener implements Listener {
	public static MainClass plugin;

	public PlayerListener(MainClass instance) {
		plugin = instance;
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getCurrentItem() == null) {
			return;
		}
		if (e.getInventory().getTitle().equalsIgnoreCase(ChatColor.DARK_AQUA+ "アイテムショップ")) {
			e.setCancelled(true);
			ItemStack im = e.getCurrentItem();
			if (im.getItemMeta() == null) {
				return;
			}
			if (im.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN+"メインアイテムを購入する")) {
				MainClass.OpenMainShop(p);
			}
			if (im.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN+"サブアイテムを購入する")) {
				MainClass.OpenSubShop(p);
			}
			if (im.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN+"特殊アイテム1を購入する")) {
				MainClass.OpenT1Shop(p);
			}
			if (im.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN+"特殊アイテム2を購入する")) {
				MainClass.OpenT2Shop(p);
			}
		}

		if (e.getInventory().getTitle().equalsIgnoreCase(ChatColor.DARK_AQUA+ "メインアイテムを購入する")) {
			e.setCancelled(true);
			ItemStack im = e.getCurrentItem().clone();
			if (im.getItemMeta() == null) {
				return;
			}
			if (im.getItemMeta().getLore() == null) {
				return;
			}
			String price = im.getItemMeta().getLore().get(im.getItemMeta().getLore().size()-1);
			price = price.replace("値段: ", "");
			int i = Integer.parseInt(price);
			if (MainClass.economy.getBalance(p) >= i) {
				ItemMeta meta = im.getItemMeta();
				List<String> lore = meta.getLore();
				lore.remove(im.getItemMeta().getLore().size()-1);
				meta.setLore(lore);
				im.setItemMeta(meta);
				p.getInventory().setItem(0, im);
				p.updateInventory();
				MainClass.economy.withdrawPlayer(p, i);
				p.closeInventory();
			} else {
				p.sendMessage(MainClass.pex+ChatColor.RED+"所持金が足りません!");
			}
		}

		if (e.getInventory().getTitle().equalsIgnoreCase(ChatColor.DARK_AQUA+ "サブアイテムを購入する")) {
			e.setCancelled(true);
			ItemStack im = e.getCurrentItem().clone();
			if (im.getItemMeta() == null) {
				return;
			}
			if (im.getItemMeta().getLore() == null) {
				return;
			}
			String price = im.getItemMeta().getLore().get(im.getItemMeta().getLore().size()-1);
			price = price.replace("値段: ", "");
			int i = Integer.parseInt(price);
			if (MainClass.economy.getBalance(p) >= i) {
				ItemMeta meta = im.getItemMeta();
				List<String> lore = meta.getLore();
				lore.remove(im.getItemMeta().getLore().size()-1);
				meta.setLore(lore);
				im.setItemMeta(meta);
				p.getInventory().setItem(1, im);
				p.updateInventory();
				MainClass.economy.withdrawPlayer(p, i);
				p.closeInventory();
			} else {
				p.sendMessage(MainClass.pex+ChatColor.RED+"所持金が足りません!");
			}
		}

		if (e.getInventory().getTitle().equalsIgnoreCase(ChatColor.DARK_AQUA+ "特殊アイテム1を購入する")) {
			e.setCancelled(true);
			ItemStack im = e.getCurrentItem().clone();
			if (im.getItemMeta() == null) {
				return;
			}
			if (im.getItemMeta().getLore() == null) {
				return;
			}
			String price = im.getItemMeta().getLore().get(im.getItemMeta().getLore().size()-1);
			price = price.replace("値段: ", "");
			int i = Integer.parseInt(price);
			if (MainClass.economy.getBalance(p) >= i) {
				ItemMeta meta = im.getItemMeta();
				List<String> lore = meta.getLore();
				lore.remove(im.getItemMeta().getLore().size()-1);
				meta.setLore(lore);
				im.setItemMeta(meta);
				p.getInventory().setItem(2, im);
				p.updateInventory();
				MainClass.economy.withdrawPlayer(p, i);
				p.closeInventory();
			} else {
				p.sendMessage(MainClass.pex+ChatColor.RED+"所持金が足りません!");
			}
		}

		if (e.getInventory().getTitle().equalsIgnoreCase(ChatColor.DARK_AQUA+ "特殊アイテム2を購入する")) {
			e.setCancelled(true);
			ItemStack im = e.getCurrentItem().clone();
			if (im.getItemMeta() == null) {
				return;
			}
			if (im.getItemMeta().getLore() == null) {
				return;
			}
			String price = im.getItemMeta().getLore().get(im.getItemMeta().getLore().size()-1);
			price = price.replace("値段: ", "");
			int i = Integer.parseInt(price);
			if (MainClass.economy.getBalance(p) >= i) {
				ItemMeta meta = im.getItemMeta();
				List<String> lore = meta.getLore();
				lore.remove(im.getItemMeta().getLore().size()-1);
				meta.setLore(lore);
				im.setItemMeta(meta);
				p.getInventory().setItem(3, im);
				p.updateInventory();
				MainClass.economy.withdrawPlayer(p, i);
				p.closeInventory();
			} else {
				p.sendMessage(MainClass.pex+ChatColor.RED+"所持金が足りません!");
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInv(InventoryDragEvent e) {
		ItemStack im = e.getOldCursor();
		if (im != null) {
			if (e.getInventory().getType() == InventoryType.CRAFTING) {
				if (MainClass.MainMaterial.contains(im.getType())) {
					if (MainClass.MainMeta.contains((int) im.getData().getData())) {
						if (MainClass.MainName.contains(im.getItemMeta().getDisplayName())) {
							for (int i : e.getInventorySlots()) {
								if (i != 0) {
									if (i >= 1 && i <= 8) {
										e.setCancelled(true);
									}
								}
							}
						}
					}
				}

				if (MainClass.SubMaterial.contains(im.getType())) {
					if (MainClass.SubMeta.contains((int) im.getData().getData())) {
						if (MainClass.SubName.contains(im.getItemMeta().getDisplayName())) {
							for (int i : e.getInventorySlots()) {
								if (i != 1) {
									if (i >= 1 && i <= 8) {
										e.setCancelled(true);
									}
								}
							}
						}
					}
				}

				if (MainClass.T1Material.contains(im.getType())) {
					if (MainClass.T1Meta.contains((int) im.getData().getData())) {
						if (MainClass.T1Name.contains(im.getItemMeta().getDisplayName())) {
							for (int i : e.getInventorySlots()) {
								if (i != 2) {
									if (i >= 1 && i <= 8) {
										e.setCancelled(true);
									}
								}
							}
						}
					}
				}

				if (MainClass.T2Material.contains(im.getType())) {
					if (MainClass.T2Meta.contains((int) im.getData().getData())) {
						if (MainClass.T2Name.contains(im.getItemMeta().getDisplayName())) {
							for (int i : e.getInventorySlots()) {
								if (i != 3) {
									if (i >= 1 && i <= 8) {
										e.setCancelled(true);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		ItemStack im = e.getCurrentItem();
		if (im != null) {
			if (e.getSlotType() == SlotType.QUICKBAR) {
				if (MainClass.MainMaterial.contains(im.getType())) {
					if (MainClass.MainMeta.contains((int) im.getData().getData())) {
						if (MainClass.MainName.contains(im.getItemMeta().getDisplayName())) {
							if (e.getSlot() != 0) {
								e.setCancelled(true);
							}
						}
					}
				}

				if (MainClass.SubMaterial.contains(im.getType())) {
					if (MainClass.SubMeta.contains((int) im.getData().getData())) {
						if (MainClass.SubName.contains(im.getItemMeta().getDisplayName())) {
							if (e.getSlot() != 1) {
								e.setCancelled(true);
							}
						}
					}
				}

				if (MainClass.T1Material.contains(im.getType())) {
					if (MainClass.T1Meta.contains((int) im.getData().getData())) {
						if (MainClass.T1Name.contains(im.getItemMeta().getDisplayName())) {
							if (e.getSlot() != 2) {
								e.setCancelled(true);
							}
						}
					}
				}

				if (MainClass.T2Material.contains(im.getType())) {
					if (MainClass.T2Meta.contains((int) im.getData().getData())) {
						if (MainClass.T2Name.contains(im.getItemMeta().getDisplayName())) {
							if (e.getSlot() != 3) {
								e.setCancelled(true);
							}
						}
					}
				}
			}
		}

		im = e.getCursor();
		if (im != null) {
			if (e.getSlotType() == SlotType.QUICKBAR) {
				if (MainClass.MainMaterial.contains(im.getType())) {
					if (MainClass.MainMeta.contains((int) im.getData().getData())) {
						if (MainClass.MainName.contains(im.getItemMeta().getDisplayName())) {
							if (e.getSlot() != 0) {
								e.setCancelled(true);
							}
						}
					}
				}

				if (MainClass.SubMaterial.contains(im.getType())) {
					if (MainClass.SubMeta.contains((int) im.getData().getData())) {
						if (MainClass.SubName.contains(im.getItemMeta().getDisplayName())) {
							if (e.getSlot() != 1) {
								e.setCancelled(true);
							}
						}
					}
				}

				if (MainClass.T1Material.contains(im.getType())) {
					if (MainClass.T1Meta.contains((int) im.getData().getData())) {
						if (MainClass.T1Name.contains(im.getItemMeta().getDisplayName())) {
							if (e.getSlot() != 2) {
								e.setCancelled(true);
							}
						}
					}
				}

				if (MainClass.T2Material.contains(im.getType())) {
					if (MainClass.T2Meta.contains((int) im.getData().getData())) {
						if (MainClass.T2Name.contains(im.getItemMeta().getDisplayName())) {
							if (e.getSlot() != 3) {
								e.setCancelled(true);
							}
						}
					}
				}
			}
		}
	}

}
