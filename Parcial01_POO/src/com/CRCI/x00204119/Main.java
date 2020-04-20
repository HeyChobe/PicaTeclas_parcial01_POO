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
        String nombre="", nombreEmpresa="", puesto="", numero="",op="";
        double salario=0;

        do {
            nombreEmpresa = JOptionPane.showInputDialog(null, "Nombre de la empresa");
            try {
                if (nombreEmpresa.equals("")||verificarEntrada(nombreEmpresa)) throw new EmptyInputException("Error!NO ha ingresado datos ");
                empresa = new Empresa(nombreEmpresa);

            }
            catch (EmptyInputException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }while (nombreEmpresa.equals("")||verificarEntrada(nombreEmpresa));


        do{
        
            op = JOptionPane.showInputDialog(null, menu);

            switch (op) {
                case "1":
                    try {
                        empleado = agregarEmpleado();
                        empleado.addDocumento(agregarDocumento(empleado.getNombre()));
                        empresa.addEmpleado(empleado);
                    } catch (Exception e) {
                    }
                    break;

                case "2":
                    try {
                        boolean existe = false;

                        if (empleado == null)
                            throw new EmptyListException("Aún no has contratado empleados");

                        nombre = JOptionPane.showInputDialog(null, "Empleado a despedir");
                        miEmpleado = empresa.getPlanilla();

                        for (Empleado emp : miEmpleado)
                            if (emp.getNombre().equals(nombre))
                                existe = true;

                        if (!existe)
                            throw new InputErrorException("Error!El empleado no existe");

                        empresa.quitEmpleado(nombre);
                        empleado.removeDocumento(nombre);

                        //Verifica el si la lista es vacía, para que el caso 3 pueda tirar lanzar
                        //la excepción una vez borrado un empleado
                        if (miEmpleado.isEmpty()) empleado = null;

                    } catch (InputErrorException | EmptyListException ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                    break;

                case "3":
                    try {
                        if (empleado == null)
                            throw new EmptyListException("Aún no has contratado empleados");

                        miEmpleado = empresa.getPlanilla();

                        for (Empleado emp : miEmpleado)
                            JOptionPane.showMessageDialog(null, emp);
                    } catch (EmptyListException ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                    break;

                case "4":
                    try {
                        if (empleado == null)
                            throw new EmptyListException("Aún no has contratado empleados");

                        nombre = JOptionPane.showInputDialog(null, "Empleado: ");
                        if (nombre.equals("") || verificarEntrada(nombre))
                            throw new EmptyInputException("Ingrese un empleado");
                        miEmpleado = empresa.getPlanilla();

                        boolean verificarExiste = false;
                        for (Empleado e : miEmpleado)
                            if (e.getNombre().equals(nombre)) {
                                verificarExiste = true;
                                double sueldoReal = CalculadoraImpuestos.calcularPago(e);
                                JOptionPane.showMessageDialog(null, "La cantidad de dinero que se le deberá pagar a " +
                                        nombre + " es $:" + sueldoReal);
                            }
                        if (!verificarExiste)
                            throw new InputErrorException("Error! El empleado no existe");

                    } catch (EmptyListException | EmptyInputException | InputErrorException ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                    break;

                case "5":
                    JOptionPane.showMessageDialog(null, CalculadoraImpuestos.mostrarTotales());
                    break;

                case "0":
                    salir = true;
                    JOptionPane.showMessageDialog(null, "Contrate mas gente! Adios");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opcion incorrecta!");
                    break;
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
            if (nombre.equals("")||verificarEntrada(nombre))
                throw new EmptyInputException("Error! No ha ingresado datos ");

            String[] opciones = {"Servicio Profesional", "Plaza Fija"};
            int aux = JOptionPane.showOptionDialog(null, "Tipo de trabajador", "Agregar empleado", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

            if (aux == 0) {
                puesto = JOptionPane.showInputDialog(null, "Puesto de trabajo");
                if (puesto.equals("")||verificarEntrada(puesto)) throw new EmptyInputException("Error! No ha ingresado datos ");

                salario = Double.parseDouble(JOptionPane.showInputDialog(null, "Salario en dólares ($)", "Ej: 400"));
                if (salario < 0 ) throw new NegativeNumberException("Salario no admitido");

                mesesContrato = Integer.parseInt(JOptionPane.showInputDialog(null, "Meses de contrato"));
                return new ServicioProfesional(nombre, puesto, salario, mesesContrato);
            } else {
                puesto = JOptionPane.showInputDialog(null, "Puesto de trabajo");
                if (puesto.equals("")||verificarEntrada(puesto)) throw new EmptyInputException("Error! No ha ingresado datos ");

                salario = Double.parseDouble(JOptionPane.showInputDialog(null, "Salario en dólares ($)", "Ej: 400"));
                if (salario < 0) throw new NegativeNumberException("Salario no admitido");

                extension = Integer.parseInt(JOptionPane.showInputDialog(null, "Extensión del contrato"));
                return new PlazaFija(nombre, puesto, salario, extension);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error! Ingrese un valor numérico");
        } catch (NegativeNumberException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (EmptyInputException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return null;
    }

    public static Documento agregarDocumento(String nombre){
        String numero="";
        try {
            numero = JOptionPane.showInputDialog(null, "DUI", "06096092");
            if (numero.equals("")) throw new EmptyInputException("Error! No ha ingresado datos ");
        }
        catch (EmptyInputException ex){
            JOptionPane.showMessageDialog(null,ex);
        }
        return  new Documento(nombre, numero);
    }
    /* Se creo esta funcion para verificar que el usuario no meta "        " una cadena de este estilo, ya que java
    lo considera una cadena
     */
    public static boolean verificarEntrada(String miPalabra){
        int auxCounter=1; //Necesario para que el substring vaya letra por letra, ya que no me dejo con un charAt
        for(int i=0;i<miPalabra.length();i++)
            if(!miPalabra.substring(i,i+auxCounter).equals(" "))
                return false;
        return true;
    }
}
