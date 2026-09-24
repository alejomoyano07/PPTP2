import excepciones.CupoExcedidoException;
import modelo.Estudiante;
import modelo.Eventouniversitario;
import modelo.Inscripcion;
import modelo.Sala;
import modelo.actividades.Actividad;
import modelo.actividades.Charla;
import modelo.actividades.Curso;
import modelo.actividades.Taller;
import modelo.certificacion.Certificable;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public static void main(String[] args) throws CupoExcedidoException {
    Scanner scanner = new Scanner(System.in);

    List<Estudiante>estudiantes=new ArrayList<>();

    estudiantes.add( new Estudiante("53430", "Alejo"));
    estudiantes.add( new Estudiante("47876", "Martin"));
    estudiantes.add( new Estudiante("23456", "Santiago"));
    estudiantes.add( new Estudiante("1111", "Pedro"));


    Eventouniversitario evento1 = new Eventouniversitario("0001", "evento1", 0.0, false);

    Sala sala1 = new Sala(1, "Sala 7");

    evento1.asignarsala(sala1);

    evento1.crearActividad(1, "Programacion en Java", 20, "taller");
    evento1.crearActividad(2, "Historia de compuadoras", 50, "charla");
    evento1.crearActividad(3,"Fisica",30,"curso");

    try {
        evento1.getActividades().get(0).inscribir(estudiantes.get(0));
        evento1.getActividades().get(0).inscribir(estudiantes.get(1));
        evento1.getActividades().get(0).inscribir(estudiantes.get(2));
        evento1.getActividades().get(0).inscribir(estudiantes.get(3));

    }
    catch (CupoExcedidoException e) {
        System.out.println("Error al inscribir: " + e.getMessage());
    }
    catch(Exception e){
        System.out.println("Error");
    }
    try {
        evento1.getActividades().get(2).inscribir(estudiantes.get(0));
        evento1.getActividades().get(2).inscribir(estudiantes.get(1));
        evento1.getActividades().get(2).inscribir(estudiantes.get(2));
        evento1.getActividades().get(2).inscribir(estudiantes.get(3));

    }
    catch (CupoExcedidoException e) {
        System.out.println("Error al inscribir: " + e.getMessage());
    }
    catch(Exception e) {
        System.out.println("Error");
    }
    try {
        evento1.getActividades().get(1).inscribir(estudiantes.get(0));
        evento1.getActividades().get(1).inscribir(estudiantes.get(2));
        evento1.getActividades().get(1).inscribir(estudiantes.get(1));
    } catch (CupoExcedidoException e) {
        System.out.println("Error al inscribir: " + e.getMessage());
    }
    evento1.mostrardatos();
    try{
        evento1.persistirEvento();
    }
    catch(FileNotFoundException e){
        System.out.println("Imposible guardar el evento "+ evento1.getId()+" porque no se encuantra");
    }
    catch(IOException e){
        System.out.println("Error de entrada salida "+evento1.getId());
        e.printStackTrace();
    }

    try{
        Eventouniversitario copiaDesdeArchivo =evento1.recuperarEvento(evento1.getId());
        copiaDesdeArchivo.mostrardatos();
    }
    catch (IOException e){
        System.out.println("Error e/s");
    }

    for(Actividad actividad: evento1.getActividades()){
        if(actividad instanceof Certificable certificable){
            System.out.println("Certificados Taller: ");
            for (Inscripcion inscripcion: actividad.getInscripciones()){
                String certificado= certificable.generarCertificado(inscripcion.getEstudiante());
                System.out.println(certificado);
            }
        }
    }

    List<Taller>talleres=evento1.filtrarActividadesPorTipo(Taller.class);
    List<Charla>charlas=evento1.filtrarActividadesPorTipo(Charla.class);
    List<Curso>cursos=evento1.filtrarActividadesPorTipo(Curso.class);

    //Mostrar costo de las actividades y actividades separadas
    System.out.println("Actividades por tipo y sus costos: ");
    System.out.println("Actividades: ");
    System.out.println(evento1.getActividades());
    System.out.println("Costo de las actividades: "+evento1.calcularCostosMateriales(evento1.getActividades()));
    System.out.println("*********************************");
    System.out.println("Talleres "+talleres.size());
    System.out.println("Costo de Talleres: "+evento1.calcularCostosMateriales(talleres));
    System.out.println("Cursos: "+cursos.size());
    System.out.println("Costo de Cursos: "+evento1.calcularCostosMateriales(cursos));
    System.out.println("Charlas: "+charlas.size());
    System.out.println("Costo de Charas: "+evento1.calcularCostosMateriales(charlas));
    System.out.println("*********************************");










}








