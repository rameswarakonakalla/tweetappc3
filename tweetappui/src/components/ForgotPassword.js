import React from "react";
import { Formik } from "formik";
import * as yup from "yup";
import axios from "axios";

import Form from "react-bootstrap/Form";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";

import { BsFillPersonPlusFill } from "react-icons/bs";

import styles from "./styles/SignUp.module.css";
import Container from "react-bootstrap/esm/Container";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useNavigate } from "react-router-dom";

function ForgotPassword() {
  
  let navigate = useNavigate();

  const schema = yup.object().shape({
    userName: yup.string().email().required(),
    password: yup.string().min(8).required(),
    password1: yup.string().required().oneOf([yup.ref('password'), null], 'Passwords must match'),
  });

  async function postSignUpInfo(inputData) {
    try{
    const response = await axios({
      method: "put",
      mode: 'no-cors',
      url: "/api1/apps/v1.0/tweets/"+inputData.userName+"/forgot",
      data: {
        username: inputData.userName,
        password: inputData.password,
      },
      headers:{
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": process.env.REACT_APP_API_URL,
    }
    });
    
    if (response.data !== null && response.status !== 200) {
      showWarningToast(response.data.message);      
    }

    if (response.data!== null && response.status === 200) {
      navigate("/signin");
    }
  }
  catch(error){
    showWarningToast(error.response.data.message); 
  }
  }

  function showWarningToast(inputMessage) {
    toast.warn(inputMessage, {
      position: "bottom-center",
      autoClose: 3000,
      hideProgressBar: false,
      closeOnClick: true,
      pauseOnHover: true,
      draggable: true,
      progress: undefined,
      theme: "colored",
    });
  }

  return (
    <Container fluid className={styles.container}>
      <ToastContainer />
      <Formik
        validationSchema={schema}
        initialValues={{
          userName: "",
          password: "",
          password1: "",
        }}
        onSubmit={(values, { setSubmitting }) => {
          // console.log(values);
          postSignUpInfo(values);
          setSubmitting(false);
        }}
      >
        {({
          handleSubmit,
          handleChange,
          values,
          touched,
          isInValid,
          errors,
        }) => (
          <Form
            noValidate
            onSubmit={handleSubmit}
            className={styles.formContainer}
          >
            <Row className="mb-3">
              <Form.Group as={Col} md="12" controlId="signInEmail">
                <Form.Label>Username(Email)</Form.Label>
                <Form.Control
                  type="email"
                  name="userName"
                  value={values.userName}
                  onChange={handleChange}
                  isInvalid={touched.userName && errors.userName}
                />
                <Form.Control.Feedback type="invalid">
                  Please enter a valid email
                </Form.Control.Feedback>
              </Form.Group>
            </Row>
            <Row className="mb-3">
              <Form.Group as={Col} md="12" controlId="signInPassword">
                <Form.Label>New Password</Form.Label>
                <Form.Control
                  type="password"
                  name="password"
                  value={values.password}
                  onChange={handleChange}
                  isInvalid={touched.password && errors.password }
                />

                <Form.Control.Feedback type="invalid">
                  Please enter your password
                </Form.Control.Feedback>
              </Form.Group>
            </Row>
            <Row className="mb-3">
              <Form.Group as={Col} md="12" controlId="signInPassword1">
                <Form.Label>ReEnter Password</Form.Label>
                <Form.Control
                  type="password"
                  name="password1"
                  value={values.password1}
                  onChange={handleChange}
                  isInvalid={touched.password1 && errors.password1 }
                />

                <Form.Control.Feedback type="invalid">
                  Please enter your password
                </Form.Control.Feedback>
              </Form.Group>
            </Row>
            
            <Button type="submit" variant="success">
              Sign Up <BsFillPersonPlusFill />
            </Button>
          </Form>
        )}
      </Formik>
    </Container>
  );
}

export default ForgotPassword;
