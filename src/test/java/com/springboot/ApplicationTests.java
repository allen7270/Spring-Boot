package com.springboot;

import com.springboot.project.book.model.bo.Book;
import com.springboot.project.book.model.dao.BookDao;
import com.springboot.project.book.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-dev.properties")
class ApplicationTests {
	@Autowired
	private BookService bookService;
	@Autowired
	private BookDao bookDao;
	@Test
	void contextLoads() {
		System.out.println(bookService.add(BigDecimal.valueOf(1)));
	}

	@Test
	void addAccountMoney() throws InterruptedException {

		CountDownLatch count = new CountDownLatch(2);

		ExecutorService executorService = Executors.newFixedThreadPool(2);

		executorService.execute(() -> {
			String result = bookService.add(BigDecimal.valueOf(-1));
			System.out.println(Thread.currentThread().getName() + "，result : " + result);
			count.countDown();
		});

		TimeUnit.SECONDS.sleep(1);

		executorService.execute(() -> {
			String result = bookService.add(BigDecimal.valueOf(3));
			System.out.println(Thread.currentThread().getName() + "，result : " + result);
			count.countDown();
		});

		count.await(10, TimeUnit.SECONDS);

		Book book = bookDao.findById("222c32ac-454b-0f0e-add1-fb0599b5d9c4").get();
		System.out.println("final balance ：" + book.getCount());
	}

}
