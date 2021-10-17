package com.company;

import com.company.model.*;
import com.company.repo.OrdersRepo;
import com.company.repo.ProdRepo;
import com.company.services.OrdersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

// https://github.com/maxanarki2/JoinTableTest

@SpringBootApplication
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

//    @Autowired
//    OrdersRepo ordersRepo;


//    @Autowired
//    OrderItemsRepo orderItemsRepo;

    @Bean
    @Transactional(
            //propagation = Propagation.REQUIRED,
            //isolation = Isolation.READ_UNCOMMITTED
    )
    public boolean demo1(ProdRepo prodRepo,
                        OrdersService ordersService
            //, EntityManager entityManager
    ) {
        //return (args) ->
        //{
        log.info("demo1: ");

        Product prod1 = new Product("ProdA", 100, ProdStatus.IN_STOCK);
        //prodRepo.save(prod1);
        Product prod2 = new Product("ProdB", 200, ProdStatus.IN_STOCK);
//        prodRepo.save(prod2);

//        Class c1 = ((BaseEntity)prod1).getClass();
//        Class c2 = prod2.getClass();
        //prodRepo.flush();

        Order order = new Order(ProdStatus.IN_STOCK);
        //order.getProducts().add(new OrderItems(prod2, order, 10));
        order.addProduct(prod1, 10);
        order.addProduct(prod2, 20);
        ordersService.save(order);

        //ordersService.getOrdersRepo().flush();

////        OrderItems orderItems = new OrderItems(prod1, order, 10);
////        orderItemsRepo.save(orderItems);
//
//        order.getProducts().add(new OrderItems(prod1, order, 20));
//
//        //entityManager.persist(orderItems);
////        OrderItems orderItems2 = new OrderItems(prod2, order, 20);
////        orderItemsRepo.save(orderItems2);
////        //entityManager.persist(orderItems2);

        Order order2 = new Order(ProdStatus.IN_STOCK);
        ordersService.save(order2);
        //order2.getProducts().add(new OrderItems(prod2, order2, 30));
        order2.addProduct(prod2, 50);

        ordersService.save(new Order());

        //ordersService.getOrdersRepo().flush();

//        OrderItems orderItems21 = new OrderItems(prod2, order2, 50);
//        orderItemsRepo.save(orderItems21);
//        //entityManager.persist(orderItems21);

        log.info("demo1: Ok");
        return true;
    }

    //CommandLineRunner
    @Bean
    @Transactional()
    public boolean demo2(ProdRepo prodRepo
                        //,OrdersService ordersService
                        //, EntityManager entityManager
    ) {
        //return (args) ->
        //{
        log.info("demo2: ");

//        select o, ps.size, SUM(ps.quantity), SUM (p.price)
//        from Order o
//        join o.products1 ps
//        join Product p on p.id = ps.product_id
//        group by o.id

        prodRepo.deleteById(2L);
        prodRepo.flush();

        Product product = prodRepo.findById(1L).get();
        product.getOrders().clear();


		log.info("demo2: Ok");
        //};
        return  true;
    }


    public static void main(String[] args) {
        log.info("main: ----------------------------");
        SpringApplication.run(Main.class, args);
        log.info("main: ===========================!");
    }

}

