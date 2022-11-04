public class UserRequest {

    private String name;
    private String job;


    public UserRequest(String name, String job){
        this.name = name;
        this.job = job;
    }

    public String getJob() {
        return job;
    }

    public String getName() {
        return name;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setName(String name) {
        this.name = name;
    }
}
