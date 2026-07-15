package GUI;

import FigurasGeo.IFigurasGeometricas;
import FigurasGeo.Ponto;
import SnakeGame.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.Random;

/**
 * GameConfigFrame class represents a configuration frame for the Snake Game.
 * It allows users to set various game configurations such as grid size, snake size,
 * input mode, food type, obstacles, and render type before starting the game.
 */
public class GameConfigFrame extends JFrame {
    private JTextField gridWidthField;
    private JTextField gridHeightField;
    private JTextField obstacleField;
    private JRadioButton snakeSize1Button;
    private JRadioButton snakeSize4Button;
    private JRadioButton snakeSize9Button;
    private JRadioButton automaticInputButton;
    private JRadioButton manualInputButton;
    private JRadioButton squareFoodButton;
    private JRadioButton circleFoodButton;
    private JRadioButton fullRenderButton;
    private JRadioButton outlineRenderButton;
    private int snakeSize;
    private JButton startButton;
    private boolean automaticInput;
    private boolean isFoodCircle;
    private boolean isFullRender;
    private Obstaculo obstaculo;

    /**
     * Constructs a GameConfigFrame and initializes the configuration interface.
     */
    public GameConfigFrame() {
        setTitle("Game Configuration");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Grid Width
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Grid Width:"), gbc);

        gbc.gridx = 1;
        gridWidthField = new JTextField("800", 10);
        panel.add(gridWidthField, gbc);

        // Grid Height
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Grid Height:"), gbc);

        gbc.gridx = 1;
        gridHeightField = new JTextField("800", 10);
        panel.add(gridHeightField, gbc);

        // Label for snake size
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(new JLabel("Insira o tamanho da cobra:"), gbc);

        // Buttons for snake size
        ButtonGroup snakeSizeGroup = new ButtonGroup();

        gbc.gridy = 3;
        gbc.gridwidth = 1;
        snakeSize1Button = new JRadioButton("1");
        snakeSize1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                snakeSize = 1;
            }
        });
        snakeSizeGroup.add(snakeSize1Button);
        panel.add(snakeSize1Button, gbc);

        gbc.gridx = 1;
        snakeSize4Button = new JRadioButton("4");
        snakeSize4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                snakeSize = 4;
            }
        });
        snakeSizeGroup.add(snakeSize4Button);
        panel.add(snakeSize4Button, gbc);

        gbc.gridx = 2;
        snakeSize9Button = new JRadioButton("9");
        snakeSize9Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                snakeSize = 9;
            }
        });
        snakeSizeGroup.add(snakeSize9Button);
        panel.add(snakeSize9Button, gbc);

        // Buttons for input mode
        ButtonGroup inputModeGroup = new ButtonGroup();

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        automaticInputButton = new JRadioButton("Automatic Input");
        automaticInputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                automaticInput = true;
            }
        });
        inputModeGroup.add(automaticInputButton);
        panel.add(automaticInputButton, gbc);

        gbc.gridx = 1;
        manualInputButton = new JRadioButton("Manual Input");
        manualInputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                automaticInput = false;
            }
        });
        inputModeGroup.add(manualInputButton);
        panel.add(manualInputButton, gbc);

        // Label for food type
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(new JLabel("Escolha o tipo de comida:"), gbc);

        // Buttons for food type
        ButtonGroup foodTypeGroup = new ButtonGroup();

        gbc.gridy = 6;
        gbc.gridwidth = 1;
        squareFoodButton = new JRadioButton("Comida Quadrada");
        squareFoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isFoodCircle = false;
            }
        });
        foodTypeGroup.add(squareFoodButton);
        panel.add(squareFoodButton, gbc);

        gbc.gridx = 1;
        circleFoodButton = new JRadioButton("Comida Circular");
        circleFoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isFoodCircle = true;
            }
        });
        foodTypeGroup.add(circleFoodButton);
        panel.add(circleFoodButton, gbc);

        // Obstacles input
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        panel.add(new JLabel("Insira os obstáculos (separados por ';'):"), gbc);

        gbc.gridy = 8;
        obstacleField = new JTextField("", 10);
        panel.add(obstacleField, gbc);

        // Render Type label
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        panel.add(new JLabel("Escolha o tipo de renderização:"), gbc);

        // Buttons for render type
        ButtonGroup renderTypeGroup = new ButtonGroup();

        gbc.gridy = 10;
        gbc.gridwidth = 1;
        fullRenderButton = new JRadioButton("Renderização Completa");
        fullRenderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isFullRender = true;
            }
        });
        renderTypeGroup.add(fullRenderButton);
        panel.add(fullRenderButton, gbc);

        gbc.gridx = 1;
        outlineRenderButton = new JRadioButton("Renderização de Contorno");
        outlineRenderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isFullRender = false;
            }
        });
        renderTypeGroup.add(outlineRenderButton);
        panel.add(outlineRenderButton, gbc);

        // Start Game button
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
        panel.add(startButton, gbc);

        add(panel);
    }

    /**
     * Starts the game with the specified configurations.
     * This method initializes the grid, snake, food, and other components, and launches the game interface.
     */
    private void startGame() {
        int gridWidth = Integer.parseInt(gridWidthField.getText());
        int gridHeight = Integer.parseInt(gridHeightField.getText());
        int tileSize = 20;
        Score score = new Score(null, 0);

        obstaculo = new Obstaculo();

        String[] obstacleStrings = obstacleField.getText().split(";");
        for (String obstacleString : obstacleStrings) {
            if (obstacleString.isEmpty() || obstacleString.equals("0")) break;
            try {
                String[] aos = obstacleString.trim().split(" ", 2);
                Class<?> cl = Class.forName("FigurasGeo." + capital(aos[0]));
                Constructor<?> constructor = cl.getConstructor(String.class);
                IFigurasGeometricas p = (IFigurasGeometricas) constructor.newInstance(aos[1]);
                obstaculo.addObstaculo(p);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Launch the game with the specified settings
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Snake Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Configurações da grid e componentes do jogo
            Grid grid = new Grid(gridWidth, gridHeight, tileSize, isFoodCircle, isFullRender);

            Random random = new Random();
            int x = random.nextInt(1,gridWidth/tileSize - 1); // Gera um valor entre 2 e maxLimit-1
            int y = random.nextInt(1,gridHeight/tileSize - 1) ; // Gera um valor entre 2 e maxLimit-1
            Snake snake = new Snake(snakeSize, new Ponto<>(x * tileSize, y * tileSize), grid, tileSize);

            Food food = new Food(isFoodCircle, grid, tileSize, gridWidth, gridHeight);


            // Criação da visualização do jogo
            GameArenaView gameArenaView = new GameArenaView(grid, snake, food, score, obstaculo);
            ScorePanel scorePanel = new ScorePanel(score);
            GameController controller = new GameController(snake, food, grid, gameArenaView, score, scorePanel, automaticInput, isFoodCircle, frame);

            // Painel principal contendo a área de jogo e a área de score
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BorderLayout());
            mainPanel.add(scorePanel, BorderLayout.NORTH);
            mainPanel.add(gameArenaView, BorderLayout.CENTER);

            // Configuração do frame principal
            frame.add(mainPanel);
            frame.pack();
            frame.setVisible(true);

            // Iniciar o jogo
            frame.addKeyListener(controller);
        });

        // Close the configuration frame
        this.dispose();
    }

    /**
     * Capitalizes the first letter of a given string and makes the rest of the letters lowercase.
     *
     * @param s The input string.
     * @return The capitalized string.
     */
    private String capital(String s) {
        if (s == null || s.isEmpty()) return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }
}
