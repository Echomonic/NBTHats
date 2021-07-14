package dev.echo.hats.utils;

public enum EntityEquipmentID {


    MAIN_HAND(-1),
    HELMET(3),
    CHESTPLATE(2),
    LEGGINGS(1),
    BOOTS(0),



    ;

    private final int i;

    EntityEquipmentID(int i) {
        this.i = i;
    }

    public int getId() {
        return i;
    }

}
