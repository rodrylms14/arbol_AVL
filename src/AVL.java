public class AVL {

    //Atributos.
    private NodoAVL raiz;

    //Métodos.
    //Constructor.
    public AVL() {
        raiz = null;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    public NodoAVL getRaiz() {
        return raiz;
    }

    public NodoAVL buscar(int llaveBuscar) {
        if (estaVacio()) {
            System.out.println("El árbol está vacío.\n");
            return null;
        }
        NodoAVL nodoTemp = raiz;
        while (nodoTemp.getLlave() != llaveBuscar) {
            if (llaveBuscar < nodoTemp.getLlave()) {
                nodoTemp = nodoTemp.getHijoIzquierdo();
            } else {
                nodoTemp = nodoTemp.getHijoDerecho();
            }
            if (nodoTemp == null) {
                System.out.println("El nodo buscado no está en el árbol.\n");
                return null;
            }
        }
        return nodoTemp;
    }

    public void insertar(int llaveInsertar) {
        raiz = insertarRec(raiz, llaveInsertar);
    }

    private NodoAVL insertarRec(NodoAVL nodo, int llaveInsertar) {

        if (nodo == null) {
            return new NodoAVL(llaveInsertar);
        }

        if (llaveInsertar < nodo.getLlave()) {
            nodo.setHijoIzquierdo(insertarRec(nodo.getHijoIzquierdo(), llaveInsertar));
        } else if (llaveInsertar > nodo.getLlave()) {
            nodo.setHijoDerecho(insertarRec(nodo.getHijoDerecho(), llaveInsertar));
        } else {
            return nodo;
        }

        nodo.actualizarAltura();

        int balance = nodo.balancear();

        if (balance > 1 && llaveInsertar < nodo.getHijoIzquierdo().getLlave()) {
            return nodo.rotarDerecha(nodo);
        }

        if (balance < -1 && llaveInsertar > nodo.getHijoDerecho().getLlave()) {
            return nodo.rotarIzquierda(nodo);
        }

        if (balance > 1 && llaveInsertar > nodo.getHijoIzquierdo().getLlave()) {
            return nodo.rotarIzquierdaDerecha(nodo);
        }

        if (balance < -1 && llaveInsertar < nodo.getHijoDerecho().getLlave()) {
            return nodo.rotarDerechaIzquierda(nodo);
        }

        return nodo;
    }
    
    public void enOrden(NodoAVL raizTemp) {
        if (raizTemp != null) {
            enOrden(raizTemp.getHijoIzquierdo());
            System.out.print(raizTemp.getLlave() + " ");
            enOrden(raizTemp.getHijoDerecho());
        }
    }

    public void preOrden(NodoAVL raizTemp) {
        if (raizTemp != null) {
            System.out.print(raizTemp.getLlave() + " ");
            preOrden(raizTemp.getHijoIzquierdo());
            preOrden(raizTemp.getHijoDerecho());
        }
    }

    public void postOrden(NodoAVL raizTemp) {
        if (raizTemp != null) {
            postOrden(raizTemp.getHijoIzquierdo());
            postOrden(raizTemp.getHijoDerecho());
            System.out.print(raizTemp.getLlave() + " ");
        }
    }

    private NodoAVL getSucesor(NodoAVL nodoBorrar) {
        NodoAVL padreSucesor = nodoBorrar;
        NodoAVL sucesor = nodoBorrar;
        NodoAVL nodoActual = nodoBorrar.getHijoDerecho();
        while (nodoActual != null) {
            padreSucesor = sucesor;
            sucesor = nodoActual;
            nodoActual = nodoActual.getHijoIzquierdo();
        }
        if (sucesor != nodoBorrar.getHijoDerecho()) {
            padreSucesor.setHijoIzquierdo(sucesor.getHijoDerecho());
            sucesor.setHijoDerecho(nodoBorrar.getHijoDerecho());
        }
        return sucesor;
    }

    public void eliminar(int llaveEliminar) {
        raiz = eliminarRec(raiz, llaveEliminar);
    }

    private NodoAVL eliminarRec(NodoAVL nodo, int llaveEliminar) {

        if (nodo == null) {
            return null;
        }

        if (llaveEliminar < nodo.getLlave()) {
            nodo.setHijoIzquierdo(eliminarRec(nodo.getHijoIzquierdo(), llaveEliminar));
        } else if (llaveEliminar > nodo.getLlave()) {
            nodo.setHijoDerecho(eliminarRec(nodo.getHijoDerecho(), llaveEliminar));
        } else {

            if (nodo.getHijoIzquierdo() == null && nodo.getHijoDerecho() == null) {
                return null;
            }
            else if (nodo.getHijoIzquierdo() == null) {
                return nodo.getHijoDerecho();
            } else if (nodo.getHijoDerecho() == null) {
                return nodo.getHijoIzquierdo();
            }
            else {
                NodoAVL sucesor = nodo.getHijoDerecho();
                while (sucesor.getHijoIzquierdo() != null) {
                    sucesor = sucesor.getHijoIzquierdo();
                }
                nodo.setLlave(sucesor.getLlave());
                nodo.setHijoDerecho(eliminarRec(nodo.getHijoDerecho(), sucesor.getLlave()));
            }
        }

        if (nodo == null) {
            return null;
        }

        nodo.actualizarAltura();

        int balance = nodo.balancear();

        if (balance > 1) {
            int balIzq = nodo.getHijoIzquierdo() != null ? nodo.getHijoIzquierdo().balancear() : 0;
            if (balIzq >= 0) {
                return nodo.rotarDerecha(nodo);
            } else {
                return nodo.rotarIzquierdaDerecha(nodo);
            }
        }

        if (balance < -1) {
            int balDer = nodo.getHijoDerecho() != null ? nodo.getHijoDerecho().balancear() : 0;
            if (balDer <= 0) {
                return nodo.rotarIzquierda(nodo);
            } else {
                return nodo.rotarDerechaIzquierda(nodo);
            }
        }

        return nodo;
    }
}