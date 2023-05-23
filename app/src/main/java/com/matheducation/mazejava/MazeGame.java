package com.matheducation.mazejava;

import java.util.HashMap;
import java.util.Scanner;

public class MazeGame {

        private int[][] maze;
         int currentChamber;

    public int getCurrentChamber() {
        return currentChamber;
    }

    public void setCurrentChamber(int currentChamber) {
        this.currentChamber = currentChamber;
    }

    public MazeGame(int[][] maze) {
            this.maze = maze;
            this.currentChamber = findStartChamber();
        }

    private int findStartChamber() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if ((maze[i][j] & 4) == 4) { // Check if the chamber has the up (4) bit set
                    return i * maze[i].length + j; // Calculate the index of the chamber
                }
            }
        }
        return -1; // Return -1 if start chamber is not found
    }

    public HashMap<String, Integer>  getPossibleDirections() {
        HashMap<String, Integer> directions =new HashMap<>();
        int chamber = maze[currentChamber / maze[0].length][currentChamber % maze[0].length];
        if ((chamber & 1) == 1) {
            directions.put("left", 1);
        }else{
            directions.put("left", 0);
        }
        if ((chamber & 2) == 2) {
            directions.put("right", 1);
        }else
            directions.put("right", 0);
        if ((chamber & 4) == 4) {
            directions.put("up", 1);
        }else{
            directions.put("up", 0);}

        if ((chamber & 8) == 8) {
            directions.put("down", 1);
        }else{
            directions.put("down", 0);
        }
        return directions;
    }

    int getNewChamber(int direction) {
        int newRow = currentChamber / maze[0].length;
        int newCol = currentChamber % maze[0].length;

        if (direction == 1 && (maze[newRow][newCol] & 1) == 1) { // Left
            newCol--;
        } else if (direction == 2 && (maze[newRow][newCol] & 2) == 2) { // Right
            newCol++;
        } else if (direction == 3 && (maze[newRow][newCol] & 4) == 4) { // Up
            newRow--;
        } else if (direction == 4 && (maze[newRow][newCol] & 8) == 8) { // Down
            newRow++;
        }

        return newRow * maze[0].length + newCol;
    }


    boolean gameOver(){
        return ( maze[currentChamber / maze[0].length][currentChamber % maze[0].length] == 0);
    }


}
