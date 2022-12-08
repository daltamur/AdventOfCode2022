import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Day7 {

    static long total = 0;
    static Long smallestDirSize;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("inputDay7.txt"));
        String line;
        Directory curDir = new Directory("home", null);


        while((line = reader.readLine()) != null){
            if(line.startsWith("$ cd")){
                String nextDir = line.split(" ")[2];
                if(Objects.equals(nextDir, "/")){
                    while(curDir.getParentDir() != null){
                        curDir = curDir.getParentDir();
                    }
                } else if (Objects.equals(nextDir, "..")) {
                    curDir = curDir.getParentDir();
                }else{
                    curDir = curDir.findDir(line.split(" ")[2]);
                }
            }else if(line.startsWith("dir ")){
                curDir.addDirectory(new Directory(line.substring("dir ".length()), curDir));
            }else if(!line.startsWith("$")){
                int size = Integer.parseInt(line.split(" ")[0]);
                curDir.addFile(new File(size));
            }
        }

        while(curDir.getParentDir() != null)
            curDir = curDir.getParentDir();

        curDir.setSize();
        curDir.findSmallDirs(100000);
        System.out.println("Total size of dirs with size >= 100000: "+total);
        total = curDir.getSize();
        System.out.println("Total size: "+total);
        curDir.getRidOfSmallestDir(70000000 - total);
        System.out.println("Smallest dir to get rid of: "+smallestDirSize);

    }


}

class Directory{
    private final HashMap<String, Directory> childDirs = new HashMap<>();

    private final ArrayList<File> files = new ArrayList<>();
    private final String name;

    private final Directory parentDir;

    private int size;

    public Directory(String name, Directory parentDir){
        this.name = name;
        this.parentDir = parentDir;
    }

    public String getName(){
        return name;
    }

    public Directory getParentDir(){
        return parentDir;
    }

    public void addDirectory(Directory dir){
        childDirs.put(dir.getName(), dir);
    }

    public void addFile(File file){
        files.add(file);
    }

    public void setSize(){
        for(File file: files)
            size+=file.getSize();

        for(Directory dir: childDirs.values()) {
            dir.setSize();
            size+=dir.getSize();
        }
    }

    public int getSize(){
        return size;
    }

    public Directory findDir(String name){
        return childDirs.get(name);
    }


    public void findSmallDirs(int n) {
        if(size <= n)
            Day7.total += size;

        for(Directory dir: childDirs.values())
            dir.findSmallDirs(n);

    }

    public void getRidOfSmallestDir(long currentTotal) {
        if(Day7.smallestDirSize == null && size+currentTotal >= 30000000)
            Day7.smallestDirSize = (long) size;
        else if(size+currentTotal >= 30000000 && (long) size < Day7.smallestDirSize)
            Day7.smallestDirSize = (long) size;

        for(Directory dir: childDirs.values())
            dir.getRidOfSmallestDir(currentTotal);


    }
}

class File{
    private final int size;

    public File(int size){
        this.size = size;
    }

    public int getSize(){
        return size;
    }

}
