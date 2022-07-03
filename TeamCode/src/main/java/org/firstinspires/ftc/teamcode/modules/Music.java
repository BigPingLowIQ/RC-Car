package org.firstinspires.ftc.teamcode.modules;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.configs.MusicConfig;
import org.firstinspires.ftc.teamcode.interfaces.IInputMapping;
import org.firstinspires.ftc.teamcode.interfaces.IRobotModule;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Music implements IRobotModule {
    private List<String> playlist1 = new ArrayList<>();
    private List<String> playlist2 = new ArrayList<>();
    private List<String> playlist3 = new ArrayList<>();
    private MediaPlayer player;
    private MediaPlayer horn;
    private final Telemetry telemetry;

    private Playlist currPl = Playlist.one;
    private int songIndex = 0;

    public Music(Telemetry telemetry) {
        this.telemetry = telemetry;

        try {
            horn = new MediaPlayer();
            horn.setVolume(1, 1);
            horn.setDataSource(MusicConfig.horn);
            horn.prepareAsync();
        }catch (IOException ignored){}

        String[] songs = MusicConfig.playlist1songs.split(" ");
        for(String song : songs){
            telemetry.addLine(song);
            playlist1.add(song);
        }
        songs = MusicConfig.playlist2songs.split(" ");
        for(String song : songs){
            telemetry.addLine(song);
            playlist2.add(song);
        }
        songs = MusicConfig.playlist3songs.split(" ");
        for(String song : songs){
            telemetry.addLine(song);
            playlist3.add(song);
        }

        player = new MediaPlayer();
        player.setVolume(1,1);
        setPlaylist(Playlist.one);
    }

    @Override
    public void emergencyStop() {
    }

    @Override
    public void loop() {
    }

    public void nextTrack() {
        switch (currPl){
            case one:{
                if(songIndex==playlist1.size()-1)
                    songIndex = 0;
                else
                    songIndex++;
                try {
                    player.reset();
                    player.setDataSource(MusicConfig.FOLDER_PATH+playlist1.get(songIndex));
                    player.prepare();
                } catch (IOException ignored) {}
                break;
            }
            case two:{
                if(songIndex==playlist2.size()-1)
                    songIndex = 0;
                else
                    songIndex++;
                try {
                    player.reset();
                    player.setDataSource(MusicConfig.FOLDER_PATH+playlist2.get(songIndex));
                    player.prepare();
                } catch (IOException ignored) {}
                break;
            }
            case three:{
                if(songIndex==playlist3.size()-1)
                    songIndex = 0;
                else
                    songIndex++;
                try {
                    player.reset();
                    player.setDataSource(MusicConfig.FOLDER_PATH+playlist3.get(songIndex));
                    player.prepare();
                } catch (IOException ignored) {}
                break;
            }
        }
    }

    public void previousTrack() {
        switch (currPl){
            case one:{
                if(songIndex==0)
                    songIndex = playlist1.size()-1;
                else
                    songIndex--;
                try {
                    player.reset();
                    player.setDataSource(MusicConfig.FOLDER_PATH+playlist1.get(songIndex));
                    player.prepare();
                } catch (IOException ignored) {}
                break;
            }
            case two:{
                if(songIndex==0)
                    songIndex = playlist2.size()-1;
                else
                    songIndex--;
                try {
                    player.reset();
                    player.setDataSource(MusicConfig.FOLDER_PATH+playlist2.get(songIndex));
                    player.prepare();
                } catch (IOException ignored) {}
                break;
            }
            case three:{
                if(songIndex==0)
                    songIndex = playlist3.size()-1;
                else
                    songIndex--;
                try {
                    player.reset();
                    player.setDataSource(MusicConfig.FOLDER_PATH+playlist3.get(songIndex));
                    player.prepare();
                } catch (IOException ignored) {}
                break;
            }
        }
    }

    public void setPlaylist(Playlist playlist) {
        switch (playlist){
            case one:{
                try {
                    songIndex = 0;
                    player.reset();
                    player.setDataSource(MusicConfig.FOLDER_PATH+playlist1.get(songIndex));
                    player.prepare();
                } catch (IOException ignored) {}
                break;
            }
            case two:{
                try {
                    songIndex = 0;
                    player.reset();
                    player.setDataSource(MusicConfig.FOLDER_PATH+playlist2.get(songIndex));
                    player.prepare();
                } catch (IOException ignored) {}
                break;
            }
            case three:{
                try {
                    songIndex = 0;
                    player.reset();
                    player.setDataSource(MusicConfig.FOLDER_PATH+playlist3.get(songIndex));
                    player.prepare();
                } catch (IOException ignored) {}
                break;
            }
        }
        currPl = playlist;
    }

    public void horn() {
        if(!horn.isPlaying())
             horn.start();
    }

    public void togglePlay() {
        if(!player.isPlaying())player.start();
        else player.pause();
    }

    public void telemetry() {
        switch (currPl){
            case one:{
                telemetry.addData("Playlist","1");
                telemetry.addData("Song",playlist1.get(songIndex));
                break;
            }
            case two:{
                telemetry.addData("Playlist","2");
                telemetry.addData("Song",playlist2.get(songIndex));
                break;
            }
            case three:{
                telemetry.addData("Playlist","3");
                telemetry.addData("Song",playlist3.get(songIndex));
                break;
            }
        }

    }

    public enum Playlist{
        one(0),
        two(1),
        three(2);
        final int value;
        Playlist(int i) {
            value = i;
        }
    }

}
