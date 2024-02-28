import React from "react";
import ItemReview from "./itemreview/ItemReview";
import ItemComment from "./itemcomment/ItemComment";
import { useEffect, useRef, useState } from "react";

const ItemInfo: React.FC<{ itemId: number }> = ({ itemId }) => {
  const [activeTab, setActiveTab] = useState<string>("comments");

  return (
    <section className="product_description_area">
      <div className="container">
        <ul className="nav nav-tabs" id="myTab" role="tablist">
          <li className="nav-item">
            <a
              className={`nav-link ${activeTab === "comments" ? "active" : ""}`}
              id="contact-tab"
              onClick={() => setActiveTab("comments")}
              role="tab"
              aria-controls="contact"
              aria-selected={activeTab === "comments"}
            >
              Comments
            </a>
          </li>
          <li className="nav-item">
            <a
              className={`nav-link ${activeTab === "reviews" ? "active" : ""}`}
              id="review-tab"
              onClick={() => setActiveTab("reviews")}
              role="tab"
              aria-controls="review"
              aria-selected={activeTab === "reviews"}
            >
              Reviews
            </a>
          </li>
        </ul>
        <div className="tab-content" id="myTabContent">
          {activeTab === "reviews" && <ItemReview itemId={itemId} />}
          {activeTab === "comments" && <ItemComment itemId={itemId} />}
        </div>
      </div>
    </section>
  );
};

export default ItemInfo;
