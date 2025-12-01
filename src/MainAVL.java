import java.util.Scanner;

public class MainAVL {

    public static void main(String[] args) {

        AVL arbol = new AVL();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n===== MENU ARBOL AVL =====");
            System.out.println("1. Insertar nodo");
            System.out.println("2. Buscar nodo");
            System.out.println("3. Eliminar nodo");
            System.out.println("4. Mostrar en orden");
            System.out.println("5. Mostrar preorden");
            System.out.println("6. Mostrar postorden");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            while (!sc.hasNextInt()) {
                System.out.print("Ingrese un número válido: ");
                sc.next();
            }
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la llave a insertar: ");
                    int llaveIns = sc.nextInt();
                    arbol.insertar(llaveIns);
                    System.out.println("Nodo insertado.");
                    break;

                case 2:
                    System.out.print("Ingrese la llave a buscar: ");
                    int llaveBus = sc.nextInt();
                    NodoAVL encontrado = arbol.buscar(llaveBus);
                    if (encontrado != null) {
                        System.out.println("Nodo encontrado con llave: " + encontrado.getLlave());
                    }
                    break;

                case 3:
                    System.out.print("Ingrese la llave a eliminar: ");
                    int llaveElim = sc.nextInt();
                    arbol.eliminar(llaveElim);
                    System.out.println("Si la llave existía, fue eliminada.");
                    break;

                case 4:
                    System.out.print("Recorrido en orden: ");
                    arbol.enOrden(arbol.getRaiz());
                    System.out.println();
                    break;

                case 5:
                    System.out.print("Recorrido preorden: ");
                    arbol.preOrden(arbol.getRaiz());
                    System.out.println();
                    break;

                case 6:
                    System.out.print("Recorrido postorden: ");
                    arbol.postOrden(arbol.getRaiz());
                    System.out.println();
                    break;

                case 0:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 0);

        sc.close();
    }
}
