package net.online.player.list;

import com.mojang.brigadier.Command;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.minecraft.server.command.CommandManager.*;
import static net.online.player.list.ConfigManager.removePlayerName;
import static net.online.player.list.ConfigManager.writePlayerName;

public class OnlinePlayerList implements ModInitializer {

    

    @Override
    public void onInitialize() {
        final Item FABRIC_ITEM = new PlayerKicker(new Item.Settings().group(ItemGroup.MISC));
        // When user runs /addPlayerName, add player name to config file
        // Initialize the command
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("testAdd").executes(context -> {
            // For versions below 1.19, replace "Text.literal" with "new LiteralText".
            context.getSource().sendMessage((Text) literal("Adding " + context.getSource().getName() + " to config file..."));
            // Add player name to config file
            // Get player name
            String playerName = context.getSource().getName();
            writePlayerName(playerName);
            return Command.SINGLE_SUCCESS;
        })));

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("testRem").executes(context -> {
            // For versions below 1.19, replace "Text.literal" with "new LiteralText".
            context.getSource().sendMessage((Text) literal("Adding " + context.getSource().getName() + " to config file..."));
            // Remove player name from config file
            // Get player name
            String playerName = context.getSource().getName();
            removePlayerName(playerName);
            return Command.SINGLE_SUCCESS;
        })));
        
        ConfigManager.createConfigFolder("OnlinePlayerList");
        // Create file "config/OnlinePlayerList/onlinePlayerList.json" if it doesn't exist
        ConfigManager.createConfigFile("OnlinePlayerList", "onlinePlayerList.json");
        // Empty the file
        ConfigManager.emptyConfigFile("OnlinePlayerList", "onlinePlayerList.json");
        Registry.register(Registry.ITEM, new Identifier("playerlist", "player_kicker"), FABRIC_ITEM);
    }
}
