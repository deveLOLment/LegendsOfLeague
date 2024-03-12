import React from "react";
import { useEffect, useRef, useState } from "react";
import AxiosInstance from "../../common/AxiosInstance";

interface ItemReviewInfoModel {
  overall: number;
  reviewArr: number[];
  reviewList: ItemReviewModel[];
}

interface ItemReviewModel {
  id: number;
  nickname: string;
  content: string;
  score: number;
}

interface ItemReviewCreateModel {
  content: string;
  score: number;
}

const ItemReview: React.FC<{ itemId: number }> = ({ itemId }) => {
  const [itemReviewInfo, setItemReviewInfo] = useState<ItemReviewInfoModel>({
    overall: 0,
    reviewArr: [],
    reviewList: [],
  });
  const [itemReviewCreateForm, setItemReviewCreateForm] =
    useState<ItemReviewCreateModel>({
      content: "",
      score: 0,
    });

  const fetchData = async () => {
    const url = `http://localhost:8080/items/${itemId}/reviews`;

    try {
      const response = await AxiosInstance.get(url);
      const responseData: ItemReviewInfoModel = response.data;
      setItemReviewInfo(responseData);
      console.log(responseData);
    } catch (e) {}
  };

  useEffect(() => {
    fetchData();
  }, []);

  const handleReview = async (event: React.FormEvent) => {
    event.preventDefault();

    try {
      const url = `http://localhost:8080/items/${itemId}/reviews`;
      const response = await AxiosInstance.post(url, itemReviewCreateForm);
      console.log(response.data);
      fetchData();
    } catch (error) {
      alert("리뷰 작성에 실패했습니다!");
    }

    setItemReviewCreateForm({ score: 0, content: "" });
  };

  return (
    <>
      {!itemReviewInfo || itemReviewInfo === null ? (
        <div> waiting</div>
      ) : (
        <div
          className="tab-pane fade show active"
          id="review"
          role="tabpanel"
          aria-labelledby="review-tab"
        >
          <div className="row">
            <div className="col-lg-6">
              <div className="row total_rate">
                <div className="col-6">
                  <div className="box_total">
                    <h5>Overall</h5>
                    <h4>
                      {itemReviewInfo.overall
                        ? Math.round(itemReviewInfo.overall * 100) / 100
                        : 0}
                    </h4>
                    <h6>{itemReviewInfo.reviewList.length + " Reviews"}</h6>
                  </div>
                </div>
                <div className="col-6">
                  <div className="rating_list">
                    <h3>Based on {itemReviewInfo.reviewList.length} Reviews</h3>
                    <ul className="list">
                      <li>
                        <a>
                          {Array.from({ length: 5 }).map((_, index) => (
                            <i key={index} className="fa fa-star"></i>
                          ))}
                        </a>
                        <span>({itemReviewInfo.reviewArr.at(4)})</span>
                      </li>
                      <li>
                        <a>
                          {Array.from({ length: 4 }).map((_, index) => (
                            <i key={index} className="fa fa-star"></i>
                          ))}
                        </a>
                        <span>({itemReviewInfo.reviewArr.at(3)})</span>
                      </li>
                      <li>
                        <a>
                          {Array.from({ length: 3 }).map((_, index) => (
                            <i key={index} className="fa fa-star"></i>
                          ))}
                        </a>
                        <span>({itemReviewInfo.reviewArr.at(2)})</span>
                      </li>
                      <li>
                        <a>
                          {Array.from({ length: 2 }).map((_, index) => (
                            <i key={index} className="fa fa-star"></i>
                          ))}
                        </a>
                        <span>({itemReviewInfo.reviewArr.at(1)})</span>
                      </li>
                      <li>
                        <a>
                          {Array.from({ length: 1 }).map((_, index) => (
                            <i key={index} className="fa fa-star"></i>
                          ))}
                        </a>
                        <span>({itemReviewInfo.reviewArr.at(0)})</span>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
              <div className="review_list">
                {itemReviewInfo.reviewList.map((itemReview) => (
                  <ItemReviewRow key={itemReview.id} itemReview={itemReview} />
                ))}
              </div>
            </div>
            <div className="col-lg-6">
              <div className="review_box">
                <h4>Add a Review</h4>
                <form className="form-contact form-review mt-3">
                  <div className="form-group">
                    <select
                      className="form-control different-control w-100"
                      name="score"
                      id="score"
                      value={itemReviewCreateForm.score}
                      onChange={(e) =>
                        setItemReviewCreateForm({
                          ...itemReviewCreateForm,
                          score: Number(e.target.value),
                        })
                      }
                    >
                      <option value="0" disabled selected>
                        Select Score
                      </option>
                      <option value="1">1</option>
                      <option value="2">2</option>
                      <option value="3">3</option>
                      <option value="4">4</option>
                      <option value="5">5</option>
                    </select>
                  </div>
                  <div className="form-group">
                    <textarea
                      className="form-control different-control w-100"
                      name="textarea"
                      id="textarea"
                      value={itemReviewCreateForm.content}
                      placeholder="Enter Message"
                      onChange={(e) =>
                        setItemReviewCreateForm({
                          ...itemReviewCreateForm,
                          content: e.target.value,
                        })
                      }
                    ></textarea>
                  </div>
                  <div className="form-group text-center text-md-right">
                    <button
                      type="submit"
                      className="white_button"
                      style={buttonStyle}
                      onClick={handleReview}
                    >
                      제출
                    </button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      )}
    </>
  );
};

const buttonStyle = {
  width: "100px",
  padding: "0px",
};

const ItemReviewRow: React.FC<{ itemReview: ItemReviewModel }> = ({
  itemReview,
}) => {
  return (
    <div className="review_item" style={{ margin: "10px" }}>
      <div className="media">
        <div className="media-body">
          <h4>{itemReview.nickname}</h4>

          <a>
            {Array.from({ length: itemReview.score }).map((_, index) => (
              <i key={index} className="fa fa-star"></i>
            ))}
          </a>
        </div>
      </div>
      <p>{itemReview.content}</p>
    </div>
  );
};

export default ItemReview;
