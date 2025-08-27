package tests;

import backEnd.services.BookStoreService;
import org.testng.annotations.Test;

public class GetAllBooksTest {

    @Test
    public void testMethod(){

        BookStoreService bookStoreService = new BookStoreService("https://demoqa.com");
        bookStoreService.getAllBooks();
    }
}
