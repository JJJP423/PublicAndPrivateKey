package code;

import java.util.ArrayList;

/**
 * @author Jay
 *
 */
public class Functionality {
	
	/**@param a is one prime number to be used in the creation
	 * of keys.
	 * @param b is a different prime number to be used in the 
	 * creation of keys.
	 * @return returns the answer to ax + by
	*/
	public int inverse(int a, int b){
		int prevPrevR = a;			// r(i-1)
		int prevR = b;				// r(i)
		int R;	// r(i+1) = r(i-1)-q(i)r(i)
		int Q;
		int prevPrevS = 0;			// s(i-1)
		int prevS = 1;				// s(i)
		int S;// s(i+1) = s(i-1)-q(i)s(i)
		int prevPrevT = 1;			// t(i-1)
		int prevT = 0;				// t(i)
		int T;// t(i+1) = t(i-1)-q(i)t(i)
		do{
			R = prevPrevR%prevR;
			Q = prevPrevR/prevR;
			S = prevPrevS - Q*prevS;
			T = prevPrevT - Q*prevT;
			 System.out.println(prevPrevR+"="+prevR+"*"+Q+"+"+R);
			 System.out.println("S(i)="+S);
			 System.out.println("T(i)="+T);
			prevPrevR = prevR;
			prevR = R;
			prevPrevS = prevS;
			prevS = S;
			prevPrevT = prevT;
			prevT = T;
		}while(R!=0);
		System.out.print(prevPrevS+"*"+a+"+"+prevPrevT+"*"+b+"=");
		int lhs1 = S*a;
		//if (lhs1<0){ lhs1 = -lhs1; }
		int lhs2 = T*b;
		//if (lhs2<0){ lhs2 = -lhs2; }
		int lhs3 = lhs1+lhs2;
		System.out.println(lhs3);
		System.out.println(lhs3+"%"+(a*b));
		int num = (lhs3%(a*b));
		if (num < 0){ return -num; }
		return num;
	}
	
	/**This is the functionality for decrypting a string
	 * @param s is the string to be decrypted
	 * @param k is the reference to a PublicKey object that holds the valyes
	 * of a PrivateKey object
	 * @return calls encrypt to reduce code duplication
	*/
	public String decrypt(String s, PublicKey k){ return crypt(s,k,false); }
	
	/**This is the functionality for encrypting a string
	 * @param s is the string to be encrypted
	 * @param k is the reference to a PublicKey object
	 * @return calls encrypt to reduce code duplication
	*/
	public String encrypt(String s, PublicKey k){ return crypt(s,k,true); }
	
	public String encryptDecrypt(){
		return null;
		
	}
	
	/**This is the functionality for modifying a string
	 * @param s is the string to be modified
	 * @param k is the reference to a PublicKey object. This PublicKey 
	 * will hold the values of a PrivateKey if called from a PrivateKey 
	 * @return returns the encrypted string
	*/
	public String crypt(String s, PublicKey k, boolean encrypt){
		String toReturn = "";
		if(encrypt){ System.out.println("'"+s+"' is about to be encrypted"); }
		else{ System.out.println("'"+s+"' is about to be decrytped"); }
		int key = k.getKey();
		 System.out.println("The key is :"+key);
		int max = k.getMax();
		 System.out.println("The max is :"+max);
		char toAdd;
		for(int i=0; i<s.length(); i++){
			int num = (int) s.charAt(i);
			int temp;
			if(encrypt){ 
				temp = crypt(num,k);
				/*toAdd = (char) encodeToChar(temp);*/
			}else{
				temp = decodeFromChar(num);
				/*toAdd = (char)crypt(temp,k);*/
			}
			toReturn = toReturn + /*toAdd*/ temp;
			 System.out.println(toReturn);
		}
		return toReturn;
	}
	
	private int crypt(int num, PublicKey k){
		int max = k.getMax();
		int key = k.getKey();
		if(key%2 == 1){
			int temp = modExp(num,1,max);
			temp = (temp*modExp(num,key-1,max))%max;
			return temp;
		}else return modExp(num,key,max);
	}
	
	private int modExp(int x, int y, int z){
		if(y!=1){
			System.out.println("y != 1, cutting in half and computing");
			int num = modExp(x,y/2,z)%z;
			num = (num*num)%z;
			System.out.println(num);
			return num;
		}
		else return (x%z);
	}
	
	public void testE(int num){
		encodeToChar(num);	
	}
	public void testD(int num){
		decodeFromChar(num);
	}
	public void testED(int num){
		int n = encodeToChar(num);
		decodeFromChar(n);
	}
	
	private int encodeToChar(int num){
		int key = 5;
		int max = 91;
		System.out.println("Number being encoded: "+num);
		int encoded = modExp(num,1,max);
		int temp = modExp(num,28,max);
		encoded = (encoded*temp)%max;
/*		System.out.println(encoded);
		int temp = (encoded*encoded)%max;
		System.out.println(temp);
		encoded = (encoded*(temp*temp))%max;*/
		// PublicKey k = new PublicKey(key,max);
		// int encoded = crypt(num,k);
		System.out.println(encoded+"+32");
		encoded = encoded + 32;
		System.out.println("After encoding it is: "+encoded);
		return encoded;
	}
	
	private int decodeFromChar(int num){
		int key = 29;
		int max = 91;
		System.out.println("Number being decoded: "+num+"-32=");
		num = num - 32;
		int decoded = modExp(num,1,max);
		System.out.println(decoded);
		int temp = modExp(num,28,max);
		System.out.println(temp);
		decoded = (decoded*temp)%max;
		System.out.print("After decoding it is: ");
		// PublicKey k = new PublicKey(key,max);
		// int decoded = crypt(num,k);
		System.out.println(decoded);
		return decoded;
	}
	
	private int getKey(int p, int q, int e){
		return 0;
	}
}