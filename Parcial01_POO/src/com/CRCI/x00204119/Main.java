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

        do {
            nombreEmpresa = JOptionPane.showInputDialog(null, "Nombre de la empresa");
            try {
                if (nombreEmpresa.equals("")) throw new EmptyInputException("Error!NO ha ingresado datos ");
                empresa = new Empresa(nombreEmpresa);
            } catch (EmptyInputException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }while (nombreEmpresa.equals(""));


        do{
            op=JOptionPane.showInputDialog(null, menu).charAt(0);
            switch (op){
                case '1':
                    //documento=agregarDocumento();
                    empleado=agregarEmpleado();
                    empleado.addDocumento(agregarDocumento(empleado.getNombre()));
                    empresa.addEmpleado(empleado);
                    break;
                case '2':
                    try {
                        boolean existe = false;

                        if(empleado==null)
                            throw new EmptyListException("Aún no has contratado empleados");

                        nombre = JOptionPane.showInputDialog(null, "Empleado a despedir");
                        miEmpleado = empresa.getPlanilla();

                        for (Empleado emp : miEmpleado)
                            if (emp.getNombre().equals(nombre))
                                existe = true;

                        if (!existe)
                            throw new InputErrorException("Empleado no eiste");

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

                        for (Empleado emp : miEmpleado)
                            JOptionPane.showMessageDialog(null, emp);
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
                        if(nombre.equals(""))
                            throw new EmptyInputException("Ingrese un empleado");
                        miEmpleado = empresa.getPlanilla();

                        boolean verificarExiste=false;
                        for (Empleado e : miEmpleado)
                            if (e.getNombre().equals(nombre)) {
                                verificarExiste=true;
                                double sueldoReal = CalculadoraImpuestos.calcularPago(e);
                                JOptionPane.showMessageDialog(null, "La cantidad de dinero que se le debera pagar a " +
                                        nombre + " es $:" + sueldoReal);
                            }
                        if(!verificarExiste)
                            throw new InputErrorException("Error!Empleado inexistente");

                    }catch (EmptyListException|EmptyInputException|InputErrorException ex){
                        JOptionPane.showMessageDialog(null,ex);
                    }

                    break;
                case '5':
                    CalculadoraImpuestos.mostrarTotales();

                    break;
                case '0':
                    salir=true;
                    JOptionPane.showMessageDialog(null,"Contrate mas gente! Adios");

                    break;

                default: JOptionPane.showMessageDialog(null, "Opcion incorrecta!");
            }
        }while(!salir);


    }
    //Se paso el case 1 a una funcion que entorpecia la lectura del codigo en el Main
    public static Empleado agregarEmpleado() {
        String nombre="",puesto="";
        double salario=0;
        int mesesContrato=0,extension=0;
        try {
            nombre = JOptionPane.showInputDialog(null, "Nombre", "Ej: Carlos Roberto Cortez Amaya");
            if(nombre.equals(""))
                throw new EmptyInputException("Error!NO ha ingresado datos ");

            String[] opciones = {"Servicio Profesional", "Plaza Fija"};
            int aux = JOptionPane.showOptionDialog(null, "Tipo de trabajador", "Agregar empleado", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

            if (aux == 0) {
                puesto = JOptionPane.showInputDialog(null, "Puesto de trabajo");
                if(puesto.equals("")) throw new EmptyInputException("Error!NO ha ingresado datos ");

                salario = Double.parseDouble(JOptionPane.showInputDialog(null, "Salario en dólares ($)", "Ej: 400"));
                if(salario<0) throw new NegativeNumberException("Salario no admitido");

                mesesContrato = Integer.parseInt(JOptionPane.showInputDialog(null, "Meses de contrato"));
                return new ServicioProfesional(nombre, puesto, salario, mesesContrato);
            } else {
                puesto = JOptionPane.showInputDialog(null, "Puesto de trabajo");
                if(puesto.equals("")) throw new EmptyInputException("Error!NO ha ingresado datos ");

                salario = Double.parseDouble(JOptionPane.showInputDialog(null, "Salario en dólares ($)", "Ej: 400"));
                if(salario<0) throw new NegativeNumberException("Salario no admitido");

                extension = Integer.parseInt(JOptionPane.showInputDialog(null, "Extensión del contrato"));
                return new PlazaFija(nombre, puesto, salario, extension);
            }
        }catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Error! Ingrese un valor numérico");
        }
        catch (NegativeNumberException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        catch (EmptyInputException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return null;
    }

    public static Documento agregarDocumento(String nombre){
        String numero="";
        try {
            numero = JOptionPane.showInputDialog(null, "DUI", "06096092");
            if (numero.equals("")) throw new EmptyInputException("Error!NO ha ingresado datos ");
        }
        catch (EmptyInputException ex){
            JOptionPane.showMessageDialog(null,ex);
        }
        return  new Documento(nombre, numero);
    }
}
