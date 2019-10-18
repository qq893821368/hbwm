package nchu.software.ruanko.hbwmbl.impl;


import nchu.software.ruanko.hbwmcommon.model.Singer;
import nchu.software.ruanko.hbwmda.repository.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SingerImpl {
    @Autowired
    SingerRepository singerRepository;
    public List<Singer> findsinger(){
        List<Singer> list = null;
        list = singerRepository.searchsinger();

        return list;
    }
    public boolean addsinger(String singer_name,String intro,String exp,String singer_pic,String nation){
        boolean list = false;
        list = singerRepository.insertSinger(singer_pic,singer_name,nation,intro,exp);
        return list;
    }
    public boolean modifysinger(String singer_pic,int singer_id,String name,String nation,String introduction,String experience){
        boolean list = false;
        list = singerRepository.update(singer_pic,singer_id,name,nation,introduction,experience);
        return list;
    }
    public boolean delete(String singer_id){
        boolean list = false;
        int sid = Integer.parseInt(singer_id);
        System.out.println("sid");
        list = singerRepository.delete(sid);
        return list;

    }
    public Singer findsingerbyid(int singer_id){
        return singerRepository.searchsingerbyid(singer_id);
    }
}
