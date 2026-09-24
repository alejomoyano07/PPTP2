package modelo.actividades;

import modelo.Estudiante;
import modelo.certificacion.Certificable;

public class Taller extends Actividad implements Certificable {
    private boolean requierenotebook;

    public Taller(int id,String titulo,int cupomaximo,boolean requierenotebook){
        super(id,titulo,cupomaximo);
        this.requierenotebook=requierenotebook;
    }

    public boolean getRequierenotebook(){return requierenotebook;}

    public void setRequierenotebook(boolean requierenotebook){
        this.requierenotebook=requierenotebook;
    }

    public double calcularcostomateriales(){
        if(requierenotebook){
            return 5000.0;
        }
        return 2000.0;
    }

    public String getTipo(){
        return this.getClass().getSimpleName();
    }
    public String generarCertificado(Estudiante estudiante){
        return "Certificado emitido por "+ENTIDAD_EMISORA
            +"se deja constancia de que : "+estudiante.getNombre()
            +" participa en el taller : "+getTitulo()+".";

    }
}
