package entity;

import java.sql.DriverPropertyInfo;

public class Project {
    private String name;
    private String color;
    private Boolean is_favorite;

    public Project(String name, String color, Boolean is_favorite) {
        this.name = name;
        this.color = color;
        this.is_favorite = is_favorite;
    }

    public static ProjectBuilder builder() {
        return new ProjectBuilder();
    }

    public String getName() {
        return this.name;
    }

    public static  class ProjectBuilder {
        private String name;
        private String color;
        private Boolean is_favorite;

        public ProjectBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProjectBuilder color(String color) {
            this.color = color;
            return this;
        }

        public ProjectBuilder is_favorite(Boolean is_favorite) {
            this.is_favorite = is_favorite;
            return this;
        }

        public Project build() {
            return new Project(name, color, is_favorite);
        }
    }
}
