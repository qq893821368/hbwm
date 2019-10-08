package nchu.software.ruanko.hbwmcommon.model;

public class Music {
    int id;
    int p1;
    int p2;

    public Music(){
        this(1,1, 1);
    }
    public Music(int id, int p1, int p2){
        this.id = id;
        this.p1 = p1;
        this.p2 = p2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getP1() {
        return p1;
    }

    public void setP1(int p1) {
        this.p1 = p1;
    }

    public int getP2() {
        return p2;
    }

    public void setP2(int p2) {
        this.p2 = p2;
    }
}
