package com.lamda;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamGroupByPair2 {

    /**
     * 데이터 pair2 Group by  :  Key 두개
     */
    public void groupByPair() {

        List<Users> users = new ArrayList<>();

        users.add(new Users("sangkil.an@cj.net", "13", UUID.randomUUID().toString()));
        users.add(new Users("sangkil.an@cj.net", "14", UUID.randomUUID().toString()));
        users.add(new Users("sangkil.an@cj.net", "14", UUID.randomUUID().toString()));
        users.add(new Users("sangkil.an@cj.net", "14", UUID.randomUUID().toString()));
        users.add(new Users("kyd@cj.net", "14", UUID.randomUUID().toString()));
        users.add(new Users("kyd@cj.net", "14", UUID.randomUUID().toString()));

        // 데이터 그룹핑
        Function<Users, List<String>> compositeKey = user -> Arrays.<String>asList(user.getEmail(), user.getMediaId());
        Map<Object, List<Users>> groupByData = users.stream().collect(Collectors.groupingBy(compositeKey, Collectors.toList()));

        // 그룹핑 데이터 확인
        //System.out.println("groupByPair  = " + groupByData);

        groupByData.forEach(
                (o, users1) -> {
                    System.out.printf("data key : %s value %s \n", o, users1);
                }
        );


    }

    /**
     * 데이터 pair2 Group by : Key 하나
     */
    public void groupByPairCase2() {

        List<Users> users = new ArrayList<>();

        users.add(new Users("sangkil.an@cj.net", "13", UUID.randomUUID().toString()));
        users.add(new Users("sangkil.an@cj.net", "14", UUID.randomUUID().toString()));
        users.add(new Users("sangkil.an@cj.net", "14", UUID.randomUUID().toString()));
        users.add(new Users("sangkil.an@cj.net", "14", UUID.randomUUID().toString()));
        users.add(new Users("kyd@cj.net", "14", UUID.randomUUID().toString()));
        users.add(new Users("kyd@cj.net", "14", UUID.randomUUID().toString()));

        users.stream().collect(Collectors.groupingBy(Users::getEmail))
                .forEach(
                        (o, users1) -> {
                            System.out.printf("data key : %s value %s \n", o, users1);
                        });

    }

    /**
     * 데이터 pair2 Group by : Key 하나
     */
    public void groupByPairCase3() {

        List<Users> users = new ArrayList<>();

        users.add(new Users("sangkil.an@cj.net", "13", UUID.randomUUID().toString()));
        users.add(new Users("sangkil.an@cj.net", "14", UUID.randomUUID().toString()));
        users.add(new Users("sangkil.an@cj.net", "14", UUID.randomUUID().toString()));
        users.add(new Users("sangkil.an@cj.net", "14", UUID.randomUUID().toString()));
        users.add(new Users("kyd@cj.net", "14", UUID.randomUUID().toString()));
        users.add(new Users("kyd@cj.net", "14", UUID.randomUUID().toString()));

        users.stream().collect(Collectors.groupingBy(Users::getEmail,Collectors.groupingBy(Users::getMediaId)))
                .forEach(
                        (o, users1) -> {
                            System.out.printf("data key : %s value %s \n", o, users1);
                        });

    }
}


@Data
@AllArgsConstructor
class Users {
    private String email;
    private String mediaId;
    private String accessToken;

}


@Data
@AllArgsConstructor
class UserEntity{
    private String email;
    private String accessToken;

    @Data
    static class UserEntityPK{
        private String mediaId;
    }

}
