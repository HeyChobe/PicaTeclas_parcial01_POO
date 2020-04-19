package com.CRCI.x00204119;

import javax.swing.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Empresa empresa = null;
        Documento documento = null;
        Empleado empleado = null;
        ArrayList<Empleado> miEmpleado = new ArrayList<>();

        boolean salir=false;
        int mesesContrato=0, extension=0;
        String menu="Qué desea hacer?\n1) Agregar Empleado\n2) Despedir Empleado\n3) Ver lista de empleados\n" +
                "4) Calcular suelo\n5) Mostrar totales\n0) Salir";
        String nombre="", nombreEmpresa="", puesto="", numero="";
        double salario=0;
        char op='0';

        nombreEmpresa=JOptionPane.showInputDialog(null, "Nombre de la empresa");
        empresa = new Empresa(nombreEmpresa);

        do{
            op=JOptionPane.showInputDialog(null, menu).charAt(0);
            switch (op){
                case '1':
                        nombre = JOptionPane.showInputDialog(null, "Nombre", "Ej: Carlos Roberto Cortez Amaya");
                        numero = JOptionPane.showInputDialog(null, "DUI", "06096092");

                        String[] opciones = {"Servicio Profesional", "Plaza Fija"};
                        int aux = JOptionPane.showOptionDialog(null, "Tipo de trabajador", "Agregar empleado", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

                    try {
                        if (aux == 0) {
                            puesto = JOptionPane.showInputDialog(null, "Puesto de trabajo");
                            salario = Double.parseDouble(JOptionPane.showInputDialog(null, "Salario en dólares ($)", "Ej: 400"));
                            if(salario<0) throw new NegativeNumberException("Salario no admitido");

                            mesesContrato = Integer.parseInt(JOptionPane.showInputDialog(null, "Meses de contrato"));
                            documento = new Documento(nombre, numero);
                            empleado = new ServicioProfesional(nombre, puesto, salario, mesesContrato);
                            empleado.addDocumento(documento);
                            empresa.addEmpleado(empleado);

                        } else {
                            puesto = JOptionPane.showInputDialog(null, "Puesto de trabajo");
                            salario = Double.parseDouble(JOptionPane.showInputDialog(null, "Salario en dólares ($)", "Ej: 400"));
                            if(salario<0) throw new NegativeNumberException("Salario no admitido");

                            extension = Integer.parseInt(JOptionPane.showInputDialog(null, "Extensión del contrato"));
                            documento = new Documento(nombre, numero);
                            empleado = new PlazaFija(nombre, puesto, salario, extension);
                            empleado.addDocumento(documento);
                            empresa.addEmpleado(empleado);
                        }
                    }catch (NumberFormatException|NegativeNumberException ex){
                        JOptionPane.showMessageDialog(null, "Error! Ingrese un valor numérico");
                    }
                    break;

                case '2':
                    try {
                        boolean existe = false;

                        if(empleado==null)
                            throw new EmptyListException("Aún no has contratado empleados");

                        nombre = JOptionPane.showInputDialog(null, "Empleado a despedir");
                        miEmpleado = empresa.getPlanilla();

                        for (Empleado emp : miEmpleado) {
                            if (emp.getNombre().equals(nombre)) {
                                existe = true;
                            }
                        }
                        if (!existe) {
                            throw new InputErrorException("El empleado no existe");
                        }
                        empresa.quitEmpleado(nombre);
                        empleado.removeDocumento(nombre);

                    }
                    catch (InputErrorException|EmptyListException ex){
                        JOptionPane.showMessageDialog(null, ex);
                    }

                    break;

                case '3':
                    try {
                        if(empleado==null)
                            throw new EmptyListException("Aún no has contratado empleados");

                        miEmpleado = empresa.getPlanilla();

                        for (Empleado emp : miEmpleado) {
                            JOptionPane.showMessageDialog(null, emp);
                        }

                    }
                    catch (EmptyListException ex){
                        JOptionPane.showMessageDialog(null,ex);
                    }
                    break;

                case '4':
                    try {
                        if(empleado==null)
                            throw new EmptyListException("Aún no has contratado empleados");

                        nombre = JOptionPane.showInputDialog(null, "Empleado: ");
                        miEmpleado = empresa.getPlanilla();

                        for (Empleado e : miEmpleado)
                            if (e.getNombre().equals(nombre)) {
                                double sueldoReal = CalculadoraImpuestos.calcularPago(e);
                                JOptionPane.showMessageDialog(null, "La cantidad de dinero que se le debera pagar a " +
                                        nombre + " es $:" + sueldoReal);
                            }

                    }catch (EmptyListException ex){
                        JOptionPane.showMessageDialog(null,ex);
                    }
                    break;

                case '5':
                    CalculadoraImpuestos.mostrarTotales();
                    break;
                case '0': salir=true;
                    break;

                default: JOptionPane.showMessageDialog(null, "Opcion incorrecta!");
            }
        }while(!salir);


    }
}
