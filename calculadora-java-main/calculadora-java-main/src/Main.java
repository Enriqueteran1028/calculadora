import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    // ════════════════════════════════════════════════════════
    //  METODO PRINCIPAL
    // ════════════════════════════════════════════════════════
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        System.setErr(new PrintStream(System.err, true, StandardCharsets.UTF_8));

        boolean continuar = true;

        separador("CALCULADORA JAVA");

        while (continuar) {
            mostrarMenu();
            int opcion = leerOpcionMenu();

            switch (opcion) {
                case 1: ejecutarOperacion("+",  "SUMA");           break;
                case 2: ejecutarOperacion("-",  "RESTA");          break;
                case 3: ejecutarOperacion("*",  "MULTIPLICACIÓN"); break;
                case 4: ejecutarOperacion("/",  "DIVISIÓN");       break;
                case 5: ejecutarOperacion("%",  "MÓDULO");         break;
                case 6: ejecutarPotencia();                        break;
                case 7: ejecutarRaizCuadrada();                    break;
                case 8: continuar = false;                         break;
                default:
                    System.out.println("  Opción inválida. Intente de nuevo.");
            }

            if (continuar) {
                System.out.println("\n  Presione ENTER para continuar...");
                scanner.nextLine();
            }
        }

        separador("HASTA LUEGO");
        scanner.close();
    }

    // ════════════════════════════════════════════════════════
    //  MENÚ
    // ════════════════════════════════════════════════════════
    private static void mostrarMenu() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║        CALCULADORA MATEMÁTICA        ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║  1)  Suma            [+]             ║");
        System.out.println("║  2)  Resta           [-]             ║");
        System.out.println("║  3)  Multiplicación  [×]             ║");
        System.out.println("║  4)  División        [÷]             ║");
        System.out.println("║  5)  Módulo          [%]             ║");
        System.out.println("║  6)  Potencia        [^]             ║");
        System.out.println("║  7)  Raíz Cuadrada   [√]             ║");
        System.out.println("║  8)  Salir                           ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.print("   ➜ Seleccione una opción: ");
    }

    private static int leerOpcionMenu() {
        try {
            int op = Integer.parseInt(scanner.nextLine().trim());
            return op;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // ════════════════════════════════════════════════════════
    //  OPERACIONES BÁSICAS ( +  -  *  /  % )
    // ════════════════════════════════════════════════════════
    private static void ejecutarOperacion(String operador, String nombre) {
        separador(nombre);
        double a = leerNumero("  Ingrese el primer  número: ");
        double b = leerNumero("  Ingrese el segundo número: ");
        double resultado = calcular(a, operador, b);
        mostrarResultado(a, operador, b, resultado);
    }

    private static double calcular(double a, String operador, double b) {
        switch (operador) {
            case "+": return sumar(a, b);
            case "-": return restar(a, b);
            case "*": return multiplicar(a, b);
            case "/": return dividir(a, b);
            case "%": return modulo(a, b);
            default:  return 0;
        }
    }

    // ════════════════════════════════════════════════════════
    //  MÉTODOS DE CADA OPERACIÓN
    // ════════════════════════════════════════════════════════
    private static double sumar(double a, double b) {
        return a + b;
    }

    private static double restar(double a, double b) {
        return a - b;
    }

    private static double multiplicar(double a, double b) {
        return a * b;
    }

    private static double dividir(double a, double b) {
        if (b == 0) {
            System.out.println(" Error: no se puede dividir entre cero.");
            return Double.NaN;
        }
        return a / b;
    }

    private static double modulo(double a, double b) {
        if (b == 0) {
            System.out.println("  Error: no se puede calcular módulo con divisor cero.");
            return Double.NaN;
        }
        return a % b;
    }

    private static void ejecutarPotencia() {
        separador("POTENCIA");
        double base     = leerNumero("  Ingrese la base:      ");
        double exponente = leerNumero("  Ingrese el exponente: ");
        double resultado = Math.pow(base, exponente);
        System.out.printf("%n  %.4f ^ %.4f = %.4f%n", base, exponente, resultado);
    }

    private static void ejecutarRaizCuadrada() {
        separador("RAÍZ CUADRADA");
        double numero = leerNumero("  Ingrese el número: ");
        if (numero < 0) {
            System.out.println(" Error: no existe raíz cuadrada de un número negativo.");
            return;
        }
        double resultado = Math.sqrt(numero);
        System.out.printf("%n  √ %.4f = %.4f%n", numero, resultado);
    }

    // ════════════════════════════════════════════════════════
    //  UTILIDADES
    // ════════════════════════════════════════════════════════
    private static double leerNumero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("  Valor inválido. Ingrese un número (ej: 3.14)");
            }
        }
    }

    private static void mostrarResultado(double a, String op, double b, double resultado) {
        if (Double.isNaN(resultado)) return;
        System.out.println("\n  ┌──────────────────────────────────┐");
        System.out.printf( "  │  %.4f  %s  %.4f  =  %.4f  │%n", a, op, b, resultado);
        System.out.println("  └──────────────────────────────────┘");
    }

    private static void separador(String titulo) {
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.printf( "║  %-48s║%n", titulo);
        System.out.println("╚══════════════════════════════════════════════════╝");
    }
}