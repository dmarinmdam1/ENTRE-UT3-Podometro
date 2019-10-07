/**
 * La clase modela un "sencillo" podómetro que registra información
 * acerca de los pasos, distancia, ..... que una persona (hombre o mujer)
 * ha dado en una semana. 
 * 
 * @author  Daniel Marín
 * @version  1.2
 */
public class Podometro
{
    /** ----- Constantes y Atributos ----- **/

    private final char HOMBRE = 'H';
    private final char MUJER  = 'M';

    private final double ZANCADA_HOMBRE = 0.45;
    private final double ZANCADA_MUJER  = 0.41;

    private final int SABADO  = 6;
    private final int DOMINGO = 7;

    // ----- //

    private String marca;

    private double altura;
    private char sexo;
    private double longitudZancada;

    private int totalPasosLaborables;
    private int totalPasosSabado;
    private int totalPasosDomingo;

    private double totalDistanciaSemana;
    private double totalDistanciaFinSemana;

    private int tiempo;
    private int caminatasNoche;

    /** ----- Constructor ----- **/

    /**
     * Inicializa el podómetro con la marca indicada por el parámetro.
     * El resto de atributos se ponen a 0 y el sexo, por defecto, es mujer
     */
    
    public Podometro(String queMarca)
    {
        marca = queMarca;

        altura = 0;
        sexo = MUJER;
        longitudZancada = 0;

        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;

        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;

        tiempo = 0;
        caminatasNoche = 0;
    }

    /** ----- Métodos ----- **/

    /**
     * Devuelve la marca del podómetro
     */
    
    public String getMarca()
    {
        return marca;
    }

    /**
     * Simula la configuración del podómetro.
     * Recibe como parámetros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna además el valor adecuado al atributo longitudZancada
     */
    
    public void configurar(double queAltura, char queSexo)
    {        
        altura = queAltura;
        sexo = queSexo;
        
        if(sexo == HOMBRE)
        {
            longitudZancada = Math.ceil(altura * ZANCADA_HOMBRE);
        }
        else
        {
            longitudZancada = Math.floor(altura * ZANCADA_MUJER);
        }
    }

    /**
     *  Recibe cuatro parámetros que supondremos correctos:
     *  
     *       pasos - el nº de pasos caminados
     *         dia - nº de día de la semana en que se ha hecho la caminata 
     *               (1 - Lunes, 2 - Martes - .... - 6 - Sábado, 7 - Domingo)
     *  horaInicio – hora de inicio de la caminata
     *   horaFinal – hora de fin de la caminata
     *    
     *  A partir de estos parámetros el método debe realizar ciertos cálculos
     *  y  actualizará el podómetro adecuadamente  
     */
    
    public void registrarCaminata(int pasos, int dia, int horaInicio, int horaFin)
    {
        switch(dia)
        {
            case 1 :
            case 2 :
            case 3 :
            case 4 :
            case 5 :
            {
                totalPasosLaborables += pasos;
                totalDistanciaSemana += (pasos * longitudZancada / 100000);

                break;
            }
            case SABADO :
            {
                totalPasosSabado += pasos;
                totalDistanciaFinSemana += (pasos * longitudZancada / 100000);

                break;
            }
            case DOMINGO :
            {
                totalPasosDomingo += pasos;
                totalDistanciaFinSemana += (pasos * longitudZancada / 100000);

                break;
            }
        }

        // ----- //
        
        int calc_horaInicio = horaInicio / 100;
        int calc_minutoInicio = horaInicio % (calc_horaInicio * 100);

        int calc_horaFin = horaFin / 100;
        int calc_minutoFin = horaFin % (calc_horaFin * 100);

        if(calc_minutoInicio < calc_minutoFin)
        {
            tiempo += ((calc_horaFin - calc_horaInicio) * 60) + (calc_minutoFin - calc_minutoInicio);
        }
        else if(calc_minutoInicio == calc_minutoFin)
        {
            tiempo += (calc_horaFin - calc_horaInicio) * 60;
        }
        else
        {
            tiempo += ((calc_horaFin - calc_horaInicio - 1) * 60) + (60 + calc_minutoFin - calc_minutoInicio);
        }
        
        // ----- //
        
        if(calc_horaInicio > 20)
        {
            caminatasNoche++;
        }
    }

    /**
     * Muestra en pantalla la configuración del podómetro
     * (altura, sexo y longitud de la zancada)
     */
    
    public void printConfiguracion()
    {
        System.out.println(" Configuración del podómetro");
        System.out.println(" ***************************");
        
        System.out.println(" Altura: " + altura / 100 + " metros");
        
        if(sexo == HOMBRE)
        {
            System.out.print(" Sexo: HOMBRE");
        }
        else
        {
            System.out.print(" Sexo: MUJER");
        }
        
        System.out.println("\n Longitud zancada: " + longitudZancada / 100 + " metros");
    }

    /**
     * Muestra en pantalla información acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     */
    
    public void printEstadísticas()
    {        
        System.out.println("\n Estadísticas");
        System.out.println(" ***************************");
        
        System.out.println(" Distancia recorrida toda la semana: " + (totalDistanciaSemana + totalDistanciaFinSemana) + " Km");
        System.out.println(" Distancia recorrida fin de semana: " + totalDistanciaFinSemana + " Km");
        
        System.out.println("\n Nº pasos días laborables: " + totalPasosLaborables);
        System.out.println(" Nº pasos SÁBADO: " + totalPasosSabado);
        System.out.println(" Nº pasos DOMINGO: " + totalPasosDomingo);
        
        System.out.println("\n Nº caminatas realizadas a partir de las 21h.: " + caminatasNoche);
        
        System.out.println("\n Tiempo total caminado en la semana: " + (tiempo / 60) + "h. y " + (tiempo % 60) + "m.");
    }

    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se ha caminado más pasos - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    
    public String diaMayorNumeroPasos()
    {
        if(totalPasosLaborables > totalPasosSabado &&
           totalPasosLaborables > totalPasosDomingo)
        {
            return "LABORALES";
        }
        else if(totalPasosSabado > totalPasosLaborables &&
                totalPasosSabado > totalPasosDomingo)
        {
            return "SÁBADO";
        }
        else
        {
            return "DOMINGO";
        }
    }

    /**
     * Restablecer los valores iniciales del podómetro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no varía
     */  
    
    public void reset()
    {
        altura = 0;
        sexo = MUJER;
        longitudZancada = 0;

        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;

        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;

        tiempo = 0;
        caminatasNoche = 0;
    }
}

