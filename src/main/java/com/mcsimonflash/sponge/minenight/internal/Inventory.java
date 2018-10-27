package com.mcsimonflash.sponge.minenight.internal;

import com.mcsimonflash.sponge.minenight.MineNight;
import com.mcsimonflash.sponge.minenight.game.Character;
import com.mcsimonflash.sponge.minenight.game.Game;
import com.mcsimonflash.sponge.minenight.game.Proposal;
import com.mcsimonflash.sponge.teslalibs.inventory.Element;
import com.mcsimonflash.sponge.teslalibs.inventory.Layout;
import com.mcsimonflash.sponge.teslalibs.inventory.View;
import com.mcsimonflash.sponge.teslalibs.message.Message;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.DyeColors;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.InventoryArchetype;
import org.spongepowered.api.item.inventory.InventoryArchetypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.property.InventoryTitle;
import org.spongepowered.api.text.Text;

public class Inventory {

    public static final Element
            RED_PANE = Element.of(ItemStack.builder().itemType(ItemTypes.STAINED_GLASS_PANE).add(Keys.DYE_COLOR, DyeColors.RED).build()),
            GRAY_PANE = Element.of(ItemStack.builder().itemType(ItemTypes.STAINED_GLASS_PANE).add(Keys.DYE_COLOR, DyeColors.GRAY).build());

    private static View view(InventoryArchetype archetype, String title) {
        return View.builder()
                .archetype(archetype)
                .property(InventoryTitle.of(Message.toText(title)))
                .build(MineNight.getContainer());
    }

    public static View gameMenu(Character player, Game game) {
        return view(InventoryArchetypes.CHEST, game.name)
                .define(Layout.builder()
                        .checker(RED_PANE, GRAY_PANE)
                        .set(Element.of(Utils.item(ItemTypes.DRAGON_EGG, "Proposal").build(), a ->
                                proposalMenu(player, game.getCurrentNode().getCurrentProposal()).open(a.getPlayer())), 13)
                        .build());
    }

    public static View proposalMenu(Character player, Proposal proposal) {
        boolean modifiable = proposal.modifiable && player == proposal.owner;
        return view(InventoryArchetypes.CHEST, "Proposal")
                .define(Layout.builder()
                        .checker(RED_PANE, GRAY_PANE)
                        .set(proposalElement(proposal, proposal.game.characters.get(0), modifiable), 11)
                        .set(proposalElement(proposal, proposal.game.characters.get(1), modifiable), 12)
                        .set(proposalElement(proposal, proposal.game.characters.get(2), modifiable), 13)
                        .set(proposalElement(proposal, proposal.game.characters.get(3), modifiable), 14)
                        .set(proposalElement(proposal, proposal.game.characters.get(4), modifiable), 15)
                        .build());
    }

    private static Element proposalElement(Proposal proposal, Character character, boolean modifiable) {
        ItemStack.Builder builder = Utils.item(ItemTypes.SKULL, character.name);
        if (modifiable) {
            if (proposal.characters.contains(character)) {
                return Element.of(Utils.enchant(builder).build(), a -> {
                    a.getSlot().set(builder.build());
                    proposal.characters.remove(character);
                    proposal.game.sendMessage(Text.of(character.name, " has been removed from the proposal."));
                });
            } else if (proposal.characters.size() != proposal.node.players) {
                return Element.of(builder.build(), a -> {
                    a.getSlot().set(Utils.enchant(builder).build());
                    proposal.characters.add(character);
                    proposal.game.sendMessage(Text.of(character.name, " has been added to the proposal."));
                });
            }
        }
        return Element.of((proposal.characters.contains(character) ? Utils.enchant(builder) : builder).build());
    }

    public static View voteMenu(Character player, Proposal proposal) {
        return view(InventoryArchetypes.CHEST, "Vote")
                .define(Layout.builder()
                        .checker(RED_PANE, GRAY_PANE)
                        .set(Element.of(Utils.item(ItemTypes.SLIME_BALL, "Approve").build(), a -> proposal.votes.put(player, true)), 11)
                        .set(Element.of(Utils.item(ItemTypes.MAGMA_CREAM, "Reject").build(), a -> proposal.votes.put(player, false)), 15)
                        .build());
    }

}