import React from "react";

interface BannerProps {
  title: string;
}

const BlogBanner: React.FC<BannerProps> = ({ title }) => {
  return (
    <section className="blog-banner-area" id="category">
      <div className="container">
        <div className="blog-banner">
          <div className="text-center">
            <h1>{title}</h1>
          </div>
        </div>
      </div>
    </section>
  );
};

export default BlogBanner;
