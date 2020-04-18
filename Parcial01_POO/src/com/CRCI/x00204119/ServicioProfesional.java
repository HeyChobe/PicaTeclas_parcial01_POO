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

    public String mostrarDocs(){
        String mensaje="";
        for(Documento d : documentos){
            mensaje+=d.getNombre()+"/"+d.getNumero();
        }
        return mensaje;
    }

    @Override
    public String toString() {
        return "ServicioProfesional{" +
                "mesesContrato=" + mesesContrato +
                ", nombre='" + nombre + '\'' +
                ", puesto='" + puesto + '\'' +
                ", salario=" + salario + "documentos=" +
                mostrarDocs() +
                '}';
    }
}
