package com.example.demo.controller;

import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.thread.ThreadQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RestController
public class TestDemoThreadController {

    @Autowired
    UserService userService;

//    @Autowired
//    ThreadQuery threadQuery;

    @GetMapping("/test2")
    public Object test2() {
        Long st = System.currentTimeMillis();

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
//        String id = "1";

        ExecutorService ex = Executors.newFixedThreadPool(10);

        try {
            ArrayList<ThreadQuery> list1 = new ArrayList<>();
            ArrayList<Future<List>> list2 = new ArrayList<>();

//            for (Integer id : numbers) {
            for (int i = 1; i < 10000; i++) {
//                int id = (i / 10) + 1;
                int id = i;
                ThreadQuery tQuery = new ThreadQuery(id + "");
                Future<List> future = ex.submit(tQuery);
                list2.add(future);
            }

//            Future<List> future = ex.submit(tQuery);

            for (Future<List> f3 : list2) {
                List list = f3.get();
//                list.forEach(item -> System.out.println(item));
//                System.out.println("111 " + list.size());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        ex.shutdownNow();
        Long ed = System.currentTimeMillis();

        Long res = (ed - st);
        System.out.println("----------》 " + res);

        return res;
    }

    @GetMapping("/test3")
    public Object test3() {
        Long st = System.currentTimeMillis();

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

//        for (Integer id : numbers) {
        for (int i = 0; i < 10000; i++) {
//            int id = (i / 10) + 1;
            int id = i;
            User user = userService.queryById(id + "");
//            System.out.println("222 " + user);
        }

        Long ed = System.currentTimeMillis();

        Long res = (ed - st);
        System.out.println("----------》 " + res);

        return res;
    }

    @GetMapping("/getuser")
    public Object getTuser(@RequestParam("id") String id) {
        if (id == null || id.equals("")) {
            id = "1";
        }
        User tUser = userService.queryById(id);
        return tUser;
    }

    @GetMapping("/test1")
    public Object test1() {
        Long st = System.currentTimeMillis();
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        numbers.parallelStream()
//                .forEach(System.out::println);
                .forEach(item -> {
                    User tUser = userService.queryById(item + "");
                    System.out.println(tUser);

                });

        Long ed = System.currentTimeMillis();

        System.out.println("----------》 " + (ed - st));

        st = System.currentTimeMillis();
        numbers
//                .forEach(System.out::println);
                .forEach(item -> {
                    User tUser = userService.queryById(item + "");
                    System.out.println(tUser);
                });

        ed = System.currentTimeMillis();

        System.out.println("-------222---》 " + (ed - st));

        return null;
    }
}
