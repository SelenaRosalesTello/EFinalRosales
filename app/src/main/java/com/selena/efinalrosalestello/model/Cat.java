package com.selena.efinalrosalestello.model;

public class Cat extends Shows{
    private String adoptado;

    public Cat(String name, String imgUrl, String edad, String adoptado) {
        super(name, imgUrl, edad);
        this.adoptado = adoptado;
    }

    public String getAdoptado() {
        return adoptado;
    }

    public void setAdoptado(String adoptado) {
        this.adoptado = adoptado;
    }
}
