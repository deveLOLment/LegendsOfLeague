import React from "react";
import { useEffect, useRef, useState } from "react";
import AxiosInstance from "../../common/AxiosInstance";
import { formatDate } from "../../common/utils.js";

interface ItemCommentCreateModel {
  content: string;
  parentCommentId: Number;
}

interface ParentItemCommentModel {
  id: number;
  content: string;
  nickname: string;
  createdDate: Date;
  childCommentList: ChildItemCommentModel[];
}

interface ChildItemCommentModel {
  id: number;
  content: string;
  nickname: string;
  createdDate: Date;
}

const ItemComment: React.FC<{ itemId: number }> = ({ itemId }) => {
  const [parentItemCommentModel, setParentItemCommentModel] =
    useState<ParentItemCommentModel[]>();
  const [message, setMessage] = useState<string>("");

  const fetchData = async () => {
    const url = `/items/${itemId}/comments`;

    try {
      const response = await AxiosInstance.get(url);
      const responseData: ParentItemCommentModel[] = response.data;
      console.log(responseData);
      setParentItemCommentModel(responseData);
    } catch (e) {}
  };

  useEffect(() => {
    fetchData();
  }, []);

  const handleParentItemCommentSubmit = async () => {
    const itemCommentCreateModel: ItemCommentCreateModel = {
      content: message,
      parentCommentId: -1,
    };

    const url = `/items/${itemId}/comments`;

    try {
      const response = await AxiosInstance.post(url, itemCommentCreateModel);
      fetchData();
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <>
      {!parentItemCommentModel ? (
        <div> waiting</div>
      ) : (
        <div
          className="tab-pane fade active show"
          id="contact"
          role="tabpanel"
          aria-labelledby="contact-tab"
        >
          <div className="row">
            <div className="col-lg-6">
              <div className="comment_list">
                {parentItemCommentModel.map((parentItemComment) => (
                  <ItemCommentParentRow
                    key={parentItemComment.id}
                    parentItemComment={parentItemComment}
                    fetchData={fetchData}
                  />
                ))}
              </div>
            </div>
            <div className="col-lg-6">
              <div className="review_box">
                <h4>Post a comment</h4>
                <form className="row contact_form" id="contactForm">
                  <div className="col-md-12">
                    <div className="form-group">
                      <textarea
                        className="form-control"
                        name="message"
                        id="message"
                        placeholder="Message"
                        onChange={(e) => setMessage(e.target.value)}
                      ></textarea>
                    </div>
                  </div>
                  <div className="col-md-12 text-right">
                    <button
                      type="submit"
                      onClick={handleParentItemCommentSubmit}
                      className="white_button"
                      style={buttonStyle}
                    >
                      Submit
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

const ItemCommentParentRow: React.FC<{
  parentItemComment: ParentItemCommentModel;
  fetchData: () => Promise<void>;
}> = ({ parentItemComment, fetchData }) => {
  const exampleItemId = 1;
  const [message, setMessage] = useState<string>("");
  const [showReply, setShowReply] = useState<boolean>(false);

  const handleParentItemCommentSubmit = async () => {
    const itemCommentCreateModel: ItemCommentCreateModel = {
      content: message,
      parentCommentId: parentItemComment.id,
    };

    const url = `/items/${exampleItemId}/comments`;

    try {
      const response = await AxiosInstance.post(url, itemCommentCreateModel);

      fetchData();
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="review_item">
      <div className="media">
        <div className="media-body">
          <h4>{parentItemComment.nickname}</h4>
          <h5>{formatDate(parentItemComment.createdDate.toString())}</h5>
          <div>
            <a
              className="reply_btn"
              href="#"
              onClick={() => setShowReply(!showReply)}
            >
              Reply
            </a>
          </div>
        </div>
      </div>
      <p className="item-comment">{parentItemComment.content}</p>
      {showReply && (
        <div className="review_item reply">
          <div className="review_box">
            <h4>Post a comment</h4>
            <form
              className="row contact_form"
              id="contactForm"
              onSubmit={handleParentItemCommentSubmit}
            >
              <div className="col-md-12">
                <div className="form-group">
                  <textarea
                    className="form-control"
                    name="message"
                    id="message"
                    placeholder="Message"
                    onChange={(e) => setMessage(e.target.value)}
                  ></textarea>
                </div>
              </div>
              <div className="col-md-12 text-right">
                <button
                  type="submit"
                  className="white_button"
                  style={buttonStyle}
                >
                  Submit
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
      {parentItemComment.childCommentList.map((childItemComment) => (
        <ItemCommentChildRow
          key={childItemComment.id}
          childItemComment={childItemComment}
        />
      ))}
    </div>
  );
};

const ItemCommentChildRow: React.FC<{
  childItemComment: ChildItemCommentModel;
}> = ({ childItemComment }) => {
  return (
    <div className="review_item reply">
      <div className="media">
        <div className="media-body">
          <h4>{childItemComment.nickname}</h4>
          <h5>{formatDate(childItemComment.createdDate)}</h5>
        </div>
      </div>
      <p className="item-comment">{childItemComment.content}</p>
    </div>
  );
};

export default ItemComment;
