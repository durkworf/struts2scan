package struts2scan.payload;

import java.util.HashMap;
import java.util.Map;

public class Store {
    public static Map<String,Check> VUL_LIST =new HashMap<String,Check>();

    static{
        VUL_LIST.put("S2_001",new S2_001());
    }
}
