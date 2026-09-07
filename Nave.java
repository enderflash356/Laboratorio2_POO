import java.util.ArrayList;

public class Nave {
    private String nombre;
    private String codigoIdenti;
    private String comandante;
    private Modulo[] modulos;
    private ArrayList<Planeta> planetas;

    public Nave(String nombre, String codigoIdenti, String comandante) {
        this.nombre = nombre;
        this.codigoIdenti = codigoIdenti;
        this.comandante = comandante;
        this.modulos = new Modulo[5];
        this.planetas = new ArrayList<>();
    }

    public boolean instalarModulo(int pos, Modulo modulo) {
        if (pos < 0 || pos >= 5) {
            System.out.println("Posición inválida. Debe estar entre 0 y 4.");
            return false;
        }
        if (modulos[pos] != null) {
            System.out.println("Ya hay un módulo instalado en la posición " + pos);
            return false;
        }
        modulos[pos] = modulo;
        return true;
    }

    public boolean retirarModulo(int pos) {
        if (pos < 0 || pos >= 5) {
            System.out.println("Posición inválida. Debe estar entre 0 y 4.");
            return false;
        }
        if (modulos[pos] == null) {
            System.out.println("No hay módulo instalado en la posición " + pos);
            return false;
        }
        modulos[pos] = null;
        return true;
    }

    public Modulo consultarModulo(int pos) {
        if (pos < 0 || pos >= 5) {
            return null;
        }
        return modulos[pos];
    }

    public boolean registrarPlaneta(Planeta planeta) {
        for (Planeta p : planetas) {
            if (p.getCodigo().equalsIgnoreCase(planeta.getCodigo())) {
                System.out.println("Ya existe una planeta con el mismo código " + planeta.getCodigo());
                return false;
            }
        }
        planetas.add(planeta);
        return true;
    }

    public Planeta buscarPlaneta(String codigo) {
        for (Planeta p : planetas) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }

    public boolean eliminarPlaneta(String codigo) {
        Planeta p = buscarPlaneta(codigo);
        if (p != null) {
            planetas.remove(p);
            return true;
        }
        return false;
    }

    public void generarReporte() {
        System.out.println("Reporte de la nave " + nombre + " (Código: " + codigoIdenti + ")");
        System.out.println("Comandante: " + comandante);
        int instalados = 0;
        Modulo mayorConsumo = null;
        for (Modulo m : modulos) {
            if (m != null) {
                instalados++;
                if (mayorConsumo == null || m.getConsumoEnergia() > mayorConsumo.getConsumoEnergia()) {
                    mayorConsumo = m;
                }
            }
    }
    System.out.println("Modulos");
    System.out.println("Modulos instalados: " + instalados + " / 5");
    System.out.println("Espacios disponibles: " + (5 - instalados));
    if (mayorConsumo != null) {
        System.out.println("Modulo de más consumo de energía: " + mayorConsumo.getNombre() + " (" + mayorConsumo.getConsumoEnergia() + " kW)");
    } else{
        System.out.println("No hay módulos de energía instalados");
    }

    System.out.println("Planetas");
    System.out.println("Planetas descubiertos: " + planetas.size());
    if (!planetas.isEmpty()) {
        Planeta mayorHab = planetas.get(0);
        Planeta menorHab = planetas.get(0);
        double sumaHab = 0;

        for (Planeta p : planetas) {
            if (p.getNivelHabit() > mayorHab.getNivelHabit()) mayorHab = p;
            if (p.getNivelHabit() < menorHab.getNivelHabit()) menorHab = p;
            sumaHab += p.getNivelHabit();
        }

        double promedio = sumaHab / planetas.size();
        System.out.println("Más habitable: " + mayorHab.getNombre() + " (" + mayorHab.getNivelHabit() + "%)");
        System.out.println("Menos habitable: " + menorHab.getNombre() + " (" + menorHab.getNivelHabit() + "%)");
        System.out.printf("Promedio de habitabilidad: %.2f%%\n", promedio);
    } else {
        System.out.println("No hay planetas descubiertos");
    }
    }
    public String getNombre() {return nombre;}
    public String getCodigoIdenti() {return codigoIdenti;}
    public String getComandante() {return comandante;}
    public Modulo[] getModulos() {return modulos;}
    public ArrayList<Planeta> getPlanetas() {return planetas;}

}
