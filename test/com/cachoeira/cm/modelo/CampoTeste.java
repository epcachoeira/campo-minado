package com.cachoeira.cm.modelo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cachoeira.cm.excecao.ExplosaoException;

class CampoTeste {

	private Campo campo = new Campo(3, 3);

	@BeforeEach
	void iniciarCampo() {
		campo = new Campo(3, 3);
	}

	@Test
	void testVizinhoEsquerda() {
		Campo vizinho = new Campo(3, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}

	@Test
	void testVizinhoDireita() {
		Campo vizinho = new Campo(3, 4);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}

	@Test
	void testVizinhoEmCima() {
		Campo vizinho = new Campo(2, 3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}

	@Test
	void testVizinhoEmbaixo() {
		Campo vizinho = new Campo(4, 3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}

	@Test
	void testVizinhoSE() {
		Campo vizinho = new Campo(4, 4);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}

	@Test
	void testVizinhoSW() {
		Campo vizinho = new Campo(4, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}

	@Test
	void testVizinhoNW() {
		Campo vizinho = new Campo(2, 2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}

	@Test
	void testVizinhoNE() {
		Campo vizinho = new Campo(2, 4);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
	}

	@Test
	void testVizinhoInvalido() {
		Campo vizinho = new Campo(1, 4);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertFalse(resultado);
	}

	@Test
	void testValorAtributoMarcado() {
		assertFalse(campo.isMarcado());
	}

	@Test
	void testAlternarMarcacao() {
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
	}

	@Test
	void testAlternarMarcacao02() {
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
	}

	@Test
	void testAbrirNaoMinadoNaoMarcado() {
		assertTrue(campo.abrir());
	}

	@Test
	void testAbrirNaoMinadoMarcado() {
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}

	@Test
	void testAbrirMinadoMarcado() {
		campo.alternarMarcacao();
		campo.minar();
		assertFalse(campo.abrir());
	}

	@Test
	void testAbrirMinadoNaoMarcado() {
		campo.minar();
		assertThrows(ExplosaoException.class, () -> {
			campo.abrir();
		});
	}
	
	@Test
	void testAbrirComVizinho() {
		Campo campo22 = new Campo(2, 2);
		Campo campo11 = new Campo(1, 1);
		
		campo22.adicionarVizinho(campo11);
		campo.adicionarVizinho(campo22);
		
		campo.abrir();
		
		assertTrue(campo22.isAberto() && campo11.isAberto());
	}
	
	@Test
	void testAbrirComVizinho2() {
		Campo campo22 = new Campo(2, 2);
		Campo campo11 = new Campo(1, 1);
		Campo campo12 = new Campo(1, 1);
		campo12.minar();
		
		campo22.adicionarVizinho(campo11);
		campo22.adicionarVizinho(campo12);
		campo.adicionarVizinho(campo22);
		
		campo.abrir();
		
		assertTrue(campo22.isAberto() && campo11.isFechado());
	}
	
	@Test
	void testMinasNaVizinhanca() {
		Campo campo22 = new Campo(2, 2);
		campo22.minar();
		campo.adicionarVizinho(campo22);
		
		assertTrue(campo.minasNaVizinhanca() > 0);
	}
	
	@Test
	void testContagemMinasNaVizinhanca() {
		Campo campo22 = new Campo(2, 2);
		Campo campo23 = new Campo(2, 3);
		campo22.minar();
		campo23.minar();
		campo.adicionarVizinho(campo22);
		campo.adicionarVizinho(campo23);
		campo.abrir();
		assertTrue(campo.toString().equals("2"));
	}
	
	@Test
	void testStringMarcado() {
		campo.alternarMarcacao();
		assertTrue(campo.toString() == "X");
	}
	
	@Test
	void testStringAbertoMinado() {
		campo.abrir();
		campo.minar();
		assertTrue(campo.toString() == "*");
	}
	
	@Test
	void testObjetivoNaoAtingido() {
		assertFalse(campo.objetivoAlcancado());
	}
	
	@Test
	void testObjetivoAtingido() {
		campo.abrir();
		assertTrue(campo.objetivoAlcancado());
	}
	
	@Test
	void testStringAberto() {
		campo.abrir();
		assertTrue(campo.toString() == " ");
	}
	
	@Test
	void testString() {
		assertTrue(campo.toString() == "?");
	}
	
	@Test
	void testReinicio() {
		campo.reiniciar();
		assertTrue(campo.isFechado() && !campo.isMarcado() && !campo.isMinado());
	}
	
	@Test
	void testCampoFechado() {
		campo.abrir();
		assertFalse(campo.isFechado());
	}
	
	@Test
	void testRecuperaLinha() {
		assertTrue(campo.getLinha() == 3);
		
	}
	
	@Test
	void testRecuperaColuna() {
		assertTrue(campo.getColuna() == 3);
		
	}

}
