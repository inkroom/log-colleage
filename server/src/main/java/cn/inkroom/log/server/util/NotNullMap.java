package cn.inkroom.log.server.util;


import java.util.HashMap;

/**
 * 处理map，不存储null值，也不报错
 *
 * @author 墨盒
 * @date 19-1-9
 */
public class NotNullMap<K, V> extends HashMap<K, V> {


    @Override
    public V put(K key, V value) {
        if (value != null)
            return super.put(key, value);
        return null;
    }
}
