package TsDesignModel.bridgemodel;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 20:20
 * @from
 **/
public class WindowsSystem extends OpearSystem{
    public WindowsSystem(VideoFile videoFile) {
        super(videoFile);
    }

    @Override
    public void playFile(String filename) {
        System.out.println("windows 进行播放 : " + filename);
        videoFile.decode(filename);
    }
}
