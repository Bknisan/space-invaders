package mentasks;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gamemechanisim.AnimationRunner;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @param <T>
 */
public class MenuAnimation<T> implements Menu {
    private String subject;
    private KeyboardSensor gameSensor;
    private AnimationRunner runMyAn;
    private List<Selection> mySelections;
    private Object status;
    private boolean isAlreadyPressed = true;
    private Map<String, Menu> subMenus;

    /**
     * constructor.
     *
     * @param title      what is the menu about.
     * @param myKeyBoard the key board sensor.
     * @param myRunner   the animation runner.
     */
    public MenuAnimation(String title, KeyboardSensor myKeyBoard, AnimationRunner myRunner) {
        this.subject = title;
        this.gameSensor = myKeyBoard;
        this.runMyAn = myRunner;
        this.mySelections = new ArrayList<>();
        this.subMenus = new TreeMap<>();
        this.status = null;
    }

    /**
     * @param key       press the key in order to continue.
     * @param message   what message to print.
     * @param returnVal what is the value returned.
     */
    @Override
    public void addSelection(String key, String message, Object returnVal) {
        this.mySelections.add(new Selection(key, message, returnVal));
    }

    /**
     * @return the animation status.
     */
    @Override
    public Object getStatus() {
        return this.status;
    }

    /**
     * @param key     stop when this key is pressed.
     * @param message the message yo print.
     * @param subMenu wanted seb menu.
     */
    @Override
    public void addSubMenu(String key, String message, Menu subMenu) {
        this.mySelections.add(new Selection(key, message, subMenu));
        this.subMenus.put(key, subMenu);

    }

    /**
     *
     */
    public void beginAgain() {
        this.status = null;
        this.isAlreadyPressed = true;
    }

    /**
     * @param d  the game surface.
     * @param dt how long since last call.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.lightGray);
        d.drawText(350, 70, this.subject, 35);
        for (int i = 0; i < this.mySelections.size(); i++) {
            d.drawText(100, 150 + i * 50,
                    this.mySelections.get(i).getPrintedLine() + "  (" + this.mySelections.get(i).getStopKey() + ")",
                    30);
        }
        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("bigcroc.jpg");
            BufferedImage menu = ImageIO.read(is);
            d.drawImage(300, 300, menu);
        } catch (Exception e) {
            System.out.println("couldnt load the image");
        }
        for (Selection mySelection : this.mySelections) {
            if (this.gameSensor.isPressed(mySelection.getStopKey()) && this.isAlreadyPressed) {
                if (this.subMenus.containsKey(mySelection.getStopKey())) {
                    Menu subMenu = this.subMenus.get(mySelection.getStopKey());
                    this.runMyAn.run(subMenu);
                    this.status = subMenu.getStatus();
                    subMenu.beginAgain();
                    break;
                } else {
                    this.status = mySelection.getWhatVal();
                    break;
                }
            }
        }

    }

    /**
     * @return true of false if the animation should stop.
     */
    @Override
    public boolean shouldStop() {
        return (this.status != null);
    }


}
