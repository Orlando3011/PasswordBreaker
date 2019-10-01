package PatternsGenerator.model.classes.common;

import org.springframework.stereotype.Component;

@Component
public class Pattern {
    private String name;
    private int version;
    private boolean areCommentsIncluded;

    public Pattern() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getAreCommentsIncluded() {
        return areCommentsIncluded;
    }

    public void setAreCommentsIncluded(boolean areCommentsIncluded) {
        this.areCommentsIncluded = areCommentsIncluded;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
