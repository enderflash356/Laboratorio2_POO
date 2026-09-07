import java.util.InputMismatchException;
import java.util.Scanner;


public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Configuración de la nave");
        System.out.println("Ingrese el nombre de la nave: ");
        String nombreNave = scanner.nextLine();
        System.out.println("Ingrese el código de identidad de la nave: ");
        String codigoNave = scanner.nextLine();
        System.out.println("Ingrese el nombre del comandante: ");
        String comandante = scanner.nextLine();

        Nave nave = new Nave(nombreNave, codigoNave, comandante);
        System.out.println("Nave inicializada con exito!");

        boolean salir = false;

        while (!salir) {
            System.out.println("Menu de opciones de la nave:");
            System.out.println("1. Consultar todos los módulos");
            System.out.println("2. Consultar módulo en especifico");
            System.out.println("3. Instalar módulo");
            System.out.println("4. Modificar módulo");
            System.out.println("5. Retirar módulo");
            System.out.println("6. Listar todos las planetas");
            System.out.println("7. Buscar planeta por codigo");
            System.out.println("8. Registrar nuevo planeta");
            System.out.println("9. Modificar datos de planeta");
            System.out.println("10. Eliminar planeta");
            System.out.println("11. Generar reporte de misión");
            System.out.println("12. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcion) {
                    case 1:
                        System.out.println("Módulos instalados en la nave:");
                        Modulo[] modulos = nave.getModulos();
                        for (int i = 0; i < modulos.length; i++) {
                            if (modulos[i] != null) {
                                System.out.println("Posicion " + i + ": " + modulos[i].getNombre() + " (" + modulos[i].getConsumoEnergia() + " kW, " + modulos[i].getEstado() + ")");
                            }
                        }
                        break;

                    case 2:
                        System.out.print("Ingrese la posición del módulo a consultar (0-4): ");
                        int posConsulta = scanner.nextInt();
                        scanner.nextLine();
                        Modulo mod = nave.consultarModulo(posConsulta);
                        if (mod != null) {
                            System.out.println("Codigo: " + mod.getCodigo() + ", Nombre: " + mod.getNombre() + ", Tipo: " + mod.getTipo() + ", Consumo de energía: " + mod.getConsumoEnergia() + " kW, Estado: " + mod.getEstado());
                        } else {
                            System.out.println("No hay módulo instalado en la posición " + posConsulta +  " o es invalido");
                        }
                        break;

                    case 3:
                        System.out.print("Ingrese la posición del módulo a instalar (0-4): ");
                        int posInstalar = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Codigo del módulo: ");
                        String codM = scanner.nextLine();
                        System.out.print("Nombre del módulo: ");
                        String nomM = scanner.nextLine();
                        System.out.print("Tipo del módulo: ");
                        String tipM = scanner.nextLine();
                        System.out.print("Consumo de energía del módulo (kW): ");
                        double conM = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Estado del módulo (activo/inactivo): ");
                        String estM = scanner.nextLine();

                        Modulo nuevomodulo = new Modulo(codM, nomM, tipM, conM, estM);
                        if (nave.instalarModulo(posInstalar, nuevomodulo)) {
                            System.out.println("Módulo instalado con éxito en la posición " + posInstalar);
                        } 
                        break;

                    case 4:
                        System.out.print("Ingrese la posición del módulo a modificar (0-4): ");
                        int posMod = scanner.nextInt();
                        scanner.nextLine();
                        Modulo modExistente = nave.consultarModulo(posMod);
                        if (modExistente != null) {
                            System.out.print("Nuevo consumo de energía: ");
                            double nuevoCon = scanner.nextDouble();
                            scanner.nextLine();
                            System.out.print("Nuevo estado del módulo (activo/inactivo): ");
                            String nuevoEst = scanner.nextLine();

                            modExistente.setConsumoEnergia(nuevoCon);
                            modExistente.setEstado(nuevoEst);
                            System.out.println("Módulo modificado con éxito en la posición " + posMod);
                        } else {
                            System.out.println("No hay módulo instalado en la posición " + posMod);
                        }
                        break;
                    case 5:
                        System.out.println("Ingrese posición a retirar (0-4): ");
                        int posRet = scanner.nextInt();
                        scanner.nextLine();
                        if (nave.retirarModulo(posRet)) {
                            System.out.println("Módulo retirado con éxito en la posición " + posRet);
                        }
                        break;
                    case 6:
                        System.out.println("Lista de planetas descubiertos:");
                        if (nave.getPlanetas().isEmpty()) {
                            System.out.println("No hay planetas descubiertos");
                        } else {
                            for (Planeta p : nave.getPlanetas()) {
                                System.out.println("Codigo: " + p.getCodigo() + ", Nombre: " + p.getNombre() + ", Habitabilidad: " + p.getNivelHabit() + "%");
                            }
                        }
                        break;
                    case 7:
                        System.out.println("Ingrese el codigo del planeta a buscar: ");
                        String codBuscar = scanner.nextLine();
                        Planeta pBuscado = nave.buscarPlaneta(codBuscar);
                        if (pBuscado != null) {
                            System.out.println("Planeta encontrado con codigo " + codBuscar + ", nombre " + pBuscado.getNombre() + ", distancia " + pBuscado.getDistancia() + " y habitabilidad " + pBuscado.getNivelHabit() + "%");
                        } else {
                            System.out.println("No hay planeta con ese codigo");
                        }
                        break;
                    case 8:
                        System.out.println("Ingrese el nombre del planeta a registrar: ");
                        String nomP = scanner.nextLine();
                        System.out.println("Ingrese el codigo del planeta a registrar: ");
                        String codP = scanner.nextLine();
                        System.out.println("Ingrese la distancia del planeta a registrar (>0): ");
                        double distP = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.println("Ingrese la temperatura del planeta a registrar: ");
                        double tempP = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.println("Ingrese la habitabilidad del planeta a registrar (0-100): ");
                        double habP = scanner.nextDouble();
                        scanner.nextLine();

                        Planeta nuevoPlaneta = new Planeta(codP, nomP, distP, tempP, habP);
                        if (nave.registrarPlaneta(nuevoPlaneta)) {
                            System.out.println("Planeta registrado con éxito");
                        }
                        break;
                    case 9:
                        System.out.println("Ingrese el codigo del planeta a modificar: ");
                        String codModP = scanner.nextLine();
                        Planeta planetaAMod = nave.buscarPlaneta(codModP);
                        if (planetaAMod != null) {
                            System.out.println("Ingrese la nueva distancia del planeta (0-): ");
                            double nDist = scanner.nextDouble();
                            System.out.println("Ingrese la nueva temperatura del planeta: ");
                            double nTem = scanner.nextDouble();
                            System.out.println("Ingrese la nueva habitabilidad del planeta (0-100): ");
                            double nHab = scanner.nextDouble();
                            scanner.nextLine();

                            planetaAMod.setDistancia(nDist);
                            planetaAMod.setTemperatura(nTem);
                            planetaAMod.setNivelHabit(nHab);
                            System.out.println("Planeta modificado con éxito");
                        } else {
                            System.out.println("No hay planeta con ese codigo");
                        }
                        break;
                    case 10: 
                        System.out.println("Ingrese el codigo del planeta a eliminar: ");
                        String codElim = scanner.nextLine();
                        if (nave.eliminarPlaneta(codElim)) {
                            System.out.println("Planeta eliminado con éxito");
                        } else {
                            System.out.println("No hay planeta con ese codigo");
                        }
                        break;
                    case 11:
                        nave.generarReporte();
                        break;
                    case 12:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción no válida");
                }
            }
            catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                scanner.nextLine(); 
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                System.out.println();
            }
        }
        scanner.close();
    }
}
