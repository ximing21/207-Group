package entity;

public class Project {
    private String name;
    private Boolean is_favorite;


    private final String id;

    public Project(String name, Boolean is_favorite, String id) {
        this.name = name;
        this.is_favorite = is_favorite;
        this.id = id;
    }

    public static ProjectBuilder builder() {
        return new ProjectBuilder();
    }

    public static  class ProjectBuilder {
        private String name;
        private Boolean is_favorite;
        private String id;

        ProjectBuilder(){}

        public ProjectBuilder name(String name) {
            this.name = name;
            return this;
        }


        public ProjectBuilder is_favorite(Boolean is_favorite) {
            this.is_favorite = is_favorite;
            return this;
        }

        public ProjectBuilder id(String id) {
            this.id = id;
            return this;
        }

        public Project build() {
            return new Project(name, is_favorite, id);}
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }


}