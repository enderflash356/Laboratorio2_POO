public class Planeta {
    private String codigo;
    private String nombre;
    private double distancia;
    private double temperatura;
    private double nivelHabit;

    public Planeta(String codigo, String nombre, double distancia, double temperatura, double nivelHabit) {
        if (distancia <= 0) {
            throw new IllegalArgumentException("La distancia debe ser mayor a 0");
        }
        if (nivelHabit < 0 || nivelHabit > 100) {
            throw new IllegalArgumentException("El nivel de habitabilidad debe estar entre 0 y 100");
        }
        this.codigo = codigo;
        this.nombre = nombre;
        this.distancia = distancia;
        this.temperatura = temperatura;
        this.nivelHabit = nivelHabit;
    }

    public String getCodigo() {
        return codigo;
    }
    public String getNombre() {
        return nombre;
    }
    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        if (distancia <= 0) {
            throw new IllegalArgumentException("La distancia debe ser mayor a 0");
        }
        this.distancia = distancia;
    }
    public double getTemperatura() {
        return temperatura;
    }
    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }
    public double getNivelHabit() {
        return nivelHabit;
    }
    public void setNivelHabit(double nivelHabit) {
        if (nivelHabit < 0 || nivelHabit > 100) {
            throw new IllegalArgumentException("El nivel de habitabilidad debe estar entre 0 y 100");
        }
        this.nivelHabit = nivelHabit;
    }
}
