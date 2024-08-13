package com.yourcompany.ipoapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yourcompany.ipoapp.model.IPO;
import com.yourcompany.ipoapp.service.IPOService;

@SpringBootTest
class IpoappApplicationTests {
	@Autowired
	private IPOService ipoService;
	@Test
	void contextLoads() {
		IPO ipo=new IPO();
		ipo.setCompanyName("abab");
		ipo.setShares(100);
		ipo.setSector("testing");
		ipoService.save(ipo);
		assertEquals(ipoService.ipoExists("abab"), true);
	}
	@AfterEach
	void delete(){
		ipoService.delete("abab");
	}

}
