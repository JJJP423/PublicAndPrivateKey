package code;

/**
 * @author Jay
 *
 */
public class PrivateKey{
	
	private int prime1;
	private int prime2;
	private int max;
	private int privKey;
	private int pubKey;
	public Functionality f;
	private PublicKey pub;
	
	
	/**This is the constructor to create a completely random key
	 */
	public PrivateKey(){
		prime1 = choosePrime();
		prime2 = choosePrime();
		max = prime1*prime2;
		initializeKeys();
	}
	
	/**This creates a key based on one prime number known at the
	 * time of this method call
	 * @param p1 is a prime number to be used in the creation of a key
	*/
	public PrivateKey(int p1){
		prime1 = p1;
		prime2 = choosePrime();
		max = prime1*prime2;
		initializeKeys();
	}
	
	/**This creates a key based on two prime numbers known at the
	 * time of this method call
	 * @param p1 is a prime number to be used in the creation of a key
	 * @param p2 is a prime number to be used in the creation of a key
	*/
	public PrivateKey(int p1, int p2){
		prime1 = p1;
		prime2 = p2;
		max = prime1*prime2;
		pubKey = choosePrime();
		initializeKeys();
	}
	
	/**This creates a key based on two prime numbers known at the
	 * time of this method call and a public key to be used
	 * @param p1 is a prime number to be used in the creation of a key
	 * @param p2 is a prime number to be used in the creation of a key
	 * @param pub is the public key if known at the time of the method call
	*/
	public PrivateKey(int p1, int p2, int pub){
		prime1 = p1;
		prime2 = p2;
		max = prime1*prime2;
		pubKey = pub;
		initializeKeys();
	}
	
	/**This creates a key based on two prime numbers, a public key, and
	 * a private key all known at the time of the method call
	 * @param p1 is a prime number to be used in the creation of a key
	 * @param p2 is a prime number to be used in the creation of a key
	 * @param pub is the public key if known at the time of the method call
	 * @param priv is the private key known at the time of the method call
	*/
	public PrivateKey(int p1, int p2, int pub, int priv){
		f = new Functionality();
		prime1 = p1;
		prime2 = p2;
		max = prime1*prime2;
		pubKey = pub;
		privKey = priv;
		this.pub = new PublicKey(pubKey,max);
	}
	
	/**This initializes both the private and public keys
	*/
	private void initializeKeys() {
		f = new Functionality();
		privKey = f.inverse(prime1,prime2);
		pub = new PublicKey(pubKey,max);
	}

	private int choosePrime(){
		return 0;
	}
	
	/**This calls for the string to be decrypted with this key
	 * @param m is the string to be decrypted
	*/
	public String decrypt(String m){
		PublicKey k = new PublicKey(privKey,max);
		return f.decrypt(m,k);
	}
	
	public String encrypt(String m){ return f.encrypt(m,pub); }
}