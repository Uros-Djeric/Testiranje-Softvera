package testiranje;

/**
 * Povezana lista brojeva sa operacijama nad njom i brojace elemenata. Lista u
 * svakom momentu moze da efikasno vrati broj elemenata u njoj.
 */
public class ListaBrojevaBr {
	class Element {
		double info;
		Element veza;

		public Element(double br) {
			this.info = br;
			this.veza = null;
		}

		public String toString() {
			return info + "";
		}
	}

	// pokazivac na prvi element liste
	Element prvi;

	// interni brojac koji pomaze kod nekih operacija
	int brojElemenata;

	/** Kreira praznu listu brojeva. */
	public ListaBrojevaBr() {
		this.prvi = null;
		this.brojElemenata = 0;
	}

	/** vraca broj elemenata u listi */
	public int velicina() {
		return brojElemenata;
	}

	/** Vraca da li je lista prazna */
	public boolean jePrazna() {
		return prvi == null;
	}

	public void dodajNaPocetak(double br) {
		Element novi = new Element(br);
		novi.veza = prvi;
		prvi = novi;
		brojElemenata++;
	}

	public void stampajNaEkran() {
		if (prvi == null) {
			System.out.println("Lista je prazna");
		} else {
			System.out.println("Sadrzaj liste duzine:"+velicina());
			Element tekuci = prvi;
			while (tekuci != null) {
				System.out.println(tekuci.info);
				tekuci = tekuci.veza;
			}
			System.out.println();
		}
	}

	public String toString() {
		String rez = "Lista: [ ";
		Element tekuci = prvi;
		while (tekuci != null) {
			rez += tekuci.info + " ";
			tekuci = tekuci.veza;
		}
		rez += "]";
		return rez;
	}

	/**
	 * Vraca vrednost k-tog elementa. Prvi element liste je na mestu 0, drugi na
	 * mestu 1 i tako dalje. Ne postoje elementi na negativnim pozicijama. U
	 * slucaju nepostojeceg indeksa baca IndexOutOfBounds izuzetak.
	 * 
	 * @throws IndexOutOfBoundsException
	 */
	public double element(int k) {
		if (k < 0) {
			throw new IndexOutOfBoundsException(
					"Ne postoje elementi na negativnim pozicijama!");
		}

		if (k < brojElemenata) {
			Element tekuci = prvi;
			int br = 0;
			while (br < k) {
				tekuci = tekuci.veza;
				br++;
			}
			return tekuci.info;
		} else {
			throw new IndexOutOfBoundsException("Lista je kraca od "
					+ k);
		}
	}

	// alternativna verzija procedure koja bi radila ako nemamo brojElemenata
	// kao polje
	public double elementB(int k) {
		if (k < 0) {
			throw new IndexOutOfBoundsException(
					"Ne postoje elementi na negativnim pozicijama!");
		}

		Element tekuci = prvi;
		int brojac = 0;

		while (tekuci != null && k > brojac) {
			tekuci = tekuci.veza;
			brojac++;
		}

		if (tekuci != null) {
			return tekuci.info;
		} else {
			throw new IndexOutOfBoundsException("Lista je kraca od "
					+ k);
		}
	}

	/** Vraca minimum liste. U slucaju prazne liste vratice maksimalni Double. */
	public double minimum() {
		double min = Double.MAX_VALUE;

		Element tekuci = prvi;
		while (tekuci != null) {
			if (tekuci.info < min) {
				min = tekuci.info;
			}
			tekuci = tekuci.veza;
		}

		return min;
	}

	/** Vraca da li broj br postoji u listi. */
	public boolean uListi(double br) {
		Element tekuci = prvi;
		while (tekuci != null && tekuci.info != br) {
			tekuci = tekuci.veza;
		}

		// ako smo dosli do kraja liste i nismo ga nasli
		// tekuci ce biti jednako null
		return tekuci != null;
	}

	/**
	 * Dodaje broj 'br' na poziciju 'pos'. Ako je pozicija negativna, broj ce
	 * biti dodat na prvo mesto. Ako je pozicija veza od broja elemenata, broj
	 * ce biti dodat na poslednje mesto.
	 * 
	 * @param br
	 *            broj koji se dodaje u listu
	 * @param pos
	 *            pozicija na koju se broj dodaje
	 */
	public void dodaj(double br, int pos) {
		Element novi = new Element(br);

		// proverimo i popravimo pos po potrebi
		if (pos < 0)
			pos = 0;
		if (pos > brojElemenata) {
			pos = brojElemenata;
		}

		// dodavanje na pocetak je jednostavno
		if (pos == 0) {
			novi.veza = prvi;
			prvi = novi;
			brojElemenata++;
		} else {
			// inace nam treba prethodni element
			Element prethodni = prvi;
			int brojac = 0;
			while (prethodni !=  null && brojac < pos - 1) {
				prethodni = prethodni.veza;
				brojac++;
			}
			novi.veza = prethodni.veza;
			prethodni.veza = novi;
			brojElemenata++;
		}
	}

	/**
	 * Izbacuje broj 'br' iz liste, naravno ako postoji i vraca da li je
	 * operacija uspesno obavljena.
	 */
	public boolean izbaciIzListe(double br) {
		// proverimo da li je prvi element
		if (prvi != null && prvi.info == br) {
			prvi = prvi.veza;
			brojElemenata--;
			return true;
		} else {
			/* trazimo u ostatku liste */
			Element tekuci, prethodni;
			tekuci = prvi;
			prethodni = null;
			while (tekuci != null && tekuci.info != br) {
				/*
				 * dok ne dodjemo do kraja liste ili ne nadjemo broj
				 */
				prethodni = tekuci;
				tekuci = tekuci.veza;
			}
			if (tekuci != null) {
				/*
				 * znaci da nismo na kraju liste, odnosno da smo nasli broj,
				 * prevezemo listu oko elementa
				 */
				prethodni.veza = tekuci.veza;
				brojElemenata--;
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * Izbacuje sve brojeve 'br' iz liste, naravno ako postoje i vraca koliko ih
	 * je bilo.
	 */
	public int izbaciIzListeSve(double br) {
		int brojac = 0;

		/* uklanjamo sa pocetka koliko je potrebno */
		while (prvi != null && prvi.info == br) {
			prvi = prvi.veza;
			brojac++;
		}

		Element tekuci, prethodni;
		/* trazimo u ostatku liste */
		if (prvi != null) {
			tekuci = prvi;
			while (tekuci.veza != null) {
				/* idemo korak po korak do poslednjeg elementa liste */
				prethodni = tekuci;
				tekuci = tekuci.veza;
				if (tekuci.info == br) {
					/* prevezemo listu oko elementa */
					prethodni.veza = tekuci.veza;
					brojac++;
				}
			}
		}
		brojElemenata -= brojac;
		return brojac;
	}

	/**
	 * Izbacuje K-ti element iz liste. Vraca da li je uspesno izbacen. Ako je
	 * lista prekratka vratice false.
	 */
	public boolean izbaciK(int k) {
		if (k >= brojElemenata) {
			return false;
		}
		if (k < 0) {
			return false;
		}
		// da li izbacujemo prvog?
		if (k == 0) {
			prvi = prvi.veza;
		} else {
			// brojimo do elementa pre onog koji izbacujemo
			Element prethodni = prvi;
			int brojac = 0;

			while (k - 1 > brojac) {
				prethodni = prethodni.veza;
				brojac++;
			}
			// prevezemo oko k
			prethodni.veza = prethodni.veza.veza;
		}
		brojElemenata--;
		return true;
	}

}
