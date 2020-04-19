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

    //Metodo que muestra el documento correspondiente en el toString
    public String mostrarDocs(){
        String mensaje="";
        for(Documento d : documentos){
            mensaje+="Nombre:"+d.getNombre()+", DUI:"+d.getNumero();
        }
        return mensaje;
    }

    @Override
    public String toString() {
        return "ServicioProfesional{" +
                "Extensión:" + extension + "meses" +
                ", Nombre:" + nombre +
                ", Puesto:" + puesto +
                ", Salario: $" + salario +
                "\n\tDocumentos:" + mostrarDocs() +
                '}';
    }
}
