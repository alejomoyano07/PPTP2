package modelo;

import java.io.Serializable;

public class Sala  implements Serializable {
    private int id;
    private String nombre;

    public Sala (int id, String nombre){
        this.id=id;
        this.nombre=nombre;
    }

    public int getId(){return id;}

    public void setId(int id){
        if(id<0){
            return;
        }
        this.id=id;
    }

    public String getNombre(){return nombre;}

    public void setNombre(String nombre){
        if (nombre==null||nombre.isBlank()||nombre.isEmpty()){
            System.out.println ("Nombre titulo invalido");
            return;
        }
        this.nombre=nombre;
    }


}
