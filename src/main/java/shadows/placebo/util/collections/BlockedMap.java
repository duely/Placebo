package shadows.placebo.util.collections;

import java.util.HashMap;
import java.util.Map;

public abstract class BlockedMap<K, V> extends HashMap<K, V> {

	private static final long serialVersionUID = 3265695314048724801L;

	public abstract boolean isBlocked(K k, V v);

	public abstract boolean isBlocked(Map<? extends K, ? extends V> m);

	@Override
	public V put(K key, V value) {
		if (isBlocked(key, value)) return null;
		return super.put(key, value);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		if (isBlocked(m)) return;
		super.putAll(m);
	}

	@Override
	public V putIfAbsent(K key, V value) {
		if (isBlocked(key, value)) return null;
		return super.putIfAbsent(key, value);
	}
}
