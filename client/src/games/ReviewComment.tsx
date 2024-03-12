import React, { useState, useEffect } from "react";
import "../naver/css/reviewComment.css";
import AxiosInstance from "../common/AxiosInstance";

interface Props {
  gameId: number | undefined;
  isActive: boolean | undefined;
}

interface ReviewCommentRequestDto {
  gameId: number | undefined;
  comment: string;
}

interface ReviewCommentData {
  reviewCommentId: number;
  gameId: number;
  username: string;
  reviewComment: string;
  buffCount: number;
}

interface BuffRequestDto {
  reviewCommentId: number;
}

const ReviewComment: React.FC<Props> = ({ gameId, isActive }) => {
  const [comment, setComment] = useState<string>("");
  const [reviewComments, setReviewComments] = useState<ReviewCommentData[]>();

  const handleChange = (event: React.ChangeEvent<HTMLTextAreaElement>) => {
    setComment(event.target.value);
  };

  useEffect(() => {
    //각 게임에 해당하는 reviewComments 보여주기
    fetchReviewComments();
    setComment("");
  }, [gameId]);

  const fetchReviewComments = async () => {
    console.log(gameId + "의 reviewComment 가져오기");
    try {
      const url = `/reviewComment/${gameId}`;
      const response = await AxiosInstance.get(url);
      console.log(response.data);
      const responseData: ReviewCommentData[] = response.data;
      setReviewComments(responseData);
    } catch (error) {
      console.error("ReviewComments 가져오는 중 오류 발생:", error);
    }
  };

  const handleRegisterComment = async () => {
    try {
      console.log("작성한 댓글: ", comment);

      // 게임 ID와 댓글 내용을 서버로 전송
      const url = `/reviewComment/${gameId}`; // 적절한 API 엔드포인트로 대체해주세요

      // Comment DTO 생성
      const commentDto: ReviewCommentRequestDto = {
        gameId: gameId,
        comment: comment,
      };

      const response = await AxiosInstance.post(url, commentDto);
      console.log("댓글이 성공적으로 등록되었습니다:", response.data);
      fetchReviewComments();
      setComment("");
      // 댓글 등록 후 처리 로직 추가 (예: 댓글 목록 다시 불러오기)
    } catch (error) {
      console.error("댓글 등록 중 오류 발생:", error);
    }
  };

  const handleBuffButton = async (reviewCommentId: number) => {
    console.log(reviewCommentId + "버프 버튼 클릭");
    const url = `/buff/${gameId}`;

    // BuffRequestDto 생성
    const buffDto: BuffRequestDto = {
      reviewCommentId: reviewCommentId,
    };
    // POST 요청 보내기
    try {
      const response = await AxiosInstance.post(url, buffDto);
      console.log("버프 요청이 성공적으로 전송되었습니다:", response.data);
      fetchReviewComments();
    } catch (error) {
      console.error("버프 요청 전송 중 오류 발생:", error);
    }
  };

  return (
    <div className="gametalk_comment__2kDwX">
      <div className="comment_list_container__1ifX0">
        <div className="header_wrap__msv8Y"></div>
        <div className="comment_list_input_area__2Twke">
          <div>
            <div className="input_container__19FQU">
              <div className="input_wrap__Ga4WD">
                <div className="input_content__1GHUa">
                  <div className="input_textarea_mirror__1GkxY">
                    {/* <div></div> */}
                  </div>
                  <textarea
                    placeholder="경기 리뷰를 남겨주세요"
                    className="input_textarea__1VL68"
                    value={comment}
                    onChange={handleChange}
                    style={{
                      height: "20px !important",
                    }}
                  ></textarea>
                  <div className="input_attachment_area__u7mkr"></div>
                </div>
                <div className="input_bottom_area__2qmr1">
                  <button type="button" onClick={handleRegisterComment}>
                    등록
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
        {/* 댓글 목록 */}
        <div className="comment_list_wrap__1c4Ku">
          {reviewComments ? (
            reviewComments.map((reviewComment) => (
              <div key={reviewComment.reviewCommentId}>
                <div
                  style={{ display: "flex", justifyContent: "space-between" }}
                >
                  <div>
                    <div>{reviewComment.username}</div>
                    <div>{reviewComment.reviewComment}</div>
                  </div>
                  <div>
                    <button
                      type="button"
                      onClick={() =>
                        handleBuffButton(reviewComment.reviewCommentId)
                      }
                    >
                      <span>버프</span>
                    </button>
                    <span>{reviewComment.buffCount}</span>
                  </div>
                </div>
              </div>
            ))
          ) : (
            <div>
              {/* reviewComments가 없을 때 표시할 내용을 여기에 작성 */}
              <span>댓글 없음</span>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default ReviewComment;
