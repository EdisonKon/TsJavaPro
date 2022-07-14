package TsDesignModel.bridgemodel;

import org.junit.Test;

/**
 * @author dekai.kong
 * @difficult
 * @create 2022-07-14 20:19
 * @from
 **/
public abstract class OpearSystem {

    protected VideoFile videoFile;

    public OpearSystem(VideoFile videoFile) {
        this.videoFile = videoFile;
    }
    public abstract void playFile(String filename);
}
