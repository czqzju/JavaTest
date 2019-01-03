import java.io.*;

public class Filelei implements FilenameFilter {//此次使用本类作为接口

    /**
     * @param args
     */
    public void f(){
        File f=new File("src");
        String []filename=f.list();//以字符串的形式，先列出当前目录下所有文件看一下（有什么类型的文件）
        for(int i=0;i<filename.length;i++){
            System.out.println(filename[i]);
        }
        System.out.println("********************************");
        String []fname=f.list(this);
        for(int i=0;i<fname.length;i++){
            System.out.println(fname[i]);
        }
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new Filelei().f();
        
    }

    @Override
    public boolean accept(File f, String name) {//重写接口方法
        // TODO Auto-generated method stub
        return name.contains("Test");//返回当前目录下以.java结尾的文件
    }
}