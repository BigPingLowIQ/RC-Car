package org.firstinspires.ftc.teamcode.modules;

import android.sax.StartElementListener;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.interfaces.IInputMapping;
import org.firstinspires.ftc.teamcode.interfaces.IRobotModule;

public class MusicController implements IRobotModule {
    private IInputMapping mapping;
    private Music music;
    public MusicController(IInputMapping mapping, Music music) {
        this.mapping = mapping;
        this.music = music;
    }

    @Override
    public void emergencyStop() {

    }

    @Override
    public void loop() {
        if(mapping.nextTrack())music.nextTrack();
        if(mapping.previousTrack())music.previousTrack();
        if(mapping.playlist1())music.setPlaylist(Music.Playlist.one);
        if(mapping.playlist2())music.setPlaylist(Music.Playlist.two);
        if(mapping.playlist3())music.setPlaylist(Music.Playlist.three);
        if(mapping.horn())music.horn();
        if(mapping.togglePlay())music.togglePlay();
        music.telemetry();

    }
}
