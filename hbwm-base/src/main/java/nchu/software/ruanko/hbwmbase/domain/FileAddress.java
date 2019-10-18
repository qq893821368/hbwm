package nchu.software.ruanko.hbwmbase.domain;

public class FileAddress {

    private String path = "D:\\IdeaSpace\\hbwm\\hbwm-ui\\src\\main\\resources\\static";
    public String music_file(String filename)
    {
        return path+"\\music\\" + filename;
    }
    public String picture(String filename)
    {
        return path+"\\picture\\"+filename;
    }
    public String lrc(String filename)
    {
       return path+"\\lrc\\"+filename;
    }

}
