package lendmanager;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import lendmanager.account.Account;
import lendmanager.account.AccountRepository;
import lendmanager.person.Person;
import lendmanager.person.PersonLookupHelper;
import lendmanager.person.PersonRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PersonHelperTest {

	private static final String TEST_EMAIL = "test@email.com";

	@InjectMocks private PersonLookupHelper personLookup;
	
	@Mock private AccountRepository accountRepository;
	@Mock private PersonRepository personRepository;
	
	@Before
	public void setUp() throws Exception {
		Account x = new Account(TEST_EMAIL, "");
		MockitoAnnotations.initMocks(this);
		x.setId("testid");
		when(accountRepository.findByEmail(TEST_EMAIL)).thenReturn(x);
		when(personRepository.findByAccountId("testid")).thenReturn(new Person("Jan", "Kowalski"));
	}
	
	@Test
	public void test() {
		assertTrue(accountRepository.findByEmail(TEST_EMAIL).getEmail().equals(TEST_EMAIL));
	}
	
	@Test
	public void testFindPersonByEmail() {
		assertEquals("Jan", personLookup.findPersonByEmail(TEST_EMAIL).getFirstName());
		assertEquals("Kowalski", personLookup.findPersonByEmail(TEST_EMAIL).getLastName());
	}
	
	@Test
	public void testFindPersonByEmail2() {
		assertNull(personLookup.findPersonByEmail(TEST_EMAIL+"2"));
	}
}
