import java.util.Map;

public class encryptor {
	private Map<Character, Map<Character, Character>> map;
	private String key;
	private String keystream = "";
	private String plain_text;
	private String cipher_text = "";
	
	public encryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) {
		// do not edit this method
		map = _map;
		key = _key;
		plain_text = text;
	}
	
	public void encrypt() {
		// do not edit this method
		generate_keystream();
		generate_cipher_text();
	}
	
	private void generate_keystream() {
		// Vinegere cipher
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < plain_text.length(); i++) {
			sb.append(key.charAt(i % key.length()));
		}
		keystream = sb.toString();
	}
	
	private void generate_cipher_text() {
		// Vinegere cipher
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < plain_text.length(); i++) {
			char c = plain_text.charAt(i);
			char k = keystream.charAt(i);
			sb.append(map.get(c).get(k));
		}
		cipher_text = sb.toString();
	}

	public String get_keystream() {
		return keystream;
	}
	
	public String get_cipher_text() {
		return cipher_text;
	}
}
