import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";

const initialState = {
  followingPosts: null,
};

export const getFollowingPosts = createAsyncThunk(
  "/api2/apps/v1.0/tweets/all",
  async (thunkAPI) => {
   
    const response = await axios({
      method: "get",
      url: "/api2/apps/v1.0/tweets/all",
      headers: {
        Authorization: "Bearer "+localStorage.getItem("token"),
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*",
      },
    });
    console.log(response);
    return response.data;
 
  }
);

async function insertComment(postId, commentContent) {
  console.log(commentContent);
  const response = await axios({
    method: "post",
    url: "/api2/apps/v1.0/tweets/"+localStorage.getItem("userName")+"/reply/"+postId,
    headers: {
      Authorization: "Bearer "+localStorage.getItem("token"),
    },
    data:  commentContent,
  });
}
async function updateLove(postId, currentUserId) {
    console.log(currentUserId);
    const response = await axios({
        method: "put",
        url: "/api2/apps/v1.0/tweets/"+currentUserId+"/like/"+postId,
        headers: {
         Authorization: "Bearer "+localStorage.getItem("token"),
        },
        
    });
    
    return response.data;
}
async function updateUnLove(postId, currentUserId) {
  const response = await axios({
      method: "put",
      url: "/api2/apps/v1.0/tweets/"+localStorage.getItem("userName")+"/unlike/"+postId,
      headers: {
       Authorization: "Bearer "+localStorage.getItem("token"),
      },
      
  });
  
  return response.data;
}


export const PostSlice = createSlice({
  name: "PostSlice",
  initialState,
  reducers: {
      addLove: (state, action) => {
        if (state.followingPosts !== null) {
            for (let i = 0; i < state.followingPosts.length; i++) {
                if (state.followingPosts[i].id === action.payload.postId) {
                  console.log(state.followingPosts[i].likes);
                    if(state.followingPosts[i].likes===null){
                      state.followingPosts[i].likes=[action.payload.userId];
                      updateLove(action.payload.postId, action.payload.userId);
                    }
                    else if (!state.followingPosts[i].likes.includes(action.payload.userId)) {
                        state.followingPosts[i].likes.push(action.payload.userId);
                        updateLove(action.payload.postId, action.payload.userId);
                    } else {
                      console.log(state.followingPosts[i].likes);
                        state.followingPosts[i].likes = state.followingPosts[i].likes.filter(item => item !== action.payload.userId);
                        updateUnLove(action.payload.postId, action.payload.userId);
                    }
                }
            }
        }
      },

      addComment: (state, action) => {
        if (state.followingPosts !== null) {
          for (let i = 0; i < state.followingPosts.length; i++) {
            if (state.followingPosts[i].id === action.payload.postId) {
              if(state.followingPosts[i].replies===null){
                state.followingPosts[i].replies=[action.payload.newComment.content];
              }
              else{
                state.followingPosts[i].replies.push(action.payload.newComment.content);
              }
              console.log(action.payload.newComment.content);
              insertComment(action.payload.postId, action.payload.newComment.content);
            }
          }
        }
      }
  },
  extraReducers: (builder) => {
    builder.addCase(getFollowingPosts.fulfilled, (state, action) => {
      state.followingPosts = action.payload;
    });
  },
});

export const {addLove, addComment} = PostSlice.actions;
export default PostSlice.reducer;
