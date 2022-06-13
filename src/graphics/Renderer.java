package graphics;

import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.util.FPSAnimator;
import game.*;
import game.player.Audio;
import game.player.GameLoop;
import game.player.PlayerMovement;
import input.KeyBoard;
import input.Mouse;
import resource.TextMaker;

public class Renderer {
    private static GLWindow window = null;

    public static int windowHeight = 360;
    public static int windowWidth = 640;

    //public static float[][] speed = new float[5][2];
    //window opdelen in 10 kolommen
    //if unitsWide is increased: looks like it zooms out
    public static float unitsWide = 10;
    public static float unitsTall = 0;
    public static float leftBound = -10;
    public static float lowerBound = 0;
    public static float rightBound = 10;
    public static float upperBound = 20;
    public static float leftPos = -10;
    public static float lowPos = 0;
    public static float previousUnitsTall = 0;
    public static float currentUnitsTall = 0;
    public static Loop currentLoop = null;
    private static GLProfile profile;
    public static BeginLoop beginLoop;
    public static GameLoop gameLoop;
    public static SettingsLoop settingsLoop;
    public static WonLoop wonLoop;
    public static LostLoop lostLoop;
    public static OverlayLoop overlayLoop;
    public static String state = "begin";
    public static void init() {

        /*for (int i =0 ; i< 5; i++){
            speed[i] = new float[]{0, 0};
        }*/
        GLProfile.initSingleton();
        profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        window = GLWindow.create(capabilities);
        window.setSize(windowWidth, windowHeight);
        window.setResizable(true);
        //als dit wordt gedaan is het onmogelijk het terug af te sluiten
        //window.setFullscreen(true);
        window.setVisible(true);
        window.setTitle("woensel");

        //zorgen dat de muis wordt geregistreerd op window
        window.requestFocus();
        unitsTall = unitsWide/window.getWidth()*window.getHeight();


        //text initialiseren voor de loops, want daarin wordt al gebruik gemaakt van de text classe
        TextMaker.init();
        //audio initialiseren
        Audio.audioInit();
        //de verschillende loops
        beginLoop = new BeginLoop();
        gameLoop = new GameLoop();
        settingsLoop = new SettingsLoop();
        wonLoop = new WonLoop();
        lostLoop = new LostLoop();
        overlayLoop = new OverlayLoop();
        currentLoop = beginLoop;

        //om de windows te kunnen updaten
        previousUnitsTall = Renderer.getUnitsTall();
        currentUnitsTall = Renderer.getUnitsTall();


        FPSAnimator animator = new FPSAnimator(window, 30);
        animator.start();
        window.addGLEventListener(new EventListener());
        window.addMouseListener(new Mouse());
        window.addKeyListener(new KeyBoard());

    }
    public static void drawCurrentLoop(GL2 gl){
        gl.glClearColor(0, 0,0 , 1);
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        currentLoop.loop();
    }
    public static void switchLoop(String newLoop){

        state = newLoop;
        if (newLoop.equals("game")){
            currentLoop = gameLoop;
        }
        else if (newLoop.equals("begin")){
            currentLoop = beginLoop;
        }
        if (newLoop.equals("settings")){
            currentLoop = settingsLoop;
        }
        if (newLoop.equals("won")){
            currentLoop = wonLoop;
        }
        if (newLoop.equals("lost")){
            currentLoop = lostLoop;
        }
        updateCurrentLoop(); //als er ooit een screen adjustment is geweest,
        //dan is die nog niet doorgevoerd op de nieuwe loop
    }
    public static void setLowerLeftPos(float lower, float left){
        lowPos = lower;
        leftPos = left;
        EventListener.updateScreenDisplacement();
    }
    public static int getWindowWidth(){
        return window.getWidth();
    }
    public static int getWindowHeight(){
        return window.getHeight();
    }
    public static float getUnitsTall() {return Renderer.getWindowHeight() / (Renderer.getWindowWidth()/Renderer.unitsWide);}
    public static void main(String[] args){// throws IOException {
        //ImageToMatrix.init();
        init();
    }
    public static void updateCurrentLoop(){
        previousUnitsTall = currentUnitsTall;
        currentUnitsTall = getUnitsTall();
        currentLoop.updateScreen(previousUnitsTall, currentUnitsTall);

    }
    public static GLProfile getProfile() {
        return profile;
    }
    public static void adjustScreenToNewHeight(float newHeight){
        window.setSize((int)(newHeight*unitsWide/unitsTall), (int)newHeight);
    }
}
