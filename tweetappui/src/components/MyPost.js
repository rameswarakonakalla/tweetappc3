import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { Spinner } from "react-bootstrap";
import { getProfilePosts } from "../feature/checkProfile/checkProfileSlice";
import PostItem from "./PostItem";

function MyPost() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const postList = useSelector((state) => state.checkProfileReducer.postList);
  

  useEffect(() => {
    if (localStorage.getItem("token") === null) {
      navigate("/unauthorized");
    }

    if (localStorage.getItem("userName") !== null) {
      dispatch(getProfilePosts(localStorage.getItem("userName")));
      
    }
  }, []);

  return (
    <div>
      <h1 className = "text-secondary">Tweet's of {localStorage.getItem("firstName")+" "+localStorage.getItem("lastName")}</h1>
      {postList !== null ? (
        postList.map((post) => {
          return (
            <PostItem
              key={post.id}
              postId={post.id}
              userId={post.username}
              firstName={post.fname}
              lastName={post.lname}
              image=""
              handleName={post.handleName}
              content={post.message}
              loveList={post.likes}
              shareList=""
              commentList={post.replies}
              postDate={post.time}
              handles="user"
            />
          );
        })
      ) : (
        <div className="d-flex justify-content-center align-items-center my-5">
          <Spinner animation="border" variant="success" />
          <span>No Tweets Found</span>
        </div>
      )}
    </div>
  );
}

export default MyPost;
