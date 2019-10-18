package nchu.software.ruanko.hbwmcommon.model;

import java.util.Date;

public class Music {
    private int music_id;
    private int play_volume, download_volume;
    private boolean isVip;
    private int info_id;
    private int singer_id;
    private Date public_date;


    public Music(int music_id, int play_volume, int download_volume, boolean isVip, int info_id, int singer_id, Date public_date){
        this.music_id = music_id;
        this.play_volume = play_volume;
        this.download_volume = download_volume;
        this.isVip = isVip;
        this.info_id = info_id;
        this.singer_id = singer_id;
        this.public_date = public_date;
    }

    public Music(int id, int play, int download){
        music_id = id;
        play_volume = play;
        download_volume = download;
    }


    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }

    public int getInfo_id() {
        return info_id;
    }

    public void setInfo_id(int info_id) {
        this.info_id = info_id;
    }

    public int getSinger_id() {
        return singer_id;
    }

    public void setSinger_id(int singer_id) {
        this.singer_id = singer_id;
    }

    public Date getPublic_date() {
        return public_date;
    }

    public void setPublic_date(Date public_date) {
        this.public_date = public_date;
    }

    public int getMusic_id() {
        return music_id;
    }

    public void setMusic_id(int music_id) {
        this.music_id = music_id;
    }

    public int getPlay_volume() {
        return play_volume;
    }

    public void setPlay_volume(int play_volume) {
        this.play_volume = play_volume;
    }

    public int getDownload_volume() {
        return download_volume;
    }

    public void setDownload_volume(int download_volume) {
        this.download_volume = download_volume;
    }
}
