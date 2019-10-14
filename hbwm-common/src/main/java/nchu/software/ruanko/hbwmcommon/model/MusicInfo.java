package nchu.software.ruanko.hbwmcommon.model;

public class MusicInfo {
    private int info_id;
    private String music_name;
    private String singer;
    private String album;
    private int druation;
    private String music_file;
    private String lrc_file;
    private String pic_file;

    public void setInfo_id(int info_id) {
        this.info_id = info_id;
    }

    public void setMusic_name(String music_name) {
        this.music_name = music_name;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setDruation(int druation) {
        this.druation = druation;
    }

    public void setMusic_file(String music_file) {
        this.music_file = music_file;
    }

    public void setLrc_file(String lrc_file) {
        this.lrc_file = lrc_file;
    }

    public int getInfo_id() {
        return info_id;
    }

    public String getMusic_name() {
        return music_name;
    }

    public String getSinger() {
        return singer;
    }

    public String getAlbum() {
        return album;
    }

    public int getDruation() {
        return druation;
    }

    public String getMusic_file() {
        return music_file;
    }

    public String getLrc_file() {
        return lrc_file;
    }

    public String getPic_file() {
        return pic_file;
    }

    public void setPic_file(String pic_file) {
        this.pic_file = pic_file;
    }

    public MusicInfo() {
    }

    public MusicInfo(int info_id, String music_name, String singer, String album, int druation, String music_file, String lrc_file, String pic_file) {
        this.info_id = info_id;
        this.music_name = music_name;
        this.singer = singer;
        this.album = album;
        this.druation = druation;
        this.music_file = music_file;
        this.lrc_file = lrc_file;
        this.pic_file = pic_file;
    }

    public MusicInfo(String music_name, String singer, String music_file, String lrc_file, String pic_file) {
        this.music_name = music_name;
        this.singer = singer;
        this.music_file = music_file;
        this.lrc_file = lrc_file;
        this.pic_file = pic_file;
    }
}
