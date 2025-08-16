package net.soldierzoom.misfortune.curse.main;

import net.minecraft.resources.ResourceLocation;

public enum CurseType {
    NONE("none"),
    BLINDNESS("blindness"),
    DEAFNESS("deafness"),
    CANT_FEEL("cant_feel");

    private final String id;

    CurseType(String id) { this.id = id; }

    public String id() { return id; }

    public static CurseType fromId(String id) {
        for (CurseType t : values()) if (t.id.equals(id)) return t;
        return NONE;
    }

    public ResourceLocation rl(String modid) {
        return ResourceLocation.fromNamespaceAndPath(modid, id);
    }
}
