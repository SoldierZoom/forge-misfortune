package net.soldierzoom.misfortune.curse.capability;


import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.soldierzoom.misfortune.curse.main.CurseComponent;
import net.soldierzoom.misfortune.curse.main.CurseType;
import net.soldierzoom.misfortune.curse.main.ICurse;

public class CurseCapability {
    public static final Capability<ICurse> CURSE = CapabilityManager.get(new CapabilityToken<>() {
    });
    public static final ResourceLocation KEY = ResourceLocation.fromNamespaceAndPath("misfortune", "curse");

    // Provider that gets attached to the Player
    public static class Provider implements ICapabilitySerializable<CompoundTag> {
        private final CurseComponent backend = new CurseComponent();
        private final LazyOptional<ICurse> opt = LazyOptional.of(() -> backend);

        @Override
        public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
            return cap == CURSE ? opt.cast() : LazyOptional.empty();
        }

        @Override
        public CompoundTag serializeNBT() {
            CompoundTag tag = new CompoundTag();
            tag.putString("curse", backend.get().id());
            return tag;
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            backend.set(CurseType.fromId(nbt.getString("curse")));
        }
    }
}