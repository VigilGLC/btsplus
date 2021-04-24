package fd.se.btsplus.utils;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OpenApiExamplesTest {
    @Test
    void testRespOk() {
        String expected = "{\"code\":200,\"message\":null,\"data\":[{\"id\":321,\"iouNum\":\"L2104031952191\",\"customer\":{\"id\":13,\"code\":\"AB2121202104031\",\"name\":\"\u8BF7\u4E0D\u8981\u4FAE\u8FB1\u4E8C\u6B21\u5143\",\"idNum\":\"331355200005085616\",\"creator\":null,\"createdTime\":\"2021-04-03 19:33\",\"sex\":0,\"phone\":\"13333333333\",\"email\":\"3333333@fudan.edu.cn\",\"address\":\"\u4E0A\u6D77\u5E02\u6768\u6D66\u533A\u952E\u76D8\u4FA0\u53EA\u80FD\u51FA\u95E8\u53F3\u8F6C2500\u53F7\"},\"creator\":null,\"createdTime\":\"2021-04-03 19:52\",\"loanDate\":\"2021-04-03\",\"productCode\":\"20001\",\"productName\":\"\u4E70\u623F\u8D37\u6B3E\",\"amount\":20000.0,\"interest\":66.66,\"total\":20066.66},{\"id\":123,\"iouNum\":\"L2104032014071\",\"customer\":{\"id\":13,\"code\":\"AB2121202104031\",\"name\":\"\u8BF7\u4E0D\u8981\u4FAE\u8FB1\u4E8C\u6B21\u5143\",\"idNum\":\"331355200005085616\",\"creator\":null,\"createdTime\":\"2021-04-03 19:33\",\"sex\":0,\"phone\":\"13333333333\",\"email\":\"3333333@fudan.edu.cn\",\"address\":\"\u4E0A\u6D77\u5E02\u6768\u6D66\u533A\u952E\u76D8\u4FA0\u53EA\u80FD\u51FA\u95E8\u53F3\u8F6C2500\u53F7\"},\"creator\":null,\"createdTime\":\"2021-04-03 20:14\",\"loanDate\":\"2021-04-03\",\"productCode\":\"20005\",\"productName\":\"\u8BF7\u952E\u76D8\u4FA0\u51FA\u95E8\u53F3\u8F6C\u8D37\u6B3E\",\"amount\":100000.0,\"interest\":667.3394,\"total\":100667.3394}]}";
        Assertions.assertEquals(expected, OpenApiExamples.LOANS_RESP_OK);
    }
}