package testiranje;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ListaBrojevaBrTest {

	/*
	 * Testovi se mogu organizovati u grupe.
	 * 
	 * Potrebno je test metode grupisati u klase koje su oznacene "@Nested"
	 * anotacijom.
	 * 
	 * Prednosti su sto se mogu koristiti isti metodi za @Before i @After po potrebi
	 * u tim grupama. Takodje se mogu pokretati samo neke grupe testova, a i u
	 * prikazu pokrenutih testova je jasnije koji su medjusobono srodni.
	 */
	@Nested
	public class BrojElemenata {
		ListaBrojevaBr b;

		/*
		 * Sledeci metod ce biti pozvan pre svakog od test metoda u ovoj klasi/grupi
		 * testova.
		 */
		@BeforeEach
		void setUp() throws Exception {
			b = new ListaBrojevaBr();
		}

		@Test
		void test() {
			b.dodajNaPocetak(3.0);
			assertEquals(1, b.velicina());
		}

		@Test
		void test2() {
			b.dodajNaPocetak(3.0);
			b.dodajNaPocetak(3.0);
			assertEquals(2, b.velicina());
		}

		@Test
		void test0() {
			assertEquals(0, b.velicina());
		}
	}

	/*
	 * Ovaj test je nezavistan od gornje grupe testova.
	 */
	@Test
	void testRedosled() {
		ListaBrojevaBr lb = new ListaBrojevaBr();
		lb.dodajNaPocetak(3);
		lb.dodajNaPocetak(2);
		lb.dodajNaPocetak(1);

		/*
		 * Koristimo toString koji vraca redosled svih elemenata u listi. U opstem
		 * slucaju je oslanjanje na toString nepouzdano za ovako nesto, ovde je
		 * prikazano samo kao primer "brzog" resenja.
		 */
		assertEquals("Lista: [ 1.0 2.0 3.0 ]", lb.toString());
	}

	@Nested
	class PristupanjeElementu {
		ListaBrojevaBr lb;

		@BeforeEach
		void init() {
			lb = new ListaBrojevaBr();
			lb.dodajNaPocetak(6.1);
			lb.dodajNaPocetak(2.3);
			lb.dodajNaPocetak(2.3);
			lb.dodajNaPocetak(3.8);
			lb.dodajNaPocetak(2.3);
			lb.dodajNaPocetak(1.1);
		}

		@Test
		void prvi() {
			// assertEquals za tip double ocekuje treci tip - preciznost
			// poredjenja!
			assertEquals(1.1, lb.element(0), 0);
		}

		@Test
		void neg() {
			/*
			 * jedan nacin da se obradi metod koji _treba_ da baci izuzetak, odnosno
			 * ispravno ponasanje je izuzetak.
			 */
			try {
				lb.element(-2);
			} catch (IndexOutOfBoundsException e) {
				return;
			}
			fail("IndexOutOfBoundsException was expected!");
		}

		@Test
		void preveliko() {
			/*
			 * Preporuceni nacin za Junit5, sa lambda izrazima, u sustini je
			 * "dovoljno dobro" i kako je radjeno u "neg" metodu.
			 */
			assertThrows(IndexOutOfBoundsException.class, () -> lb.element(100));
		}

		@Test
		void posl() {
			// assertEquals za tip double ocekuje treci tip - preciznost
			// poredjenja!
			assertEquals(6.1, lb.element(5), 0);
		}

		@Test
		void sredina() {
			// assertEquals za tip double ocekuje treci tip - preciznost
			// poredjenja!
			assertEquals(3.8, lb.element(2), 0);
		}
	}

	@Nested
	class PraznostListe {

		@Test
		void jestePrazna() {
			assertTrue(new ListaBrojevaBr().jePrazna());
		}

		@Test
		void nijePrazna() {
			ListaBrojevaBr l = new ListaBrojevaBr();
			l.dodajNaPocetak(1);
			assertTrue(!l.jePrazna());
		}
	}

	@Nested
	class Minimalnost {

		ListaBrojevaBr ll = new ListaBrojevaBr();

		@BeforeEach
		void init() {

			ll.dodajNaPocetak(1);
			ll.dodajNaPocetak(3);
			ll.dodajNaPocetak(2);
			ll.dodajNaPocetak(4);
			ll.dodajNaPocetak(5);
			ll.dodajNaPocetak(6);
		}

		@Test
		void testMinimum() {
			ListaBrojevaBr l = new ListaBrojevaBr();
//			assertTrue(!l.jePrazna());
			l.dodajNaPocetak(1);
			l.dodajNaPocetak(3);
			l.dodajNaPocetak(2);
			l.dodajNaPocetak(4);
			l.dodajNaPocetak(5);
			l.dodajNaPocetak(6);
			
			 assertEquals(1,l.minimum());
		}

	}
}
