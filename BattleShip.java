package com.company;
import java.util.Scanner;

public class BattleShip {

    public static void main(String[] args) {
        System.out.println("Welcome to BattleShips Game!");
        System.out.println("Right now the sea is empty...");

        char[][] map = new char[10][10];
        char[][] computerMap = new char[10][10];

        initializeMap(map);
        printMap(map);

        System.out.println("Let's deploy your ships!");
        deployUserShips(map);
        printMap(map);

        System.out.println("Let's deploy computer's ships!");
        deployComputerShips(map, computerMap);
        play(map, computerMap);
    }

    public static void play(char[][] map, char[][] computerMap) {
        Scanner input = new Scanner(System.in);
        int x;
        int y;

        int playerShips = 5;
        int computerShips = 5;

        int round;

        for (round = 0; playerShips > 0 && computerShips > 0; round++) {
            System.out.println("Round NO: "round);
            if (round % 2 != 0) {
                System.out.println("Your turn...");
                System.out.println("Enter a x coordinate");
                x = input.nextInt();
                System.out.println("Enter a y coordinate");
                y = input.nextInt();

                if (map[x][y] == '@') {
                    System.out.println("Oh no, you sunk your own ship :(");
                    map[x][y] = 'X';
                    playerShips--;
                } else if (map[x][y] == ' ') {
                    if (computerMap[x][y] == '-') {
                        System.out.println("Boom! You sunk a ship.");
                        map[x][y] = '!';
                        computerMap[x][y] = '!';
                        computerShips--;
                    } else {
                        System.out.println("You missed.");
                        map[x][y] = '-';
                    }
                    printMap(map);
                }
            } else {
                System.out.println("Computers turn...");

                int xCoordinate = (int) (Math.random() * (10 - 0));
                int yCoordinate = (int) (Math.random() * (10 - 0));

                if (map[xCoordinate][yCoordinate] == '@') {
                    System.out.println("The Computer sunk one of your ships!");
                    map[xCoordinate][yCoordinate] = 'X';
                    playerShips--;
                } else if (computerMap[xCoordinate][yCoordinate] == '-') {
                    System.out.println("The Computer sunk one of its own ships!");
                    map[xCoordinate][yCoordinate] = '!';
                    computerMap[xCoordinate][yCoordinate] = ' ';
                    computerShips--;
                }
                printMap(map);
            }
            System.out.println("Your ships: " + playerShips + " | Computer ships: " + computerShips);
        }

        if (playerShips == 0) {
            System.out.println("You lose the battle!");
        } else {
            System.out.println("Hooray! You win the battle!");
        }
    }


    public static void deployComputerShips(char[][] map, char[][] computerMap) {
        for (int i = 0; i < 5; i++) {
            int xCoordinate = (int) (Math.random() * (10 - 0));
            int yCoordinate = (int) (Math.random() * (10 - 0));

            if (map[xCoordinate][yCoordinate] == '@') {
                i--;
            } else if (map[xCoordinate][yCoordinate] == ' ') {
                computerMap[xCoordinate][yCoordinate] = '-';
                System.out.println("Ship deployed");
            }
        }
        printMap(computerMap);
    }
    public static void deployUserShips(char[][] map) {
        int xCoordinate;
        int yCoordinate;
        Scanner input = new Scanner(System.in);

        for (int i = 0; i < 5; i++) {
            System.out.println("Enter x coordinate for your ship: ");
            xCoordinate = input.nextInt();

            System.out.println("Enter y coordinate for your ship: ");
            yCoordinate = input.nextInt();
            if (xCoordinate > 10 || xCoordinate < 0 || yCoordinate > 10 || yCoordinate < 0) {
                System.out.println("Invalid Coordinates, try again");
                i--;
            } else if (map[xCoordinate][yCoordinate] == ' ') {
                map[xCoordinate][yCoordinate] = '@';
                System.out.println("Ship deployed.");
            } else if (map[xCoordinate][yCoordinate] == '@') {
                System.out.println("You already have a ship there!");
                i--;
            }
        }
    }

    public static void initializeMap(char[][] map) {
        //fill the map with ' '
        for (int k = 0; k < 10; k++) {
            for (int j = 0; j < 10; j++) {
                map[k][j] = ' ';
            }
        }
    }

    public static void printMap(char[][] map) {
        System.out.println("\t" + "0123456789");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " | ");
            for (int j = 0; j < 10; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println(" | " + i);
        }
        System.out.println("\t" + "0123456789");
    }
}


