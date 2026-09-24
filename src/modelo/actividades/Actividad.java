package modelo.actividades;

import excepciones.CupoExcedidoException;
import modelo.Estudiante;
import modelo.Inscripcion;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Actividad implements Serializable {
    private int id;
    private String titulo;
    private int cupomaximo;
    public final static int cupominimo;
    private List<Inscripcion> inscripciones;

    static{
        cupominimo=1;
    }


    public Actividad(int id, String titulo, int cupomaximo){
        this.id=id;
        this.titulo=titulo;
        this.cupomaximo=cupomaximo;
        this.inscripciones= new ArrayList<>();
    }

    public int getId(){return id;}

    public void setId(int id){
        if(id<0){
            return;
        }
        this.id=id;
    }

    public String getTitulo(){return titulo;}

    public void setTitulo(String titulo){
        if (titulo==null||titulo.isEmpty()||titulo.isBlank()){
            ;System.out.println ("Nombre titulo invalido");
            return;
        }
        this.titulo=titulo;
    }

    public int getCupomaximo(){return cupomaximo;}

    public void setCupomaximo(int cupomaximo){
        if(cupomaximo<cupominimo){
            this.cupomaximo=cupominimo;
        }
        this.cupomaximo=cupomaximo;
    }

    public Inscripcion inscribir(Estudiante estudiante) throws CupoExcedidoException {
        if (inscripciones.size()>=cupomaximo){
            throw new CupoExcedidoException("No se puede incribir al estudiante");
        }
        Inscripcion inscripcion = new Inscripcion(this, estudiante, LocalDate.now(),"Registrado");
        inscripciones.add(inscripcion);
        return inscripcion;
    }

    public List<Inscripcion> getInscripciones(){return inscripciones;}

    public abstract double calcularcostomateriales();


    public void mostarInscripciones(){
        if (inscripciones.isEmpty()){
            System.out.println("No hay inscripciones registradas");
            return;
        }
        System.out.println("Inscripciones: ");
        System.out.println("=========================================");
        for(Inscripcion inscripcion:inscripciones){
            System.out.println(" "+inscripcion.getFecha()+"-"+inscripcion.getEstado()+"-"+inscripcion.getEstudiante().getNombre()+"- Legajo: "+inscripcion.getEstudiante().getLegajo());
        }
        System.out.println("=========================================");
    }

    public abstract String getTipo();

}
