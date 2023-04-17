package testiranje;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

class PrimeriTest {

	/*
	 * U najjednostavnijoj varijanti postavimo neki logicki izraz koji treba da bude
	 * tacan ako je ponasanje metoda ispravno
	 */
	@Test
	void testDeo() {
		assertTrue(Primeri.sumN(new int[] { -1, 4, 3 }, 2) == 3);
	}

	/*
	 * U mnogim situacijama je logicnije da imamo poredjenje ocekivanog izlaza
	 * metoda sa stvarnim rezultatom - primarno zato sto ako test 'padne' dobicemo
	 * informaciju o tome koja je vrednost dobijena, sto nas moze bolje usmeriti ga
	 * uzroku greske.
	 */
	@Test
	void testSvi() {
		assertEquals(6, Primeri.sumN(new int[] { 3, 3, 0 }, 3));
	}

	@Test
	void testVise() {
		assertTrue(Primeri.sumN(new int[] { 1, 2, 3 }, 4) ==  6);
	}

	@Test
	void testNula() {
		assertTrue(Primeri.sumN(new int[] { 1, 2, 3 }, 0) ==  0);
	}

	/*
	 * Moguce je i testirati da li metod bacio izuzetak, ali ga je potrebno umotati
	 * nekako da bude "Executable" - najlakse je koristiti lamba izraz kao sto je
	 * dole pokazano. Alternativno se moze eksplicitno uhvatiti izuzetak i proveriti
	 * da li je takakv kakav treba da bude.
	 */
	@Test
	void testNullNiz() {
		assertThrows(NullPointerException.class, () -> Primeri.sumN(null, 0));
	}

	// Proveriti "dobru parnost"
	@Test
	void testDobraParnost() {
		assertEquals(3, Primeri.oddOrPos(new int[] { -11, -21, 2, 5, 7, -12 }));
	}

	@Test
	void testLosaParnost() {
		assertFalse(Primeri.oddOrPos(new int[] { 1, 2, 3 }) == 6);

	}

	@Test
	void testPozitivan() {
		assertTrue(Primeri.oddOrPos(new int[] { 1, 2, 3 }) > 0);
	}

}
