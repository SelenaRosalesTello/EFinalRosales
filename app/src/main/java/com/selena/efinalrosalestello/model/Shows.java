package com.selena.efinalrosalestello.model;

public class Shows {
    private String name;
    private String imgUrl  ;
    private String edad;

    public Shows(String name, String imgUrl, String edad) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.edad = edad;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }
}
