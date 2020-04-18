package com.CRCI.x00204119;

import javax.swing.*;
import java.util.ArrayList;

public class Empresa {
    private String nombre="";
    private ArrayList<Empleado> Planilla;

    public Empresa(String nombre) {
        this.nombre = nombre;
        Planilla = new ArrayList<Empleado>();
    }

    public String getNombre() {
        return nombre;
    }
    public void addEmpleado(Empleado empleado){
        Planilla.add(empleado);
    }

    public void quitEmpleado(String nombreEmpleado){
        Empleado empleado=null;
        String empleadoFinal=nombreEmpleado;
        Planilla.removeIf(obj-> obj.getNombre().equals(empleadoFinal));
    }

    public void mostrarEmpleados(){
        for(Empleado aux : Planilla)
            JOptionPane.showMessageDialog(null, aux);
    }

    public ArrayList<Empleado> getPlanilla() {
        return Planilla;
    }
}

