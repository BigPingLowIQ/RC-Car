package org.firstinspires.ftc.teamcode.modules;

import android.media.MediaPlayer;

import org.firstinspires.ftc.teamcode.configs.MusicConfig;
import org.firstinspires.ftc.teamcode.interfaces.IInputMapping;
import org.firstinspires.ftc.teamcode.interfaces.IRobotModule;

import java.io.IOException;

public class Music implements IRobotModule {
    private IInputMapping mapping;
    private MediaPlayer player1;
    private MediaPlayer player2;
    private MediaPlayer player3;
    private MediaPlayer player4;


    public Music(IInputMapping mapping) {
        this.mapping = mapping;
        try {
            player1 = new MediaPlayer();
            player1.setDataSource(MusicConfig.SUTUTU);
            player1.prepareAsync();
            player1.setVolume(1,1);
        }catch (IOException ignored){}
        try {
            player2 = new MediaPlayer();
            player2.setDataSource(MusicConfig.PUMP_IT);
            player2.prepareAsync();
            player2.setVolume(1,1);
        }catch (IOException ignored){}
        try {
            player3 = new MediaPlayer();
            player3.setDataSource(MusicConfig.ROLLIN);
            player3.prepareAsync();
            player3.setVolume(1,1);
        }catch (IOException ignored){}
        try {
            player4 = new MediaPlayer();
            player4.setDataSource(MusicConfig.OYE);
            player4.prepareAsync();
            player4.setVolume(1,1);
        }catch (IOException ignored){}
    }

    @Override
    public void emergencyStop() {

    }

    @Override
    public void loop() {
        if(mapping.toggleSong()){
            if(!player1.isPlaying()) player1.start();
            else player1.pause();
        }
        if(mapping.toggleSong2()){
            if(!player2.isPlaying()) player2.start();
            else player2.pause();
        }
        if(mapping.toggleSong3()){
            if(!player3.isPlaying()) player3.start();
            else player3.pause();
        }
        if(mapping.toggleSong4()){
            if(!player4.isPlaying()) player4.start();
            else player4.pause();
        }


    }
}
