package com.company.Controller;

public class Gauss {
    double[][] matriz;
    double mult[] = new double[9];
    double resposta[] = {1,1,1,1,1,1,1,1,1,1};
    int k = 0;
    double[][] matriz_L = new double[10][10];
    double[][] matrizLU = new double[10][10];
    int pos_x = 0;
    int pos_y = 1;

    public Gauss(double gauss[][]){
        for(int x = 0; x < matriz_L.length; x++){
            for(int y = 0; y < matriz_L[0].length; y++){
                matriz_L[x][y] = 0;
            }
        }
        this.matriz = gauss;
    }

    public double[][] getMatriz() {
        return matriz;
    }

    public void iteração(){
        acharM();
        calculo();
        this.k++;
    }

    public void acharM(){
        double dividendo = matriz[k][k];
        for(int x = 1; x<mult.length;x++){
            if(dividendo == 0){dividendo = 1;}
            mult[x-1]= matriz[x][k]/dividendo;
        }
    }

    public double[] getMult(){
        return mult;
    }

    public void calculo(){
        for(int x = k+1; x < matriz.length; x++){
            double mul = mult[x-1];
            for(int y = k; y < matriz[0].length; y++){
                double novo = matriz[x][y] - mul*matriz[k][y];
                matriz[x][y] = novo;
                if(y<matriz_L[0].length){
                    matriz_L[x][y] = mul;
                }
            }
        }
        for(int x = 1; x < matriz_L.length; x++){
            for(int y = 0; y < matriz_L[0].length; y++){
                if(x-1 == y){
                    matriz_L[x-1][y] = 1;
                }
                else if(x-1<y){
                    matriz_L[x][y] = 0;
                }
            }
        }
    }

    public void valorX(){
        double controle = 0;
        for(int x = 9; x > 0; x--){
            for(int y = 9; y >= x; y--){
                controle = matriz[x][y] * (matriz[11][y] - resposta[y]);
                resposta[y] = controle;
            }
        }
    }

    public String retorno(){
        int cont = 0;
        String saida = "";

        for(int z = 0; z < matriz.length; z++) {
            if (cont > 0) {
                iteração();
            }
            boolean vazia = true;
            for (int x = 0; x < matriz.length - 1; x++) {
                for (int y = 0; y < (matriz[0].length - 1); y++) {
                    if (matriz[x][y] != 0) {
                        saida += "(" + (Double) matriz[x][y] + "*X" + (y + 1) + ") + ";
                    }
                    if (matriz[x][y] != 0) {
                        vazia = false;
                    }
                }
                if (!vazia) {
                    saida = saida.substring(0, saida.length() - 2);
                    saida += "= " + matriz[x][matriz[0].length - 1];
                    saida += '\n';
                    vazia = true;
                }
            }
            saida += '\n';
            cont++;
        }
        return saida;
    }

    public String retorno_U(){
        String ret = "Matriz U:" + '\n';
        for(int i = 0; i < matriz.length; i++) {
            for(int j = 0; j < matriz.length; j++) {
                ret += matriz[i][j] + " | ";
                if(j == matriz.length - 1) {
                    ret += '\n';
                }
            }
        }
        return ret;
    }

    public String retorno_L() {
        concertaDiagonal();
        String retL = "Matriz L:" + '\n';
        for(int i = 0; i < matriz_L.length; i++) {
            for(int j = 0; j < matriz_L.length; j++) {
                retL += matriz_L[i][j] + " | ";
                if(j == matriz_L.length - 1) {
                    retL += '\n';
                }
            }
        }
        return retL;
    }

    public String retorno_LU(){
        calcula_LU();
        String retLU = "Matriz LU:" + '\n';
        for(int i = 0; i < matrizLU.length; i++) {
            for(int j = 0; j < matrizLU.length; j++) {
                retLU += matrizLU[i][j] + " | ";
                if(j == matrizLU.length - 1) {
                    retLU += '\n';
                }
            }
        }
        return retLU;
    }

    public  void calcula_LU(){
        for (int i = 0; i < matriz.length; i++){
            for (int j = 0; j < matriz_L[0].length; j++) {
                double somatoria = 0;
                for (int k = 0; k < matriz[0].length; k++) {
                    try{double produto = matriz[i][k] * matriz_L[k][j];
                        somatoria += produto;}
                    catch(Exception e) {
                        ;
                    }
                }
                matrizLU[i][j] = somatoria ;
            }
        }
    }

    public void concertaDiagonal(){
        for(int g = 9; g > k; g--){
            matriz_L[g][g] = 0;
        }
    }
}



