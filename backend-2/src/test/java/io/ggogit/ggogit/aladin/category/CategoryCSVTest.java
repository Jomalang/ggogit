package io.ggogit.ggogit.aladin.category;


import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategoryCSVTest {

    @Test
    public void test() {
        
        // csv 파일 추출
        // CSV 데이터를 저장할 리스트
        List<List<String>> csvData = new ArrayList<>();

        // CSV 파일 경로
        String filePath = "C:\\Users\\user\\Desktop\\taegyu\\ggogit\\backend-2\\src\\test\\resources\\aladin_category_CID_20210927.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            // 파일의 각 줄을 읽어서 쉼표로 구분하고 리스트에 저장
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");  // 쉼표로 구분
                // values[0] : CID, values[1] : Category
                csvData.add(Arrays.asList(values).subList(0, 2));
            }

            // 읽은 데이터 출력
            for (List<String> row : csvData) {
                // (1230, 0, '가정/요리/뷰티')
                StringBuilder sb = new StringBuilder();

                sb.append("(");
                sb.append(row.get(0));
                sb.append(", ");
                sb.append(0);
                sb.append(", ");
                sb.append("'");
                sb.append(row.get(1).replace("'", ""));
                sb.append("'");
                sb.append(")");
                sb.append(",");
                System.out.println(sb);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
