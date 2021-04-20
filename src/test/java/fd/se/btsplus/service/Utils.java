package fd.se.btsplus.service;

public class Utils {

    public static final double PRECISE_EPSILON = 1e-8;

    public static boolean epsilonEqual(double v1, double v2) {
        return Math.abs(v1 - v2) < PRECISE_EPSILON;
    }
}
