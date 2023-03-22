package com.sysops.constant;

public enum CategoryConstant {
    COMPUTERS_AND_TABLETS(1, "Computers And Tablets"),//capitalized
    PHONE(2, "Phone"),
    CAMERA(3, "Camera"),
    SMART_HOME_DEVICE(4, "Smart Home Device"),
    DRONE(5, "Drone"),
    CAR_ELECTRONIC(6, "Car Electronic");


    private int key;
    private String value;

    CategoryConstant(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static String queryValueByKey(int key) {
        for (CategoryConstant category : CategoryConstant.values()) {
            if (category.getKey() == key) {
                return category.getValue();
            }
        }
        return null;
    }

}
