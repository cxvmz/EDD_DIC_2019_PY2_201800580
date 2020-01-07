/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2_edd.EDD.ARBOLB;

/**
 *
 * @author Christian
 */
public class nodoB {

    int m;
    int[] llaves;
    nodoB[] hijos;
    nodoB padre;
    int n;
    boolean esHoja;
    boolean esRaiz;

    int id;
    static int nodos = 0;

    public nodoB(int m, boolean Eshoja) {
        n = 0;
        this.m = m;
        this.esHoja = Eshoja;
        esRaiz = false;
        llaves = new int[m];
        hijos = new nodoB[m + 1];
        id = nodos;

        nodos++;
    }

    public void insertar(int llave) {
        int i = n;
        while (i > 0 && llaves[i - 1] > llave) {
            llaves[i] = llaves[i - 1];
            i--;
        }
        llaves[i] = llave;
        n++;
        if (n == m) { //**1
            this.dividirHijo();
        }
    }

    public nodoB buscar(int k) {

        int i = 0;
        while (i < n && k > llaves[i]) {
            i++;
        }
        if (llaves[i] == k) {
            return this;
        }
        if (esHoja == true) {
            return null;
        }
        return hijos[i].buscar(k);

    }

    public void dividirHijo() {

        if (esRaiz) {

            nodoB hijo1 = new nodoB(m, esHoja);
            nodoB hijo2 = new nodoB(m, esHoja);
            int i = 0;
            for (i = 0; i < m / 2; i++) {
                hijo1.llaves[i] = llaves[i];
                hijo1.hijos[i] = hijos[i];
                if (!esHoja) {
                    hijo1.hijos[i].padre = hijo1;
                }
                hijo1.n++;
            }
            hijo1.hijos[i] = hijos[i];
            if (!esHoja) {
                hijo1.hijos[i].padre = hijo1;
            }

            i = m / 2 + 1;

            int j = 0;

            for (j = 0; j < m / 2; j++, i++) { //**2
                hijo2.llaves[j] = llaves[i];
                hijo2.hijos[j] = hijos[i];
                if (!esHoja) {
                    hijo2.hijos[j].padre = hijo2;
                }
                hijo2.n++;
            }
            hijo2.hijos[j] = hijos[i];
            if (!esHoja) {
                hijo2.hijos[j].padre = hijo2;
            }
            llaves[0] = llaves[m / 2];
            hijos[0] = hijo1;
            hijos[1] = hijo2;
            hijo1.padre = this;
            hijo2.padre = this;
            esHoja = false;
            n = 1;
        } else {

            nodoB hermano = new nodoB(m, esHoja);
            int i = m / 2 + 1;
            int j = 0;
            for (j = 0; j < m / 2; j++, i++) { //**2
                hermano.llaves[j] = llaves[i];
                hermano.hijos[j] = hijos[i];
                if (!esHoja) {
                    hermano.hijos[j].padre = hermano;
                }
                hermano.n++;
            }
            hermano.hijos[j] = hijos[i];
            if (!esHoja) {
                hermano.hijos[j].padre = hermano;
            }

            hermano.padre = padre;
            n = m / 2;
            int z = padre.n;
            while (z > 0 && llaves[m / 2] < padre.llaves[z - 1]) {
                padre.hijos[z + 1] = padre.hijos[z];
                z--;
            }
            padre.hijos[z + 1] = hermano;
            padre.insertar(llaves[m / 2]);
        }
    }

    public void eliminar(int k) {
        nodoB nodo = buscar(k);
        if (nodo != null) {
            int i = 0;
            while (k != nodo.llaves[i]) {
                i++;
            }

            if (!nodo.esHoja) {
                nodoB aux = nodo.hijos[i + 1];
                while (!aux.esHoja) {
                    aux = aux.hijos[0];
                }
                nodo.llaves[i] = aux.llaves[0];
                aux.n--;
                int j = 0;
                for (j = 0; j < aux.n; j++) {
                    aux.llaves[j] = aux.llaves[j + 1];
                }
                nodo = aux;
            } else {
                while (i < nodo.n) {
                    nodo.llaves[i] = nodo.llaves[i + 1];
                    i++;
                }
                nodo.n--;

            }
            while (!nodo.esRaiz && nodo.n < m / 2) {
                int j = 0;
                while (nodo.padre.hijos[j] != nodo) {
                    j++;
                }
                if (nodo.n >= m / 2) {
                    return;
                } else if (j != nodo.padre.n && nodo.padre.hijos[j + 1].n > m / 2) {
                    nodo.redistribuirIzq(j + 1);
                } else if (j != 0 && nodo.padre.hijos[j - 1].n > m / 2) {
                    nodo.redistribuirDer(j - 1);
                } else if (nodo.padre.esRaiz) {
                    if (nodo.padre.n == 1) {
                        nodo.combinarRaiz();
                    } else {
                        if (j != nodo.padre.n) {
                            nodo.combinarHermanoIzq(j + 1);
                        } else {
                            nodo.combinarHermanoDer(j - 1);
                        }
                    }
                    return;
                } else {
                    if (j != nodo.padre.n) {
                        nodo.combinarHermanoIzq(j + 1);
                    } else {
                        nodo.combinarHermanoDer(j - 1);
                    }
                }
                nodo = nodo.padre;
            }
        }

    }

    void redistribuirIzq(int j) {

        llaves[n] = padre.llaves[j - 1];

        n++;

        hijos[n] = padre.hijos[j].hijos[0];
        if (!esHoja) {
            hijos[n].padre = this;
        }

        padre.llaves[j - 1] = padre.hijos[j].llaves[0];
        padre.hijos[j].n--;
        int i = 0;
        for (i = 0; i < padre.hijos[j].n; i++) {
            padre.hijos[j].llaves[i] = padre.hijos[j].llaves[i + 1];
            padre.hijos[j].hijos[i] = padre.hijos[j].hijos[i + 1];
        }
        padre.hijos[j].hijos[i] = padre.hijos[j].hijos[i + 1];

    }

    void redistribuirDer(int j) {
        int i = n;

        for (n = i; i > 0; i--) {
            llaves[i] = llaves[i - 1];
            hijos[i + 1] = hijos[i];
        }
        hijos[i + 1] = hijos[i];

        llaves[0] = padre.llaves[j];
        hijos[0] = padre.hijos[j].hijos[padre.hijos[j].n];
        if (!esHoja) {
            hijos[0].padre = this;
        }
        n++;

        padre.llaves[j] = padre.hijos[j].llaves[padre.hijos[j].n - 1];
        padre.hijos[j].n--;
    }

    void combinarHermanoIzq(int j) {
        llaves[n] = padre.llaves[j - 1];
        n++;
        int z = 0;
        for (z = 0; z < m / 2; z++) {
            llaves[m / 2 + z] = padre.hijos[j].llaves[z];
            hijos[m / 2 + z] = padre.hijos[j].hijos[z];
            if (!esHoja) {
                hijos[m / 2 + z].padre = this;
            }
            n++;
        }

        hijos[m / 2 + z] = padre.hijos[j].hijos[z];
        if (!esHoja) {
            hijos[m / 2 + z].padre = this;
        }

        for (int i = 0; i < padre.n - j; i++) {
            padre.llaves[j - 1 + i] = padre.llaves[j + i];
            padre.hijos[j + i] = padre.hijos[j + i + 1];
        }
        padre.n--;

    }

    void combinarHermanoDer(int j) {
        int i = m - 2;
        int k = n - 1;

        hijos[i + 1] = hijos[k + 1];
        for (k = (n - 1); k >= 0; i--, k--) {
            llaves[i] = llaves[k];
            hijos[i] = hijos[k];
        }

        llaves[m / 2] = padre.llaves[j];
        n++;

        int z = 0;
        hijos[m / 2] = padre.hijos[j].hijos[padre.hijos[j].n];
        if (!esHoja) {
            hijos[m / 2 + z].padre = this;
        }

        for (z = 0; z < m / 2; z++) {
            llaves[m / 2 - 1 - z] = padre.hijos[j].llaves[padre.hijos[j].n - 1 - z];
            hijos[m / 2 - 1 - z] = padre.hijos[j].hijos[padre.hijos[j].n - 1 - z];
            if (!esHoja) {
                hijos[m / 2 - 1 - z].padre = this;
            }
            n++;
        }

        for (int ii = 0; ii < padre.n - j; ii++) {
            padre.llaves[j + ii] = padre.llaves[j + 1 + ii];
            padre.hijos[j + ii] = padre.hijos[j + ii + 1];
        }
        padre.n--;
    }

    void combinarRaiz() {
        if (llaves[0] < padre.llaves[0]) {
            combinarRaizIzq();
        } else {
            combinarRaizDer();
        }
    }

    void combinarRaizIzq() {
        padre.esHoja = esHoja;
        padre.llaves[m / 2 - 1] = padre.llaves[0];
        nodoB aux = padre.hijos[1];
        int i = 0;
        for (i = 0; i < n; i++) {
            padre.llaves[i] = llaves[i];
            padre.hijos[i] = hijos[i];
            if (!padre.esHoja) {
                padre.hijos[i].padre = padre;
            }
            padre.n++;
        }
        padre.hijos[i] = hijos[i];
        if (!padre.esHoja) {
            padre.hijos[i].padre = padre;
        }
        int z = 0;
        for (z = 0; z < aux.n; z++) {
            padre.llaves[m / 2 + z] = aux.llaves[z];
            padre.hijos[m / 2 + z] = aux.hijos[z];
            if (!padre.esHoja) {
                padre.hijos[m / 2 + z].padre = padre;
            }
            padre.n++;
        }
        padre.hijos[m / 2 + z] = aux.hijos[z];
        if (!padre.esHoja) {
            padre.hijos[m / 2 + z].padre = padre;
        }

    }

    void combinarRaizDer() {
        padre.esHoja = esHoja;
        padre.llaves[m / 2] = padre.llaves[0];
        nodoB aux = padre.hijos[0];
        int i = 0;
        for (i = 0; i < n; i++) {
            padre.llaves[m / 2 + 1 + i] = llaves[i];
            padre.hijos[m / 2 + 1 + i] = hijos[i];
            if (!padre.esHoja) {
                padre.hijos[m / 2 + 1 + i].padre = padre;
            }
            padre.n++;
        }

        padre.hijos[m / 2 + 1 + i] = hijos[i];
        if (!padre.esHoja) {
            padre.hijos[m / 2 + 1 + i].padre = padre;
        }

        int z = 0;
        for (z = 0; z < aux.n; z++) {
            padre.llaves[z] = aux.llaves[z];
            padre.hijos[z] = aux.hijos[z];
            if (!padre.esHoja) {
                padre.hijos[z].padre = padre;
            }
            padre.n++;
        }
        padre.hijos[z] = aux.hijos[z];
        if (!padre.esHoja) {
            padre.hijos[z].padre = padre;
        }

    }
}
