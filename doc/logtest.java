import java.util.logging.Level;
import java.util.logging.Logger; //loging API

public class logtest {
    private final static Logger LOG = Logger.getGlobal();
    //Loging을 위해서 Loger 객체를 생성해야함.
    //객체 생성을

    public static void main(String[] args) {
        LOG.setLevel(Level.INFO);
        LOG.severe("sesvere Log");
        LOG.warning("warning Log");
        LOG.info("info Log");
    }
}
