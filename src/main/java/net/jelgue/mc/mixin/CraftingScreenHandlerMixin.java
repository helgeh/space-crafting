package net.jelgue.mc.mixin;

import net.jelgue.mc.client.Recraftinator;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.screen.CraftingScreenHandler;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CraftingScreenHandler.class)
public class CraftingScreenHandlerMixin {

    @Inject(method = "onClosed", at = @At("RETURN"))
    public void onClosed(PlayerEntity player, CallbackInfo ci) {
        // System.out.println("CraftingScreen Closed!");
        Recraftinator.onScreenClosed();
    }

//    @Inject(method = "onContentChanged", at = @At("RETURN"))
//    private void onContentChanged(Inventory inventory, CallbackInfo ci) {
//        System.out.println("Something changed at RETURN in crafting screen ");
//
//
//    }

}
