package model.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import asw.dbManagement.model.Participant;

public class ParticipantTest {

	@Test
	public void T1domainModelEqualsTest() {
		Participant participant1 = new Participant("paco@hotmail.com");
		Participant participant2 = new Participant("pac@hotmail.com");
		Participant participant3 = new Participant("paco@hotmail.com");
		Participant participant4 = new Participant("pepe@gmail.com");
		assertFalse(participant1.equals(participant2));
		assertFalse(participant1.equals(4));
		assertTrue(participant1.equals(participant3));
		assertTrue(participant1.equals(participant1));
		assertFalse(participant1.equals(participant4));
	}

	@Test
	public void T2domainModelToString() {
		Participant participant1 = new Participant("paco@hotmail.com");
		assertEquals(participant1.toString(),
				"Participant [id=" + participant1.getId() + ", nombre=" + participant1.getNombre() + ", apellidos=" + participant1.getApellidos() + ", password=" + participant1.getPassword()
				+ ", fechaNacimiento=" + participant1.getFechaNacimiento() + ", email=" + participant1.getEmail() + ", DNI=" + participant1.getDNI() + ", direccion="
				+ participant1.getDireccion() + ", nacionalidad=" + participant1.getNacionalidad() + ", isAdmin=" + false + ", isPolitician="
				+ false + "]");
	}

	@Test
	public void T3domainModelHashCodeTest() {
		Participant participant1 = new Participant("paco@hotmail.com");
		Participant participant3 = new Participant("paco@hotmail.com");
		assertEquals(participant1.hashCode(), participant3.hashCode());
	}

}
