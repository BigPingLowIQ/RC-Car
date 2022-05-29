package org.firstinspires.ftc.teamcode.gamepad;

class KeyDown {
    private boolean pressed;

    public boolean down(boolean key){
        boolean p = pressed;
        return (pressed = key) && !p;
    }
}
