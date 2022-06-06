package be.pxl.auctions.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmailValidatorIsValidTest {

	@Test
	public void returnsTrueWhenValidEmail() {
		// TODO implement test
		assertTrue(EmailValidator.isValid("mark@facebook.com"));
	}

	@Test
	public void returnsFalseWhenAtSignMissing() {
		// TODO implement test
		assertFalse(EmailValidator.isValid("mark.facebook.com"));
	}

}
