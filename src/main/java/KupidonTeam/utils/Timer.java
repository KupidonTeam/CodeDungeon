package KupidonTeam.utils;

//Класс таймер испл для ожидания (возврашает true когда время кончилось)
public class Timer {
    public static boolean timer(long milliseconds) {
        milliseconds += System.currentTimeMillis();

        while (System.currentTimeMillis() < milliseconds) {
        }

        return true;
    }
}
