package Tests;

import code.Functionality;
import code.PublicKey;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FunctionalityTests {
	
	private void inverse(int a, int b, int expected){
		Functionality f = new Functionality();
		int actual = f.inverse(a, b);
		assertTrue("\nThe input was a:"+a+"  b:"+b+
				"\nThe expected answer was "+expected+
				"\n but "+actual+" was returned",
				expected == actual);
	}
	
	@Test public void inverse0_11(){ inverse(3,11,13); }
	@Test public void inverse13_07(){ inverse(7,13,29); }
	
	private void encrypt(String s, int key, int max, boolean encrypt){
		Functionality f = new Functionality();
		PublicKey k = new PublicKey(key,max);
		String actual;
		if(encrypt){ actual = f.encrypt(s, k); }
		else{ actual = f.decrypt(s, k); }
		assertTrue("\nThe input was :"+s+
					"\nThe key was :"+key+
					"\nThe max was :"+max+
					"\nThe returned string is :"+actual,
					false);
	}
	
	@Test public void encrypt1(){ encrypt("Hello", 5, 91, true); }
	@Test public void decryptH(){ encrypt("h", 29, 91, false); }
	@Test public void decryptE(){ encrypt("*", 29, 91, false); }
	@Test public void decryptL(){ encrypt("1", 29, 91, false); }
	@Test public void decryptO(){ encrypt("4", 29, 91, false); }

}
