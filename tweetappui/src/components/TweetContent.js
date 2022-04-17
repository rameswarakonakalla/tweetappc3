import React, { useEffect } from "react";
import PostCompose from "./PostCompose";
import PostItem from "./PostItem";
import { Spinner } from "react-bootstrap";
import {getFollowingPosts} from "../feature/Post/PostSlice";
import { useDispatch, useSelector } from "react-redux";

function TweetContent() {
  const dispatch = useDispatch();
  const storeFollowingPosts = useSelector((state) => state.PostReducer.followingPosts);

  
  

  useEffect(() => {
    dispatch(getFollowingPosts());
  });

  return (
    <div>
      <PostCompose />
      {console.log(storeFollowingPosts)
      }
      {storeFollowingPosts !== null && storeFollowingPosts !== ""? (
        storeFollowingPosts.map((post) => {
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

export default TweetContent;
