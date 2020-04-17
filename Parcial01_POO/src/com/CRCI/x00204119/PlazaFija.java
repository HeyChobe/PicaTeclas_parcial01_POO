package com.CRCI.x00204119;

public class PlazaFija extends Empleado {
    private int extension;

    public PlazaFija(String nombre, String puesto, double salario, int extension){
        this.nombre=nombre;
        this.puesto=puesto;
        this.salario=salario;
        this.extension=extension;
    }

    public int getExtension() {
        return extension;
    }

    public void setExtension(int extension) {
        this.extension = extension;
    }
}
