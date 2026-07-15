package SnakeGame;

import FigurasGeo.IFigurasGeometricas;
import GUI.GameConfigFrame;

import javax.swing.*;
import java.lang.reflect.Constructor;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    /**
     * Capitalizes the first letter of the given string.
     *
     * @param s The string to capitalize.
     * @return The capitalized string, or the original string if it is null or empty.
     */
    public static String capital(String s) {
        if (s == null || s.isEmpty())
            return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Snake Game!\nYou will have to choose between some options about the game and the snake.");
        // Asking the user for the display mode
        System.out.println("Would you like to play the game with graphics or in text mode?");
        System.out.println("1. Graphics");
        System.out.println("2. Text");
        int displayMode = 0;
        while (true) {
            try {
                displayMode = scanner.nextInt();
                if (displayMode == 1 || displayMode == 2) {
                    break;
                } else {
                    System.out.println("Invalid input, please choose 1 for Graphics or 2 for Text.");
                }
            }catch (IllegalArgumentException e) {
                System.out.println("An error occurred: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                main(args);
            }


        }
        if (displayMode == 1) {

            try {
                SwingUtilities.invokeLater(() -> {
                    GameConfigFrame configFrame = new GameConfigFrame();
                    configFrame.setVisible(true);
                });

            } catch (IllegalArgumentException e) {

            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                main(args);
            }

        } else {
            // Text mode
            System.out.println("You have chosen to play the game in text mode.");
            System.out.println("The game will start in a few moments.");


            //choosing the width of the arena
            System.out.println("1. Choose the Width of the arena");

            int width = scanner.nextInt();
            //choosing the Height of the arena
            System.out.println("2. Choose the Height of the arena");
            scanner = new Scanner(System.in);
            int height = scanner.nextInt();
            //choosing the size of the snake

            int snakeTiles = 0;
            System.out.println("3. Choose the size of the snake: 1, 4 or 9 tiles.");
            scanner = new Scanner(System.in);
            snakeTiles = scanner.nextInt();
            while (true) {
                if ((snakeTiles == 1) || (snakeTiles == 4) || (snakeTiles == 9)) {
                    break;
                } else {
                    System.out.println("Invalid size, please choose between 1, 4 or 9 tiles.");
                    scanner = new Scanner(System.in);
                    snakeTiles = scanner.nextInt();
                }
            }
            //choosing the type of food
            int typeFood = 0;
            System.out.println("4. Choose the type of the food: 1 for circles or 2 for squares.");
            scanner = new Scanner(System.in);
            typeFood = scanner.nextInt();
            while (true) {
                if ((typeFood == 1) || (typeFood == 2)) {
                    break;
                } else {
                    System.out.println("Invalid size, please choose between 1 or 2.");
                    scanner = new Scanner(System.in);
                    typeFood = scanner.nextInt();
                }
            }
            boolean isFoodCircle;
            if (typeFood == 1) {
                isFoodCircle = true;
            } else {
                isFoodCircle = false;
            }

            //CHOSING OBSTACLES
            Constructor<?> constructor;
            Class<?> cl;
            IFigurasGeometricas p;
            String s;
            String r;
            String[] aos;
            String[] rVector;
            Obstaculo obstaculo = new Obstaculo();


            System.out.println("5. How many obstacles do you want to have in the game?");
            scanner = new Scanner(System.in);
            int n_obstaculos = scanner.nextInt();
            //percorre o número de obstáculos
            for (int i = 0; i < n_obstaculos; i++) {

                System.out.println(" 5.1 Please insert the " + (i + 1) + " obstacle type and its coordinates, separated by a space.");
                //exemplo: Poligono 4 5 5 8 6 8 7 5 7;Quadrado 2 2 4 2 4 4 2 4
                //exemplo: Quadrado 2 2 4 2 4 4 2 4
                scanner = new Scanner(System.in);

                s = scanner.nextLine();
                if (s.isEmpty()) {
                    break;
                }
                aos = s.split(" ", 2);
                try {
                    cl = Class.forName("FigurasGeo." + capital(aos[0]));
                    constructor = cl.getConstructor(String.class);
                    p = (IFigurasGeometricas) constructor.newInstance(aos[1]);


                    System.out.println("Is the obstacle: 1- Dynamic or 2- Static?");
                    scanner = new Scanner(System.in);
                    int dynamic = scanner.nextInt();

                    //exit option
                    if (dynamic == 1) {
                        int dx = 0;
                        int dy = 0;
                        System.out.println("Insert movement vector, dx first then dy. Example: 1 0.");
                        scanner = new Scanner(System.in);
                        dx = scanner.nextInt();
                        dy = scanner.nextInt();
                        p.setDx(dx);
                        p.setDy(dy);

                        System.out.print("Does it have rotation? (1) Yes (2) No.");
                        scanner = new Scanner(System.in);
                        int rotation = scanner.nextInt();
                        if (rotation == 1) {
                            System.out.println("Insert rotation angle, followed by the rotation center.\nExample: 90 1 0, being 90 the angle and 1 0 the center.");
                            scanner = new Scanner(System.in);
                            int angle = scanner.nextInt();
                            int cX = scanner.nextInt();
                            int cY = scanner.nextInt();
                            p.setAngle(angle);
                            p.setcX(cX);
                            p.setcY(cY);

                        } else if (dynamic == 2) {
                            break;
                            //invalid input
                        } else {
                            boolean isDynamic = true;
                            while (isDynamic) {

                                System.out.println("Invalid input, please choose between 1 or 2.");
                                scanner = new Scanner(System.in);
                                dynamic = scanner.nextInt();
                                if (dynamic == 2 || dynamic == 1) {
                                    isDynamic = false;
                                }
                            }
                        }
                    }
                    obstaculo.addObstaculo(p);

                } catch (ClassNotFoundException cnfe) {
                    System.out.println("Classe não encontrada: " + cnfe.getMessage());
                } catch (Exception e) {
                    System.out.println("Erro: " + e.getMessage());
                }
            }

            obstaculo.printObstaculos();


            //choosing the method of input
            int input = 0;
            System.out.println("6. Choose the method of input, 1 for automatic or 2 for user based input.");
            scanner = new Scanner(System.in);
            input = scanner.nextInt();
            while (true) {
                if ((input == 1) || (input == 2)) {
                    break;
                } else {
                    System.out.println("Invalid input, please choose between 1 or 2.");
                    scanner = new Scanner(System.in);
                    input = scanner.nextInt();
                }
            }
            boolean isAutomatic = false;
            isAutomatic = (input == 1);
            GameArenaTxt gameArenaTxt = null;
            try {
                GameArenaTxt gameArenaText;
                int obsLevel = 1;

                gameArenaTxt = new GameArenaTxt(isFoodCircle, false, isAutomatic, width, height, snakeTiles, typeFood, obstaculo);


            } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
                System.out.println("An error occurred: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                main(args);
            }
        }
    }
}