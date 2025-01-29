package in.umesh.dto;

public class DashboardResponse {

    private Integer totalEnqs; // Corrected typo from 'toatlEnqus' to 'totalEnqs'
    private Integer openEnqs;
    private Integer enrolledEnqs;
    private Integer lostEnqs;

    // Getter and Setter for totalEnqs
    public Integer getTotalEnqs() {
        return totalEnqs;
    }

    public void setTotalEnqs(Integer totalEnqs) {
        this.totalEnqs = totalEnqs;
    }

    // Getter and Setter for openEnqs
    public Integer getOpenEnqs() {
        return openEnqs;
    }

    public void setOpenEnqs(Integer openEnqs) {
        this.openEnqs = openEnqs;
    }

    // Getter and Setter for enrolledEnqs
    public Integer getEnrolledEnqs() {
        return enrolledEnqs;
    }

    public void setEnrolledEnqs(Integer enrolledEnqs) {
        this.enrolledEnqs = enrolledEnqs;
    }

    // Getter and Setter for lostEnqs
    public Integer getLostEnqs() {
        return lostEnqs;
    }

    public void setLostEnqs(Integer lostEnqs) {
        this.lostEnqs = lostEnqs;
    }
}
