package me.piggypiglet.randomspawn.file.objects.spawn.location;

// ------------------------------
// Copyright (c) PiggyPiglet 2020
// https://www.piggypiglet.me
// ------------------------------
public final class Location {
    private double x;
    private double y;
    private double z;
    private double pitch;
    private double yaw;

    public Location(final double x, final double y, final double z, final double pitch, final double yaw) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public double getX() {
        return x;
    }

    public void setX(final double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(final double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(final double z) {
        this.z = z;
    }

    public double getPitch() {
        return pitch;
    }

    public void setPitch(final double pitch) {
        this.pitch = pitch;
    }

    public double getYaw() {
        return yaw;
    }

    public void setYaw(final double yaw) {
        this.yaw = yaw;
    }
}
