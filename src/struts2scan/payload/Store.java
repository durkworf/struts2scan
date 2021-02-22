package struts2scan.payload;

import java.util.HashMap;
import java.util.Map;

public class Store {
    public static Map<String,Check> VUL_LIST =new HashMap<String,Check>();

    static{
        VUL_LIST.put("S2_001",new S2_001());
        VUL_LIST.put("S2_003",new S2_003());
        VUL_LIST.put("S2_005",new S2_005());
        VUL_LIST.put("S2_007",new S2_007());
        VUL_LIST.put("S2_008",new S2_008());
        VUL_LIST.put("S2_009",new S2_009());
        VUL_LIST.put("S2_012",new S2_012());
        VUL_LIST.put("S2_013/14",new S2_013());
        VUL_LIST.put("S2_015",new S2_015());
        VUL_LIST.put("S2_016",new S2_016());
        VUL_LIST.put("S2_019",new S2_019());
        VUL_LIST.put("S2_029",new S2_029());
        VUL_LIST.put("S2_032",new S2_032());
        VUL_LIST.put("S2_033",new S2_033());
        VUL_LIST.put("S2_037",new S2_037());
        VUL_LIST.put("S2_045",new S2_045());
        VUL_LIST.put("S2_046",new S2_046());
        VUL_LIST.put("S2_048",new S2_048());
        VUL_LIST.put("S2_052",new S2_052());
        VUL_LIST.put("S2_053",new S2_053());
        VUL_LIST.put("S2_devMode",new S2_007());
        VUL_LIST.put("S2_057",new S2_057());
        VUL_LIST.put("S2_059",new S2_007());
        VUL_LIST.put("S2_061",new S2_007());
    }
}
