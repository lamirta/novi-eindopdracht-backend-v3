package nl.novi.eindopdrachtv3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes={Eindopdrachtv3Application.class})
class Eindopdrachtv3ApplicationTests {

	@Test
	@DisplayName("Testing Context")
	void contextLoads(ApplicationContext context) {
		assertNotNull(context);
	}

}
