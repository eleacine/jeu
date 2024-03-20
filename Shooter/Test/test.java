package Shooter.Test;

import Shooter.factory.PlateauLevelLoader;

public class test {

    int[][] level_tab;
    int[][] res;

    public test() {
        this.level_tab = PlateauLevelLoader.loadPlayingBoard("Shooter/factory/PlateauLevels.txt", 1);
        this.res = new int[this.level_tab.length][this.level_tab[0].length];
    }

    // ------------- Flood fill ----------------

    // public int[][] floodfill(int xCible, int yCible) {
    // int[][] res = new int[this.level_tab.length][this.level_tab[0].length];
    // for (int i = 0; i < this.level_tab.length; i++) {
    // for (int j = 0; j < this.level_tab[i].length; j++) {

    // if (this.level_tab[i][j] == 1 || this.level_tab[i][j] == 2
    // || this.level_tab[i][j] == 7) {
    // res[i][j] = 1000;
    // } else {
    // res[i][j] = -1;
    // }
    // }
    // }
    // res[yCible][xCible] = 0; // on met la case cible à 0
    // res = floodfill2(yCible, xCible, res);
    // return res;
    // }

    public void floodfill(int xCible, int yCible) {
        // System.out.println(this.level_tab.length + " " + this.level_tab[0].length );
        // System.out.println(this.res.length + " " + this.res[0].length);
        for (int i = 0; i < this.level_tab.length; i++) {
            for (int j = 0; j < this.level_tab[i].length; j++) {


                if (this.level_tab[i][j] == 1 || this.level_tab[i][j] == 2
                        || this.level_tab[i][j] == 7) {
                    this.res[i][j] = 1000;
                } else {
                    this.res[i][j] = -1;
                }
            }
        }
        this.res[yCible][xCible] = 0; // on met la case cible à 0
        this.res = floodfill2(yCible, xCible, res);
    }

    public int[][] floodfill2(int x, int y, int[][] res) {
        int v = res[x][y] + 1;

        // Liste des directions
        int[][] directions = {
                { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 },
                { 1, 1 }, { -1, -1 }, { 1, -1 }, { -1, 1 }
        };

        // Parcours de toutes les directions
        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];

            // Vérification des limites de la grille
            if (newX >= 0 && newX < res.length && newY >= 0 && newY < res[0].length) {
                // Vérification si la case est vide et la valeur non mise à jour
                if (res[newX][newY] == -1 || res[newX][newY] > v && res[newX][newY] != 1000) {
                    res[newX][newY] = v;
                    // Appel récursif pour la nouvelle case
                    floodfill2(newX, newY, res);
                }
            }
        }

        return res;
    }

    public void printLenght() {
        for (int i = 0; i < this.level_tab.length; i++) {
            System.out.println(i + " " + this.level_tab[i].length);
        }
    }

    public void printFloodFill(int[][] res) {
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        test m = new test();
        // m.printFloodFill(m.level_tab);
        // m.printLenght();
        // int[][] res = m.floodfill(5, 2);
        m.floodfill(5, 2);
        m.printFloodFill(m.res);
    }

}
