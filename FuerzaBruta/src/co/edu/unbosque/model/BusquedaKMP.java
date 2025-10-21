package co.edu.unbosque.model;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class BusquedaKMP {

	public ArrayList<Integer> buscarPatron(String texto, String patron, boolean caseSensitive) {
		ArrayList<Integer> res = new ArrayList<>();
		if (texto == null || patron == null) return res;
		if (patron.isEmpty()) return res;

		String t = texto;
		String p = patron;

		if (!caseSensitive) {
			t = t.toLowerCase();
			p = p.toLowerCase();
		}

		int n = t.length();
		int m = p.length();
		if (m > n) return res;

		int[] lps = buildLps(p);
		int i = 0; // índice en t
		int j = 0; // índice en p

		while (i < n) {
			if (t.charAt(i) == p.charAt(j)) {
				i++; j++;
				if (j == m) {
					// Match completo: (i - m) es el INICIO en el texto original
					res.add(i - m);
					j = lps[j - 1]; // continuar buscando
				}
			} else {
				if (j != 0) {
					j = lps[j - 1];
				} else {
					i++;
				}
			}
		}
		return res;
	}

	private int[] buildLps(String p) {
		int m = p.length();
		int[] lps = new int[m];
		int len = 0;
		int i = 1;
		while (i < m) {
			if (p.charAt(i) == p.charAt(len)) {
				len++;
				lps[i] = len;
				i++;
			} else {
				if (len != 0) {
					len = lps[len - 1];
				} else {
					lps[i] = 0;
					i++;
				}
			}
		}
		return lps;
	}

	@SuppressWarnings("unused")
	private String quitarAcentos(String s) {
		String n = Normalizer.normalize(s, Normalizer.Form.NFD);
		return n.replaceAll("\\p{M}", "");
	}
}
