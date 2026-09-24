package modelo.actividades;

import modelo.Estudiante;
import modelo.certificacion.Certificable;

public class Curso extends Actividad implements Certificable {
    private int nivel;

    public Curso(int id,String titulo,int cupo,int nivel){
        super(id,titulo,cupo);
        this.nivel=nivel;
    }

    public int getNivel(){return nivel;};

    public void setNivel(int nivel){
        if(nivel>0){
            this.nivel=nivel;
            return;
        }
        System.out.println("Nivel Invalido");
        return;
    }
    @Override
    public String getTipo(){
        return this.getClass().getSimpleName();
    }

    @Override
    public double calcularcostomateriales() {
        switch (nivel) {
            case 1:
                return 1000;
            case 2:
                return 2000;
            case 3:
                return 3200;
            case 4:
                return 4500;
            default:
                return 200;
        }
    }
    public String generarCertificado(Estudiante estudiante){
        return "Certificado emitido por "+ENTIDAD_EMISORA
                +"se deja constancia de que : "+estudiante.getNombre()
                +" Esta inscripto en el curso: "+getTitulo()+".";

    }

}
