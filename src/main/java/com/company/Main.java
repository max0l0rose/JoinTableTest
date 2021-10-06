package com.company;

import com.company.model.*;
import com.company.repo.OrdersRepo;
import com.company.repo.ProdRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

// https://github.com/maxanarki2/JoinTableTest

@SpringBootApplication
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    @Autowired
    static OrdersRepo ordersRepo1;


    //CommandLineRunner
    @Bean
    @Transactional
    public boolean demo(ProdRepo prodRepo,
                        OrdersRepo ordersRepo
                        , EntityManager entityManager
    ) {
        //return (args) ->
        //{
        log.info("demo: ");

        Order order = new Order(ProdStatus.IN_STOCK);
        ordersRepo.save(order);

        Order order2 = new Order(ProdStatus.IN_STOCK);
        ordersRepo.save(order2);

        Product prod1 = new Product("Prod1", 100, ProdStatus.IN_STOCK);
        prodRepo.save(prod1);

        Product prod2 = new Product("Prod2", 200, ProdStatus.IN_STOCK);
        prodRepo.save(prod2);

        OrderItems orderItems = new OrderItems(prod1, order, 10);
        OrderItems orderItems2 = new OrderItems(prod2, order, 20);
        OrderItems orderItems21 = new OrderItems(prod2, order2, 50);
        entityManager.persist(orderItems);
        entityManager.persist(orderItems2);
        entityManager.persist(orderItems21);


//        select o, ps.size, SUM(ps.quantity), SUM (p.price)
//        from Order o
//        join o.products1 ps
//        join Product p on p.id = ps.product_id
//        group by o.id



		log.info("demo: Ok");
        //};
        return  true;
    }


    public static void main(String[] args) {
        log.info("main: ----------------------------");

        SpringApplication.run(Main.class, args);

        log.info("main: ===========================!");
    }

}

