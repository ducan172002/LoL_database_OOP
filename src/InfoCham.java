public class InfoCham {
    private int id;
    private String champion, classes, position;

    public InfoCham() {
    }
    public InfoCham(int id, String champion, String classes, String position) {
        this.id = id;
        this.champion = champion;
        this.classes = classes;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChampion() {
        return champion;
    }

    public void setChampion(String champion) {
        this.champion = champion;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    void display()
    {
        System.out.println("Champion: " + champion);
        System.out.println("Classes: " + classes);
        System.out.println("Position: " + position);
    }
}
