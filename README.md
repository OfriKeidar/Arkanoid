# Java Arkanoid Desktop Game: 

A custom implementation of the classic arcade game **Breakout / Arkanoid**, built from scratch in Java.
This project demonstrates advanced Object-Oriented Programming principles, focusing on collision detection physics and the **Observer Design Pattern**.

## 🚀 Unique Game Mechanics

Unlike the traditional Arkanoid, this version introduces a strategic color-switching mechanic:

* **Color Matching Rule:** A block is destroyed **only** if its color is *different* from the ball's color.
* **The "Chameleon" Ball:** Upon a successful hit (different colors), the ball **adopts the color** of the destroyed block.
* **Strategy:** If the ball matches the block's color, it will simply bounce off without destroying the block. You must plan your shots to alternate colors effectively.

## 🎮 How to Play

### Objective
Clear all block rows from the screen without losing all your lives (balls).

### Controls
| Key | Action |
| :--- | :--- |
| **Left Arrow** | Move Paddle Left |
| **Right Arrow** | Move Paddle Right |

* **Paddle Wrap-around:** The paddle is not bound by the screen edges; moving past the left border makes it reappear on the right (and vice versa).

### Scoring System
* **Block Hit:** +5 points (only if block is removed).
* **Level Cleared:** +100 bonus points.
* **Status Bar:** Displays current score and remaining lives at the top of the screen.

---

## 🛠️ Architecture & Design

The codebase emphasizes clean separation of concerns and loose coupling:

* **Physics Engine:** Custom collision detection logic located in `GameEnvironment` and `Line` classes. It calculates intersection points between the ball's trajectory and game objects.
* **Observer Pattern:** Used extensively for game events.
    * `HitListener` interface allows objects to react to collisions.
    * `BlockRemover`: Handles logic when a block is destroyed.
    * `BallRemover`: Manages life loss when a ball hits the death region.
    * `ScoreTrackingListener`: Updates the score counter upon events.
* **Interfaces:** `Sprite` (for drawing/animating) and `Collidable` (for physical interaction) separate visual representation from game logic.

## 📂 Project Structure

```text
Arkanoid/
├── .idea/                         
├── src/                           
│   ├── game/                      
│   │   ├── Counter.java           
│   │   ├── Game.java              
│   │   └── GameEnvironment.java   
│   ├── geometry/                  
│   │   ├── Line.java              
│   │   ├── Point.java             
│   │   └── Rectangle.java         
│   ├── listeners/                 
│   │   ├── BallRemover.java       
│   │   ├── BlockRemover.java      
│   │   ├── HitListener.java       
│   │   ├── HitNotifier.java       
│   │   ├── ScoreIndicator.java    
│   │   └── ScoreTrackingListener.java
│   ├── sprite/                    
│   │   ├── Ball.java              
│   │   ├── Block.java             
│   │   ├── Collidable.java        
│   │   ├── CollisionInfo.java     
│   │   ├── Paddle.java            
│   │   ├── Sprite.java            
│   │   ├── SpriteCollection.java  
│   │   └── Velocity.java          
│   └── ArkanoidGame.java          
├── .gitignore                     
├── README.md                      
├── biuoop-1.4.jar                 
└── build.xml

