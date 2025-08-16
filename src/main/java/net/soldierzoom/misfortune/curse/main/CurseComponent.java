package net.soldierzoom.misfortune.curse.main;

public class CurseComponent implements ICurse {
    private CurseType curse = CurseType.NONE;

    @Override public CurseType get() { return curse; }
    @Override public void set(CurseType type) { this.curse = (type == null ? CurseType.NONE : type); }
    @Override public boolean isAssigned() { return curse != CurseType.NONE; }
}