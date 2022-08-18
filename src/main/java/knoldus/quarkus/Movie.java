package knoldus.quarkus;

public class Movie {

    private Long id;
    private String title;

    /**
     * get Id
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * set Id
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * get title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * set title
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
