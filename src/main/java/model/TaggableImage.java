package model;

import java.util.ArrayList;
import java.util.List;

public class TaggableImage {

    private String path;
    private List<String> tags;
    private List<String> appliedFilters;

    public TaggableImage() {}

    public TaggableImage(String path){
        this.path = path;
        this.tags = new ArrayList<>();
        this.appliedFilters = new ArrayList<>();
    }

    public void addTag(String tag){
        tags.add(tag);
    }

    public List<String> getTags() {
        return tags;
    }

    // ✅ AJOUT SETTER
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path){
        this.path = path;
    }

    public void addAppliedFilter(String filterName) {
        appliedFilters.add(filterName);
    }

    public List<String> getAppliedFilters() {
        return appliedFilters;
    }


    public void setAppliedFilters(List<String> appliedFilters) {
        this.appliedFilters = appliedFilters;
    }
}
