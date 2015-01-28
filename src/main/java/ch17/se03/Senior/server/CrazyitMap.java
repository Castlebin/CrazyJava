package ch17.se03.Senior.server;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CrazyitMap<K, V> {
	// 创建一个线程安全的HashMap
	public Map<K, V> map = Collections.synchronizedMap(new HashMap<K, V>());
	
	// 根据value来删除指定项
	public synchronized void removeByValue(Object value) {
		for(Object key : map.keySet()) {
			if(map.get(key) == value) {
				map.remove(key);
				break;
			}
		}
	}
	
	// 获取所有value组成的Set集合
	public synchronized Set<V> valueSet() {
		Set<V> result = new HashSet<>();
		// 将map中所有value添加到result集合中
		map.forEach((key, value) -> result.add(value));
		
		return result;
	}
	
	// 根据value查找key
	public synchronized K getKeyByValue(V val) {
		for(K key : map.keySet()) {
			if(map.get(key) == val || map.get(key).equals(val)) {// 何必呢？==一定equals啊，所以只要后面的equals就行了(好吧，其实不一定，完全看该对象对应类自身的实现)
				return key;
			}
		}
		
		return null;
	}
	
	// 实现put方法，该方法不允许value重复
	public synchronized V put(K key, V value) {
		for(V val : valueSet()) {
			if(val.equals(value) && val.hashCode()==value.hashCode()) {
				throw new RuntimeException("CrazyitMap实例中不允许有重复的value！");
			}
		}
		
		return map.put(key, value);
	}
}
