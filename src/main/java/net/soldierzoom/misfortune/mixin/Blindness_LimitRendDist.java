package net.soldierzoom.misfortune.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.soldierzoom.misfortune.curse.capability.PlayerCurse;
import net.soldierzoom.misfortune.curse.main.CurseType;


@Mixin(Options.class)
public class Blindness_LimitRendDist {
    // Intercept getEffectiveRenderDistance() (the method used for chunk rendering)
    @Inject(method = "getEffectiveRenderDistance", at = @At("RETURN"), cancellable = true)
    private void misfortune$capRenderDistance(CallbackInfoReturnable<Integer> cir) {
        Minecraft mc = Minecraft.getInstance();
        System.out.println("[Misfortune] OptionsMixin applied, player curse=" + PlayerCurse.get(mc.player).get());
        if (mc.player != null && PlayerCurse.get(mc.player).get() == CurseType.BLINDNESS) {
            // hard cap at 2 chunks
            cir.setReturnValue(2);
        }
    }
}
