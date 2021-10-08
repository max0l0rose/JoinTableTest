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

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

// https://github.com/maxanarki2/JoinTableTest

@SpringBootApplication
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    @Autowired
    static OrdersRepo ordersRepo1;


    @Bean
    @Transactional(value = Transactional.TxType.REQUIRED)
    public boolean demo1(ProdRepo prodRepo,
                        OrdersService ordersService
            , EntityManager entityManager
    ) {
        //return (args) ->
        //{
        log.info("demo1: ");

        prodRepo.save(prod2);

        GeneralSequenceNumber n = new GeneralSequenceNumber();
        entityManager.persist(n);
        GeneralSequenceNumber n2 = new GeneralSequenceNumber();
        entityManager.persist(n2);

        Order order = new Order(ProdStatus.IN_STOCK);
        ordersService.save(order);

        Product prod1 = new Product("Prod1", 100, ProdStatus.IN_STOCK);
        prodRepo.save(prod1);

        OrderItems orderItems = new OrderItems(prod1, order, 10);
        entityManager.persist(orderItems);
        OrderItems orderItems2 = new OrderItems(prod2, order, 20);
        entityManager.persist(orderItems2);

        log.info("demo1: Ok");
        return true;
    }


    Product prod2 = new Product("Prod2", 200, ProdStatus.IN_STOCK);

    //CommandLineRunner
    @Bean
    @Transactional(value = Transactional.TxType.REQUIRED)
    public boolean demo2(ProdRepo prodRepo,
                        OrdersService ordersService
                        , EntityManager entityManager
    ) {
        //return (args) ->
        //{
        log.info("demo2: ");

        Order order2 = new Order(ProdStatus.IN_STOCK);
        ordersService.save(order2);

        ordersService.save(new Order());

        OrderItems orderItems21 = new OrderItems(prod2, order2, 50);
        entityManager.persist(orderItems21);


//        select o, ps.size, SUM(ps.quantity), SUM (p.price)
//        from Order o
//        join o.products1 ps
//        join Product p on p.id = ps.product_id
//        group by o.id



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

