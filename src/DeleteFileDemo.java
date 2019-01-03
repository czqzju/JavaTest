import java.io.File;
public class DeleteFileDemo{
	public static void main(String [] args) {
		File f = new File("src/tmp/java");
		deleteFolder(f);
				
	}
	
	public static void deleteFolder(File folder){
		File [] files = folder.listFiles();
		if(files.length != 0) {
			for(File f : files) {
				if(f.isDirectory()) {
					deleteFolder(f);
				}
				else{
					f.delete();
				}
			}
		}
		folder.delete();
	}
}