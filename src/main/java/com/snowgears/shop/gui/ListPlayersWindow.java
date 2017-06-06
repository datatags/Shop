package com.snowgears.shop.gui;

import com.snowgears.shop.Shop;
import com.snowgears.shop.util.OfflinePlayerNameComparator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ListPlayersWindow extends ShopGuiWindow {

    public ListPlayersWindow(UUID player){
        super(player);
        this.page = Bukkit.createInventory(null, INV_SIZE, "All Player Shops");
        initInvContents();
    }

    @Override
    protected void initInvContents(){
        super.initInvContents();
        this.clearInvBody();

        makeMenuBarUpper();
        makeMenuBarLower();

        List<OfflinePlayer> owners = Shop.getPlugin().getShopHandler().getShopOwners();
        owners.sort(new OfflinePlayerNameComparator());

        int startIndex = pageIndex * 36; //36 items is a full page in the inventory
        ItemStack icon;
        boolean added = true;

        for (int i=startIndex; i< owners.size(); i++) {
            icon = createIcon(owners.get(i));

            if(!this.addIcon(icon)){
                added = false;
                break;
            }
        }

        if(added){
            page.setItem(53, null);
        }
        else{
            page.setItem(53, this.getNextPageIcon());
        }
    }

    private ItemStack createIcon(OfflinePlayer owner){
        ItemStack icon;

        List<String> lore = new ArrayList<>();
        lore.add("Shops: "+Shop.getPlugin().getShopHandler().getShops(owner.getUniqueId()).size());
        lore.add("UUID: "+ owner.getUniqueId().toString());

        String name;
        if(Shop.getPlugin().getShopHandler().getAdminUUID().equals(owner.getUniqueId())) {
            name = "Admin";
            icon = new ItemStack(Material.CHEST);

            ItemMeta meta = icon.getItemMeta();
            meta.setDisplayName(name);
            meta.setLore(lore);

            icon.setItemMeta(meta);
        }
        else {
            name = owner.getName();
            icon = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);

            SkullMeta meta = (SkullMeta) icon.getItemMeta();
            meta.setOwner(name);
            meta.setDisplayName(name);
            meta.setLore(lore);

            icon.setItemMeta(meta);
        }
        return icon;
    }

    @Override
    protected void makeMenuBarUpper(){
        super.makeMenuBarUpper();

//        ItemStack searchIcon = new ItemStack(Material.COMPASS);
//        ItemMeta meta = searchIcon.getItemMeta();
//        meta.setDisplayName("Search");
//        searchIcon.setItemMeta(meta);
//
//        page.setItem(8, searchIcon);
    }

    @Override
    protected void makeMenuBarLower(){
        super.makeMenuBarLower();

    }
}

