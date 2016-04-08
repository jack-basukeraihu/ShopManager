package org.bitbucket.jack_basukeraihu.ShopManager;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;

public class MainClass extends JavaPlugin {

	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerListener(this), this);
		saveDefaultConfig();
		setup();
		setupEconomy();
	}

	private boolean setupEconomy() {
		RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null) {
			economy = economyProvider.getProvider();
		}

		return (economy != null);
	}

	public static Economy economy;

	static ArrayList<Material> MainMaterial = new ArrayList<Material>();
	static ArrayList<Material> SubMaterial = new ArrayList<Material>();
	static ArrayList<Material> T1Material = new ArrayList<Material>();
	static ArrayList<Material> T2Material = new ArrayList<Material>();

	static ArrayList<Integer> MainMeta = new ArrayList<Integer>();
	static ArrayList<Integer> SubMeta = new ArrayList<Integer>();
	static ArrayList<Integer> T1Meta = new ArrayList<Integer>();
	static ArrayList<Integer> T2Meta = new ArrayList<Integer>();

	static ArrayList<String> MainName = new ArrayList<String>();
	static ArrayList<String> SubName = new ArrayList<String>();
	static ArrayList<String> T1Name = new ArrayList<String>();
	static ArrayList<String> T2Name = new ArrayList<String>();

	static String pex = ChatColor.AQUA+"[ShopManager] ";

	public void setup() {
		for (String str : getConfig().getStringList("LimitItems")) {
			String[] get = str.split(",");
			if (get[0].equalsIgnoreCase("Main")) {
				MainMaterial.add(Material.valueOf(get[1]));
				MainMeta.add(Integer.parseInt(get[2]));
				MainName.add(get[3]);
			}
			if (get[0].equalsIgnoreCase("Sub")) {
				SubMaterial.add(Material.valueOf(get[1]));
				SubMeta.add(Integer.parseInt(get[2]));
				SubName.add(get[3]);
			}
			if (get[0].equalsIgnoreCase("T1")) {
				T1Material.add(Material.valueOf(get[1]));
				T1Meta.add(Integer.parseInt(get[2]));
				T1Name.add(get[3]);
			}
			if (get[0].equalsIgnoreCase("T2")) {
				T2Material.add(Material.valueOf(get[1]));
				T2Meta.add(Integer.parseInt(get[2]));
				T2Name.add(get[3]);
			}
		}
	}

	public static ItemStack ItemUtil(Material material,short data,int amount,String name) {
		ItemStack im = new ItemStack(material,amount,data);
		ItemMeta meta = im.getItemMeta();
		meta.setDisplayName(name);
		im.setItemMeta(meta);
		return im;
	}

	public static ItemStack ItemUtil(Material material,short data,int amount,String name,ArrayList<String> lore) {
		ItemStack im = new ItemStack(material,amount,data);
		ItemMeta meta = im.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(lore);
		im.setItemMeta(meta);
		return im;
	}

	public void OpenShop(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9, ChatColor.DARK_AQUA+ "アイテムショップ");
		inv.setItem(1, ItemUtil(Material.DIAMOND_SWORD, (short) 0, 1, ChatColor.GREEN+"メインアイテムを購入する"));
		inv.setItem(3, ItemUtil(Material.IRON_SWORD, (short) 0, 1, ChatColor.GREEN+"サブアイテムを購入する"));
		inv.setItem(5, ItemUtil(Material.GOLD_SWORD, (short) 0, 1, ChatColor.GREEN+"特殊アイテム1を購入する"));
		inv.setItem(7, ItemUtil(Material.STONE_SWORD, (short) 0, 1, ChatColor.GREEN+"特殊アイテム2を購入する"));
		p.openInventory(inv);
	}

	public static void OpenMainShop(Player p) {
		Inventory inv = Bukkit.createInventory(null, 45, ChatColor.DARK_AQUA+ "メインアイテムを購入する");
		for (String str : PlayerListener.plugin.getConfig().getStringList("MainShop")) {
			String[] get = str.split(",");
			if (get.length >= 5) {
				ArrayList<String> lore = new ArrayList<String>();
				int y = 0;
				for (String string : get) {
					if (y <= 3) {
						y++;
						continue;
					}
					lore.add(string);
				}
				lore.add("値段: "+get[3]);
				inv.addItem(ItemUtil(Material.valueOf(get[0]),Short.parseShort(get[1]),1,get[2],lore));
			} else {
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("値段: "+get[3]);
				inv.addItem(ItemUtil(Material.valueOf(get[0]),Short.parseShort(get[1]),1,get[2],lore));
			}
		}
		p.openInventory(inv);
	}

	public static void OpenSubShop(Player p) {
		Inventory inv = Bukkit.createInventory(null, 45, ChatColor.DARK_AQUA+ "サブアイテムを購入する");
		for (String str : PlayerListener.plugin.getConfig().getStringList("SubShop")) {
			String[] get = str.split(",");
			if (get.length >= 5) {
				ArrayList<String> lore = new ArrayList<String>();
				int y = 0;
				for (String string : get) {
					if (y <= 3) {
						y++;
						continue;
					}
					lore.add(string);
				}
				lore.add("値段: "+get[3]);
				inv.addItem(ItemUtil(Material.valueOf(get[0]),Short.parseShort(get[1]),1,get[2],lore));
			} else {
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("値段: "+get[3]);
				inv.addItem(ItemUtil(Material.valueOf(get[0]),Short.parseShort(get[1]),1,get[2],lore));
			}
		}
		p.openInventory(inv);
	}

	public static void OpenT1Shop(Player p) {
		Inventory inv = Bukkit.createInventory(null, 45, ChatColor.DARK_AQUA+ "特殊アイテム1を購入する");
		for (String str : PlayerListener.plugin.getConfig().getStringList("T1Shop")) {
			String[] get = str.split(",");
			if (get.length >= 5) {
				ArrayList<String> lore = new ArrayList<String>();
				int y = 0;
				for (String string : get) {
					if (y <= 3) {
						y++;
						continue;
					}
					lore.add(string);
				}
				lore.add("値段: "+get[3]);
				inv.addItem(ItemUtil(Material.valueOf(get[0]),Short.parseShort(get[1]),1,get[2],lore));
			} else {
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("値段: "+get[3]);
				inv.addItem(ItemUtil(Material.valueOf(get[0]),Short.parseShort(get[1]),1,get[2],lore));
			}
		}
		p.openInventory(inv);
	}

	public static void OpenT2Shop(Player p) {
		Inventory inv = Bukkit.createInventory(null, 45, ChatColor.DARK_AQUA+ "特殊アイテム2を購入する");
		for (String str : PlayerListener.plugin.getConfig().getStringList("T2Shop")) {
			String[] get = str.split(",");
			if (get.length >= 5) {
				ArrayList<String> lore = new ArrayList<String>();
				int y = 0;
				for (String string : get) {
					if (y <= 3) {
						y++;
						continue;
					}
					lore.add(string);
				}
				lore.add("値段: "+get[3]);
				inv.addItem(ItemUtil(Material.valueOf(get[0]),Short.parseShort(get[1]),1,get[2],lore));
			} else {
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("値段: "+get[3]);
				inv.addItem(ItemUtil(Material.valueOf(get[0]),Short.parseShort(get[1]),1,get[2],lore));
			}
		}
		p.openInventory(inv);
	}

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equals("shop")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (args.length == 0) {
					OpenShop(p);
					return true;
				}
				if (args.length == 1) {
					if (p.isOp()) {
						if (args[0].equalsIgnoreCase("help")) {
							p.sendMessage(pex+ChatColor.GOLD+"/shop add <Main|Sub|T1|T2> <値段> :ショップにアイテムを追加します");
							p.sendMessage(pex+ChatColor.GOLD+"/shop help :コマンドヘルプを表示します");
							p.sendMessage(pex+ChatColor.GOLD+"/shop reload :configを再読み込みします");
						}

						if (args[0].equalsIgnoreCase("reload")) {
							reloadConfig();
							MainMaterial.clear();
							SubMaterial.clear();
							T1Material.clear();
							T2Material.clear();
							MainMeta.clear();
							SubMeta.clear();
							T1Meta.clear();
							T2Meta.clear();
							MainName.clear();
							SubName.clear();
							T1Name.clear();
							T2Name.clear();
							setup();
							p.sendMessage(pex+ChatColor.LIGHT_PURPLE+"configを再読み込みしました");
						}
					}
				}
				if (args.length == 3) {
					if (args[0].equalsIgnoreCase("add")) {
						if (p.getItemInHand() == null) {
							p.sendMessage(pex+ChatColor.RED+"手に追加したいアイテムを持ってください");
							return true;
						}
						if (p.getItemInHand().getType() == Material.AIR) {
							p.sendMessage(pex+ChatColor.RED+"手に追加したいアイテムを持ってください");
							return true;
						}
						if (p.getItemInHand().getItemMeta().getDisplayName() == null) {
							p.sendMessage(pex+ChatColor.RED+"DisplayNameが無いアイテムは追加できません");
							return true;
						}
						ItemStack im = p.getItemInHand();
						if (args[1].equalsIgnoreCase("Main")) {
							MainMaterial.add(im.getType());
							MainMeta.add((int) im.getData().getData());
							MainName.add(im.getItemMeta().getDisplayName());
							List<String> LimitList = getConfig().getStringList("LimitItems");
							LimitList.add("Main,"+im.getType().toString()+","+im.getData().getData()+","+im.getItemMeta().getDisplayName());
							getConfig().set("LimitItems", LimitList);
							String lore = "";
							if (im.getItemMeta().getLore() != null) {
								for (String str : im.getItemMeta().getLore()) {
									lore = lore + ","+str;
								}
							}
							List<String> ShopList = getConfig().getStringList("MainShop");
							ShopList.add(im.getType().toString()+","+im.getData().getData()+","+im.getItemMeta().getDisplayName()+","+Integer.parseInt(args[2])+lore);
							getConfig().set("MainShop", ShopList);
							saveConfig();
						}
						if (args[1].equalsIgnoreCase("Sub")) {
							SubMaterial.add(im.getType());
							SubMeta.add((int) im.getData().getData());
							SubName.add(im.getItemMeta().getDisplayName());
							List<String> LimitList = getConfig().getStringList("LimitItems");
							LimitList.add("Sub,"+im.getType().toString()+","+im.getData().getData()+","+im.getItemMeta().getDisplayName());
							getConfig().set("LimitItems", LimitList);
							String lore = "";
							if (im.getItemMeta().getLore() != null) {
								for (String str : im.getItemMeta().getLore()) {
									lore = lore + ","+str;
								}
							}
							List<String> ShopList = getConfig().getStringList("SubShop");
							ShopList.add(im.getType().toString()+","+im.getData().getData()+","+im.getItemMeta().getDisplayName()+","+Integer.parseInt(args[2])+lore);
							getConfig().set("SubShop", ShopList);
							saveConfig();
						}
						if (args[1].equalsIgnoreCase("T1")) {
							T1Material.add(im.getType());
							T1Meta.add((int) im.getData().getData());
							T1Name.add(im.getItemMeta().getDisplayName());
							List<String> LimitList = getConfig().getStringList("LimitItems");
							LimitList.add("T1,"+im.getType().toString()+","+im.getData().getData()+","+im.getItemMeta().getDisplayName());
							getConfig().set("LimitItems", LimitList);
							String lore = "";
							if (im.getItemMeta().getLore() != null) {
								for (String str : im.getItemMeta().getLore()) {
									lore = lore + ","+str;
								}
							}
							List<String> ShopList = getConfig().getStringList("T1Shop");
							ShopList.add(im.getType().toString()+","+im.getData().getData()+","+im.getItemMeta().getDisplayName()+","+Integer.parseInt(args[2])+lore);
							getConfig().set("T1Shop", ShopList);
							saveConfig();
						}
						if (args[1].equalsIgnoreCase("T2")) {
							T2Material.add(im.getType());
							T2Meta.add((int) im.getData().getData());
							T2Name.add(im.getItemMeta().getDisplayName());
							List<String> LimitList = getConfig().getStringList("LimitItems");
							LimitList.add("T2,"+im.getType().toString()+","+im.getData().getData()+","+im.getItemMeta().getDisplayName());
							getConfig().set("LimitItems", LimitList);
							String lore = "";
							if (im.getItemMeta().getLore() != null) {
								for (String str : im.getItemMeta().getLore()) {
									lore = lore + ","+str;
								}
							}
							List<String> ShopList = getConfig().getStringList("T2Shop");
							ShopList.add(im.getType().toString()+","+im.getData().getData()+","+im.getItemMeta().getDisplayName()+","+Integer.parseInt(args[2])+lore);
							getConfig().set("T2Shop", ShopList);
							saveConfig();
						}
					}
				}

			} else {
				sender.sendMessage(pex+ChatColor.RED+"このコマンドはプレイヤーからのみ実行できます");
			}
		}
		return false;
	}

}
