package nchu.software.ruanko.hbwmcommon.model;

public class Singer {
    private int singer_id;
    private String singer_name;
    private String intro;
    private String exp;
    private String singer_pic;
    private String nation;

    public int getSinger_id() {
        return singer_id;
    }

    public void setSinger_id(int singer_id) {
        this.singer_id = singer_id;
    }

    public String getSinger_name() {
        return singer_name;
    }

    public void setSinger_name(String singer_name) {
        this.singer_name = singer_name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getSinger_pic() {
        return singer_pic;
    }

    public void setSinger_pic(String singer_pic) {
        this.singer_pic = singer_pic;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }
}
