package com.CRCI.x00204119;

import javax.swing.*;
//Clase estatica
public class CalculadoraImpuestos {
    private static double totalRenta,totalAFP,totalISSS;
    private CalculadoraImpuestos(){}

    public static double calcularPago(Empleado empleado) {
        double SalarioReal=empleado.getSalario(),Renta=0,Isss=0,Afp=0,Restante=0;

        if(empleado instanceof ServicioProfesional) {
            Renta = 0.1 * empleado.getSalario(); //totalRenta += Renta;
            SalarioReal -= Renta;
        } else {
            //No es necesario un if porque si o si sera PlazaFija
            Isss=empleado.getSalario()*0.3;  totalISSS+=Isss;
            Afp=empleado.getSalario()*0.625; totalAFP+=Afp;
            Restante=empleado.getSalario()-Isss-Afp;
            // Primer bloque, el empleado no paga renta
            if(Restante>=0.1 && Restante<=472)
                SalarioReal -= Restante;
            // Segundo bloque de impuesto sobre la renta

            else if(Restante>=472.01 && Restante <=895.24) {
                Renta= 0.1*(Restante-472) + 17.67; totalRenta+=Renta;
                SalarioReal-=Restante-Renta;
            // Tercer bloque de impuesto sobre la renta

            } else if(Restante>=895.25 && Restante<=2038.10) {
                Renta= 0.2*(Restante-895.24)+ 60;  totalRenta+=Renta;
                SalarioReal -= Restante-Renta;
            //Cuarto bloque no es necesario colocar otro if
            } else {
                Renta=0.3*(Restante-2038.10)+ 288.57; totalRenta+=Renta;
                SalarioReal -= Restante-Renta;
            }
        }
        return SalarioReal;
 }
    public static void mostrarTotales(){
        JOptionPane.showConfirmDialog(null,"Los impuestos presentes son: \n " +
                "ISSS: $" + totalISSS + "\nAFP: $"+ totalAFP+ "\nRenta: $"+ totalRenta);
    }
}


