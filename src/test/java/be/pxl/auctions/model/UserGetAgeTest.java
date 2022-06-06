package be.pxl.auctions.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserGetAgeTest {

	@Test
	public void returnsCorrectAgeWhenHavingBirthdayToday() {
		// DONE implement test
		User user = new User();
		user.setDateOfBirth(LocalDate.of(1989, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()));
		assertEquals(LocalDate.now().getYear() - 1989, user.getAge());
	}

	@Test
	public void returnsCorrectAgeWhenHavingBirthdayTomorrow() {
		// DONE implement test
		User user = new User();
		user.setDateOfBirth(LocalDate.of(1989, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth() + 1));
		assertEquals(LocalDate.now().getYear() - 1989 - 1, user.getAge());
	}

	@Test
	public void returnsCorrectAgeWhenBirthdayWasYesterday() {
		// TODO implement test
		User user = new User();
		user.setDateOfBirth(LocalDate.of(1989, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth() - 1));
		assertEquals(LocalDate.now().getYear() - 1989, user.getAge());
	}

}
