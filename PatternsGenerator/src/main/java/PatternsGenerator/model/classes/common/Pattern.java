package PatternsGenerator.model.classes.common;

import org.springframework.stereotype.Component;

@Component
public class Pattern {
    private String name;
    private String description;
    private boolean areCommentsIncluded;

    public Pattern() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAreCommentsIncluded() {
        return areCommentsIncluded;
    }

    public void setAreCommentsIncluded(boolean areCommentsIncluded) {
        this.areCommentsIncluded = areCommentsIncluded;
    }
}
