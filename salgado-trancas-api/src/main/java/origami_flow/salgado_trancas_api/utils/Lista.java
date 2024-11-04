package origami_flow.salgado_trancas_api.utils;

public class Lista<T>{
    private T[] lista;

    public Lista() {
        this.lista = (T[])new Object[0];
    }

    public Lista(int tamanho) {
        this.lista = (T[])new Object[tamanho];
    }

    public boolean add(T elemento) {
        T[] listaAux = (T[]) new Object[lista.length+1];
        for (int i = 0; i < lista.length; i++) {
            listaAux[i] = lista[i];
        }
        listaAux[lista.length] = elemento;
        lista = listaAux;
        return true;
    }

    public void add(int index, T elemento) {
        if (index >= lista.length || index < 0) throw new IndexOutOfBoundsException();
        lista[index] = elemento;
    }

    public void remove(T elemento) {
        int posicao = 0;
        T[] listaAux = (T[]) new Object[lista.length-1];
        for (T t : lista) {
            if (!t.equals(elemento)) {
                listaAux[posicao++] = t;
            }
        }
        lista = listaAux;
    }

    public void remove(int index) {
        int posicao = 0;
        T[] listaAux = (T[]) new Object[lista.length-1];
        for (int i = 0; i < lista.length ; i++) {
            if (i != index) {
                listaAux[posicao++] = lista[i];
            }
        }
        lista = listaAux;
    }

    public T get(int index) {
        if (index < 0 || index > lista.length) throw new IllegalStateException();
        return lista[index];
    }

    public int find(T elemento) {
        for (int i = 0; i < lista.length; i++) {
            if (lista[i].equals(elemento)) return i;
        }
        return -1;
    }

    public int size() {
        return lista.length;
    }

    public String toString() {
        if (lista.length == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < lista.length; i++) {
            sb.append(lista[i]);
            if (i+1 < lista.length) sb.append(',').append(' ');
        }
        sb.append(']');
        return sb.toString();
    }
}
