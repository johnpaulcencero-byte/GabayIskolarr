// ================= Scholarship.java =================

public class Scholarship {

    private String name;
    private String deadline;
    private String status;

    public Scholarship(String name,
                       String deadline,
                       String status) {

        this.name = name;
        this.deadline = deadline;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}