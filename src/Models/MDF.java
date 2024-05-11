package Models;

import java.util.HashMap;

public class MDF {
    private HashMap<String,UFD> userSystem;

    public MDF() {
        userSystem = new HashMap<String,UFD>();
        userSystem.put("xxx",new UFD("root"));
        userSystem.put("小雨",new UFD("root"));
        userSystem.put("小雪",new UFD("root"));
        userSystem.put("小天",new UFD("root"));
        userSystem.put("小雷",new UFD("root"));
        userSystem.put("小雾",new UFD("root"));
    }
    public HashMap<String, UFD> getUserSystem() {
        return userSystem;
    }

    public void setUserSystem(HashMap<String, UFD> userSystem) {
        this.userSystem = userSystem;
    }

}
