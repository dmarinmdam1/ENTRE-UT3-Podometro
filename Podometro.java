/**
 * La clase modela un sencillo pod�metro que registra informaci�n
 * acerca de los pasos, distancia, ..... que una persona (hombre o mujer)
 * ha dado en una semana. 
 * 
 * @author  Daniel Mar�n
 * @version  1.0
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
     * Inicializa el pod�metro con la marca indicada por el par�metro.
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

    /** ----- M�todos Accesores ----- **/
    /** ----- M�todos Mutadores ----- **/
    /** ----- M�todos Print ----- **/
    /** ----- Otros M�todos ----- **/

    /**
     * Devuelve la marca del pod�metro
     */
    public String getMarca()
    {
        return marca;
    }

    /**
     * Simula la configuraci�n del pod�metro.
     * Recibe como par�metros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna adem�s el valor adecuado al atributo longitudZancada
     */
    public void configurar(double queAltura, char queSexo)
    {        
        altura = queAltura;
        sexo = queSexo;

        switch(sexo)
        {
            case HOMBRE : longitudZancada = Math. ceil(altura * ZANCADA_HOMBRE); break;
            case MUJER  : longitudZancada = Math.floor(altura * ZANCADA_MUJER);  break;
            default : System.out.println("Sexo err�neo");
        }
    }

    /**
     *  Recibe cuatro par�metros que supondremos correctos:
     *  
     *       pasos - el n� de pasos caminados
     *         dia - n� de d�a de la semana en que se ha hecho la caminata 
    (1 - Lunes, 2 - Martes - .... - 6 - S�bado, 7 - Domingo)
     *  horaInicio � hora de inicio de la caminata
     *   horaFinal � hora de fin de la caminata
     *    
     *  A partir de estos par�metros el m�todo debe realizar ciertos c�lculos
     *  y  actualizar� el pod�metro adecuadamente  
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
     * Muestra en pantalla la configuraci�n del pod�metro
     * (altura, sexo y longitud de la zancada)
     */
    public void printConfiguracion()
    {
        System.out.print("\n Configuraci�n del pod�metro");
        System.out.print("\n ***************************");
        
        System.out.print("\n Altura: " + altura / 100 + " metros");
        
        if(sexo == HOMBRE)
        {
            System.out.print("\n Sexo: HOMBRE");
        }
        else
        {
            System.out.print("\n Sexo: MUJER");
        }
        
        System.out.print("\n Longitud zancada: " + longitudZancada / 100 + " metros");
    }

    /**
     * Muestra en pantalla informaci�n acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     */
    public void printEstad�sticas()
    {
        System.out.print("\n\n Estad�sticas");
        System.out.print("\n ***************************");
        
        System.out.print("\n Distancia recorrida toda la semana: " + (totalDistanciaSemana + totalDistanciaFinSemana) + " Km");
        System.out.print("\n Distancia recorrida fin de semana: " + totalDistanciaFinSemana + " Km");
        
        System.out.print("\n");
        
        System.out.print("\n N� pasos d�as laborables: " + totalPasosLaborables);
        System.out.print("\n N� pasos S�BADO: " + totalPasosSabado);
        System.out.print("\n N� pasos DOMINGO: " + totalPasosDomingo);
        
        System.out.print("\n");
        
        System.out.print("\n N� caminatas realizadas a partir de las 21h.: " + caminatasNoche);
        
        System.out.print("\n");
        
        System.out.print("\n Tiempo total caminado en la semana: " + (tiempo / 60) + "h. y " + (tiempo % 60) + "m.");
        
        int diasConMasPasosCaminados = totalPasosLaborables;
        
        if(totalPasosSabado > diasConMasPasosCaminados)
        {
            diasConMasPasosCaminados = totalPasosSabado;
        }
        if(totalPasosDomingo > diasConMasPasosCaminados)
        {
            diasConMasPasosCaminados = totalPasosDomingo;
        }
        
        if(diasConMasPasosCaminados == totalPasosLaborables)
        {
            System.out.print("\n D�a/s con m�s pasos caminados: LABORALES");
        }
        else if(diasConMasPasosCaminados == totalPasosSabado)
        {
            System.out.print("\n D�a/s con m�s pasos caminados: S�BADO");
        }
        else
        {
            System.out.print("\n D�a/s con m�s pasos caminados: DOMINGO");
        }
    }

    /**
     *  Calcula y devuelve un String que representa el nombre del d�a
     *  en el que se ha caminado m�s pasos - "S�BADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroPasos()
    {
        int diasConMasPasosCaminados = totalPasosLaborables;
        
        if(totalPasosSabado > diasConMasPasosCaminados)
        {
            diasConMasPasosCaminados = totalPasosSabado;
        }
        if(totalPasosDomingo > diasConMasPasosCaminados)
        {
            diasConMasPasosCaminados = totalPasosDomingo;
        }
        
        if(diasConMasPasosCaminados == totalPasosLaborables)
        {
            return "LABORALES";
        }
        else if(diasConMasPasosCaminados == totalPasosSabado)
        {
            return "S�BADO";
        }
        else
        {
            return "DOMINGO";
        }
    }

    /**
     * Restablecer los valores iniciales del pod�metro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no var�a
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

