package com.mcsimonflash.sponge.minenight.internal;

import com.google.common.collect.Lists;
import com.mcsimonflash.sponge.teslalibs.message.Message;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.inventory.ItemStack;

public class Utils {

    public static ItemStack.Builder item(ItemType type, String name) {
        return ItemStack.builder()
                .itemType(type)
                .add(Keys.DISPLAY_NAME, Message.toText(name));
    }

    public static ItemStack.Builder enchant(ItemStack.Builder builder) {
        return builder.add(Keys.ITEM_ENCHANTMENTS, Lists.newArrayList());
    }

}