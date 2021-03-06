package com.snowgears.shop.gui;

import com.snowgears.shop.Shop;
import com.snowgears.shop.handler.ShopGuiHandler;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class CommandsWindow extends ShopGuiWindow {

    public CommandsWindow(UUID player){
        super(player);
        this.title = Shop.getPlugin().getGuiHandler().getTitle(ShopGuiHandler.GuiTitle.COMMANDS);
        this.page = Bukkit.createInventory(null, INV_SIZE, title);
        initInvContents();
    }

    @Override
    protected void initInvContents(){

        ItemStack currencyIcon = Shop.getPlugin().getGuiHandler().getIcon(ShopGuiHandler.GuiIcon.COMMANDS_CURRENCY, null, null);
        page.setItem(10, currencyIcon);


        ItemStack setCurrencyIcon = Shop.getPlugin().getGuiHandler().getIcon(ShopGuiHandler.GuiIcon.COMMANDS_SET_CURRENCY, null, null);
        page.setItem(11, setCurrencyIcon);


        ItemStack setGambleIcon = Shop.getPlugin().getGuiHandler().getIcon(ShopGuiHandler.GuiIcon.COMMANDS_SET_GAMBLE, null, null);
        page.setItem(12, setGambleIcon);


        ItemStack refreshDisplaysIcon = Shop.getPlugin().getGuiHandler().getIcon(ShopGuiHandler.GuiIcon.COMMANDS_REFRESH_DISPLAYS, null, null);
        page.setItem(13, refreshDisplaysIcon);


        ItemStack reloadIcon = Shop.getPlugin().getGuiHandler().getIcon(ShopGuiHandler.GuiIcon.COMMANDS_RELOAD, null, null);
        page.setItem(14, reloadIcon);

    }
}
