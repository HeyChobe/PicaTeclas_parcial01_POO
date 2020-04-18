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
        for(Empleado aux : Planilla){
            JOptionPane.showMessageDialog(null, aux);
        }
    }
    public void conocerSalario(String nombre){
        Empleado empleado=null;
        for(Empleado e: Planilla){
            if(e.getNombre().equals(nombre))
                empleado=e;
        }
        double sueldoReal= CalculadoraImpuestos.calcularPago(empleado);
        JOptionPane.showConfirmDialog(null,"La cantidad de dinero que se le debera pagar a "+
                nombre +" es $:"+sueldoReal);
    }
}

