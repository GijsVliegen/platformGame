package game.player;

import drawings.Background;
import drawings.Square;
import game.Collision;
import game.Loop;
import game.OverlayLoop;
import graphics.Renderer;
import resource.ImageResource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameLoop extends Loop {
    public static float leftPos = -10;
    public static float lowPos = 0;
    static Background background = null;
    static float gravity = .02f;
    static float jumpSpeedY = 0.3f;
    public static List<Square> winSquares = new ArrayList<>();
    static Square[] allPossibleWinSquares = new Square[10];
    public static int winSqrsLeft;
    public OverlayLoop overlayLoop;

    public GameLoop(){
        allPossibleWinSquares[0] = new Square(-9.5f, 1f, 0.3f, 0.3f);
        allPossibleWinSquares[1] = new Square(9.2f, 1f, 0.3f, 0.3f);
        allPossibleWinSquares[2] = new Square(-2f, 2.5f, 0.3f, 0.3f);
        allPossibleWinSquares[3] = new Square(3.7f, 3f, 0.3f, 0.3f);
        allPossibleWinSquares[4] = new Square(-4f, 3f, 0.3f, 0.3f);
        allPossibleWinSquares[5] = new Square(-.15f, 6f, 0.3f, 0.3f);
        allPossibleWinSquares[6] = new Square(-.15f, 4f, 0.3f, 0.3f);
        allPossibleWinSquares[7] = new Square(-6f, 5.5f, 0.3f, 0.3f);
        allPossibleWinSquares[8] = new Square(5.7f, 5.5f, 0.3f, 0.3f);
        allPossibleWinSquares[9] = new Square(1.7f, 2.5f, 0.3f, 0.3f);
        ImageResource backgroundRes = null;
        try{
            backgroundRes = new ImageResource("hackerman Background.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (backgroundRes == null){
            System.out.println("er is een image null in GameLoop");
        }
        background = new Background(backgroundRes);
        Collision.initCol();
        PlayerMovement.initPlayer();
        overlayLoop = new OverlayLoop();
        setWinSquares();
    }

    public static void setWinSquares(){
        Random random = new Random();
        List<Integer> ints = new ArrayList<>();
        winSquares = new ArrayList<>();
        while(ints.size() < 5){
            int r = random.nextInt(10);
            if (!ints.contains(r)){
                ints.add(r);
            }
        }
        for(int i = 0; i < 5; i ++){
            Square sq = allPossibleWinSquares[ints.get(i)];
            sq.setColor(0.2f, 0.2f,0.2f, 1f);
            sq.setCollided(false);
            winSquares.add(sq);
        }
        winSqrsLeft = 5;
    }

    public static void reset(){
        setWinSquares();
        PlayerMovement.reset();
    }
    private static void checkWin(){
        if (winSqrsLeft == 0){
            Renderer.switchLoop("won");
            PlayerMovement.XStill();
        }
    }
    protected static void removeWinSq(Square winSq){
        if (!winSq.hasCollided()){
            winSq.setCollided(true);
            winSqrsLeft--;
        }
    }

    @Override
    public void updateScreen(float previousHeight, float newHeight) {
        //hier moet niks gebeuren denk ik voorlopig
    }

    public void loop(){
        checkWin();
        Renderer.setLowerLeftPos(lowPos, leftPos);
        background.drawToScreen();

        Collision.drawCol();

        for(Square sq: winSquares){
            if (!sq.hasCollided())
                sq.drawToScreen();
        }
        PlayerMovement.drawPosition();
        //PlayerDrawing.drawDrawings();
        if (PlayerMovement.getState().equals("jump")){
            PlayerMovement.setSpeedY(jumpSpeedY);
            PlayerMovement.setState("airborn");
        }
        PlayerMovement.addGravity(gravity);
        PlayerMovement.updatePosition();
        overlayLoop.loop();

    }
}
