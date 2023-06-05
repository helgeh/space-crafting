package net.jelgue.mc.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.client.util.InputUtil;
import net.minecraft.recipe.Recipe;
import org.lwjgl.glfw.GLFW;

public class Recraftinator {

    private static Recipe<?> lastRecipe;
    private static boolean recipeReLoaded = false;

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (!isValidWorld(client))
                return;
            if (!isValidScreen(client))
                return;
            if (lastRecipe == null)
                return;
            while (isSpacePressed() && !recipeReLoaded) {
                // System.out.println("Reloading last recipe!");
                loadRecipe(lastRecipe);
            }
        });
    }

    public static void onRecipeClicked(Recipe<?> recipe) {
        if (recipe != null) {
            // System.out.println("Setting new Last Recipe " + recipe.getId());
            lastRecipe = recipe;
            recipeReLoaded = false;
        }
    }

    public static void onScreenClosed() {
        lastRecipe = null;
        recipeReLoaded = false;
    }

    public static void onResultTaken() {
        recipeReLoaded = false;
    }

    private static boolean isValidScreen(MinecraftClient client) {
        ClientPlayerEntity ply = client.player;
        ClientPlayerInteractionManager im = client.interactionManager;
        if (im == null)
            return false;
        return true;
    }

    private static boolean isValidWorld(MinecraftClient client) {
        if (client.player == null || client.player.world == null)
            return false;
        if (!client.player.world.isClient())
            return false;
        return true;
    }

    private static void loadRecipe(Recipe<?> lastRecipe) {
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayerEntity ply = client.player;
        ClientPlayerInteractionManager im = client.interactionManager;
        if (im == null)
            return;
        im.clickRecipe(ply.currentScreenHandler.syncId, lastRecipe, Screen.hasShiftDown());
        recipeReLoaded = true;
    }

    private static boolean isSpacePressed() {
        return InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), GLFW.GLFW_KEY_SPACE);
    }
}
