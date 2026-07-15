# OOPS - Snake Game with Geometric Shapes

A Java Snake Game developed for the **Object-Oriented Programming (OOP)** course (2023/2024).

This project combines the classic Snake game with a small geometry library, allowing the game to use geometric shapes such as circles, squares, rectangles and triangles. The game can be played in **Text Mode** or through a **Graphical User Interface (GUI)**.

---

## Features

- 🎮 Two game modes
  - Text Mode (Command Line)
  - GUI Mode (Java Swing)
- 🐍 Classic Snake gameplay
- 🍎 Food can be displayed as:
  - Circle
  - Square
- 🚧 Static or dynamic obstacles
- 📏 Configurable grid size
- 🐍 Multiple snake head sizes
- 🏆 Highscore system
- 🔄 Automatic or manual movement
- 📐 Custom geometric shapes library

---

## Technologies

- Java
- Java Swing
- JUnit
- Object-Oriented Programming
- MVC Design Pattern
- Strategy Design Pattern

---

## Project Structure

```
src/
│
├── SnakeGame/
│   ├── Game logic
│   ├── Snake
│   ├── Food
│   ├── Obstacles
│   ├── Score
│   └── Highscores
│
├── FigurasGeo/
│   ├── Circle
│   ├── Square
│   ├── Rectangle
│   ├── Triangle
│   ├── Polygon
│   ├── Point
│   └── Geometry interfaces
│
└── GUI/
    ├── GameArenaView
    ├── GameController
    ├── GameConfigFrame
    ├── GameOverFrame
    └── ScorePanel
```

---

## How to Run

1. Clone the repository.

```bash
git clone https://github.com/your-username/your-repository.git
```

2. Open the project in **IntelliJ IDEA** (recommended).

3. Build the project.

4. Run:

```
Main.java
```

5. Choose the game mode:

- **1** → GUI Mode
- **2** → Text Mode

---

## Controls

### GUI Mode

| Key | Action |
|------|--------|
| ↑ | Move Up |
| ↓ | Move Down |
| ← | Move Left |
| → | Move Right |

### Text Mode

| Key | Action |
|------|--------|
| W | Move Up |
| S | Move Down |
| A | Move Left |
| D | Move Right |

Press **Enter** after each command in Text Mode.

---

## Game Configuration

Before starting the game you can configure:

- Grid size
- Snake size
- Food shape
- Automatic or manual input
- Static or dynamic obstacles
- Rendering mode

---

## Gameplay

The objective is simple:

- Eat food to increase your score.
- Avoid walls.
- Avoid obstacles.
- Avoid hitting your own body.

When the game ends, you can save your score in the Highscore table.

---

## Architecture

The project follows the **Model-View-Controller (MVC)** architecture.

### Model

Contains the game logic:

- Snake
- Food
- Grid
- Obstacles
- Score

### View

Displays the game:

- GameArenaView
- ScorePanel
- GameOverFrame

### Controller

Controls user input and updates the game state.

---

## Design Patterns

### MVC (Model-View-Controller)

Separates the project into:

- Model
- View
- Controller

This makes the project easier to maintain and extend.

### Strategy Pattern

The Strategy pattern is used to choose how food is represented.

The food can be drawn as:

- Circle
- Square

without changing the rest of the game logic.

---

## Geometry Library

The project includes a small geometry package with classes such as:

- Point
- Line
- Line Segment
- Polygon
- Triangle
- Rectangle
- Square
- Circle

These classes implement basic geometric operations used by the game.

---

## Highscores

The game stores the player's name and score.

After each game:

- Enter your name.
- Your score is saved.
- The Top 5 scores are displayed.

---

## Unit Tests

JUnit tests are included to validate different parts of the project.

---

## Known Limitations

- Simple graphical interface.
- Highscores are stored in text files.
- Automatic snake movement is basic.
- Large game boards may reduce performance.
- The current structure could be improved for future game modes.

---

## Authors

Developed by:

- Catarina Machado
- Diogo Luz
- João Morales

Object-Oriented Programming  
University of Algarve (UAlg)
Professor JVO
2023/2024

---

## License

This project was developed for academic purposes.