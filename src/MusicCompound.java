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
        //设置byte数组，每次往输出流中传入8K的内容
        byte by[] = new byte[1024*8];
        try
        {
            fileOutputStream = new FileOutputStream("E:/music/合并.mp3");
            for(int i=0;i<2;i++)
            {
                int count = 0;
                fileInputStream = new FileInputStream(fileNames[i]);
                //跳过前面3M的歌曲内容
                fileInputStream.skip(1024*1024*3);
                while(fileInputStream.read(by) != -1)
                {                    
                    fileOutputStream.write(by);
                    count++;
                    System.out.println(count);
                    //要截取中间2MB的内容，每次输入8k的内容，所以输入的次数是1024*2/8
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
                //输出完成后关闭输入输出流
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
