public class preprocessor {
	private String initial_string;
	private String preprocessed_string;
		
	public preprocessor(String str) {
		// do not edit this method
		initial_string = str;
		preprocessed_string = "";
	}

	public void preprocess() {
		// do not edit this method
		capitalize();
		clean();
	}
	
	private void capitalize() {
		// 
		for (var c : initial_string.toCharArray()) {
			if (c >= 'a' && c <= 'z') {
				preprocessed_string += (char)(c - 32);
			}
		}
	}

	private void clean() {
		initial_string = "";
	}
	
	public String get_preprocessed_string() {
		// do not edit this method
		return preprocessed_string;
	}
}