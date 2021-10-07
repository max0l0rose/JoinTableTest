import com.company.Main;
import com.company.model.Order;
import com.company.model.ProdStatus;
import com.company.repo.OrdersRepo;
import com.company.services.OrdersService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

//@DataJpaTest
@SpringBootTest(classes = Main.class)
//@TestPropertySource(locations = "/foo.properties") //"classpath:application-integrationtest.properties"
public class MyTest {

	@Value("${foo}")
	String foo;

//	@Autowired
//	OrdersService ordersService;

	//@MockBean
	@Autowired
	OrdersRepo ordersRepo;

	@Test
	void test1Test() {
		Assertions.assertEquals(1, 1);
		Order order = new Order(ProdStatus.IN_STOCK);
		ordersRepo.save(order);

		order.setUserId(10);

		ordersRepo.save(order);

		//Assertions.assertNotEquals(
				ordersRepo.findById(1L).get();
		//		, null);

		//assertThat(foo).isEqualTo("bar");

//		assertThat(session.createQuery(
//						"from com.baeldung.hibernate.pojo.inheritance.Person")
//				.getResultList())
//				.hasSize(1);
	}
}
