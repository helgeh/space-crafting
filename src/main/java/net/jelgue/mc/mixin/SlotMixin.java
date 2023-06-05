package net.jelgue.mc.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.CraftingResultSlot;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Slot.class)
public class SlotMixin {
//
//    @Inject(method = "setStack", at = @At("RETURN"))
//    public void setStack(ItemStack stack, CallbackInfo ci) {
//        if (this instanceof CraftingResultSlot)
//            System.out.println("ResultSlot.setStack()");
//    }
}
