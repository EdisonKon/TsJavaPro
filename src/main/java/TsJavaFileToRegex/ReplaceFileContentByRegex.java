package TsJavaFileToRegex;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author dekai.kong
 * @difficult
 * @create 2020-02-07 09:53
 * @from [\u4E00-\u9FA5]+ 中文匹配
 **/
public class ReplaceFileContentByRegex {
    Map<String,String> smap = new HashMap();
    Map<String,String> nomap = new HashMap();
    public ReplaceFileContentByRegex() {
    }

    public Map<String,List<String>> getFileList(String strPath) {
        Map<String,List<String>> mapKv = new HashMap<>();
        List<String> needFilelist = new ArrayList<>();
        List<String> notFilelist = new ArrayList<>();
        File dir = new File(strPath);
        // 该文件目录下文件全部放入数组
        File[] files = dir.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String strFileName = files[i].getAbsolutePath();
                String fileName = files[i].getName();
                if (files[i].isDirectory()) {
                    //遍历子文件夹里面的东西
                    Map<String,List<String>> mapKvx = getFileList(files[i].getAbsolutePath());
                    needFilelist.addAll(mapKvx.get("need"));
                    notFilelist.addAll(mapKvx.get("not"));
                } else if (fileName.endsWith("jsp")||fileName.endsWith("js")) {
                    // 以***结尾的文件
                    needFilelist.add(strFileName);
                } else {
                    notFilelist.add(strFileName);
                    continue;
                }
            }
        }
        mapKv.put("need",needFilelist);
        mapKv.put("not",notFilelist);
    return mapKv;
  }

    /**
     * 替换文本文件中的 非法字符串
     * @param path
     * @throws IOException
     */
    public void replacTextContent(String path,Map<String,String> replaceMap) throws IOException {
        // 读
        File file = new File(path);
        FileReader in = new FileReader(file);
        BufferedReader bufIn = new BufferedReader(in);
        // 内存流, 作为临时流
        CharArrayWriter  tempStream = new CharArrayWriter();
        // 替换
        String line = null;
        while ( (line = bufIn.readLine()) != null) {
            // 替换每行中, 符合条件的字符串
            for (Map.Entry<String,String> entry:replaceMap.entrySet()) {
                if(line.contains(entry.getKey())){
                    line = line.replaceAll(entry.getKey(), entry.getValue());
                    System.out.println("替换了:"+entry.getKey()+"-->"+entry.getValue());
                }
            }

            // 将该行写入内存
            tempStream.write(line);
            // 添加换行符
            tempStream.append(System.getProperty("line.separator"));
        }
        // 关闭 输入流
        bufIn.close();
        // 将内存中的流 写入 文件
        FileWriter out = new FileWriter(file);
        tempStream.writeTo(out);
        out.close();
        //System.out.println("文件写入完成:"+path);

    }

    /**
     * 一次性读取文件,正则判断是否有中文,进一步筛选文件
     * 二次筛选,哪些中文没替换
     */
    public boolean acceptFile(String filePath,boolean needSec) throws IOException {
        boolean isNeed = false;
        File file = new File(filePath);
        byte[] bytes = new byte[(int) file.length()];
        FileInputStream fileInputStream = new FileInputStream(filePath);
        int ret  = fileInputStream.read(bytes);
        String content = new String(bytes, 0, ret);
        String pattern = "[\\u4E00-\\u9FA5]+";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(content);
        while (m.find()) {
            isNeed = true;
            if(needSec){
                System.out.println("未替换:"+m.group());
                nomap.put(m.group(),"");
            }else{
                break;
            }
        }

        return isNeed;
    }

    @Test
    public void test() {
        smap.put("新建","create");
        smap.put("修改","modify");
        smap.put("查询","select");
        smap.put("删除","delete");
        smap.put("工单","Work order");
        Map<String,List<String>> mapKv = getFileList("/Users/edison/gitlab/LUCKYCUSTOMERSERVICEWEB/src/main/webapp");
//        System.out.println(mapKv);
        for (String fileName:mapKv.get("need")) {
            try {
                if(acceptFile(fileName,false)){
                    System.out.println("转化:"+fileName+"--->开始");
                    replacTextContent(fileName,smap);
                    acceptFile(fileName,true);
                }
            } catch (IOException e) {
                System.out.println("转化:"+fileName+"--->失败");
            }
            System.out.println("转化:"+fileName+"--->结束");
            break;
        }
    }
}
