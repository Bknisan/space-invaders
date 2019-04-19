package fileraeders;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class ColorsParser {
    private Map<String, Color> colorMap = new HashMap<>();
    // parse color definition and return the specified color.

    /**
     * @param s string of wanted color.
     * @return real color out of the string.
     */
    public java.awt.Color colorFromString(String s) {
        if (s.startsWith("color(RGB")) {
            s = s.substring(s.indexOf("R"));
            int r = Integer.parseInt(s.substring(4, s.indexOf(",")));
            String subS = s.substring(s.indexOf(",") + 1, s.indexOf(")"));
            int g = Integer.parseInt(subS.substring(0, subS.indexOf(",")));
            String subSubS = subS.substring(subS.indexOf(",") + 1);
            int b = Integer.parseInt(subSubS);
            return new Color(r, g, b);
        } else {
            s = s.substring(6, s.length() - 1);
            try {
                s = s.toLowerCase();
                return this.colorMap.get(s);
            } catch (Exception ignored) {
                System.out.println("something wrong");
            }
        }
        return null;
    }

    /**
     *
     */
    public ColorsParser() {
        this.colorMap.put("black", Color.black);
        this.colorMap.put("white", Color.white);
        this.colorMap.put("yellow", Color.yellow);
        this.colorMap.put("blue", Color.blue);
        this.colorMap.put("red", Color.red);
        this.colorMap.put("orange", Color.orange);
        this.colorMap.put("gray", Color.gray);
        this.colorMap.put("cyan", Color.cyan);
        this.colorMap.put("green", Color.green);
        this.colorMap.put("pink", Color.pink);
        this.colorMap.put("magenta", Color.magenta);
        this.colorMap.put("darkgray", Color.darkGray);
        this.colorMap.put("lightgray", Color.lightGray);
    }
}
