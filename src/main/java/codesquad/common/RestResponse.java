package codesquad.common;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestResponse {
    private static final String SUCCESS_MESSAGE = "답변 등록 성공";
    private static final String OK_MESSAGE = "답변 삭제 성공";
    private static final String FAIL_MESSAGE = "답변 삭제 실패";
    private Long questionId;
    private Long answerId;
    private String resultMessage;

    public static RestResponse success(Long questionId) {
        return new RestResponse(questionId,null, SUCCESS_MESSAGE);
    }

    public static RestResponse ok(Long questionId, Long answerId) {
        return new RestResponse(questionId, answerId, OK_MESSAGE);
    }

    public static RestResponse fail(Long questionId, Long answerId) {
        return new RestResponse(questionId, answerId, FAIL_MESSAGE);
    }

}
