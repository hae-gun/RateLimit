package com.example.demo;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MyTest {
    @Test
    void test() {
        List<Map<String, Object>> testData = test(null);
        assertThat(testData.isEmpty()).isTrue();
        Collections.emptyList();
    }

    private List<Map<String, Object>> test(String s) {
        if (s == null) {
            return Collections.emptyList();
        }
        return new ArrayList<>();
    }

    private void castingTest(ArrayList<Map<String, Object>> item) {
        System.out.println("good");
    }

    @Test
    void regTest(){
        String pattern = "[ㄱ-ㅎㅏ-ㅣ가-힣]";
        String text = "한글 ABC 123 ㅏㅣ ㅏ";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(text);
        List<String> result = new ArrayList<>();
        while (m.find()) {
            result.add(m.group());
        }
//        System.out.println(result);
        assertThat(result.size()).isEqualTo(5);
    }
}
