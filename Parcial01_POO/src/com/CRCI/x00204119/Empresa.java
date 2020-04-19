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
        String empleadoFinal=nombreEmpleado;
        Planilla.removeIf(obj-> obj.getNombre().equals(empleadoFinal));
    }

    public ArrayList<Empleado> getPlanilla() {
        return Planilla;
    }
}

