package com.x20.frogger.tests;

import static org.junit.Assert.assertEquals;

import com.x20.frogger.GameScreen;
import com.x20.frogger.data.DataEnums;

import org.junit.Test;

public class TestSprint3 {
    @Test
    public void testGenerateIronGolemWidth() {
        int width = GameScreen.getVehicleWidth(DataEnums.VehicleType.IRON_GOLEM);
        assertEquals(140, width);
    }

    @Test
    public void testGenerateCreeperWidth() {
        int width = GameScreen.getVehicleWidth(DataEnums.VehicleType.CREEPER);
        assertEquals(60, width);
    }

    //Evan's Tests
    @Test
    public void testGenerateIronGolem() {
        DataEnums.VehicleType test = GameScreen.generateVehicleType(1);
        assertEquals(DataEnums.VehicleType.IRON_GOLEM, test);
    }

    @Test
    public void testGenerateCreeper() {
        DataEnums.VehicleType test = GameScreen.generateVehicleType(2);
        assertEquals(DataEnums.VehicleType.CREEPER, test);
    }

    //testGenerateSkeleton = DataEnums.VehicleType.SKELETON
    //GenerateSkeletonWidth = 110

    @Test
    public void testGenerateIronGolemVelocity() {
        int vel = GameScreen.getVehicleVelocity(DataEnums.VehicleType.IRON_GOLEM);
        assertEquals(30, vel);
    }

    @Test
    public void testGenerateCreeperVelocity() {
        int vel = GameScreen.getVehicleVelocity(DataEnums.VehicleType.CREEPER);
        assertEquals(130, vel);
    }
    
    //GenerateSkeletonVelocity = 80

    //GenerateIronGolemSpacing = 7
    //GenerateCreeperSpacing = 6
    //GenerateSkeletonSpacing = 4
}
