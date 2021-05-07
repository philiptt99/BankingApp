package com.project0.BankAppTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.project0.dao.BankDao;
import com.project0.dao.CustomerDao;
import com.project0.model.BankAccount;
import com.project0.model.User;
import com.project0.service.BankAccountService;
import com.project0.service.CustomerService;

public class CustomerServiceTest {
	
	@Mock
	private CustomerDao fakeCS;
	private BankDao fakeBank;
	private CustomerService csServ;
	private BankAccountService bServ;
	private BankAccount bankAccount;
	
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this); //initialize Mockito to create an instance of field member 
		csServ = new CustomerService("tsmith");
		bServ = new BankAccountService(100);
		bankAccount= new BankAccount(100,2298.21,"p","chequing");
		
		User ts = new User("tsmith","abc","tom","smith",3);
		//some methods to test
		when(fakeCS.getAll("tsmith")).thenReturn(ts);
		when(fakeCS.getUserID("tsmith")).thenReturn(1);
		when(fakeCS.isValid("cdunphy")).thenReturn(false);
		
		
	
		
		
	}
	
	@Test
	public void getAllTest() {
		assertEquals("tom",csServ.getAll("tsmith").getFirstName(),"getALl method works");
	}
	
	
	@Test
	public void getUserIDTest() {
		assertEquals(1,csServ.getUserID("tsmith"),"getUserID method works");
	}
	
	@Test
	public void getIsValidTest() {
		assertEquals(false, csServ.isValid("cdunphy"),"isValid method works");
	}
	
	
	
	
	@Test
	public void accountStatusTest() {
		assertEquals(bankAccount.getAccountStatus(), bServ.accountStatus(100),"accountStatus works");
	}
	
	@Test
	public void accountTypeTest() {
		assertEquals(bankAccount.getAccountStatus(), bServ.accountStatus(100), "accountType works");
	}

}
