package com.CRCI.x00204119;

import javax.swing.*;

public class ServicioProfesional extends Empleado{
    private int mesesContrato;

    public ServicioProfesional(String nombre, String puesto, double salario, int mesesContrato){
    super(nombre, puesto, salario);
    this.mesesContrato=mesesContrato;
    }

    public int getMeses() {
        return mesesContrato;
    }

    public void setMeses(int mesesContrato) {
        this.mesesContrato = mesesContrato;
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
                "Contrato:" + mesesContrato + "meses" +
                ", Nombre:" + nombre +
                ", Puesto:" + puesto +
                ", Salario: $" + salario +
                "\n\tDocumentos:" + mostrarDocs() +
                '}';
    }
}
