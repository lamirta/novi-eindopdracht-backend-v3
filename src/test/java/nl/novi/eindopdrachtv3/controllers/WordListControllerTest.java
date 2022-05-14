//package nl.novi.eindopdrachtv3.controllers;
//
//import nl.novi.eindopdrachtv3.services.WordListService;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.hamcrest.Matchers.containsString;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest
//class WordListControllerTest {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @MockBean
//    WordListService mockService;
//
//    @Test
//    void shouldReturnWordListByTitle() throws Exception {
//        Mockito.when(mockService.getWordListByTitle("dieren")).thenReturn("dieren");
//
//        mockMvc.perform(get("/wordlists/dieren"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("order with number 123")));
//    }
//
//}