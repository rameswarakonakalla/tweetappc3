import React from "react";
import {HashRouter, Routes, Route} from "react-router-dom";

import HomePage from "./HomePage";
import NotFoundPage from "./NotFoundPage";
import SignIn from "./SignIn";
import SignUp from "./SignUp";
import TweetHome from "./TweetHome";
import TweetContent from "./TweetContent";
import Profile from "./Profile";
import MyPost from "./MyPost";
import AllAccounts from "./AllAccounts";
import UnauthorizedPage from "./UnauthorizedPage";
import ForgotPassword from "./ForgotPassword";

function AppContainer() {
  return (
    <HashRouter>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/signin" element={<SignIn />} />
        <Route path="/signup" element={<SignUp />} />
        <Route path="/forgotPassword" element={<ForgotPassword />}/>
        <Route path="/home" element={<TweetHome />}>
          <Route path="" element={<TweetContent />} />
          <Route path="profile" element={<Profile />} />
          <Route path="mypost" element={<MyPost />} />
          <Route path="allaccounts" element={<AllAccounts />} />
        </Route>
        <Route path="/unauthorized" element={<UnauthorizedPage />} />
        <Route path="*" element={<NotFoundPage />} />        
      </Routes>
    </HashRouter>
  );
}

export default AppContainer;
