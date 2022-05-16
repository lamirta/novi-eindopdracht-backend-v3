//package nl.novi.eindopdrachtv3.controllers;
//
//import nl.novi.eindopdrachtv3.Eindopdrachtv3Application;
//import nl.novi.eindopdrachtv3.dtos.WordListDto;
//import nl.novi.eindopdrachtv3.models.WordList;
//import nl.novi.eindopdrachtv3.services.WordListService;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultMatcher;
//
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest
//@ContextConfiguration(classes={Eindopdrachtv3Application.class})
//@EnableConfigurationProperties
//class WordListControllerTest {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @MockBean
//    WordListService mockService;
//
////    @Test
////    void shouldReturnWordListByTitle() throws Exception {
//////        WordListDto wldto = new WordListDto("dieren");
////
////        Mockito
////                .when(mockService.getWordListByTitle("dieren"))
////                .thenReturn("dieren");
////
////        mockMvc.perform(get("/wordlists/dieren"))
////                .andDo(print())
////                .andExpect(status().isOk())
////                .andExpect((ResultMatcher) content());
////    }
//
////    @Test
////    void shouldReturnAllWordLists() throws Exception {
////        Customer customer = new Customer("Albert", "Einstein");
////        List<Customer> allCustomers = Arrays.asList(customer);
////
////        given(customerService.getAllCustomers()).willReturn(allCustomers);
////
////        mvc.perform(get("/customers")
////                        .contentType(MediaType.APPLICATION_JSON))
////                .andExpect(status().isOk())
////                .andExpect(jsonPath("$", hasSize(1)))
////                .andExpect(jsonPath("$[0].lastName", is(customer.getLastName())));
////    }
//
//}