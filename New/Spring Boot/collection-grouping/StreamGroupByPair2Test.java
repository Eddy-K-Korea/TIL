package com.lamda;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StreamGroupByPair2Test {

    StreamGroupByPair2 streamGroupByPair2 = new StreamGroupByPair2();

    @Test
    void groupByPair() {
        System.out.println("groupByPairCase - 1   ");
        var comment =
                """
                    (1) 데이터 그룹바이키 email, mediaId 두개 적용 - Function 을 활용한 pair2 적용 
                        - DB 에서 그룹바이 하는것 저럼 동작된다.
                        
                    (2) 데이터 그룹바이키(email)에 데이터 Key 중복은 2개
                    (3) data 예제 
                           ------------------------------------------
                           sangkil (string)
                              | - 13,14 (list) 
                           ------------------------------------------
                           kyd (string)
                             | - 14 (list)
                """;
        System.out.println(comment);
        streamGroupByPair2.groupByPair();
    }

    @Test
    void groupByPairCase2() {
        System.out.println("groupByPairCase - 2   ");
        var comment =
                """
                    (1) 데이터 그룹바이키 email 한개 적용
                           - 데이터 중복은 키하나로 분류되어 2개가 나오지만, 
                           - 미디어는 그룹바이 되어지지 않고 모두 검색된다.
                    (2) data 예제 
                           ------------------------------------------
                           sangkil (string)
                              | - 13,14,14 (list)
                               
                           ------------------------------------------
                           kyd (string)
                             | - 14 (list)
                """;
        System.out.println(comment);
        streamGroupByPair2.groupByPairCase2();
    }

    @Test
    void groupByPairCase3() {
        System.out.println("groupByPairCase - 3");
        var comment =
                """
                    (1) 데이터 그룹바이키 email, mediaId 두개 적용
                        - 각 키(email)에 달린 미디어별 중복데이터 제거후 필터링 됨
                        - 데이터를 그룹바이 해도 첫번째 스트림의 키(email)에 데이터 Key 중복은 2개가 발생된다. 
                    (2) data 예제    
                           ------------------------------------------
                           sangkil (string)
                              | - 13 (map, list)
                              | - 14 (map, list) 
                           ------------------------------------------
                           kyd (string)
                             | - 14 (map, list)
                """;
        System.out.println(comment);
        streamGroupByPair2.groupByPairCase3();
    }
}