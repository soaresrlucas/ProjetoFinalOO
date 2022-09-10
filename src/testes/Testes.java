package testes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import modelo.Rotina;

class Testes {
	Rotina rotina;
	
	@Test
	void testExercicio() {
		String nome = "Exercicio";
		String tipo = "cardio";
		rotina = new Rotina();
		
		rotina.adicionaExercicio(nome, tipo);
		assertEquals(nome, rotina.getExercicio(0).getNome());
		assertEquals(tipo, rotina.getExercicio(0).getTipo());

	}
	
	@Test
	void testEditaExercicio() {
		rotina = new Rotina();
		
		rotina.adicionaExercicio("Exercicio", "cardio");
		rotina.editaExercicio(0, "Novo Exercicio", "maquina");
		assertEquals("Novo Exercicio", rotina.getExercicio(0).getNome());
		assertEquals("maquina", rotina.getExercicio(0).getTipo());
	}
	
	@Test
	void testRemoveExercicio() {
		rotina = new Rotina();
		
		rotina.adicionaExercicio("Exercicio", "cardio");
		rotina.removeExercicio(0);
		
		assertNull(rotina.getExercicio(0));
	}

	
	
	
}