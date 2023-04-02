package com.x20.frogger.tests;

import static org.testng.AssertJUnit.assertEquals;

import com.badlogic.gdx.math.Vector2;
import com.x20.frogger.game.Mover;
import com.x20.frogger.game.Player;
import com.x20.frogger.utils.MiscUtils;

import org.junit.Before;
import org.junit.Test;

public class TestMover {

    Player player;

    @Before
    public void init() {
        player = new Player(0, 0);
    }

    @Test
    public void test() {
        Mover mover = player.getMover();
        float moveSpeed = 1f;
        Vector2 moveDir = Vector2.X.scl(moveSpeed);
        float[] deltas = new float[] {0.0166666667f, 0.0181818182f, 0.0172413793f, 0.0161290323f};
        int i = 0;
        mover.deriveTargetPos(moveDir);
        mover.startMoving();
        while (player.getPosition().x < 1) {
            player.setPositionRaw(mover.moveToTarget(deltas[i]));
            i = (i + 1) % 4;
        }
        System.out.println(MiscUtils.getMaxPrecisionFormat().format(player.getPosition().x));
        assertEquals(player.getPosition().x, 1.0f);
    }
}
