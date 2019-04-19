package fileraeders;
/**
 *
 */
public class LevelSet {
    private String setKey;
    private String definitionsPath;
    private String description;

    /**
     * constructor.
     */
    public LevelSet() {
        this.definitionsPath = null;
        this.setKey = null;
        this.description = null;

    }

    /**
     * @return level press key.
     */
    public String getSetKey() {
        return setKey;
    }

    /**
     * @param setKeyMy set level press key.
     */
    public void setSetKey(String setKeyMy) {
        this.setKey = setKeyMy;
    }

    /**
     * @return the path to the game parameters.
     */
    public String getDefinitionsPath() {
        return definitionsPath;
    }

    /**
     * @param definitionsPathMy set parameters.
     */
    public void setDefinitionsPath(String definitionsPathMy) {
        this.definitionsPath = definitionsPathMy;
    }

    /**
     *
     * @return set description.
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param descriptionMy level set description.
     */
    public void setDescription(String descriptionMy) {
        this.description = descriptionMy;
    }
}
