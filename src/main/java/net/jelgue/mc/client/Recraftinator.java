package net.jelgue.mc.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.screen.ScreenHandler;
import org.lwjgl.glfw.GLFW;

public class Recraftinator { //implements IKeyboardInputHandler {

    private static final Recraftinator INSTANCE = new Recraftinator();
    private static boolean ghosting;

    private RecipeEntry<?> lastRecipe;
    private boolean spacePressed = false;

    private Recraftinator() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (!isValidWorld(client))
                return;
            if (!isValidScreen(client))
                return;
            if (lastRecipe == null)
                return;
            if (wasSpacePressed()) {
                if (ghosting) {
                    //System.out.println("In GHOST mode, recipe cancelled");
                    return;
                }
                // System.out.println("Reloading last recipe!");
                loadRecipe(lastRecipe);
            }
        });

        // InputEventHandler.getInputManager().registerKeyboardInputHandler(this);
    }

    public static Recraftinator getInstance() {
        return INSTANCE;
    }

    public static void setGhosting(boolean b) {
        ghosting = b;
    }

    public boolean onKeyInput(int keyCode, int scanCode, int modifiers, boolean eventKeyState) {
        if (keyCode == GLFW.GLFW_KEY_SPACE && eventKeyState) {
            spacePressed = true;
            return true;
        }
        return false;
    }

    public void onRecipeClicked(RecipeEntry<?> recipe) {
        if (recipe != null) {
            // System.out.println("Setting new Last Recipe " + recipe.getId());
            ghosting = false;
            lastRecipe = recipe;
        }
    }

    public void onScreenClosed() {
        ghosting = false;
        lastRecipe = null;
    }

    public void onResultTaken() {
        // recipeReLoaded = false;
    }

    private boolean isValidScreen(MinecraftClient client) {
        ClientPlayerEntity ply = client.player;
        ScreenHandler sh = ply.currentScreenHandler;
        if (sh == null)
            return false;
        ClientPlayerInteractionManager im = client.interactionManager;
        if (im == null)
            return false;
        return true;
    }

    private boolean isValidWorld(MinecraftClient client) {
        if (client.world == null)
            return false;
        if (!client.world.isClient())
            return false;
        return true;
    }

    private void loadRecipe(RecipeEntry<?> lastRecipe) {
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayerEntity ply = client.player;
        ClientPlayerInteractionManager im = client.interactionManager;
        if (im == null)
            return;
        im.clickRecipe(ply.currentScreenHandler.syncId, lastRecipe, Screen.hasShiftDown());
    }

    private boolean wasSpacePressed() {
        // return InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), GLFW.GLFW_KEY_SPACE);
        if (spacePressed) {
            spacePressed = false;
            return true;
        }
        return false;
    }
}
