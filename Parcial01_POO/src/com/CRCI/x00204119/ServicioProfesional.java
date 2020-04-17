package com.CRCI.x00204119;

import javax.sql.rowset.serial.SerialArray;

public class ServicioProfesional extends Empleado{
    private int mesesContrato;

    public ServicioProfesional(String nombre, String puesto, double salario, int mesesContrato){
    this.nombre=nombre;
    this.puesto=puesto;
    this.salario=salario;
    this.mesesContrato=mesesContrato;
    }

    public int getMeses() {
        return mesesContrato;
    }

    public void setMeses(int mesesContrato) {
        this.mesesContrato = mesesContrato;
    }
}
