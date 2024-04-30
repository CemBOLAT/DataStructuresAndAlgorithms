import java.util.Map;
import java.util.Iterator;

public class decryptor {
	private Map<Character, Map<Character, Character>> map;
	private String key;
	private String keystream = "";
	private String plain_text = "";
	private String cipher_text;
	
	public decryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) {
		// do not edit this method
		map = _map;
		key = _key;
		cipher_text = text;
	}

	public void decrypt() {
		// do not edit this method
		generate_keystream();
		generate_plain_text();
	}
	
	private void generate_keystream() {
		// Vinegere cipher
		for (int i = 0; i < cipher_text.length(); i++) {
			keystream += key.charAt(i % key.length());
		}
	}
	
	private void generate_plain_text() {
		// You must use map.get(x).keySet() with an iterator in this method
		for (int i = 0; i < cipher_text.length(); i++) {
			char c = keystream.charAt(i);
			char k = cipher_text.charAt(i);
			Iterator<Character> it = map.get(c).keySet().iterator();
			while(it.hasNext()){
				char c2 = it.next();
				if(map.get(c).get(c2) == k){
					plain_text += c2;
					break;
				}
			}
		}
	}

	public String get_keystream() {
		return keystream;
	}
	
	public String get_plain_text() {
		return plain_text;
	}
}
