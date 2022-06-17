package org.firstinspires.ftc.teamcode.modules;

import android.media.MediaPlayer;
import android.provider.MediaStore;

import org.firstinspires.ftc.teamcode.configs.MusicConfig;
import org.firstinspires.ftc.teamcode.interfaces.IInputMapping;
import org.firstinspires.ftc.teamcode.interfaces.IRobotModule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Music implements IRobotModule {
    private IInputMapping mapping;
    private List<MediaPlayer> media = new ArrayList<>();

    public Music(IInputMapping mapping) {
        this.mapping = mapping;

        for(String song:MusicConfig.music){
            MediaPlayer player = new MediaPlayer();
            try {
                player.setDataSource(MusicConfig.FOLDER_PATH + song);
                player.setVolume(1,1);
                player.prepareAsync();
                media.add(player);
            }catch (IOException ignored){}
        }

    }

    @Override
    public void emergencyStop() {

    }

    @Override
    public void loop() {
        if(mapping.toggleSong()){
        }

    }
}
