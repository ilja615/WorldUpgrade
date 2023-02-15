package com.github.ilja615.worldupgrade.client;

public class ClientFogData {
    private static int playerFog;

    public static void set(int fog) {
        ClientFogData.playerFog = fog;
    }

    public static int getPlayerFog() {
        return playerFog;
    }
}