package logserviciotcp.server;

import java.util.Calendar;

/**
 * Clase que nos permite saber la fecha actual
 * @author Jesús Rivero
 */
public class Calendario {
    
    // Propiedad que nos permite obtener información sobre la fecha actual
    Calendar calendario;
    
    private String dia;
    private String mes;
    private String anio;
    private String hora;
    private String minutos;
    private String segundos;
    
    public Calendario() {
        calendario = Calendar.getInstance();
        dia = Integer.toString(calendario.get(Calendar.DATE));
        mes = Integer.toString(calendario.get(Calendar.MONTH));
        anio = Integer.toString(calendario.get(Calendar.YEAR));
        hora = Integer.toString(calendario.get(Calendar.HOUR_OF_DAY));
        minutos = Integer.toString(calendario.get(Calendar.MINUTE));
        segundos = Integer.toString(calendario.get(Calendar.SECOND));
    }
    
    public String getDia() {
        return dia;
    }

    public String getMes() {
        return mes;
    }

    public String getAnio() {
        return anio;
    }

    public String getHora() {
        return hora;
    }

    public String getMinutos() {
        return minutos;
    }

    public String getSegundos() {
        return segundos;
    }
}
