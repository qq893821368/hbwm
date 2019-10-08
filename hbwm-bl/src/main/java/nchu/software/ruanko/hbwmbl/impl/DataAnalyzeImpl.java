package nchu.software.ruanko.hbwmbl.impl;

import nchu.software.ruanko.hbwmcommon.model.Music;
import nchu.software.ruanko.hbwmda.mapper.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@ComponentScan("nchu.software.ruanko.hbwmda")
public class DataAnalyzeImpl {
    @Autowired
    DataMapper mapper;

    /* Create by hjb 2019/9/21
     * Logic Method
     * 添加登录记录并返回登录记录信息
     * 根据给定的用户以及成功与否信息,
     * 返回一个登录信息字符串
     * params: String, Boolean
     * return: String
     * --------------------END
     */
    public String visit(String account, Boolean flag){
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :HH:mm:ss");
        String logging = "User[" + account + "] visit the system at " + dateFormat.format(date) + " and " + (flag?"access":"fail");
        /*
         * 访问数据入库
         */
        return logging;
    }

    /* Create by hjb 2019/10/02
     * Util Method
     * 计算两首音乐的相似度
     * 根据给定的两首音乐,
     * 以及给定的参数列表,
     * 对音乐的各参数进行计算距离
     * 返回这个距离(欧式距离)
     * params: Music, Music, String[]
     * return: double
     * --------------------END
     */
    public static double musicSimilarity(Music musicA, Music musicB, String[] args) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        double dis = 0;
        for(String arg: args){
            String argName = arg.toLowerCase();
            String getter = "get" + argName.substring(0, 1).toUpperCase() + argName.substring(1);
            Method method = Music.class.getMethod(getter, new Class[] {});
            double differ = (int)method.invoke(musicA) - (int)method.invoke(musicB);
            dis += differ * differ;
        }
        dis = (int)Math.sqrt(dis);
        return dis;
    }

    /* Create by hjb 2019/10/08
     * Util Method
     * 计算多首音乐的相似度矩阵
     * 根据给定的音乐列表,
     * 以及给定的参数列表,
     * 对音乐的各参数进行计算距离
     * 返回距离矩阵(欧式距离)
     * params: Music[], String[]
     * return: double[][]
     * --------------------END
     */
    public static double[][] musicSimilarityMatrix(Music[] musics, String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        double[][] matrix = new double[musics.length+1][musics.length+1];

        for(double[] line :matrix)                              //初始化矩阵, 全0
            for(double elem :line)
                elem = 0;

        for(int i=1; i<matrix.length; i++)                      //给矩阵添加id信息
            matrix[0][i] = matrix[i][0] = musics[i-1].getId();

        for(int i=1; i<matrix.length; i++)                      //从第2行第2列开始为正式数据, 即matrix[1][1]开始
            for(int j=i+1; j<matrix.length; j++)
                matrix[i][j] = matrix[j][i] = musicSimilarity(musics[i-1], musics[j-1], args);
        /*System.out.print("-------------------------------------\n");
        for(double[] line :matrix){
            System.out.print("[");
            for (double elem :line)
                System.out.print(elem+" ");
            System.out.print("]\n");
        }
        System.out.println("-------------------------------------\n");*/
        return matrix;
    }

    /* Create by hjb 2019/10/08
     * Util Method
     * 推荐相似歌曲
     * 给定一首歌曲, 一个距离矩阵,
     * 以及n值, 推荐与这首歌曲距离最近的前n首,
     * 返回这n首歌曲的id列表
     * params: Music, double[][], int
     * return: int[]
     * --------------------END
     */
    public static int[] recommend(Music music, double[][] matrix, int k) throws Exception {
        double[] similarities = null;
        int[] top_k = new int[k];

        for(int i=1; i<matrix.length; i++)                                          //抽出歌曲对应的距离向量
            if(matrix[i][0] == music.getId())
                similarities = matrix[i];

        if(similarities == null) throw new Exception("matrix can't match music");   //若没有对应向量则抛出异常

        for(k=0; k<top_k.length; k++){                                              //推荐k个最近的目标
            double min = 0;
            double max = 0;
            int index = 0;
            for(int i=1; i<similarities.length; i++)                                //寻找最大值
                if(similarities[i] > max){
                    max = similarities[i];
                    index = i;
                }
            min = max;                                                              //将最小值置为最大的位置以便寻找真正的最小值

            for(int i=1; i<similarities.length; i++) {                              //在距离向量中寻找最小值及其定位, 0号下标为自己的id
                if(matrix[0][i] == music.getId())
                    continue;
                if (min > similarities[i]) {
                    min = similarities[i];
                    index = i;
                }
            }
            top_k[k] = (int)matrix[0][index];                                       //根据定位记录id
            similarities[index] = max;                                              //将已记录的id下的距离置max, 方便寻找次一级相似
        }

        return top_k;
    }

    public static void test() throws Exception {
        Music a = new Music(1, 200, 157);
        Music b = new Music(4, 800, 736);
        Music c = new Music(8, 53600, 153);
        Music d = new Music(2022, 2200, 2187);
        Music e = new Music(3, 357, 164);
        Music f = new Music(767, 4534, 2435);
        Music[] musics = {a, b, c, d, e, f};

        int[] top_k = recommend(a, musicSimilarityMatrix(musics, new String[]{"p1", "p2"}), 3);
        for(Music m : musics)
            if(m.getId() == top_k[0])
                System.out.println( "get music[@id-"+top_k[0]+"@p1-"+m.getP1()+"@p2-"+m.getP2()+"]\n"+
                                    "get music[@id-"+top_k[1]+"@p1-"+m.getP1()+"@p2-"+m.getP2()+"]\n"+
                                    "get music[@id-"+top_k[2]+"@p1-"+m.getP1()+"@p2-"+m.getP2()+"]\n");
    }
}
