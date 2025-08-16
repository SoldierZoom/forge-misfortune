package net.soldierzoom.misfortune.curse.main;

public interface ICurse {
    CurseType get();
    void set(CurseType type);
    boolean isAssigned();
}
