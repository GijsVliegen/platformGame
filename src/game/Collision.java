package game;

import drawings.Drawing;
import drawings.Square;
import graphics.Renderer;
import graphics.graphics;

import java.util.ArrayList;
import java.util.List;

public class Collision {

    public static List<Drawing> collisions = new ArrayList<>();

    public static void addCollision(Drawing drawing) {
        collisions.add(drawing);
    }
    public static void initCol() {
        float leb = Renderer.leftBound;
        float lob = Renderer.lowerBound;
        float rb = Renderer.rightBound;
        float up = Renderer.upperBound;
        Drawing leftFloor = new Square(leb, lob, 7, .5f);
        Drawing rightFloor = new Square(rb-7, lob, 7, .5f);

        Drawing leftLowerPlatform = new Square(-4, 2f, 2, 0.2f);
        Drawing middlePlatform = new Square(-1f, 3.5f, 2, 0.2f);
        //Drawing leftWall = new Square(-3.1f, 0f, .2f, 2f);
        Drawing rightLowerPlatform = new Square(2, 2f, 2, .2f);
        Drawing leftUpperPlatform = new Square(-4f, 5f, 2, 0.2f);
        Drawing rightUpperPlatform = new Square(2f, 5f, 2f, 0.2f);
        //Drawing rightWall = new Square (2.9f, 0f, .2f, 2f);
        Drawing roof = new Square(leb - 1, up, (rb - leb) + 2, 1);
        Drawing leftSide = new Square(leb - 1, lob - 1, 1, (up-lob) + 1);
        Drawing rightSide = new Square(rb, lob - 1, 1, (up-lob) + 1);
        roof.setColor(0, 0, 0, 1);
        leftSide.setColor(0, 0, 0, 1);
        rightSide.setColor(0, 0, 0, 1);
        addCollision(leftFloor);
        addCollision(rightFloor);
        addCollision(leftLowerPlatform);
        addCollision(middlePlatform);
        //addCollision(leftWall);
        addCollision(rightLowerPlatform);
        addCollision(leftUpperPlatform);
        addCollision(rightUpperPlatform);
        //addCollision(rightWall);
        addCollision(roof);
        addCollision(leftSide);
        addCollision(rightSide);
    }
    public static void drawCol(){
        graphics.setColor(0, 0, 1, 1);
        for (Drawing drawing : collisions) {
            graphics.setColor(0, 0, 1, 1);
            drawing.drawToScreen();
        }
    }
}

