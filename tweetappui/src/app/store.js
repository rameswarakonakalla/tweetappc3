import { configureStore } from "@reduxjs/toolkit";
import PostReducer from "../feature/Post/PostSlice";
import AccountReducer from "../feature/Accounts/AccountSlice";
import checkProfileReducer from "../feature/checkProfile/checkProfileSlice";

export const store = configureStore({
    reducer: {
        PostReducer: PostReducer,
        AccountReducer: AccountReducer,
        checkProfileReducer: checkProfileReducer,
    },
});