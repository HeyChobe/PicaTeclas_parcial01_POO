package com.CRCI.x00204119;

public class PlazaFija extends Empleado {
    private int extension;

    public PlazaFija(String nombre, String puesto, double salario, int extension){
        super(nombre, puesto, salario);
        this.extension=extension;
    }

    public int getExtension() {
        return extension;
    }

    public void setExtension(int extension) {
        this.extension = extension;
    }

    public String mostrarDocs(){
        String mensaje="";
        for(Documento d : documentos){
            mensaje+=d.getNombre()+"/"+d.getNumero();
        }
        return mensaje;
    }

    @Override
    public String toString() {
        return "PlazaFija{" +
                "extension=" + extension +
                ", nombre='" + nombre + '\'' +
                ", puesto='" + puesto + '\'' +
                ", salario=" + salario + "documentos=" + mostrarDocs() +
                '}';
    }
}
