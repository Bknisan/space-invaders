package fileraeders;

import factorisandas.BlockCreator;
import factorisandas.BlockDecoretor;
import factorisandas.BlocksFromSymbolsFactory;
import factorisandas.SimpleBlockCreator;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class BlockDefinitionsReader {
    private static String auto = "default";
    private static String sdef = "sdef";
    private static String bdef = "bdef";
    private Map<String, String> defaults;
    private BlocksFromSymbolsFactory myFactory;

    /**
     *
     */
    public BlockDefinitionsReader() {
        this.defaults = new HashMap<>();
        this.myFactory = new BlocksFromSymbolsFactory();
    }

    /**
     * @param textPath path to block definitions.
     */
    public void blockDefReader(String textPath) {
        LineNumberReader blockFile = null;
        String currentLine;
        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(textPath);
            blockFile = new LineNumberReader(new InputStreamReader(is));
            while ((currentLine = blockFile.readLine()) != null) {
                if (!currentLine.startsWith("#")) {
                    if (currentLine.startsWith(auto)) {
                        String settings = currentLine.substring(currentLine.indexOf(" ") + 1);
                        this.extractDefaults(settings);
                    } else if (currentLine.startsWith(bdef)) {
                        String settings = currentLine.substring(currentLine.indexOf(" ") + 1);
                        Map<String, String> currentDef = this.extractSpecials(settings);
                        Map<String, String> allProperties = new HashMap<>(this.defaults);
                        allProperties.putAll(currentDef);
                        BlockCreator anotherOne = new SimpleBlockCreator();
                        String blockSymbol = currentDef.get("symbol");
                        currentDef.remove("symbol");
                        allProperties.remove("symbol");
                        for (Object key : allProperties.keySet()) {
                            anotherOne = BlockDecoretor.addNewSet(anotherOne, (String) key,
                                    allProperties.get(key));
                        }
                        this.myFactory.addBlockSymbol(blockSymbol, anotherOne);

                    } else if (currentLine.startsWith(sdef)) {
                        String settings = currentLine.substring(currentLine.indexOf(" ") + 1);
                        Map<String, String> spacers = this.extractSpecials(settings);
                        this.myFactory.addSpaceSymbol(spacers.get("symbol"),
                                Integer.parseInt(settings.substring(settings.lastIndexOf(":") + 1)));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("wrong input");
        }
    }

    /**
     * @param line defaults line.
     */
    public void extractDefaults(String line) {
        String[] definitions = line.split(" ");
        for (int i = 0; i < definitions.length; i++) {
            String[] value = definitions[i].split(":");
            this.defaults.put(value[0], value[1]);
        }
    }

    /**
     * @param line special settings line
     * @return map with values.
     */
    public Map<String, String> extractSpecials(String line) {
        Map<String, String> settings = new HashMap<>();
        String[] definitions = line.split(" ");
        for (String definition : definitions) {
            String[] value = definition.split(":");
            settings.put(value[0], value[1]);
        }
        return settings;
    }

    /**
     * @return new block factory.
     */
    public BlocksFromSymbolsFactory getMyFactory() {
        return this.myFactory;
    }
}
