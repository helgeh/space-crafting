package net.jelgue.mc.mixin;

import net.jelgue.mc.client.Recraftinator;
import net.minecraft.client.Keyboard;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public abstract class KeyboardMixin {
    @Inject(method = "onKey", cancellable = true, at = @At(value = "FIELD", target = "Lnet/minecraft/client/Keyboard;debugCrashStartTime:J", ordinal = 0))
    private void onKeyboardInput(long windowPointer, int key, int scanCode, int action, int modifiers, CallbackInfo ci) {
        boolean eventKeyState = action != GLFW.GLFW_RELEASE;

        if (Recraftinator.getInstance().onKeyInput(key, scanCode, modifiers, eventKeyState)) {
            ci.cancel();
        }
    }
}
