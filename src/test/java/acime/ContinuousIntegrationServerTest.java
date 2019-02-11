package acime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;

public class ContinuousIntegrationServerTest {

    /**
       Removes a folder and all files in the folder
       @param directory a file representing the folder
       @return A boolean value stating if the folder was successfully deleted or not
     **/
    private static boolean deleteDirectory(File directory) {
        if(directory.exists()){
            File[] files = directory.listFiles();
            if(null!=files){
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteDirectory(file);
                    } else {
                        file.delete();
                    }
                }
            }
        }
        return(directory.delete());
    }

    /**
       Tests if content from single file is returned from method
     **/
    @Test
    public void testPresentSingleFile() {
        File dir = new File(".", "builds");
        boolean exists = dir.isDirectory();

        File file = new File(dir, "test1");
        String[] target = {"builds", "test1"};
        String testString = "testing parent single file method";

        try {
            dir.mkdir();
            FileWriter writer = new FileWriter(file);
            writer.write(testString);
            writer.close();

            ContinuousIntegrationServer ci = new ContinuousIntegrationServer();
            Assertions.assertEquals(testString, ci.presentSingleFile(target));

        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail("Exception was thrown");
        }


        // DO NOT COMMENT OUT THESE TWO LINES!
        if(exists) deleteDirectory(file);
        else deleteDirectory(dir);
    }
}
