package TsDesignModel.bridgemodel;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 20:20
 * @from
 **/
public class MacSystem extends OpearSystem{
    public MacSystem(VideoFile videoFile) {
        super(videoFile);
    }

    @Override
    public void playFile(String filename) {
        System.out.println("Mac 进行播放 : " + filename);
        videoFile.decode(filename);
    }
}
