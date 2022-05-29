package org.firstinspires.ftc.teamcode.gamepad;

import com.qualcomm.robotcore.hardware.Gamepad;

public class GamepadEx {
    private Gamepad gamepad;
    KeyDown a,b,x,y,dPadUp,dPadDown,dPadLeft,dPadRight,leftStick,rightStick;

    protected GamepadEx(Gamepad gamepad) {
        this.gamepad = gamepad;
        a = new KeyDown();
        b = new KeyDown();
        x = new KeyDown();
        y = new KeyDown();
        dPadDown = new KeyDown();
        dPadLeft = new KeyDown();
        dPadRight = new KeyDown();
        dPadUp = new KeyDown();
        leftStick = new KeyDown();
        rightStick = new KeyDown();
    }

    protected Gamepad getGamepad(){return gamepad;}

    public boolean aPressed(){return a.down(gamepad.a);}
    public boolean bPressed(){return b.down(gamepad.b);}
    public boolean xPressed(){return x.down(gamepad.x);}
    public boolean yPressed(){return y.down(gamepad.y);}
    public boolean dPadUpPressed(){return dPadUp.down(gamepad.dpad_up);}
    public boolean dPadDownPressed(){return dPadDown.down(gamepad.dpad_down);}
    public boolean dPadLeftPressed(){return dPadLeft.down(gamepad.dpad_left);}
    public boolean dPadRightPressed(){return dPadRight.down(gamepad.dpad_right);}
    public boolean leftStickPressed(){return leftStick.down(gamepad.left_stick_button);}
    public boolean rightStickPressed(){return rightStick.down(gamepad.right_stick_button);}

}
