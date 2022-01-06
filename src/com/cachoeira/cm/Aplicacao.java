package com.cachoeira.cm;

import com.cachoeira.cm.modelo.Tabuleiro;
import com.cachoeira.cm.visao.TabuleiroConsole;

public class Aplicacao {

	public static void main(String[] args) {
		
		Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);
		new TabuleiroConsole(tabuleiro);
	}

}
