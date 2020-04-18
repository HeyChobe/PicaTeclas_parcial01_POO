package com.CRCI.x00204119;

import javax.swing.*;
import java.util.Scanner;

public class Main {

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        Empresa empresa = null;
        Documento documento = null;
        Empleado empleado = null;

        boolean salir=false;
        int op=0, mesesContrato=0, extension=0;
        String menu="Qué desea hacer?\n1) Agregar Empleado\n2) Despedir Empleado\n3) Ver lista de empleados\n" +
                "4) Calcular suelo\n5) Mostrar totales\n0) Salir";
        String nombre="", nombreEmpresa="", puesto="", numero="";
        double salario=0;

        nombreEmpresa=JOptionPane.showInputDialog(null, "Nombre de la empresa");
        empresa = new Empresa(nombreEmpresa);

        do{
            op=Integer.parseInt(JOptionPane.showInputDialog(null, menu));
            switch (op){
                case 1:
                    nombre=JOptionPane.showInputDialog(null, "Nombre", "Ej: Carlos Roberto Cortez Amaya");
                    numero=JOptionPane.showInputDialog(null, "DUI","06096092");

                    String[] opciones ={"Servicio Profesional", "Plaza Fija"};
                    int aux=JOptionPane.showOptionDialog(null,"Tipo de trabajador","Agregar empleado",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,opciones, opciones[0]);

                    if(aux==0) {
                        puesto = JOptionPane.showInputDialog(null, "Puesto de trabajo");
                        salario=Integer.parseInt(JOptionPane.showInputDialog(null,"Salario en dólares ($)","Ej: 400"));
                        mesesContrato=Integer.parseInt(JOptionPane.showInputDialog(null,"Meses de contrato"));
                        documento=new Documento(nombre,numero);
                        empleado =new ServicioProfesional(nombre,puesto,salario,mesesContrato);
                        empleado.addDocumento(documento);
                        empresa.addEmpleado(empleado);
                    }

                    else {
                        puesto = JOptionPane.showInputDialog(null, "Puesto de trabajo");
                        salario=Integer.parseInt(JOptionPane.showInputDialog(null,"Salario en dólares ($)","Ej: 400"));
                        extension=Integer.parseInt(JOptionPane.showInputDialog(null,"Extensión del contrato"));
                        documento=new Documento(nombre,numero);
                        empleado =new PlazaFija(nombre,puesto,salario,extension);
                        empleado.addDocumento(documento);
                        empresa.addEmpleado(empleado);
                    }
                    break;

                case 2:
                    nombre=JOptionPane.showInputDialog(null, "Empleado a despedir");
                    empresa.quitEmpleado(nombre);
                    empleado.removeDocumento(nombre);
                    break;

                case 3:
                    empresa.mostrarEmpleados();
                    break;

                case 4:
                    break;

                case 5:
                    break;

                case 0: salir=true;
                    break;

                default: JOptionPane.showMessageDialog(null, "Opcion incorrecta!");
            }
        }while(!salir);
    }
}
