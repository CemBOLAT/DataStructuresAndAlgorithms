import java.util.Map;
import java.util.Iterator;

/**
 * decryptor class is used to decrypt a given text using the Vigenere cipher.
 * The class has the map as vinegere cipher map, key as the key, and text as the cipher text.
 * and key as instance variables.
*/

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
	/**
	 * Decrypts the given text using the Vigenere cipher.
	 * <p> This method decrypts the given text using the Vigenere cipher. </p>
	 * <p> The method generates the keystream and the plain text. </p>
	 * <p> The method uses the map to decrypt the text. </p>
	 */
	public void decrypt() {
		// do not edit this method (given by the instructor)
		generate_keystream();
		generate_plain_text();
	}
	/**
	 * Generates the keystream.
	 * <p> This method generates the keystream. </p>
	 * <p> The method uses the key to generate the keystream. </p>
	 * Algorithm:
	 * 1. Iterate through the cipher text.
	 * 2. Append the key character to the keystream.
	 * 3. Repeat step 2 until the length of the key is equal to the length of the cipher text.
	 * Avoiding out of bounds exception by using the modulo operator. 
	 */
	private void generate_keystream() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < cipher_text.length(); i++) {
			sb.append(key.charAt(i % key.length()));
		}
		keystream = sb.toString();
	}
	
	private void generate_plain_text() {
		// You must use map.get(x).keySet() with an iterator in this method (is a must given by the instructor)
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < cipher_text.length(); i++) {
			char c = keystream.charAt(i);
			char k = cipher_text.charAt(i);
			Iterator<Character> it = map.get(c).keySet().iterator();
			while(it.hasNext()){
				char c2 = it.next();
				if(map.get(c).get(c2) == k){
					sb.append(c2);
					break;
				}
			}
		}
		plain_text = sb.toString();
	}
	
	/**
	 * Returns the keystream.
	 * <p> This method returns the keystream. </p>
	 * @return the keystream
	 */
	public String get_keystream() {
		return keystream;
	}
	/**
	 * Returns the plain text.
	 * <p> This method returns the plain text. </p>
	 * @return the plain text
	 */
	public String get_plain_text() {
		return plain_text;
	}
}
