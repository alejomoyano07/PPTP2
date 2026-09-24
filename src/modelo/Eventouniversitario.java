package modelo;

import modelo.actividades.Actividad;
import modelo.actividades.Charla;
import modelo.actividades.Curso;
import modelo.actividades.Taller;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Eventouniversitario implements Serializable {
    private final String id;
    private String titulo;
    private double costobase;
    private boolean gratuitos;
    private static int cantidadeventos=0;
    private Sala sala;
    private List<Actividad> actividades;



    public Eventouniversitario (String id,String titulo, double costobase, boolean gratuitos){
        this.id=id;
        this.titulo=titulo;
        this.costobase=costobase;
        this.gratuitos=gratuitos;
        cantidadeventos++;
        this.actividades=new ArrayList<>();
    }

    public String getId (){return id;}

    public String getTitulo(){return titulo;}

    public void setTitulo(String titulo){
        if (titulo==null||titulo.isEmpty()||titulo.isBlank()){
            ;System.out.println ("Nombre titulo invalido");
            return;
        }
        this.titulo=titulo;
    }

    public double getCostobase(){return costobase;}

    public void setCostobase(double costobase){
        if (gratuitos==true){
            System.out.println("El evento es gratuito");
            return;
        } else if (costobase<0.0) {
            System.out.println("Numero invalido");
            return;
        }
        this.costobase=costobase;
    }

    public boolean getGratuitos (){return gratuitos;}

    public void setGratuitos(boolean gratuitos){this.gratuitos=gratuitos;}

    public int getCantidadeventos(){return cantidadeventos;}

    public Eventouniversitario(Eventouniversitario otro){
        this.id=otro.id;
        this.titulo=otro.titulo;
        this.costobase=otro.costobase;
        this.gratuitos=otro.gratuitos;
        cantidadeventos++;
    }
    public double calcularestimado(){
        double costototal=costobase;
        if (gratuitos==false){
            for(Actividad actividad:actividades){
                costototal+=actividad.calcularcostomateriales();
            }
            return costototal*1.21;
        }
        return 0.0;
    }
    public void asignarsala(Sala sala){
        this.sala=sala;
    }

    public Sala getSala(){return sala;}
    public void crearActividad(int id, String titulo, int cupo, String tipoactividad) {
        Scanner scanner = new Scanner(System.in);
        switch (tipoactividad) {
            case "charla":
                System.out.println("Ingrrese el nombre del disertante para la charla " + titulo + " : ");
                String disertante = scanner.nextLine();
                Actividad charla = new Charla(id, titulo, cupo, disertante);
                this.actividades.add(charla);
                break;
            case "taller":
                System.out.println("El taller " + titulo + " requier el uso de notebook? :");
                String respuesta = scanner.nextLine().trim().toLowerCase();
                boolean requierenotebook = false;
                if (respuesta.equals("s") || respuesta.equals("Si")||respuesta.equals("si")) {
                    requierenotebook = true;
                }
                Actividad taller = new Taller(id, titulo, cupo, requierenotebook);
                this.actividades.add(taller);
                break;
            case "curso":
                System.out.println("Ingrese el nivel del curso : ");
                int nivel=scanner.nextInt();
                Actividad curso= new Curso(id,titulo,cupo,nivel);
                actividades.add(curso);
                break;
            default:
                System.out.println("Tipo de actividad no reconocido");
        }
    }
    public List<Actividad> getActividades(){
        return Collections.unmodifiableList(actividades);
    }
    public void mostrardatos(){
        System.out.println("Id: "+getId());
        System.out.println("Titulo: "+getTitulo());
        System.out.println("Costo: "+calcularestimado());
        System.out.println("Gratuito: "+getGratuitos());
        System.out.println("Sala : "+sala.getNombre()+"  id : "+sala.getId());
        System.out.println("Actividades: ");
        System.out.println("______________________________________________________________________");
        for (Actividad actividad: actividades){
            System.out.println("id: "+actividad.getId()+" Titutlo: "+actividad.getTitulo()+" Tipo: "+actividad.getClass().getSimpleName()+" Cupos: "+actividad.getCupomaximo());
            actividad.mostarInscripciones();
        }
        System.out.println("______________________________________________________________________");
        System.out.println("Cantidad de eventos: "+getCantidadeventos());
    }

    public boolean persistirEvento() throws IOException {
        String nombreArchivo= "evento_"+this.id+".dat";
        FileOutputStream ofos=null;
        ObjectOutputStream oos=null;
        try{
            ofos= new FileOutputStream(nombreArchivo);
            oos= new ObjectOutputStream(ofos);
            oos.writeObject(this);
        }
        finally {
            if(oos!=null){
                oos.close();
            } else if (ofos!=null) {
                ofos.close();

            }
        }
        return true;
    }
    public Eventouniversitario recuperarEvento(String id)throws IOException{
        String nombreArchivo="evento_"+id+".dat";
        Eventouniversitario oev=null;
        FileInputStream ofis=null;
        ObjectInputStream ois=null;

        try{
            ofis=new FileInputStream(nombreArchivo);
            ois =new ObjectInputStream(ofis);
            oev=(Eventouniversitario) ois.readObject();

        }
        finally {
            if(ois!=null){
                ois.close();
            } else if (ofis!=null) {
                ofis.close();
            }
            return oev;
        }
    }

    public <T extends Actividad> List<T> filtrarActividadesPorTipo(Class<T> tipo){
        List<T> resultado= new ArrayList<>();

        for(Actividad actividad:actividades){
            if(tipo.isInstance(actividad)){
                resultado.add((T)(actividad));
            }
        }
        return resultado;
    }
    public double calcularCostosMateriales(List<? extends  Actividad> actividades){
        double total = 0;

        for (Actividad actividad: actividades){
            total+= actividad.calcularcostomateriales();
        }
        return total;
    }
}
