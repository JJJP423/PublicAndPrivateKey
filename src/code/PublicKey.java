package code;

/**
 * @author Jay
 *
 */
public class PublicKey {
	
	private int key;
	private int max;
	Functionality f;
	
	public PublicKey(){
		key = 0;
		max = 0;
		System.out.println("A public constructor was not called correctly");
	}
	
	public PublicKey(int k, int m){
		key = k;
		max = m;
	}

	public int getKey(){ return key; }
	public int getMax(){ return max; }
	
	public String encrypt(String m){
		return f.encrypt(m, this);
	}
}
