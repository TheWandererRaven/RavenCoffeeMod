package com.thewandererraven.ravencoffee.mixin;

import com.thewandererraven.ravencoffee.Constants;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class ExampleMixin {
    @Inject(at = @At("HEAD"), method = "loadWorld")
    private void init(CallbackInfo info) {

        Constants.LOGGER.info("This line is printed by an example mod mixin from Fabric!");
        Constants.LOGGER.info("Classloader: {}", this.getClass().getClassLoader());
    }
}