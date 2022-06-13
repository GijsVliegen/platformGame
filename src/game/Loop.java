package game;

import drawings.*;
import graphics.Renderer;
import resource.ImageResource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Loop {
    Square background = null;
    public List<Drawing> drawings = new ArrayList<>();
    public List<Image> images = new ArrayList<>();
    public List<Button> buttons = new ArrayList<>();
    public List<Text> texts = new ArrayList<>();

    public void updateScreen(float previousHeight, float newHeight) {


    }
    public abstract void loop();


}
