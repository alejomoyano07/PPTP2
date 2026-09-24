package modelo.actividades;

public class Charla extends Actividad {
    private String disertante;

    public Charla (int id,String titulo,int cupomaximo,String disertante){
        super(id,titulo,cupomaximo);
        this.disertante=disertante;
    }

    public String getDisertante(){return disertante;}

    public void setDisertante(String disertante){
        this.disertante=disertante;
    }

    @Override
    public double calcularcostomateriales(){
        return 0.0;
    }

    @Override
    public String getTipo(){
        return this.getClass().getSimpleName();
    }
}
