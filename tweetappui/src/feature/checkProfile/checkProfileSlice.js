import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";

const initialState = {
  profileId: null,
  postList: null,
 
};
async function deletePost(postIds) {
  console.log("in delete");
    const response = await axios({
      method: "delete",
      url: "api2/apps/v1.0/tweets/"+localStorage.getItem("userName")+"/delete/"+postIds,
      headers: {
        Authorization: "Bearer "+localStorage.getItem("token"),
      },
    });
    console.log("after delete");
    return response.data;
}

export const getProfilePosts = createAsyncThunk(
  "/api2/apps/v1.0/tweets/"+localStorage.getItem("userName"),
  async (thunkAPI) => {
   
    const response = await axios({
      method: "get",
      url: "/api2/apps/v1.0/tweets/"+localStorage.getItem("userName"),
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



export const checkProfileSlice = createSlice({
  name: "checkProfileSlice",
  initialState,
  reducers: {
    getProfileId: (state, action) => {
      state.profileId = action.payload;
    },
    deleteTweet: (state, action) => {
        state.postList=state.postList.filter(item => item.id !== action.payload.postId);
        deletePost(action.payload.postId);
    }
  },
  extraReducers: (builder) => {
    builder.addCase(getProfilePosts.fulfilled, (state, action) => {
      state.postList = action.payload;
    });
    
  },
});

export const { getProfileId ,deleteTweet} = checkProfileSlice.actions;
export default checkProfileSlice.reducer;
