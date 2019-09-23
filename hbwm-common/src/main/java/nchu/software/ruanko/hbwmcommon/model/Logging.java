package nchu.software.ruanko.hbwmcommon.model;

public class Logging {
    private String type;
    private String message;

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString(){
        return "【"+type+"】"+message;
    }
}
