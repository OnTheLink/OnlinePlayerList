package net.online.player.list;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
// Import server class to use disconnect methods
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerKicker extends Item {

    public PlayerKicker(Settings item$Settings_1) {
        super(item$Settings_1);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        if (world.isClient) {
            playerEntity.sendMessage(Text.of("Cannot use this item on the client!"), false);
            return new TypedActionResult<>(ActionResult.SUCCESS, playerEntity.getStackInHand(hand));
        } else {
            // Cast targeted player to server player
            ServerPlayerEntity target = (ServerPlayerEntity) playerEntity.getAttacking();
            // Disconnect player
            if (target != null) {
                playerEntity.playSound(SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, 1.0F, 1.0F);
                target.networkHandler.disconnect(Text.of("You have been kicked by " + playerEntity.getName().toString()));
                playerEntity.sendMessage(Text.of("Successfully kicked " + target.getName().toString()), false);
            } else {
                playerEntity.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
                playerEntity.sendMessage(Text.of("No player to kick"));
            }
            return new TypedActionResult<>(ActionResult.SUCCESS, playerEntity.getStackInHand(hand));
        }
    }
}
