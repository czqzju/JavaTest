import java.io.*;

public class Filelei implements FilenameFilter {//�˴�ʹ�ñ�����Ϊ�ӿ�

    /**
     * @param args
     */
    public void f(){
        File f=new File("src");
        String []filename=f.list();//���ַ�������ʽ�����г���ǰĿ¼�������ļ���һ�£���ʲô���͵��ļ���
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
    public boolean accept(File f, String name) {//��д�ӿڷ���
        // TODO Auto-generated method stub
        return name.contains("Test");//���ص�ǰĿ¼����.java��β���ļ�
    }
}