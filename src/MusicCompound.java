import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MusicCompound {
	public static void main(String args[])
    {
        FileOutputStream fileOutputStream = null;
        FileInputStream fileInputStream = null;
        String fileNames[] = {"E:/music/Shape Of You (Galantis Remix).mp3","E:/music/Photograph (Felix Jaehn Remix).mp3"};
        //����byte���飬ÿ����������д���8K������
        byte by[] = new byte[1024*8];
        try
        {
            fileOutputStream = new FileOutputStream("E:/music/�ϲ�.mp3");
            for(int i=0;i<2;i++)
            {
                int count = 0;
                fileInputStream = new FileInputStream(fileNames[i]);
                //����ǰ��3M�ĸ�������
                fileInputStream.skip(1024*1024*3);
                while(fileInputStream.read(by) != -1)
                {                    
                    fileOutputStream.write(by);
                    count++;
                    System.out.println(count);
                    //Ҫ��ȡ�м�2MB�����ݣ�ÿ������8k�����ݣ���������Ĵ�����1024*2/8
                    if(count == (1024*2/8))
                    {
                        break;
                    }
                }
            }
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                //�����ɺ�ر����������
                fileInputStream.close();
                fileOutputStream.close();
            } 
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }

}
