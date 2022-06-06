package org.firstinspires.ftc.teamcode.modules;

import android.media.MediaPlayer;

import org.firstinspires.ftc.teamcode.configs.MusicConfig;
import org.firstinspires.ftc.teamcode.interfaces.IInputMapping;
import org.firstinspires.ftc.teamcode.interfaces.IRobotModule;

import java.io.IOException;

public class Music implements IRobotModule {
    private IInputMapping mapping;
    private MediaPlayer player;
    private boolean playing = false;

    public Music(IInputMapping mapping) {
        this.mapping = mapping;
        try {
            player.setDataSource(MusicConfig.PATH);
            player.setLooping(true);
            player.prepareAsync();
        }catch (IOException ignored){}
    }

    @Override
    public void emergencyStop() {

    }

    @Override
    public void loop() {
        if(mapping.toggleSong()){
            if(!playing){
                player.start();
                playing = true;
            }
            else{
                player.pause();
                playing = false;
            }
        }
    }
}
