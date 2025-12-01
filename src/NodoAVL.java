public class NodoAVL {

    //Atributos.
    private int llave;
    private NodoAVL hijoIzquierdo;
    private NodoAVL hijoDerecho;
    private int altura;

    //Métodos.
    //Constructor.
    public NodoAVL(int llave) {
        this.llave = llave;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
        altura = 1; //Cualquier nodo es una hoja al momento de su inserción.
                    // La altura de toda hoja es por defecto 1.
    }

    //Getters.
    public int getLlave() {
        return llave;
    }

    public NodoAVL getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public NodoAVL getHijoDerecho() {
        return hijoDerecho;
    }

    public int getAltura() {
        return altura;
    }

    //Setters.
    public void setHijoIzquierdo(NodoAVL hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public void setHijoDerecho(NodoAVL hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
    public void setLlave(int llave) {
        this.llave = llave;
    }

    public int evaluarAltura(NodoAVL nodoEvaluar) {
        if (nodoEvaluar == null) {
            return 0;
        }
        return nodoEvaluar.getAltura();
    }

    public int evaluarBalance(NodoAVL nodoEvaluar) {
        if (nodoEvaluar == null) {
            return 0;
        }
        return evaluarAltura(nodoEvaluar.getHijoIzquierdo()) -
                evaluarAltura(nodoEvaluar.getHijoDerecho());
    }

    public int balancear() {
        return evaluarBalance(this);
    }

    public NodoAVL rotarIzquierda(NodoAVL nodoRotar) { 
        NodoAVL hijoDer = nodoRotar.getHijoDerecho();
        NodoAVL subArbol = hijoDer.getHijoIzquierdo();

        hijoDer.setHijoIzquierdo(nodoRotar);
        nodoRotar.setHijoDerecho(subArbol);

        nodoRotar.actualizarAltura();
        hijoDer.actualizarAltura();

        return hijoDer;
     }

    public NodoAVL rotarDerecha(NodoAVL nodoRotar) { 
        NodoAVL hijoIzq = nodoRotar.getHijoIzquierdo();
        NodoAVL subArbol = hijoIzq.getHijoDerecho();

        hijoIzq.setHijoDerecho(nodoRotar);
        nodoRotar.setHijoIzquierdo(subArbol);

        nodoRotar.actualizarAltura();
        hijoIzq.actualizarAltura();

        return hijoIzq;
     }

    public NodoAVL rotarIzquierdaDerecha(NodoAVL nodoRotar) { 
        nodoRotar.setHijoIzquierdo(rotarIzquierda(nodoRotar.getHijoIzquierdo()));

        return rotarDerecha(nodoRotar);
     }

    public NodoAVL rotarDerechaIzquierda(NodoAVL nodoRotar) { 
        nodoRotar.setHijoDerecho(rotarDerecha(nodoRotar.getHijoDerecho()));

        return rotarIzquierda(nodoRotar);
    }

    public void actualizarAltura() {
        this.altura = Math.max(evaluarAltura(hijoIzquierdo), evaluarAltura(hijoDerecho)) + 1;
    }
}