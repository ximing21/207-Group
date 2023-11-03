package entity;

import java.sql.DriverPropertyInfo;

public class Project {
    private String name;
    private String color;
    private Boolean is_favourite;

    public Project(String name, String color, Boolean is_favourite) {
        this.name = name;
        this.color = color;
        this.is_favourite = is_favourite;
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
        private Boolean is_favourite;

        public ProjectBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProjectBuilder color(String color) {
            this.color = color;
            return this;
        }

        public ProjectBuilder is_favourite(Boolean is_favourite) {
            this.is_favourite = is_favourite;
            return this;
        }

        public Project build() {
            return new Project(name, color, is_favourite);
        }
    }
}
