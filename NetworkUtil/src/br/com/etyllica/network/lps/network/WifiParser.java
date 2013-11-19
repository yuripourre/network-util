package br.com.etyllica.network.lps.network;

import java.io.BufferedReader;
import java.util.List;

public interface WifiParser {

	public List<WifiNetwork> parse(BufferedReader output);

}
