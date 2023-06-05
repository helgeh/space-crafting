package net.jelgue.mc.client;

import net.fabricmc.api.ClientModInitializer;

public class SpaceCraftingClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // System.out.println("Client initializer");
        Recraftinator.init();
    }
}
