package com.CRCI.x00204119;

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
        String nombreEmpleadoFinal=nombreEmpleado;
        Planilla.RemoveIf(obj-> obj (Planilla.getNombre().equals(nombreEmpleadoFinal)));
    }
}
