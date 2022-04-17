import React, { useState } from "react";
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
import { useNavigate , Link } from "react-router-dom";

function SignUp() {
  //const [userRole, setUserRole] = useState("user");
  //const [resData, setResData] = useState(null);
  
  let navigate = useNavigate();

  const schema = yup.object().shape({
    userName: yup.string().email().required(),
    password: yup.string().min(8).required(),
    firstName: yup.string().min(4).required(),
    lastName: yup.string().required(),
    contactNo: yup.string().min(8).max(10).matches("[0-9]+").required(),
  });

  async function postSignUpInfo(inputData) {
    try{
    const response = await axios({
      method: "post",
      mode: 'no-cors',
      url: "/api1/apps/v1.0/tweets/register",
      data: {
        firstName: inputData.firstName,
        lastName: inputData.lastName,
        userName: inputData.userName,
        password: inputData.password,
        contactNo: inputData.contactNo
      },
      headers:{
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": process.env.REACT_APP_API_URL,
    }
    });
    /*console.log(response);
    if (response.data !== null) {
      setResData(response.data);
    }*/
    
    if (response.data !== null && response.status !== 201) {
      showWarningToast(response.data.message);      
    }

    if (response.data!== null && response.status === 201) {
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
          firstName: "",
          lastName: "",
          contactNo:"",
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
          handleBlur,
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
            <Row className="mb-5 text-center">
              <h1 className="text-success">Sign Up</h1>
            </Row>
            <Row className="mb-3">
              <Form.Group as={Col} md="12" controlId="signInFirstName">
                <Form.Label>First Name</Form.Label>
                <Form.Control
                  type="text"
                  name="firstName"
                  value={values.firstName}
                  onChange={handleChange}
                  isInvalid={touched.firstName && errors.firstName}
                />
                <Form.Control.Feedback type="invalid">
                  Please enter your first name
                </Form.Control.Feedback>
              </Form.Group>
            </Row>
            <Row className="mb-3">
              <Form.Group as={Col} md="12" controlId="signInLastName">
                <Form.Label>Last Name</Form.Label>
                <Form.Control
                  type="text"
                  name="lastName"
                  value={values.lastName}
                  onChange={handleChange}
                  isInvalid={touched.lastName && errors.lastName}
                />
                <Form.Control.Feedback type="invalid">
                  Please enter your last name
                </Form.Control.Feedback>
              </Form.Group>
            </Row>
            
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
                <Form.Label>Password</Form.Label>
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
              <Form.Group as={Col} md="12" controlId="signInContactNo">
                <Form.Label>Contact No</Form.Label>
                <Form.Control
                  type="tel"
                  name="contactNo"
                  value={values.contactNo}
                  onChange={handleChange}
                  isInvalid={touched.contactNo && errors.contactNo}
                />
                <Form.Control.Feedback type="invalid">
                  Please enter a valid Contact Number
                </Form.Control.Feedback>
              </Form.Group>
            </Row>
            <Button type="submit" variant="success">
              Sign Up <BsFillPersonPlusFill />
            </Button>
             <h4 className="text-warning" >Do you have already an account <Link to="/signin" className={styles.linkTextFormat} >Click Here</Link> </h4> 
                  
          </Form>
        )}
      </Formik>
    </Container>
  );
}

export default SignUp;
